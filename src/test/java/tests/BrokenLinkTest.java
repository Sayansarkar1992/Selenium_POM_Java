package tests;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrokenLinkTest {

    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.automationexercise.com/");

        List<WebElement> all_a_elements = driver.findElements(By.tagName("a"));

        List<String> all_urls = new ArrayList<>();

        for(WebElement e : all_a_elements){
            all_urls.add(e.getAttribute("href"));
        }
        Map<String, Integer> url_map = new HashMap<>();
        for(String url : all_urls){
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.connect();
            url_map.put(url, httpURLConnection.getResponseCode());
            httpURLConnection.disconnect();
        }

        System.out.println(url_map.toString());


        driver.quit();

    }
}
