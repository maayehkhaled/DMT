package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ClaimantLogin extends Base {
    public ClaimantLogin(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By usernameField = By.xpath("//*[contains(@id,'wtMainContent_wttxt_EmirateId')]");

    private By passwordField = By.xpath("//*[contains(@id,'wtMainContent_wttxt_Password')]");

    private By loginButton = By.xpath("//*[contains(@id,'wtMainContent_wtbtn_Login')]");

    private By updateFamilyInformation = By.cssSelector("#DCDTheme_wt27_block_wtMainContent_wtCoCLink > .HomePageButton");

    private By selectLocation = By.id("CloneOfWebPatterns_wt20_block_wtMainContent_wtWebPortalLocation2");

    private By clickAgree = By.id("CloneOfWebPatterns_wt20_block_wtMainContent_wtok");

//https://uat.ssa.gov.ae/DCDClaimantFrontEnd/MainFlow.UserLogin.aspx?(Not.Licensed.For.Production)=
    public void claimantLogin(String eid){
        logManager.STEP("Input claimant username and password","Inputs EID on both fields as username and password");

        ActionsHelper.sendKeys(usernameField,eid);
        ActionsHelper.sendKeys(passwordField,eid);
        ActionsHelper.actionClickStepClick("Click login button", loginButton);

    }

    public void navigateToUpdateFamilyInformation(){
        ActionsHelper.actionClickStepClick("Click update family information", updateFamilyInformation);
        driver.get().switchTo().frame(0);
        ActionsHelper.actionClickStepClick("Select location", selectLocation);
        WebElement locationElement = driver.get().findElement(selectLocation);
        ActionsHelper.retryClick(locationElement.findElement(By.xpath("//option[. = 'الموقع الالكتروني']")),10);
        ActionsHelper.actionClickStepClick("Click agree",clickAgree);

    }

}
