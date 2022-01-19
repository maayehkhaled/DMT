package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BusinessParametersPage extends Base {
    public BusinessParametersPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public String url = urls.businessParameters;
    Popups feedbackMessageNotification = new Popups(driver.get());

    private By buttonShowDetails = By.xpath("//*[contains(@id,'wtbtn_ShowSpecificCode')]");

    private By applicationRef = By.xpath("//*[contains(@id,'wttxt_CodesToRelease')]");

    private By validateButton = By.xpath("//input[@value=\"Validate\"]");
//
    private By startProcessButton = By.xpath("//*[contains(@id,'wtbtn_RunSpecificCodes')]");
//wtbtn_RunSpecificCodes
    private By feedbackMessage = By.xpath("//span[@class=\"Feedback_Message_Text\"]");

    public void releaseAppliaction(String refNo){
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("Expand fields",buttonShowDetails);
        ActionsHelper.driverWait(7000);
        ActionsHelper.actionClickStepClick("Input SSP code: " + refNo, applicationRef);
        ActionsHelper.driverWait(7000);
        ActionsHelper.sendKeysWithClear(applicationRef,refNo);
        ActionsHelper.driverWait(7000);
        this.logManager.STEP("Click On Validate", "The user clicks on validate");
        ActionsHelper.actionClickStepClick("validate", validateButton);
        ActionsHelper.clickAction(validateButton);
        ActionsHelper.waitForExpectedElement(feedbackMessage, 30);
        Boolean message = feedbackMessageNotification.feedbackMessage().contains("released");
        if (message){
            ActionsHelper.actionClickStepClick("Start Process", startProcessButton);
            ActionsHelper.driverWait(7000);
            driver.get().switchTo().alert().accept();
        }
        ActionsHelper.waitForExpectedElement(feedbackMessage, 30);
        Boolean message1 = feedbackMessageNotification.feedbackMessage().contains("Update scheduled");
        Assert.assertTrue(message1);
    }
}
