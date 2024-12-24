package com.helen.testcases;

import com.helen.pageObjects.LoginPage;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTestCase {
    protected static String serverName = "";
    protected static String TraderURL;
    protected static String TraderName;
    protected static String TraderPass;

    protected static LoginPage loginP;
    /***
     * launchBrowser and cp login before run the class
     * 默认的是在每个class启动之前重启浏览器，测试有特殊需求需要覆盖这个方法
     * @param TestEnv alphar or prod
     * @param headless true or false
     * @param TraderURL
     * @param TraderName
     * @param TraderPass
     * @param context
     */
    @BeforeClass(alwaysRun = true)
    @Parameters(value= {"TestEnv","headless","TraderURL", "TraderName", "TraderPass","AdminURL","AdminName","AdminPass","Debug","Server"})
    public void beforMethod(@Optional("alpha")String TestEnv, @Optional("False") String headless,
                            @Optional("")String TraderURL, @Optional("")String TraderName, @Optional("")String TraderPass,
                            @Optional("")String AdminURL, @Optional("")String AdminName, @Optional("")String AdminPass, @Optional("True")String Debug, @Optional("")String server,
                            ITestContext context) {
        if(!"".equals(server)) {
            serverName = server;
        }
        launchBrowser( TestEnv,  headless, TraderURL, TraderName, TraderPass, AdminURL,  AdminName, AdminPass,  Debug, context);
    }
    @Parameters(value= {"TestEnv","headless","TraderURL", "TraderName", "TraderPass","AdminURL","AdminName","AdminPass","Debug"})
    public void launchBrowser(@Optional("alpha")String TestEnv, @Optional("False") String headless,
                              @Optional("")String TraderURL, @Optional("")String TraderName, @Optional("")String TraderPass,
                              @Optional("")String AdminURL, @Optional("")String AdminName, @Optional("")String AdminPass, @Optional("True")String Debug,
                              ITestContext context) {
        loginP = new LoginPage(TraderURL);
        BaseTestCase.TraderName = TraderName;
        BaseTestCase.TraderPass = TraderPass;

    }
}
