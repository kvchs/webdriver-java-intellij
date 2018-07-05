package cn.gloryroad.configuration;

public class Constants {
    public static final String Path_ExcelFile = System.getProperty("user.dir") + "\\document\\keyword2.xlsx";
    public static final String Path_ConfigurationFile = "./objectMap.properties";

    //测试数据Sheet中的列号常量设定
    public static final int Col_TestCaseID = 0;
    public static final int Col_KeyWordAction = 3;
    public static final int Col_LocatorExpression = 4;
    public static final int Col_ActionValue = 5;
    //第六列用5表示，测试结果列
    public static final int Col_TestStepResult = 6;
    //测试集合Sheet中的列号常量设定，测试用例执行结果列
    public static final int Col_TestSuiteTestResult = 3;
    //
    //测试集合中Sheet中的列号常量设定
    public static final int Col_RunFlag = 2;
    public static final String Sheet_TestSteps = "Sheet1";
    public static final String Sheet_TestSuite = "测试用例集合";
}
