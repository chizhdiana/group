package poi;

import File.ExcelReader;
import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Diana on 29.12.2016.
 */
public class Param_exel {
  public  static WebDriver driver;

    private String sTestCaseName;
    private int iTestCaseRow;

    @DataProvider
    public Object[][] testData() throws IOException {
        ExcelReader excelReader = new ExcelReader();

        excelReader.setExcelFile("C:\\Users\\Diana\\IdeaProjects\\group\\src\\test\\java\\testDate\\TestDate.xlsx", "first");
        List rowsNo = excelReader.getRowContains("check_fromFile",1 );
        return excelReader.getTableArray(rowsNo);
    }

    @Test(dataProvider = "testData",priority = 0)
    public void check_fromFile(String UserName){
        //WebElement page_start = driver.findElement(By.xpath("/html/body/div[@id='page']/div[@id='main']/div[@class='wrapper']"));
        WebElement login_form = driver.findElement(By.xpath("//*[@id=\"vxod-na-sajt\"]/form/table/tbody/tr[1]/td[2]/input"));
        login_form.click();
        login_form.sendKeys(UserName);

        System.out.println(UserName);


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
