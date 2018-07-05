package cn.gloryroad.util;

import org.apache.log4j.Logger;

public class Log {
    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String testCaseName){
        Log.info("--------------------   \"" + testCaseName + "  \"开始执行    ---------------");
    }

    public static void endTestCase(String testCaseName){
        Log.info("--------------------   \"" + testCaseName + "  \"执行结束    ---------------");
    }

    //定义info级别日志的方法
    public static void info(String message){
        Log.info(message);
    }

    //定义info级别日志的方法
    public static void error(String message){
        Log.error(message);
    }

    //定义info级别日志的方法
    public static void debug(String message){
        Log.debug(message);
    }
}
