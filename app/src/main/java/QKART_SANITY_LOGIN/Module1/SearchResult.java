package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        titleOfSearchResult = parentElement.getText();
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart(WebDriver driver) {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChart = driver.findElement(By.xpath("//button[contains(text(),'Size chart')]"));
            Thread.sleep(3000);
            sizeChart.click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            driver.findElement(By.xpath("//button[contains(text(),'Size chart')]"));
            return true;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
            //expectedTableBody.get(0).get(1);
            int sizeTableHeader = expectedTableHeaders.size();
            //this.openSizechart(driver);
            List<WebElement>tableHead = driver.findElements(By.xpath("//table/thead/tr/th"));
            if(sizeTableHeader == tableHead.size()){
                for(int i=0;i<sizeTableHeader;i++){
                    status = tableHead.get(i).getText().equalsIgnoreCase(expectedTableHeaders.get(i));
                }
            }
            //Iterate for table body 
            /*int sizeTableData = expectedTableBody.size();
            int k=0;
            for(int i=0; i<ActualTableHeaders.size(); i++){
                if(!expectedTableHeaders.get(i).equals(ActualTableHeaders.get(i).getText())){
                    return false;
                }
            }
            List<WebElement> eachrowvalues = null;
            int rownum = driver.findElements(By.xpath("//tbody/tr")).size();
            for(int i=0;i<rownum;i++){
                String xpathv ="//tbody/tr["+i+"]/td";
                eachrowvalues = driver.findElements(By.xpath(xpathv));
                for(int j=0; j<eachrowvalues.size(); j++){
                    if(!expectedTableBody.get(i).get(j).equals(eachrowvalues.get(j).getText())){
                        return false;
                    }
                }
            }
            return true;*/
            int sizeTableData = expectedTableBody.size();
            int k=0;
            List<WebElement> tableData = driver.findElements(By.xpath("//table//tr//td"));
            for (List<String>list : expectedTableBody){
                for (String item: list){
                   Thread.sleep(2000); 
                   status = item.equalsIgnoreCase(tableData.get(k).getText());
                   return status;
                }
                k++;
            }
            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            return(driver.findElement(By.xpath("//select[@id='uncontrolled-native']")).isDisplayed());
        } catch (Exception e) {
            return status;
        }
    }
}