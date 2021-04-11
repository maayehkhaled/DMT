package com.qpros.pages.web.common;

import com.qpros.common.UserType;
import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.web.data.Files;
import com.qpros.reporting.QuantaTestManager;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qpros.pages.web.data.URL;
import org.testng.Assert;

import static com.qpros.common.Utils.CreateUniqueRandomUsername;
import static com.qpros.common.Utils.GenerateRandomPassword;
import static com.qpros.helpers.ActionsHelper.*;

@Getter
public class LoginPage extends Base {

    public long DRIVER_WAIT = 1000;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(Base.driver, this);
    }

    @FindBy(xpath = "//a[@id='nonlogin']")
    public WebElement loginButton;

    @FindBy(name = "user")
    public WebElement userNameTextbox;

    @FindBy(name = "password")
    public WebElement passwordTextbox;

    @FindBy(id = "myaccountloginid")
    public WebElement loginbutton;

    @FindBy(xpath = "//*[@id=\"du-msg-error-status-1\"]")
    public WebElement ErrorMsgforInvalidUser;

    @FindBy(css = ".login")
    public WebElement profileIcon;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "Change Username or Password")
    public WebElement changeUserNameorPasswordLink;

    @FindBy(name = "name")
    public WebElement updateUserNameTextbox;

    @FindBy(id = "update-username")
    public WebElement updateUserNameButton;

    @FindBy(css = "#du-notification-username .rd-notification__content")
    public WebElement updateUsernameMsg;

    @FindBy(name = "currentPassword")
    public WebElement currentPasswordTextbox;

    @FindBy(name = "newPassword")
    public WebElement newPasswordTextbox;

    @FindBy(id = "update-password")
    public WebElement savePasswordButton;

    @FindBy(xpath = "//*[@id=\"login\"]")
    public WebElement myAccountLogin;

    @FindBy(xpath = "//*[@id=\"du-msg-error-password\"]")
    public WebElement invalidOldpassword;

    @FindBy(xpath = "//div[.='Your Username was changed with success.']")
    public WebElement updatepasswordmsg;

    @STEP(name = "The User Perform Login",description = "Perform Login")
    public synchronized void PerformLogin(UserType userType) throws Exception {
        logManager.STEP("Navigate to "+URL.DU_LOGIN_URL,"");
        clickOnLoginButton();
        logManager.STEP("Enter username and password then click \"Login\" button","");
        FillUserName(userType.getUserName());
        FillPassword(userType.getPassword());
        pressLogin();
    }

    @STEP(name = "Change User Name",description = "Change User Name")
    public synchronized String ChangeUserName() {

        String[][] credentials = ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals",Files.Login_Credentials, 1, 2);
        String CurrentUsername = credentials[0][0];

        waitVisibility(profileIcon, 40);
        moveToElementByActions(profileIcon);
        clickElementActions(profileIcon);

        waitVisibility(changeUserNameorPasswordLink, 40);
        moveToElementByActions(changeUserNameorPasswordLink);
        clickElementActions(changeUserNameorPasswordLink);

        waitVisibility(updateUserNameButton, 40);
        updateUserNameTextbox.clear();
        String NewUserName = CreateUniqueRandomUsername(CurrentUsername);
        updateUserNameTextbox.sendKeys(NewUserName);

        waitVisibility(updateUserNameButton, 40);
        isElementPresent(updateUserNameButton);
        updateUserNameButton.click();

        return NewUserName;
    }

    @STEP(name = "Change Password",description = "Change Password")
    public synchronized String ChangePassword(String CurrentPassword) {

        String NewPassword = GenerateRandomPassword();
        waitVisibility(profileIcon, 40);
        moveToElementByActions(profileIcon);
        clickElementActions(profileIcon);
        System.out.println(NewPassword);

        waitVisibility(changeUserNameorPasswordLink, 40);
        moveToElementByActions(changeUserNameorPasswordLink);
        clickElementActions(changeUserNameorPasswordLink);
        waitVisibility(savePasswordButton, 50);

        currentPasswordTextbox.sendKeys(CurrentPassword);
        newPasswordTextbox.sendKeys(NewPassword);

        waitToBeClickable(savePasswordButton, 100);
        savePasswordButton.click();
        driverWait(60);
        return NewPassword;

    }

    @STEP(name = "Logout ",description = "Logout ")
    public synchronized void Logout() {
        logManager.STEP("the User Perform Logout","the User Click on Logout button from My dashboard Menu item ");
        waitVisibility(profileIcon, 20);
        moveToElementByActions(profileIcon);
        isElementPresent(profileIcon);
        clickElementActions(profileIcon);
        waitVisibility(logOutLink, 50);
        moveToElementByActions(logOutLink);
        isElementPresent(logOutLink);
        clickElementActions(logOutLink);
        waitVisibility(myAccountLogin, 50);
        isElementPresent(myAccountLogin);

    }

    public synchronized void FillUserName(String username) {
        waitVisibility(userNameTextbox, 20);
        userNameTextbox.sendKeys(username);
        logManager.INFO("The User Enter UserName in the User Name Text Box",false);
        Assert.assertEquals(username,userNameTextbox.getAttribute("value"));

    }

    public synchronized void FillPassword(String password) {
        waitVisibility(passwordTextbox, 20);
        passwordTextbox.sendKeys(password);
        logManager.INFO("The User Enter Password in the Password Text Box",false);
        Assert.assertEquals(password,passwordTextbox.getAttribute("value"));

    }

    public synchronized void pressLogin() throws Exception {
        waitToBeClickable(loginbutton, 30);
        logManager.INFO("The User Clicks on Login Button",false);
        retryClick(loginbutton,30);
        driverWait(5000);

            if (isElementPresent(By.xpath("//p[contains(.,'Username / password mismatch. 2 incorrect attempts will lock this login for 5 mi')]"))) {
                logManager.ERROR("The User Credentials Entered is not Correct", false);
                QuantaTestManager.getTest().fail("The User Credentials Entered is not Correct");
                throw new Exception("The User Credentials Entered is not Correct to be Used");
            }

    }

    public synchronized boolean ErrorMessageInvalidUser() {
        return ErrorMsgforInvalidUser.isDisplayed();
    }

    public synchronized String getURL() {
        return driver.getCurrentUrl();
    }

    public synchronized void clickOnLoginButton(){
        isElementPresent(loginButton);
        logManager.INFO("The User Click on Login Button at the HomePage Header",false);
        retryClick(loginButton,30);
    }


}
