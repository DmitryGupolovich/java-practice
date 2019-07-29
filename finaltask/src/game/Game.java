package game;


/* Minesweeper game, which lets the solver read the data from the GUI and perform actions on it */

import image.MyImage;
import robot.MyRobot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Arrays;

public final class Game {

    private MyRobot robot;

    // UI configuration for screen-scraping
    private final Rectangle smileyButton;
    private final Rectangle gameBoard;
    private final Point cellSize;

    public int numColumns;
    public int numRows;

    // 0 = smile, 1 = frown, 2 = sunglasses
    public int smileyState;

    // 0 = blank, 1 = 1 neighboring mine, ..., 8 = 8 neighboring mines,
    // 9 = unopened, 10 = flagged, 11 = question mark,
    // 12 = opened but unknown (written by the solver but never produced by this class), 13 = out of bounds
    public int[][] cellStates;

    public void printCells(){
        for (int i = 0; i < cellStates.length; i++) {
            System.out.println(Arrays.toString(cellStates[i]));
        }
    }

    public Game(int actionDelay) throws AWTException {
        robot = new MyRobot(actionDelay);

        // Detect screen resolution
        Rectangle allScreensBounds = new Rectangle();
        for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            for (GraphicsConfiguration gc : gd.getConfigurations())
                allScreensBounds = allScreensBounds.union(gc.getBounds());
        }

        // Find smiley button
        MyImage screenshot = robot.getScreenshot(allScreensBounds);
        Point smiley = findRegion(screenshot, SMILEY_IMAGES[0]);  // Try to find smile face
        if (smiley == null)
            smiley = findRegion(screenshot, SMILEY_IMAGES[1]);  // Or try to find frown face
        if (smiley == null)
            smiley = findRegion(screenshot, SMILEY_IMAGES[2]);  // Or try to find sunglasses face
        if (smiley == null)
            throw new RuntimeException("Smile button not found on screen");
        smileyButton = new Rectangle(allScreensBounds.x + smiley.x, allScreensBounds.y + smiley.y, SMILEY_IMAGES[0].width, SMILEY_IMAGES[0].height);
        clickSmiley();  // Give focus to Minesweeper (if not focused it will give focus without activating the button; else it will click the button)
        clickSmiley();  // Click the button for sure
        System.out.printf("Smiley button: left=%d top=%d width=%d height=%d%n", smileyButton.x, smileyButton.y, smileyButton.width, smileyButton.height);

        // Find mine grid and its size
        MyImage UNOPENED = CELL_IMAGES[9];
        screenshot = robot.getScreenshot(allScreensBounds);

        // Find top left cell
        Point topLeft = findRegion(screenshot, UNOPENED);  // Relative to image coordinates (always non-negative), not screen coordinates (possibly negative or offset from zero)
        if (topLeft == null)
            throw new RuntimeException("Top left unopened cell not found on screen");

        // Find number of columns
        cellSize = new Point(UNOPENED.width, UNOPENED.height);
        numColumns = 0;
        for (int x = topLeft.x; x + cellSize.x <= screenshot.width && UNOPENED.equals(screenshot, x, topLeft.y); x += cellSize.x)
            numColumns++;

        // Find number of rows
        numRows = 0;
        for (int y = topLeft.y; y + cellSize.y <= screenshot.height && UNOPENED.equals(screenshot, topLeft.x, y); y += cellSize.y)
            numRows++;

        // игровое поле. Здесь мы должны четко определить его границы, что оно не выходило за рамки.
        gameBoard = new Rectangle(allScreensBounds.x + topLeft.x,
                                    allScreensBounds.y + topLeft.y,
                                    numColumns * cellSize.x,
                                    numRows * cellSize.y);
        System.out.printf("Game board: cols=%d rows=%d; left=%d top=%d width=%d height=%d%n",
                numColumns,
                numRows,
                gameBoard.x,
                gameBoard.y,
                gameBoard.width,
                gameBoard.height);

        cellStates = new int[numRows][numColumns];
        System.out.printf("Action delay: %d ms%n", actionDelay);
        System.out.println();
    }


    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < numColumns && y >= 0 && y < numRows;
    }


    public int getCell(int x, int y) {
        if (isInBounds(x, y))
            return cellStates[y][x];
        else
            return 13;
    }


    public void setCell(int x, int y, int val) {
        if (isInBounds(x, y))
            cellStates[y][x] = val;
    }


    public void clickSmiley() {
        robot.click(smileyButton.x + smileyButton.width / 2,
                smileyButton.y + smileyButton.height / 2,
                InputEvent.BUTTON1_MASK);
    }


    public void clickCell(int x, int y, int button) {
        if (!isInBounds(x, y))
            throw new IllegalArgumentException();
        int newX = (gameBoard.x + x * cellSize.x + cellSize.x / 2);
        int newY = (gameBoard.y + y * cellSize.y + cellSize.y / 2);

        robot.click(newX, newY, button);
    }


    public void rereadSmiley() {
        for (int delay = 1; delay < 1000; delay *= 2) {
            smileyState = findMatchingImageIndex(robot.getScreenshot(smileyButton), 0, 0, SMILEY_IMAGES);
            if (smileyState != -1)
                return;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
        throw new RuntimeException("Unknown smiley state");
    }


    public void rereadCells() {
        for (int delay = 1; delay < 1000; delay *= 2) {
            MyImage image = robot.getScreenshot(gameBoard);
            boolean fail = false;
            for (int y = 0; y < numRows; y++) {
                for (int x = 0; x < numColumns; x++) {
                    cellStates[y][x] = findMatchingImageIndex(image, x * cellSize.x, y * cellSize.y, CELL_IMAGES);
                    if (cellStates[y][x] == -1) {
                        fail = true;
                        break;
                    }
                }
            }
            if (!fail)
                return;

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
        throw new RuntimeException("Unknown cell state");
    }


    private static Point findRegion(MyImage image, MyImage pattern) {
        for (int y = 0; y + pattern.height <= image.height; y++) {
            for (int x = 0; x + pattern.width <= image.width; x++) {
                if (pattern.equals(image, x, y))
                    return new Point(x, y);
            }
        }
        return null;
    }

    private static int findMatchingImageIndex(MyImage image, int offX, int offY, MyImage[] myImages) {
        for (int i = 0; i < myImages.length; i++) {
            if (myImages[i].equals(image, offX, offY))
                return i;
        }
        return -1;
    }

    private static MyImage[] SMILEY_IMAGES = {
            new MyImage("smile.png"),
            new MyImage("frown.png"),
            new MyImage("sunglasses.png")
    };
    private static MyImage[] CELL_IMAGES = {
            new MyImage("neighbor0.png"),
            new MyImage("neighbor1.png"),
            new MyImage("neighbor2.png"),
            new MyImage("neighbor3.png"),
            new MyImage("neighbor4.png"),
            new MyImage("neighbor5.png"),
            new MyImage("neighbor6.png"),
            new MyImage("neighbor7.png"),
            new MyImage("neighbor8.png"),
            new MyImage("unopened.png"),
            new MyImage("flag.png"),
            new MyImage("question.png")
    };

}
