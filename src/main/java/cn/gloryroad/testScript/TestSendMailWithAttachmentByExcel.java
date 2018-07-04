package cn.gloryroad.testScript;

import cn.gloryroad.configuration.Constants;
import cn.gloryroad.configuration.KeyWordsAction;
import cn.gloryroad.util.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestSendMailWithAttachmentByExcel {

    public static Method method[];
    public static String keyword;
    public static String value;
    public static KeyWordsAction keyWordsAction;

    @Test
    public void testSendMailWithAttachment() throws Exception {
        keyWordsAction = new KeyWordsAction();
        //使用Java的反射机制获取KeyWordsAction类的所有方法对象
        method = keyWordsAction.getClass().getMethods();
        String excelFilePath = Constants.Path_ExcelFile;
        ExcelUtil.setExcelFile(excelFilePath, "Sheet1");

        /**
         *
         */
        for (int iRow = 1; iRow <= ExcelUtil.getLastRowNum(); iRow++){
            keyword = ExcelUtil.getCellData(iRow, Constants.Col_KeyWordAction);
            value = ExcelUtil.getCellData(iRow, Constants.Col_ActionValue);
            execute_Actions();
//            input_username
//                    input_password
        }



    }

    private static void execute_Actions(){
        try {
            for (int i = 0; i < method.length; i++){
                if (method[i].getName().equals(keyword)){
                    System.out.println(keyword);
                    method[i].invoke(keyWordsAction, value);
                    break;
                }
            }
        }catch (Exception e){
            Assert.fail("执行出现异常，测试用例执行失败！");
        }
    }
}

