package poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Diana on 21.12.2016.
 */
  public class ExcelWorker {


    public static void main(String[] args) throws ParseException, IOException, InvalidFormatException {
        // создание самого excel файла в памяти
        XSSFWorkbook workbook = new XSSFWorkbook();
        // создание листа с названием "Просто лист"
        XSSFSheet sheet = workbook.createSheet("Просто лист");

            //заполняем список данніми
            List<DataModel> dataList = fillData();

            // счетчик для строк
            int rowNum = 0;
            // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Имя");
            row.createCell(1).setCellValue("Фамилия");
            row.createCell(2).setCellValue("Город");
            row.createCell(3).setCellValue("Зарплата");

            for (DataModel dataModel : dataList) {
                createSheetHeader(sheet, ++rowNum, dataModel);
            }
            // записываем созданный в памяти Excel документ в файл
            try {
                FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Diana\\IdeaProjects\\group\\src\\test\\java\\testDate\\Test1.xlsx"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Файл отсутствует");
            }
            System.out.println("Excel файл успешно создан!");

        }

    //заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(XSSFSheet sheet, int rowNum, DataModel dataModel) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(dataModel.getName());
        row.createCell(1).setCellValue(dataModel.getSurname());
    }

    // заполняем список рандомными данными
    // в реальных приложениях данные будут из БД или интернета
    private static List<DataModel> fillData() {
        List<DataModel> dataModels = new ArrayList<DataModel>();
        dataModels.add(new DataModel("Howard", "Wolowitz"));
        dataModels.add(new DataModel("Leonard", "Hofstadter"));
        dataModels.add(new DataModel("Sheldon", "Cooper"));

        for(DataModel d:dataModels ){
            System.out.println(d);

        }


        return dataModels;
    }


}