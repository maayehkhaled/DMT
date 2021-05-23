package com.qpros.pages.web.SSA;

import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base {
    public LoginPage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By usernameField = By.id("DCDWebPortalTheme_wt15_block_wtMainContent_wt1");

    private By passwordField = By.id("DCDWebPortalTheme_wt15_block_wtMainContent_wt8");

    private By loginButton = By.id("DCDWebPortalTheme_wt15_block_wtMainContent_wtLogin");

    public void loginWithUser(String username, String password){
        logManager.STEP("Input username","Inputs the username: " + username);
        ActionsHelper.sendKeys(usernameField,username);
        logManager.STEP("Input password","Inputs the password: " + password);
        ActionsHelper.sendKeys(passwordField,password);
        ActionsHelper.actionClickStepClick("Click the login button",loginButton);


    }

    public void loginWithUser(UserType usertype){
        logManager.STEP("Input username","Inputs the username: " + usertype.getUserName());
        ActionsHelper.sendKeys(usernameField, usertype.getUserName());
        logManager.STEP("Input password","Inputs the password: " + usertype.getPassword());
        ActionsHelper.sendKeys(passwordField,usertype.getPassword());
        ActionsHelper.actionClickStepClick("Click the login button",loginButton);


    }

}
