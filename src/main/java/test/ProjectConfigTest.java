package test;


import cn.gloryroad.util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import javax.rmi.CORBA.Util;


/**
 * Created by P0061799 on 2017/11/28.
 */
public class ProjectConfigTest {
    public static void main(String[] args) {
        testBaidu();
    }

    public static void testBaidu() {
       //System.setProperty("webdriver.chrome.driver", "E:\\selenium\\xtselenium\\src\\main\\resources\\chromedriver.exe");
        Driver.chromeDriver();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com/");

//driver.manage().window().maximize();

        WebElement txtbox = driver.findElement(By.name("wd"));
        txtbox.sendKeys("Glen");

        WebElement btn = driver.findElement(By.id("su"));
        btn.click();

        driver.close();
    }
}
