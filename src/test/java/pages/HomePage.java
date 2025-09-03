package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

//    By navbar_product = By.xpath("//ul[@class='nav navbar-nav']//a[contains(@href, 'products')]");

    @FindBy(xpath = "//ul[@class='nav navbar-nav']//a[contains(@href, 'products')]")
    private static WebElement navbar_product_link;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void click_on_product_link(){
        navbar_product_link.click();
    }

}
