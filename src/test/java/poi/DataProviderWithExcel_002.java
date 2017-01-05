package poi;

import File.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



/**
 * Created by Diana on 30.12.2016.
 */
public class DataProviderWithExcel_002 {
    private String sTestCaseName;

    private int iTestCaseRow;

    WebDriver driver;

    @BeforeMethod

    public void beforeMethod() throws Exception {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://pharmbase.com.ua/");

    }

    @Test(dataProvider = "Authentication")

    public void f(String sUserName, String sPassword) {

        driver.findElement(By.xpath(".//*[@id='account']/a")).click();

        driver.findElement(By.id("log")).sendKeys(sUserName);

        System.out.println(sUserName);

        driver.findElement(By.id("pwd")).sendKeys(sPassword);

        System.out.println(sPassword);

        driver.findElement(By.id("login")).click();

        System.out.println(" Login Successfully, now it is the time to Log Off buddy.");

        driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();

    }

    @AfterMethod

    public void afterMethod() {

        driver.close();

    }

    @DataProvider

    public Object[][] Authentication() throws Exception{

        // Setting up the Test Data Excel file

        ExcelUtils.setExcelFile("C:\\Users\\Diana\\IdeaProjects\\group\\src\\test\\java\\testDate\\TestData.xlsx","first");

        sTestCaseName = this.toString();

        // From above method we get long test case name including package and class name etc.

        // The below method will refine your test case name, exactly the name use have used

        sTestCaseName = ExcelUtils.getTestCaseName(this.toString());

        // Fetching the Test Case row number from the Test Data Sheet

        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet

        iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);

        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\Diana\\IdeaProjects\\group\\src\\test\\java\\testDate\\TestData.xlsx","first",iTestCaseRow);

        return (testObjArray);

    }

}
