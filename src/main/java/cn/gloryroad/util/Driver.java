package cn.gloryroad.util;

import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public static void chromeDriver(){
        System.setProperty("webdriver.chrome.driver", "./tools/chromedriver.exe");
    }

    public static void firefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "./tools/deckodriver.exe");
    }

    public static void ieDriver(){
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/tools/IEDriverServerX64.exe");
    }

}
