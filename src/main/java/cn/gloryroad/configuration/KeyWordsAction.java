package cn.gloryroad.configuration;

import cn.gloryroad.util.Driver;
import cn.gloryroad.util.KeyBoardUtil;
import cn.gloryroad.util.ObjectMap;
import cn.gloryroad.util.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.util.List;

import static cn.gloryroad.util.WaitUtil.*;

public class KeyWordsAction {

    //声名静态WebDriver对象，在此类中相关Driver操作
    public static WebDriver driver;
    private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);

    public static void open_browser(String browserName) {
        if (browserName.toLowerCase().equals("ie")){
            Driver.ieDriver();
            driver = new InternetExplorerDriver();
        }else if (browserName.toLowerCase().equals("firefox")){
            Driver.firefoxDriver();
            driver = new FirefoxDriver();
        }else {
            Driver.chromeDriver();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        try {
            WaitUtil.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void navigate(String url){
        driver.get(url);
    }

    public static void switch_to_frame(String frameName){
        driver.switchTo().frame(frameName);
    }

    public static void input_username(String userName){
        System.out.println("收到的参数: " + userName);
        try {
            driver.findElement(objectMap.getLocator("login.username")).clear();
            driver.findElement(objectMap.getLocator("login.username")).click();
            KeyBoardUtil.setAndCtrlVClipboardData(userName);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void input_password(String password){
        System.out.println("收到的参数: " + password);
        try {
//            driver.findElement(objectMap.getLocator("login.password")).clear();
            driver.findElement(objectMap.getLocator("login.password")).sendKeys(password);
//            press_tab("");
//            KeyBoardUtil.setAndCtrlVClipboardData(password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void click_login(String string){
        try {
            driver.findElement(objectMap.getLocator("login.button")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void switch_to_default(String string){
        driver.switchTo().defaultContent();
    }

    public static void WaitFor_Element(String xpathExpression){
        try {
            waitWebElement(driver, objectMap.getLocator(xpathExpression));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void click_writeLetterLink(String string){
        try {
            driver.findElement(objectMap.getLocator("homepage.writeLetterLink")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void WaitFor_Element1(String xpathExpression1){
        try {
            waitWebElement(driver, objectMap.getLocator(xpathExpression1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void input_recipients(String recipients){
        try {
            //driver.findElement(objectMap.getLocator("writemailpage.recipients")).sendKeys(recipients);
            KeyBoardUtil.setAndCtrlVClipboardData(recipients);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void input_mailSubject(String mailSubject){
        try {
            driver.findElement(objectMap.getLocator("writemailpage.mailsuject")).sendKeys(mailSubject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void press_tab(String string){
        try {
//            WaitUtil.sleep(Integer.parseInt(2));
            KeyBoardUtil.pressTabKey();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void paste_mailContent(String mailContent){
        try {
            KeyBoardUtil.setAndCtrlVClipboardData(mailContent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void click_addAttachment(String string){
        try {
            driver.findElement(objectMap.getLocator("writemailpage.addattachmentlink")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void paste_uploadFileName(String uploadFileName){
        try {
            WaitUtil.sleep(3000);
            KeyBoardUtil.setAndCtrlVClipboardData(uploadFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void press_enter(String string){
        try {
            WaitUtil.sleep(2000);
            KeyBoardUtil.pressEnterKey();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sleep(String sleepTime){
        try {
            WaitUtil.sleep(Integer.parseInt(sleepTime));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void click_sendMailButton(String string){
        try {
            List<WebElement> buttons = driver.findElements(objectMap.getLocator("writemailpage.sendmailbutton"));
            buttons.get(0).click();
            System.out.println("发送邮件按钮被单击");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sleep1(String sleepTime){
        try {
            WaitUtil.sleep(Integer.parseInt(sleepTime));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void WaitFor_Element2(String xpathExpression2){
        try {
            waitWebElement(driver, objectMap.getLocator(xpathExpression2));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Assert_String(String assertString){
        try {
            Assert.assertTrue(driver.getPageSource().contains(assertString));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close_browser(String string){
       try {
           System.out.println("关闭浏览器");
           driver.quit();
       }catch (Exception e){
           e.printStackTrace();
       }
    }



}