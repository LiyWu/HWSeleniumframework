package com.helen.framework.helpers;

import com.helen.framework.listener.MyWebDriverListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.logging.Level;

public class WebDriverHelper {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverHelper.class);
    private static WebDriver REAL_DRIVER = null;
    private static long DRIVER_IMPLICITY_WAIT_TIME = 5;

    private WebDriverHelper(){

    }
    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    public static void getDriverBaseOnBrowser(String browser)
    {
        switch(browser.toUpperCase()) {
            case "CHROME":
                startChromeDriver();
                break;
            case "FIREFOX":
                startFireFoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type not suppport:" + System.getProperty("browserName"));
        }
    }

    private static void startChromeDriver()
    {
        ChromeOptions options = getChromeOptions();
        REAL_DRIVER = new ChromeDriver(options);
        //使用带有监听器的driver
        MyWebDriverListener ls = new MyWebDriverListener();
        REAL_DRIVER = new EventFiringDecorator(ls).decorate(REAL_DRIVER);
    }
    private static void startFireFoxDriver()
    {
        FirefoxOptions options = new FirefoxOptions();
        REAL_DRIVER = new FirefoxDriver(options);
    }
    private static ChromeOptions getChromeOptions(){
        final LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-sandbox");
        options.addArguments("allow-running-insecure-content");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless");

        options.setAcceptInsecureCerts(true);
       // options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

}
