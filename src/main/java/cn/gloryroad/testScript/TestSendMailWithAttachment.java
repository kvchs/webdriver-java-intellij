package cn.gloryroad.testScript;

import cn.gloryroad.util.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSendMailWithAttachment {
    WebDriver driver;
    String baseUrl;

    @Test
    public void testSendMailWithAttachment(){

    }

    @BeforeMethod
    public void beforeMethod(){
        Driver.chromeDriver();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
