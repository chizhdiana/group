package File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Diana on 27.12.2016.
 */
public class Data_From_File {
    public static WebDriver driver;
    private static Logger Log = Logger.getLogger(Data_From_File.class.getName());

    @DataProvider
    public Object[][] testData() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List rowsNo = excelReader.getRowContains(" Data_From_File",0);
        try {
            excelReader.setExcelFile("C:\\Users\\Diana\\IdeaProjects\\group\\testDate\\TestData.xlsx", "first");

        }catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        return excelReader.getTableArray(rowsNo);
    }

    @Test(dataProvider = "testData",priority = 1)
    public void check_fromFile(List data){
        data.get(0);
        data.get(1);
      //  System.out.println(data);


    }





    @BeforeTest
    public void beforeMethod() {
        DOMConfigurator.configure("log4j.xml");

        driver = new ChromeDriver();
        Log.info("New driver instantiated");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://pharmbase.com.ua/");
        Log.info("Web application launched");

    }
    @AfterTest
    public void afterTest(){
        driver.close();
    }

}

