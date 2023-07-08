package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            /*WebElement elementText = driver.findElement(By.name("search"));
            elementText.clear();
            elementText.sendKeys(product);*/
            driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input")).clear();
            driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input")).sendKeys(product);
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/h4")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div[1]/p[1]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/p[2]"))));
            //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div[1]/div/div/p")));
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            
            searchResults = driver.findElements(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"));
            //searchResults = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/p[1]"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            WebElement noResultsFound = driver.findElement(By.xpath("//div[@class='loading MuiBox-root css-0']"));
            if (noResultsFound.isDisplayed())
                status = true;
            else
                status = false;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> searchedProducts =
                    driver.findElements(By.className("MuiCardContent-root"));
            WebElement addToCarElement = driver.findElement(By.cssSelector(".MuiCardActions-root>button"));
            for (WebElement searchedProduct : searchedProducts) {
                WebElement searchProductName = searchedProduct.findElement(By.tagName("p"));
                // String searchProductName = searchedProduct.findElement(By.tagName("p")).getText();
                if (searchProductName.getText().trim().equalsIgnoreCase(productName)){
                    addToCarElement.click();
                    break;
                }    
                
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }
    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            driver.findElement(By.xpath("//button[contains(@class,'checkout')]")).click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName

            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)
            WebElement addButton = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//*[@data-testid='AddOutlinedIcon']/.."));
            for(int i=0; i<quantity-1; i++){
                addButton.click();
                // Uncomment the below thread
                Thread.sleep(2000);
                //wait.until(ExpectedConditions.textToBePresentInElement(findElement(By.className("css-olyig7")), String.valueOf()))
            }
            //WebElement removeButton = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//*[@data-testid='RemoveOutlinedIcon']/.."));
            //WebElement cartItem = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//div//div//div"));
            //int itemInCart = cartItem.getText().length();
            //WebElement cartItemName = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]"));
            return true;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements
            List<WebElement> cartElements = driver.findElements(By.xpath(
                    "//div[contains(@class,'cart MuiBox-root')]//div[contains(@class,'image')]/following-sibling::div"));

            // Iterate through expectedCartContents and check if item with matching product
            for (WebElement cartElement : cartElements) {
                WebElement cartProductName = cartElement.findElement(By.tagName("div"));
                for(String expectedCartContent:expectedCartContents){
                    if (cartProductName.getText().equalsIgnoreCase(expectedCartContent)) {
                        status = true;
                        break;
                    }
                }
            }
            // name is present in the cart
            return status;
        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
