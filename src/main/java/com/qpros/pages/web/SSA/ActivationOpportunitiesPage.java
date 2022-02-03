package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class ActivationOpportunitiesPage extends Base {
    public ActivationOpportunitiesPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By addOpportunityBtn= By.xpath("//input[contains(@id,'AddOpportunity')]");
    private By opportunityTypyDDL=By.xpath("//select[contains(@id,'wtddl_Opportunity')]"); //2
    private By partnerDDL=By.xpath("//select[contains(@id,'wtddl_Partner')]");//2
    private By opportunityNameTextbox=By.xpath("//input[contains(@id,'name')]");
    private By opportunityDescriptionTextbox=By.xpath("//input[contains(@id,'DescriptionInput')]");
    private By startDateCalendar=By.xpath("//input[contains(@id,'wtDateandCity_block_wtColumn1_wtdt_SDate')]");
    private By locationDDl=By.xpath("//select[contains(@id,'City')]");
    private By programCapacityTextbox=By.xpath("//input[contains(@id,'Capacity2')]");
    private By genderDDL=By.xpath("//select[contains(@id,'Genderip')]");
    private By minAgeTextbox=By.xpath("//input[contains(@id,'wtMinAge_Input')]");
    private By maxAgeTextbox=By.xpath("//input[contains(@id,'maxage3')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By successMsg=By.className("Feedback_Message_Success"); ////span[contains(@id,'wtSanitizedHtml3')]
    private By EIDTextbox=By.xpath("//textarea");
    private By addMemberBtn=By.xpath("//input[contains(@id,'AddIndividual')]");

    public void clickAddOpportunity() {
        ActionsHelper.retryClick(addOpportunityBtn, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityTypyDDL, "2");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(partnerDDL, "2");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityNameTextbox,"Automation opp name");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityDescriptionTextbox,"Automation description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(startDateCalendar,"3/2/2022");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(locationDDl,"1");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(programCapacityTextbox,"200");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(genderDDL,"2");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(minAgeTextbox,"18");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(maxAgeTextbox,"60");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(EIDTextbox,TestData.pepUserEID +"\n" +TestData.opportoionityEID);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addMemberBtn,30);
        ActionsHelper.driverWait(4000);
    }
    }
