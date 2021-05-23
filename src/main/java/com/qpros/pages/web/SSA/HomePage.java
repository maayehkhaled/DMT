package com.qpros.pages.web.SSA;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base {
    public HomePage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By agreeButton = By.id("CloneOfWebPatterns_wt6_block_wtMainContent_wtagree");

    private By loginButton = By.id("DCDWebPortalTheme_wt59_block_wtMenu_wt54_wt24");


/*@STEP(name = "The User Perform Login",description = "Perform Login")
public synchronized void PerformLogin(UserType userType) throws Exception {
    logManager.STEP("Navigate to "+URL.DU_LOGIN_URL,"");
    clickOnLoginButton();
    logManager.STEP("Enter username and password then click \"Login\" button","");
    FillUserName(userType.getUserName());
    FillPassword(userType.getPassword());
    pressLogin();
}*/

    public void navigateToLogin(){
        ActionsHelper.actionClickStepClick("Click agree",agreeButton);
        ActionsHelper.actionClickStepClick("Click on login page button",loginButton);

    }
}
