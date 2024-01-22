package courese_linkedin.selenium_essentials;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SeleniumTestBasics {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    @BeforeClass
    private void preconditions() {
        // Set the property for webdriver.chrome.driver to be the location to your local              download of chromedriver
        // Create new instance of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "F:/Downloads/chromedriver-win32/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    private void closeSession() {
        //Close the browser
        driver.quit();
    }

    @Test(testName = "2")
    public void firstTest() {
        // And now use this to visit Google
        driver.get("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");
        assertEquals(element.getText(), "Cheese!");
        // Now submit the form
        element.submit();
    }

    @Test(testName = "1")
    private void verifyKeyboardInput() {
        driver.get("https://formy-project.herokuapp.com/keypress");

        WebElement name = driver.findElement(By.id("name"));
        name.click();
        name.sendKeys("Meaghan Lewis");

        WebElement button = driver.findElement(By.id("button"));
        button.click();
    }

    @Test(testName = "3")
    private void verifyDropDown() {
        driver.get("https://formy-project.herokuapp.com/dropdown");

        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
        dropDownMenu.click();

        WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
        autocompleteItem.click();
    }

    @Test(testName = "4")
    private void verifyAutocomplete() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("1555 Park Blvd, Palo Alto, CA");
        //implicit wait
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("pac-item")));
        WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
        autocompleteResult.click();

    }

    @Test(testName = "5")
    private void verifyScrollToTheElement() {
        driver.get("https://formy-project.herokuapp.com/scroll");

        WebElement name = driver.findElement(By.id("name"));
        actions.moveToElement(name);
        name.sendKeys("Meaghan Lewis");

        WebElement date = driver.findElement(By.id("date"));
        date.sendKeys("01/01/2020");
    }

    @Test(testName = "6")
    private void verifySwitchToActiveWindow() {
        driver.get("https://formy-project.herokuapp.com/switch-window");

        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();

        String originalHandle = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        driver.switchTo().window(originalHandle);
    }

    @Test(testName = "7")
    private void verifySwitchToAlert() {
        driver.get("https://formy-project.herokuapp.com/switch-window");

        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(testName = "8")
    private void verifyJsScriptExecution() {
        driver.get("https://formy-project.herokuapp.com/modal");

        WebElement modalButton = driver.findElement(By.id("modal-button"));
        modalButton.click();

        WebElement closeButton = driver.findElement(By.id("close-button"));
        js.executeScript("arguments[0].click();", closeButton);
    }

    @Test(testName = "9")
    private void verifyDragAndDrop() {
        driver.get("https://formy-project.herokuapp.com/dragdrop");

        WebElement image = driver.findElement(By.id("image"));
        WebElement box = driver.findElement(By.id("box"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(image, box).build().perform();
    }

    @Test(testName = "10")
    private void verifyRadioButtons() {
        driver.get("https://formy-project.herokuapp.com/radiobutton");

        WebElement radioButton1 = driver.findElement(By.id("radio-button-1"));
        radioButton1.click();

        WebElement radioButton2 = driver.findElement(By.cssSelector("input[value='option2']"));
        radioButton2.click();

        WebElement radioButton3 = driver.findElement(By.xpath("/html/body/div/div[3]/input"));
        radioButton3.click();
    }

    @Test(testName = "11")
    private void verifyDatePicker() {
        driver.get("https://formy-project.herokuapp.com/datepicker");

        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.sendKeys("03/03/2020");
        dateField.sendKeys(Keys.RETURN);
    }

    @Test(testName = "12")
    private void verifyUploadFile() {
        driver.get("https://formy-project.herokuapp.com/fileupload");

        WebElement fileUploadField = driver.findElement(By.id("file-upload-field"));
        fileUploadField.sendKeys("src/test/java/courese_linkedin/selenium_essentials/file-to-upload.png");
    }
}
