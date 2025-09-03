package BaseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class BaseTest {

    public static WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @BeforeClass
    public void set_up_driver() {
        initiateBrowser();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(getURL());
    }

    @AfterClass
    public void tear_down_driver(){
        driver.quit();
    }

    public static void initiateBrowser(){
        Map<String, Object> config = null;
        try {
            config = ReadYamlFile.read_config();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String browser_name = (String) config.get("browser");
        if(browser_name.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser_name.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else if (browser_name.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

    }

    public static String getURL(){
        Map<String, Object> config = null;
        try {
            config = ReadYamlFile.read_config();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return (String) config.get("url");
    }
}
