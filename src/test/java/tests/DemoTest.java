package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v137.network.Network;
import pages.HomePage;
import pages.ProductPage;

import java.time.Duration;
import java.util.Optional;

public class DemoTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), response_obj -> {
            System.out.println("URL IS :- "+ response_obj.getResponse().getUrl() + "RESPONSE STATUS :- " +response_obj.getResponse().getStatus());
        });

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.automationexercise.com/");
        HomePage homePage = new HomePage(driver);
        homePage.click_on_product_link();
    }
}
