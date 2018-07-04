package cn.gloryroad.testScript;


import cn.gloryroad.configuration.Constants;
import cn.gloryroad.configuration.KeyWordsAction;
import cn.gloryroad.util.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestSuiteByExcel {
    public static Method method[];
    public static String keyword;
    public static String value;
    public static KeyWordsAction keyWordsAction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;

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
                testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID, Constants.Col_TestCaseID);
                testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
                //遍历测试用例的所有测试步骤
                for (; testStep < testLastStep; testStep++) {
                    keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_KeyWordAction);
                    value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
                    execute_Actions();
                }
            }


        }


    }

    private static void execute_Actions(){
        try {
            for (int i = 0; i < method.length; i++){
                if (method[i].getName().equals(keyword)){
                    method[i].invoke(keyWordsAction, value);

                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("执行出现异常，执行用例失败!！ ");
        }
    }
}
