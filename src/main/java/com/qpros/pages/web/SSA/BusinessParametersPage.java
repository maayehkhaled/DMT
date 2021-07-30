package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BusinessParametersPage extends Base {
    public BusinessParametersPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public String url = urls.businessParameters;

    private By buttonShowDetails = By.xpath("//*[contains(@id,'wtbtn_ShowSpecificCode')]");

    private By applicationRef = By.xpath("//*[contains(@id,'wttxt_CodesToRelease')]");

    private By validateButton = By.xpath("//*[contains(@id,'wtbtn_Validate')]");
//
    private By startProcessButton = By.xpath("//*[contains(@id,'wtbtn_RunSpecificCodes')]");
//wtbtn_RunSpecificCodes

    public void releaseAppliaction(String refNo){
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Expand fields",buttonShowDetails);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Input SSP code: " + refNo, applicationRef);
        ActionsHelper.driverWait(1000);
        ActionsHelper.sendKeysWithClear(applicationRef,refNo);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Validate", validateButton);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Start Process", startProcessButton);
        ActionsHelper.driverWait(1000);
        driver.get().switchTo().alert().accept();
    }
}
