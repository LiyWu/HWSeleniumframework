package com.helen.testcases;

import com.helen.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseTestCase{

    @Test
    public void login(){
        loginP.setUserName(TraderName);
        loginP.setUserPassoword(TraderPass);
        loginP.clickLoginBtn();
        loginP.waitLoading();
        Assert.assertTrue(loginP.getTitle().contains("Let's Shop"));
    }

}
