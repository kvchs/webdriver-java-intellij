package cn.gloryroad.util;

import cn.gloryroad.configuration.Constants;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * 实现操作指定Excel文件中指定的Sheet，读取指定的单元格内容，获取Sheet中最后一行行号的功能
 */
public class ExcelUtil {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        FileInputStream ExcelFile;
        try {
            //实例化Excel文件的FileInputStream对象
            ExcelFile = new FileInputStream(Path);
            //实例化Excel文件的XSSFWorkbook对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            //实例化XSSFSheet对象，指定Excel文件中的Sheet名称，后续用于Sheet中行列和单元格的操作
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }

    }

    //读取Excel文件指定单元格函数，此函数只支持.xlsx 的Excel文件
    public static String getCellData(int RowNum, int ColNum) {
        try {
            //通过函数参数指定的行号和列号，获取指定单元格对象
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String CellData = formatter.formatCellValue(Cell);
            return CellData;
        } catch (Exception e) {
            e.printStackTrace();
            //遇到读取异常，返回空字符串
            return "";
        }
    }

    //获取Excel 文件最后一行的行号
    public static int getLastRowNum() {
        return ExcelWSheet.getLastRowNum();
    }


    //设定要操作的Excel文件路径
    public static void setExcelFile(String Path) {
        FileInputStream ExcelFile;

        try {
            ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Path);
//        System.out.println(SheetName);
    }

    //读取指定Sheet中的指定单元格函数，此函数支持.xlsx的Excel文件
    public static String getCellData(String SheetName, int RowNum, int ColNum) {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String CellData = formatter.formatCellValue(Cell);
            return CellData;
        } catch (Exception e) {
            System.out.println("读取遇到一次，返回空字符串");
            e.printStackTrace();
            return "";
        }
    }

    //获取Sheet中数据总行数
    public static int getRowCount(String SheetName) {

        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        int number = ExcelWSheet.getLastRowNum();
        return number;
    }

    //在Excel指定的Sheet中，获取第一次包含指定测试用例文字的行号
    public static int getFirstRowContainsTestCaseID(String sheetName, String testCaseName, int colNum){
        int i;
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        int rowCount = ExcelUtil.getRowCount(sheetName);
        for(i = 0; i < rowCount; i++){
            if (ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)){
                //如果包含，退出for循环，返回包含测试用例序号的关键字的行号
                break;
            }
        }
        return i;
    }

    //获取指定Sheet中某个测试用例的步骤个数
    public static int getTestCaseLastStepRow(String sheetName, String testCaseID, int testCaseStartRowNumber){
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        for (int i = testCaseStartRowNumber; i <= ExcelUtil.getRowCount(sheetName)-1; i++){
            if (!testCaseID.equals(ExcelUtil.getCellData(sheetName, i, Constants.Col_TestCaseID))){
                int number = i;
                return number;
            }
        }
        int number = ExcelWSheet.getLastRowNum() + 1;
        return number;
    }
}
