package com.helen.framework.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.messages.types.Scenario;

import java.net.URL;

public interface Hooks {
    void beforeEveryScenario(Scenario scenario);

    void afterEveryScenario(Scenario scenario);

    class AndroidDriverHelper {
        private static AndroidDriver ANDROID_DRIVER = null;
        private static long DRIVER_IMPLICITY_WAIT_TIME = 5;

        private AndroidDriverHelper()
        {
            ANDROID_DRIVER = getAndroidDriver();
        }

        public static AndroidDriver getAndroidDriver() {
                if(ANDROID_DRIVER == null)
                {
                    ANDROID_DRIVER = createDecoratedDriver();
                }
                return ANDROID_DRIVER;
        }

        private static AndroidDriver createDecoratedDriver()
        {
            try {
                //setup options
                String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\vau_v3.51.0_2024-10-17_105942_beta.apk";
              /*  UiAutomator2Options options = new UiAutomator2Options().setDeviceName("pixel_8_pro").
                        setAutomationName("uiAutomator2").setApp(filePath).setFullReset(true);*/

              //  System.setProperty("APPIUM_SKIP_CHROMEDRIVER_INSTALL", "1");
                UiAutomator2Options options = new UiAutomator2Options().setDeviceName("pixel_8_pro").
                        setAutomationName("uiAutomator2").setApp(filePath).setFullReset(true);
                URL url = new URL("http://0.0.0.0:4723");
                /*options.setDeviceName("pixel_8_pro").
                        setAutomationName("uiAutomator2").setFullReset(true);*/
                       // setAppPackage("cn.com.vau").
                        //setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
                              //  setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
                ANDROID_DRIVER = new AndroidDriver(url, options);
                //ANDROID_DRIVER.manage().timeouts()
                       // .implicitlyWait(Duration.ofSeconds(DRIVER_IMPLICITY_WAIT_TIME));

                //create listener for android driver
               /* MyWebDriverListener ls = new MyWebDriverListener();
                ANDROID_DRIVER = (AndroidDriver) new EventFiringDecorator(ls).decorate(ANDROID_DRIVER);*/

            } catch (Exception e) {
                e.printStackTrace();
            }
            return ANDROID_DRIVER;
        }
        public static AppiumDriver appiumDriver(String platform) throws Exception {
            //String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\ApiDemos-debug.apk";

            /*UiAutomator2Options options = new UiAutomator2Options().setDeviceName("pixel_8_pro").
                    setAutomationName("uiAutomator2").setApp(filePath);*/
            /*options.setDeviceName("pixel_8_pro").
                        setAutomationName("uiAutomator2").setFullReset(true);*/
            // setAppPackage("cn.com.vau").
            //setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
            //  setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
            UiAutomator2Options options = new UiAutomator2Options();
            //options.setNewCommandTimeout(Duration.ofSeconds(300));
            URL url = new URL("http://0.0.0.0:4723");

            return switch (platform) {
                case "Android" -> {
                    options.setDeviceName("pixel_8_pro").
                            setAutomationName("uiAutomator2").
                            setAppPackage("com.woolworths").
                            //setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
                                    setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
                    yield new AndroidDriver(url, options);
                    //setAppActivity("cn.com.vau.MainActivity").setNoReset(true).setFullReset(false);
                }
                case "iOS" -> {
                    options.setDeviceName("iphone 13").setAutomationName("XCUITest").setUdid("").setUdid("shoubebe bundleId");
                    yield new IOSDriver(url, options);
                }
                default -> throw new Exception("invalid platform");
            };
        }
    }
}
