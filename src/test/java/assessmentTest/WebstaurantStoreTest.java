package assessmentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebstaurantStoreTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup webDriver using WebDriverManager for chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();


        try {
            // go to https://www.webstaurantstore.com/
            driver.get("https://www.webstaurantstore.com/");

            // wait a for a while to see the result
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            // Search for "stainless work table"
            WebElement searchBox = driver.findElement( By.id("searchval"));
            searchBox.sendKeys("stainless work table");
            searchBox.submit();

            // check the search result ensuring every product has the word"table" in its title

            boolean allProductsContainTable = driver.findElements(By.name("description"))
                    .stream()
                    .allMatch(element -> element.getText().toLowerCase().contains("table"));

            if ( allProductsContainTable){
                System.out.println(" All products contains the word 'Table' in their title");
            } else {
                System.out.println(" Not all products contain the word 'Table' in their title");
            }

            // add the last found item to cart
            // use the "add to cart" button to add last product to the cart
            WebElement lastProductAddToCartButton = driver.findElements(By.name("addToCartButton"))
                    .get(driver.findElements(By.name("addToCartButton")).size() -1);
            lastProductAddToCartButton.click();

            driver.findElement(By.xpath("//a[text() = 'View Cart']")).click();
            Thread.sleep(2000);

            // use the clear cart button to clear the cart

            WebElement clearCartButton = driver.findElement(By.xpath("//button[text()='Empty Cart']"));
            clearCartButton.click();
            Thread.sleep((1000));

           WebElement emptyCartBtn = driver.findElement(By.xpath("//*[@id=\"td\"]/div[11]/div/div/div/footer/button[1]"));
           emptyCartBtn.click();
            Thread.sleep(2000);

            Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Your cart is empty.')]")).getText().contains("Your cart is empty."));
            System.out.println("Your cart is empty.");
        } finally {
            // close the browser
            driver.quit();
        }
    }
}
