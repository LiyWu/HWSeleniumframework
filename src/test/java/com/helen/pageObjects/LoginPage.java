package com.helen.pageObjects;

import com.helen.framework.PageObject;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoginPage extends PageObject {
//    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private By userName = new By.ByCssSelector("[type=email]");
    private By userPassword = new By.ByCssSelector("#userPassword");
    private By loginBtn = new By.ByCssSelector("#login");

    public LoginPage(String url){
        driver.get(url);
    }

    public void setUserName(String name)
    {
        findVisibleElemntBy(userName).sendKeys(name);
    }
    public void setUserPassoword(String password)
    {
        findVisibleElemntBy(userPassword).sendKeys(password);
    }
    public void clickLoginBtn()
    {
        findClickableElemntBy(loginBtn).click();
    }

    public String getTitle()
    {
        return super.getTitle();
    }



}
