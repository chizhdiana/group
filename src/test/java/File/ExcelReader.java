package File;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
        * Created by Diana on 21.12.2016.
        */
public class ExcelReader {
    private XSSFSheet excelWSheet;
    private XSSFWorkbook excelWBook;
    private XSSFCell cell;
    private static XSSFRow row;


// открыть файл
   public void setExcelFile(String Path, String SheetName ) throws IOException {
       try{
           FileInputStream ExcelFile =new FileInputStream(Path);
           excelWBook = new XSSFWorkbook(ExcelFile);
           excelWSheet = excelWBook.getSheet(SheetName);


       }catch (Exception e){
           System.out.println("Exception"+ e.getMessage());
       }
   }
// получить данные из файла
    public String getCellData(int rowNum, int colNum) {

        cell = excelWSheet.getRow(rowNum).getCell(colNum);
        return cell.getStringCellValue();
    }





   //найти последнюю строку
   public int getRowUsed()
   {
       return excelWSheet.getLastRowNum();
   }




    //найти в столбце строку с нужным именем
    public List getRowContains(String testCaseName, int colNum){
       List list = new ArrayList();
       int rowCount = getRowUsed();// количество строк
        for(int i=0; i<=rowCount; i++){
            String cellDate = getCellData(i, colNum);
            if(cellDate.equalsIgnoreCase(testCaseName)){
                list.add(i);
            }

        }
                return list;
    }
    // считывать данные из строк с различным кол-вом колонок
    public List[] getRowData(int rowNo) {
        List []arr = new List[1];
        List list = new ArrayList();
        int startCol =1;
        int totalCols = excelWSheet.getRow(rowNo).getPhysicalNumberOfCells();
        for(int i = startCol; i<=totalCols;i++){
            String cellDate = getCellData(rowNo,i);
            list.add(cellDate);

        }
        arr[0]=list;

        return arr;


    }
    public Object[][] getTableArray(List<Integer> rowsNo) {
        Object[][] tabArray = new Object[rowsNo.size()][];
        for (int i = 0; i < rowsNo.size(); i++) {
            tabArray[i] = getRowData(rowsNo.get(i));
        }
        return tabArray;
    }


}
/*@DataProvider(name = "data")
public static Object[][] returnExcelSheetData()
        throws BiffException, IOException
{
    String absolutePath = filePath.concat("/").concat(fileName); //path to excel file
    FileInputStream file = new FileInputStream(new File(absolutePath));
    Workbook workbook = Workbook.getWorkbook(file);
    Sheet worksheet = workbook.getSheet(sheetName); //sheet name

    int ROWS = worksheet.getRows() - 1; //if headers are present - use -1
    int COLS = worksheet.getColumns(); //read all columns

    Object[][] dataset = new Object[ROWS][COLS];
    for (int rowCount = 0; rowCount < ROWS; rowCount++) {
        for (int colCount = 0; colCount < COLS; colCount++) {
            dataset[rowCount][colCount] = worksheet.getCell(colCount,
                    rowCount + 1).getContents();
        }
    }
    workbook.close();
    file.close();
    return dataset; */