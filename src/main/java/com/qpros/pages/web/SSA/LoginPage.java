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
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By usernameField = By.id("wt15_wtMainContent_wt22");

    private By passwordField = By.id("wt15_wtMainContent_wt23");

    private By loginButton = By.id("wt15_wtMainContent_wtLogin");

    public void loginWithUser(String username, String password) {
        logManager.STEP("Input username", "Inputs the username: " + username);
        ActionsHelper.waitForExpectedElement(usernameField);
        ActionsHelper.retryClick(usernameField, 30);
        ActionsHelper.sendKeys(usernameField, username);
        logManager.STEP("Input password", "Inputs the password: " + password);
        ActionsHelper.sendKeys(passwordField, password);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click the login button", loginButton);
        ActionsHelper.driverWait(5000);

    }

    public void loginWithUser(UserType usertype) {
        logManager.STEP("Input username", "Inputs the username: " + usertype.getUserName());
        ActionsHelper.waitForExpectedElement(usernameField);
        ActionsHelper.retryClick(usernameField, 30);
        ActionsHelper.sendKeys(usernameField, usertype.getUserName());
        logManager.STEP("Input password", "Inputs the password: " + usertype.getPassword());
        ActionsHelper.sendKeys(passwordField, usertype.getPassword());
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click the login button", loginButton);
        ActionsHelper.driverWait(5000);

    }

}
