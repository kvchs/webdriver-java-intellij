package cn.gloryroad.configuration;

import cn.gloryroad.testScript.TestSuiteByExcel;
import cn.gloryroad.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.w3c.dom.DOMConfiguration;

import java.util.List;

import static cn.gloryroad.util.WaitUtil.*;

public class KeyWordsAction {

    //声名静态WebDriver对象，在此类中相关Driver操作
    public static WebDriver driver;
    private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);
    static {
        DOMConfigurator.configure("./log4j.xml");
    }

    public static void open_browser(String string, String browserName) {
        if (browserName.toLowerCase().equals("ie")){
            Driver.ieDriver();
            driver = new InternetExplorerDriver();
            Log.info("IE浏览器实例已经声名");
        }else if (browserName.toLowerCase().equals("firefox")){
            Driver.firefoxDriver();
            driver = new FirefoxDriver();
            Log.info("Firefox浏览器实例已经声名");
        }else {
            Driver.chromeDriver();
            driver = new ChromeDriver();
            Log.info("Chrome浏览器实例已经声名");
        }
        driver.manage().window().maximize();
        try {
            WaitUtil.sleep(3000);
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void navigate(String string, String url){
        driver.get(url);
        Log.info("浏览器访问地址: " + url);
    }

    public static void switch_to_frame(String frameName, String string){
        driver.switchTo().frame(frameName);
    }

    public static void input_username(String string, String userName){
        System.out.println("收到的参数: " + userName);
        try {
            WaitUtil.sleep(3000);
            driver.findElement(objectMap.getLocator(string)).clear();
            driver.findElement(objectMap.getLocator(string)).click();
            KeyBoardUtil.setAndCtrlVClipboardData(userName);
            Log.info("输入用户名： " + userName);

        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void input(String locatorExpression, String inputString){
        System.out.println("收到的参数: " + inputString);
        try {
            driver.findElement(objectMap.getLocator(locatorExpression)).clear();
            Log.info("清除 " + locatorExpression + " 输入框中的所有内容");
            driver.findElement(objectMap.getLocator(locatorExpression)).click();
            KeyBoardUtil.setAndCtrlVClipboardData(inputString);
            Log.info("在" + locatorExpression + " 输入框中输入: " + inputString);

        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            Log.info("在 " + locatorExpression + "输入框中输入 " + inputString + " 时出现异常");
            e.printStackTrace();
        }
    }

//    public static void input_password(String password){
//        System.out.println("收到的参数: " + password);
//        try {
////            driver.findElement(objectMap.getLocator("login.password")).clear();
//            driver.findElement(objectMap.getLocator("login.password")).sendKeys(password);
////            press_tab("");
////            KeyBoardUtil.setAndCtrlVClipboardData(password);
//            Log.info("输入密码");
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            Log.info("在密码输入时出现异常");
//            e.printStackTrace();
//        }
//    }


    public static void click(String locatorExpression, String string){
        try {
            driver.findElement(objectMap.getLocator(locatorExpression)).click();
            Log.info("单击按钮成功: " + locatorExpression);
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            Log.info("点击" + locatorExpression + "页面元素失败, 异常信息: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public static void click_login(String string){
//        try {
//            driver.findElement(objectMap.getLocator("login.button")).click();
//            Log.info("单击登录按钮");
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

    public static void switch_to_default(String string, String string2){
        driver.switchTo().defaultContent();
    }

    public static void WaitFor_Element(String locatorExpression, String string){
        try {
            waitWebElement(driver, objectMap.getLocator(locatorExpression));
            Log.info("显式等待页面出现的元素是： " + locatorExpression);
        }catch (Exception e){
            Log.info("显式等待元素失败: " + e.getMessage());
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

//    public static void click_writeLetterLink(String string){
//        try {
//            driver.findElement(objectMap.getLocator("homepage.writeLetterLink")).click();
//            Log.info("单击写信按钮");
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

//    public static void WaitFor_Element1(String xpathExpression1){
//        try {
//            waitWebElement(driver, objectMap.getLocator(xpathExpression1));
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

    public static void input_recipients(String string, String recipients){
        try {
            //driver.findElement(objectMap.getLocator("writemailpage.recipients")).sendKeys(recipients);
            KeyBoardUtil.setAndCtrlVClipboardData(recipients);
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

//    public static void input_mailSubject(String mailSubject, String string){
//        try {
//            driver.findElement(objectMap.getLocator("writemailpage.mailsuject")).sendKeys(mailSubject);
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

    public static void press_tab(String string, String string1){
        try {
//            WaitUtil.sleep(Integer.parseInt(2));
            KeyBoardUtil.pressTabKey();
            Log.info("按Tab键成功");
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void pasteString(String string, String pasteContent){
        try {
            KeyBoardUtil.setAndCtrlVClipboardData(pasteContent);
            Log.info("成功粘贴文本: " + pasteContent);
        }catch (Exception e){
            Log.info("粘贴文本出现异常");
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

//    public static void paste_mailContent(String mailContent, String string){
//        try {
//            KeyBoardUtil.setAndCtrlVClipboardData(mailContent);
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

    public static void click_addAttachment(String string, String string2){
        try {
            driver.findElement(objectMap.getLocator(string)).click();
            Log.info("添加附件");
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void paste_uploadFileName(String string, String uploadFileName){
        try {
            WaitUtil.sleep(3000);
            KeyBoardUtil.setAndCtrlVClipboardData(uploadFileName);
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void press_enter(String string, String string1){
        try {
            WaitUtil.sleep(2000);
            KeyBoardUtil.pressEnterKey();
            Log.info("按回车键成功");
        }catch (Exception e){
            Log.info("按回车键失败");
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void sleep(String string, String sleepTime){
        try {
            WaitUtil.sleep(Integer.parseInt(sleepTime));
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void click_sendMailButton(String locatorExpression, String string){
        try {
            List<WebElement> buttons = driver.findElements(objectMap.getLocator(locatorExpression));
            buttons.get(0).click();
            System.out.println("发送邮件按钮被单击");
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

//    public static void sleep1(String sleepTime, String string){
//        try {
//            WaitUtil.sleep(Integer.parseInt(sleepTime));
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }


//    public static void WaitFor_Element2(String xpathExpression2, String string){
//        try {
//            waitWebElement(driver, objectMap.getLocator(xpathExpression2));
//        }catch (Exception e){
//            TestSuiteByExcel.testResult = false;
//            e.printStackTrace();
//        }
//    }

    public static void Assert_String(String string, String assertString){
        try {
            Assert.assertTrue(driver.getPageSource().contains(assertString));
        }catch (Exception e){
            TestSuiteByExcel.testResult = false;
            e.printStackTrace();
        }
    }

    public static void close_browser(String string, String string1){
       try {
           System.out.println("关闭浏览器");
           driver.quit();
           Log.info("关闭浏览器");
       }catch (Exception e){
           TestSuiteByExcel.testResult = false;
           e.printStackTrace();
       }
    }



}