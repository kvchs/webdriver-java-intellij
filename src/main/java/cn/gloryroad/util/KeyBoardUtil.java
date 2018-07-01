package cn.gloryroad.util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
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

    //按Tab键的方法
    public static void pressEnterKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //调用keyPress方法实现按下Enter键
        assert robot != null;
        robot.keyPress(KeyEvent.VK_ENTER);
        //调用keyRelease方法实现释放Enter键
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * 将指定的字符串设置为剪切板内容，然后执行粘贴操作
     * 将页面焦点切换到输入框后，调用此函数可以将指定字符串粘贴到输入框中
     */
    public static void setAndCtrlVClipboardData(String string){
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        }catch (AWTException e1){
            e1.printStackTrace();
        }
        //一下4行代码表示按下和释放Ctrl + V组合
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
    }

}
