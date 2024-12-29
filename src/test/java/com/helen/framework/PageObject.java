package com.helen.framework;

import com.helen.framework.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    protected static WebDriver driver;
    private static long DRIVER_WAIT_TIME = 15;
    private static WebDriverWait wait;
    JavascriptExecutor js_executor = (JavascriptExecutor) driver;

    static{
        String browserFilePath = "src/test/resources/browser.properties";
        Properties properties = new Properties();
        try{
            FileInputStream fileinput = new FileInputStream(browserFilePath);
            properties.load(fileinput);
            String browser = properties.getProperty("browser");
            WebDriverHelper.getDriverBaseOnBrowser(browser);
            driver = WebDriverHelper.getWebDriver();
            wait = new WebDriverWait(driver,Duration.ofSeconds(DRIVER_WAIT_TIME));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DRIVER_WAIT_TIME));
            driver.manage().window().maximize();
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    public static WebDriver getDriver()
    {
        return driver;
    }
    public boolean isElementPresent(String nameElement) {
        LOG.info("RUNNING : isElementPresent");
        By elementName = By.cssSelector(nameElement);
        return driver.findElement(elementName).isDisplayed();
    }
    public void getAllButtons() {
        LOG.info("Running :  getAllButtons");
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        LOG.info("Total Number of buttons found : " + buttons.size());
        for (WebElement button : buttons) {
            if (button.getText().length() > 0) {
                LOG.info("BUTTON (With Text)PRESENT : " + button.getText());
            }
        }
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected WebElement findClickableElemntBy(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement findVisibleElemntBy(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitLoading() {
        wait.until((ExpectedCondition<Boolean>) d -> {
            try{
                d.findElement(By.xpath("//i[contains(@class,'client-portal-loading') or contains(@class,'client-portal-loading-au')]"));
            }catch(Exception e) {
                return true;
            }
            return false;
        });

    }

    public void goBack() {
        driver.navigate().back();
        this.waitLoading();
    }

    public boolean checkUrlContains(String keyword) {
        this.waitLoading();
        try {
            wait.until(ExpectedConditions.urlContains(keyword));
        }catch(Exception e) {
            System.out.println("Did not navigate to the correct url which inlude : " + keyword);
            return false;
        }

        return true;
    }

    public boolean checkUrlNotContains(String keyword) {
        this.waitLoading();
        try {
            wait.until((ExpectedCondition<Boolean>) driver ->!driver.getCurrentUrl().contains(keyword));
        }catch(Exception e) {
            System.out.println("**********Did not navigate to the third party, kindly check**********");
            return false;
        }

        return true;
    }

    protected void moveElementToVisible(WebElement element) {

        String js_str = "arguments[0].scrollIntoView(true);window.scrollBy(0, -window.innerHeight / 2);";
        js_executor.executeScript(js_str,element);
    }

    public void refresh() {
        driver.navigate().refresh();
        this.waitLoading();
    }

    public String getTitle()
    {
        return driver.getTitle();
    }







}
