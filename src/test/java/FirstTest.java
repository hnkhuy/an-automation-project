import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FirstTest {
    private WebDriver driver;

    @Before
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void demo() {
        driver.get("https://www.blazemeter.com/selenium");
        WebElement button= driver.findElement(By.id("input_test_submit"));
        assertEquals(button.getText(), "Start The Test");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
