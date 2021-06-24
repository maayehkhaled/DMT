package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BusinessParametersPage extends Base {
    public BusinessParametersPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public String url = "https://10.231.1.100/DCDBusinessParameters/BusinessParameters.aspx";

    private By buttonShowDetails = By.id("DCDAgentPortalTheme_wt23_block_wtMainContent_wt8_WebPatterns_wt146_block_wtContent_wt102");

    private By applicationRef = By.id("DCDAgentPortalTheme_wt23_block_wtMainContent_wt8_WebPatterns_wt146_block_wtContent_wt213");

    private By validateButton = By.id("DCDAgentPortalTheme_wt23_block_wtMainContent_wt8_WebPatterns_wt146_block_wtContent_wt122");

    private By startProcessButton = By.id("DCDAgentPortalTheme_wt23_block_wtMainContent_wt8_WebPatterns_wt146_block_wtContent_wtrunspecificcodes");


    public void releaseAppliaction(String refNo){
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Expand fields",buttonShowDetails);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Input SSP code: " + refNo, applicationRef);
        ActionsHelper.driverWait(1000);
        ActionsHelper.sendKeysWithClear(applicationRef,refNo);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Validate", validateButton);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Start Process", startProcessButton);

        driver.get().switchTo().alert().accept();
    }
}
