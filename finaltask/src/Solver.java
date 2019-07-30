import game.Game;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

/* Main application class, containing the Minesweeper solver logic */

public final class Solver {
  
    private static Random random = new Random();
    private static Game game;


    public static void main(String[] args) throws AWTException, InterruptedException {
        Thread.sleep(3000);
        game = new Game(450);

        // Play until win
        for (int i = 0; ; i++) {
            System.out.println("Game " + i);
            if (solveGame())
                break;
        }
        System.out.println("Win!");
    }


    private static boolean solveGame() {
        game.clickSmiley();
        game.rereadCells();

        while (true) {
            int x, y;
            do { // Выбрать рандомную ячейку для клика
                x = random.nextInt(game.numColumns);
                y = random.nextInt(game.numRows);

            } while (game.getCell(x, y) != 9);
      //      game.printCells();
            game.clickCell(x, y, InputEvent.BUTTON1_MASK);

            game.rereadSmiley();

            if (game.smileyState == 1)  // Frown
                return false;
            else if (game.smileyState == 2)  // Sunglasses
                return true;
            game.rereadCells();


            while (true) {
                boolean changed = false;
                while (solveSingles() )  // Try to solve as much as possible without the expensive screen reread
                    changed = true;
                if (!changed)
                    break;
                game.rereadSmiley();
                if (game.smileyState == 2)  // Sunglasses
                    return true;
                if (game.smileyState == 1) throw new RuntimeException("Cannot lose with safe strategy");
                game.rereadCells();
            }
        }
    }


    private static boolean solveSingles() {
        boolean changed = false;
        for (int y = 0; y < game.numRows; y++) {
            for (int x = 0; x < game.numColumns; x++) {
                int state = game.getCell(x, y);
                if (state < 1 || state > 8)
                    continue;  // Skip non-numerical cell

                int flag = countNeighboring(x, y, 10);
                int unopened = countNeighboring(x, y, 9);
                if (flag > state)
                    throw new RuntimeException("Inconsistent game board");

                if (flag == state && unopened >= 1) {
                    game.clickCell(x, y, InputEvent.BUTTON2_MASK);  // Middle click to open neighbors
                    for (int yy = y - 1; yy <= y + 1; yy++) {
                        for (int xx = x - 1; xx <= x + 1; xx++) {
                            if (game.getCell(xx, yy) == 9)
                                game.setCell(xx, yy, 12);
                        }
                    }
                    changed = true;

                } else if (unopened >= 1 && flag + unopened == state) {
                    for (int yy = y - 1; yy <= y + 1; yy++) {
                        for (int xx = x - 1; xx <= x + 1; xx++) {
                            if (game.getCell(xx, yy) == 9) {
                                game.clickCell(xx, yy, InputEvent.BUTTON3_MASK);  // Right click to flag the cell
                                game.setCell(xx, yy, 10);
                            }
                        }
                    }
                    changed = true;
                }
            }
        }
        return changed;
    }


    private static int countNeighboring(int x, int y, int value) {
        int count = 0;
        for (int yy = y - 1; yy <= y + 1; yy++) {
            for (int xx = x - 1; xx <= x + 1; xx++) {
                if (game.getCell(xx, yy) == value)
                    count++;
            }
        }
        return count;
    }

}




