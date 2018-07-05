package cn.gloryroad.testScript;


import cn.gloryroad.configuration.Constants;
import cn.gloryroad.configuration.KeyWordsAction;
import cn.gloryroad.util.ExcelUtil;
import cn.gloryroad.util.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestSuiteByExcel {
    public static Method method[];
    public static String keyword;
    public static String value;
    public static String locatorExpression;
    public static KeyWordsAction keyWordsAction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;
    public static boolean testResult;

    @BeforeClass
    public void BeforeClass(){
        DOMConfigurator.configure("./log4j.xml");
    }

    @Test
    public void testTestSuite() {
        keyWordsAction = new KeyWordsAction();
        method = keyWordsAction.getClass().getMethods();
        String excelFilePath = Constants.Path_ExcelFile;

        ExcelUtil.setExcelFile(excelFilePath);
        System.out.println("----------------");
        int testCaseCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
        System.out.println("----------------");
        for (int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
            testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
            testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
            if (testCaseRunFlag.equalsIgnoreCase("y")) {
                Log.startTestCase(testCaseID);
                testResult = true;
                testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID, Constants.Col_TestCaseID);
                testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
                //遍历测试用例的所有测试步骤
                for (; testStep < testLastStep; testStep++) {
                    keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_KeyWordAction);
                    Log.info("从Excel文件中读取到的关键字是: " + keyword);
                    locatorExpression = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_LocatorExpression);
                    value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
                    Log.info("从Excel文件中读取到的操作值是: " + value);
                    execute_Actions();
                    if (testResult == false){

                        ExcelUtil.setCellData("测试用例集合", testCaseNo, Constants.Col_TestSuiteTestResult, "测试执行失败");
                        Log.endTestCase(testCaseID);
                        break;
                    }

                    if (testResult == true){
                        ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "测试执行成功");

                    }
                }

            }


        }


    }

    private static void execute_Actions(){
        try {
            for (int i = 0; i < method.length; i++){
                if (method[i].getName().equals(keyword)){

                    System.out.println(keyword);
                    method[i].invoke(keyWordsAction, locatorExpression,value);
                    if (testResult == true){
                        ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepResult, "测试步骤执行成功");
                        break;
                    }else {
                        ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepResult, "测试步骤执行失败");
                        //测试步骤执行失败，则关闭浏览器，不再执行之后的步骤
                        KeyWordsAction.close_browser("", "");
                        break;

                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("执行出现异常，执行用例失败!！ ");
        }
    }
}
