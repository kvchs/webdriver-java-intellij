package cn.gloryroad.util;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectMap {
    Properties properties;

    public ObjectMap(String propFile) {
        properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(propFile);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("读取文件对象出错");
            e.printStackTrace();
        }
    }

    public By getLocator(String ElementNameInpropFile) throws Exception {
        //根据变量ElementNameInpropFile，从属性配置文件读取对象的配置对象
        String locator = properties.getProperty(ElementNameInpropFile);
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        /**
         * //tomcat容器默认采用了iso-8859-1的编码方法
         * //通过本为UTF-8编码却被tomcat用iso-8859-1解码的字进行恢复，
         * //其将解码后的字通过iso-8859-1反解码成二进制数组，再将该字节数组用UTF-8解码。
         * //最终被new String成字符串。
         * value = new String(value.getBytes("iso8859-1"),"UTF-8");
         */
        locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("获取的定位类型: " + locatorType + "\t 获取的定位表达式 " + locatorValue);

        if (locatorType.toLowerCase().equals("id")) {
            return By.id(locatorValue);
        } else if (locatorType.toLowerCase().equals("name")) {
            return By.name(locatorValue);
        } else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))) {
            return By.className(locatorValue);
        } else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))) {
            return By.tagName(locatorValue);
        } else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))) {
            return By.linkText(locatorValue);
        } else if (locatorType.toLowerCase().equals("partiallinktext")) {
            return By.partialLinkText(locatorValue);
        } else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))) {
            return By.cssSelector(locatorValue);
        } else if (locatorType.toLowerCase().equals("xpath")) {
            return By.xpath(locatorValue);
        } else {
            throw new Exception("输入的 locator type 未在程序中定义: " + locatorType);
        }
    }

}
