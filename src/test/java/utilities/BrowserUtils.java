package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class BrowserUtils {

    public static void sleep(int seconds) {
        seconds *= 1000;

        try {
            Thread.sleep(seconds);

        } catch (InterruptedException e) {
            System.out.println("something happen in sleep method");
        }
    }

    public static void hoover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void clickOn(WebElement element, int timeout) {
        final WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(element)));
        element.click();
    }

    //this method accepts List<WebElement> and returns List<String>
    public static List<String> convertWebElementToString_andGetText(List<WebElement> elements) {
        List<String> textsOfWebElement = new LinkedList<>();

        for (WebElement element : elements) {
            textsOfWebElement.add(element.getText());
        }
        return textsOfWebElement;
    }

    // The method takes an element and takes the text you want to send by waiting explicitly and returns text as a String
    public static String sendTheKeys(WebElement element,  String text) {
        final WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 7);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(element)));
        element.sendKeys(text);
        return text;
    }

    // wait method for element until Visible by WebElement
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // wait method for webElement until Clickable
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }


}
