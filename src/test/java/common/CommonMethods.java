package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static BaseTests.BaseTest.driver;

public class CommonMethods {

    private static Logger logger;
    static WebDriverWait wait;
    Actions actions;
    WebDriver driver;

    public CommonMethods(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger = LoggerFactory.getLogger(CommonMethods.class);
        actions = new Actions(driver);
    }



    public boolean enter_text(WebElement element, String text){
            if(element.isDisplayed()){
                element.sendKeys(text);
                logger.info("Text is entered into the field");
                return true;
            }
            else {
                logger.info("Element is not displayed.");
                return false;
            }
    }

    public boolean click_on_element(WebElement element){
        waitUnitlElementIsVisible(element);
        if(element.isEnabled()){
            element.click();
            logger.info("Clicked on the element:- "+ element);
            return true;
        }else {
            logger.info("Element is not enabled");
            return false;
        }
    }

    private static void waitUnitlElementIsVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getElementText(WebElement element){
        return element.getText();
    }

    public List<String> getListOfElementText(List<WebElement> element_list){
        List<String> list_of_text = new ArrayList<>();
        for(WebElement e : element_list){
            list_of_text.add(e.getText());
        }
        return list_of_text;
    }

    public boolean mouseHover(WebElement element){
        if(element.isDisplayed()){
            actions.moveToElement(element).perform();
            logger.info("success Mouse Hover into the Element.");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        else {
            logger.info("Element is not displayed.");
            return false;
        }
    }
}
