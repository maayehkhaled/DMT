package com.qpros.pages.login;


import com.qpros.common.Base;
import com.qpros.common.UserType;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.qpros.helpers.ActionsHelper.*;


@Getter
public class LoginPage extends Base {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement email;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "submitAct")
    private WebElement LoginButton;
    @FindBy(id = "termCondition")
    private WebElement termsAndCondition;



    public void signIn(String email, String password) throws Exception {
        driverWait(300);
        clickAction(driver.findElement(By.xpath("//a[.='My Account']")));
        driverWait(300);
        retryClick(By.xpath("//a[.='Sign In']"),30);
        driverWait(3000);
        Assert.assertTrue(ActionsHelper.isElementPresent(By.id("username")));
        ActionsHelper.sendKeysWithClear(getEmail(), UserType.B2C.getUserName());
        driverWait(300);
        Assert.assertTrue(ActionsHelper.isElementPresent(By.id("password")));
        ActionsHelper.sendKeysWithClear(getPassword(), UserType.B2C.getPassword());
        driverWait(1000);
        driverWait(300);
        ActionsHelper.retryClick(By.id("submitAct"),30);
    }



}
