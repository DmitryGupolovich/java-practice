package robot;

import image.MyImage;
import java.awt.*;

public final class MyRobot {
    private Robot robot;
    private int delay;

    public MyRobot(int delay) throws AWTException {
        robot = new Robot();
    //    robot.setAutoWaitForIdle(true);
        this.delay = delay;
    }

    public MyImage getScreenshot(Rectangle rect) {
        return new MyImage(robot.createScreenCapture(rect));
    }

    public void click(int x, int y, int button) {
        robot.mouseMove(x, y);
        robot.mousePress(button);
        robot.mouseRelease(button);
        robot.delay(delay);
    }

}