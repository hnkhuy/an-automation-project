import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FirstTest {
    private WebDriver driver;
    private String driverName;

    public FirstTest(String name){
        driverName=name;
    }

    @Before
    public void startBrowser() {
        if(driverName.equals("chrome")) {
            System.out.println("driverName1 = " + driverName);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            System.out.println("driverName2 = " + driverName);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void loginExecuteAutomation() {
        driver.get("http://www.executeautomation.com/demosite/Login.html");
        driver.findElement(By.name("UserName")).sendKeys("admin");
        driver.findElement(By.name("Password")).sendKeys("adminpassw");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertTrue( "Expected Page Header Title is displayed",driver.findElement(By.tagName("h1")).isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String browsers=System.getProperty("browsers");
        String[] browerArray= browsers.split(",");
        Object[][] browser2DArray = new String[browerArray.length][1];
        for (int i = 0; i < browerArray.length; i++) {
            browser2DArray[i][0]=browerArray[i];
        }
        return Arrays.asList(browser2DArray);
    }
}
