package pages;

import common.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v137.log.Log;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.logging.Logger;

public class ProductPage {

    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    CommonMethods commonMethods;

    @FindBy(xpath="//input[@id='search_product']")
    private static WebElement search_product_input;

    @FindBy(xpath="//button[@id='submit_search']")
    private static WebElement search_button;

    @FindAll(@FindBy(xpath = "//div[@class='features_items']/div[@class='col-sm-4']//div[@class='productinfo text-center']//p"))
    private static List<WebElement> all_search_result;

    @FindAll(@FindBy(xpath = "//div[@class='product-overlay']/div/h2"))
    private static List<WebElement> overlay_content_price;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonMethods = new CommonMethods(driver);
    }

    public void search_product(String search_prameter){
        Assert.assertTrue(commonMethods.click_on_element(search_product_input));
        Assert.assertTrue(commonMethods.enter_text(search_product_input, search_prameter));
        Assert.assertTrue(commonMethods.click_on_element(search_button));
    }

    public void validate_search_result(String search_paramerter){
        List<String> all_search_results = commonMethods.getListOfElementText(all_search_result);
        System.out.println("Printign all :- "+ all_search_results);
        for(String str : all_search_results){
            if (str.contains(search_paramerter)){
                Assert.assertTrue(true, "Parameter is present");
            }else {
                Assert.assertTrue(false, "parameter is not present.");
            }
        }
    }

    public void verify_overlay_content(String expectex_text){
        WebElement element = all_search_result.get(0);
        Assert.assertTrue(commonMethods.mouseHover(element));
        String element_text = commonMethods.getElementText(overlay_content_price.get(0));
        Assert.assertEquals(element_text, expectex_text, "Assertion passed");
    }
}
