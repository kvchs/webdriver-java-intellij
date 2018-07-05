package cn.gloryroad.util;

public class Driver {
    public static void chromeDriver(){
        System.setProperty("webdriver.chrome.driver", "./tools/chromedriver.exe");
    }

    public static void firefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "./tools/geckodriver.exe");
    }

    public static void ieDriver(){
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/tools/IEDriverServerX64.exe");
    }

}
