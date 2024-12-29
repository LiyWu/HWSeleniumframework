package com.helen.testcases;

import com.helen.framework.PageObject;
import com.helen.framework.helpers.WebDriverHelper;
import com.helen.pageObjects.LoginPage;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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
    @Parameters(value= {"TestEnv","headless","TraderURL", "TraderName", "TraderPass","Debug","Server"})
    public void beforMethod(@Optional("alpha")String TestEnv, @Optional("False") String headless,
                            @Optional("")String TraderURL, @Optional("")String TraderName, @Optional("")String TraderPass, @Optional("True")String Debug, @Optional("")String server,
                            ITestContext context) {
        if(!"".equals(server)) {
            serverName = server;
        }
        launchBrowser( TestEnv,  headless, TraderURL, TraderName, TraderPass, Debug, context);
    }
    @Parameters(value= {"TestEnv","headless","TraderURL", "TraderName", "TraderPass","Debug"})
    public void launchBrowser(@Optional("alpha")String TestEnv, @Optional("False") String headless,
                              @Optional("")String TraderURL, @Optional("")String TraderName, @Optional("")String TraderPass, @Optional("True")String Debug,
                              ITestContext context) {
        loginP = new LoginPage(TraderURL);
        BaseTestCase.TraderName = TraderName;
        BaseTestCase.TraderPass = TraderPass;
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        PageObject.getDriver().quit();
    }
}
