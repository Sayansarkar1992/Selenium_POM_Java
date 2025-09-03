package tests;

import BaseTests.BaseTest;
import BaseTests.Constants;
import BaseTests.ReadYamlFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import java.io.FileNotFoundException;
import java.util.Map;

public class TestBasics extends BaseTest {

    @Test(groups = "smoke")
    public void  demo_test() throws InterruptedException {
        String title = driver.getTitle();
        System.out.println("Title is :- "+title);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        homePage.click_on_product_link();
        productPage.search_product("T-Shirt");
        productPage.validate_search_result("T-Shirt");
        productPage.verify_overlay_content("Rs. 1299");
    }

    @Test(groups = "smoke")
    public void  fail_test() throws InterruptedException {
        String title = driver.getTitle();
        System.out.println("Title is :- "+title);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        homePage.click_on_product_link();
        productPage.search_product("T-Shirt");
        productPage.validate_search_result("T-Shirt");
        productPage.verify_overlay_content("Rs. 499");
    }
}
