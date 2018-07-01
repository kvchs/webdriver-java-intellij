package cn.gloryroad.util;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {

    //按Tab键的方法
    public static void pressTabKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //调用keyPress方法实现按下Tab键
        assert robot != null;
        robot.keyPress(KeyEvent.VK_TAB);
        //调用keyRelease方法实现释放Tab键
        robot.keyRelease(KeyEvent.VK_TAB);
    }
}
