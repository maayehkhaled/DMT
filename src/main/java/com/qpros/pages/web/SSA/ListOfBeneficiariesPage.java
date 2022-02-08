package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ListOfBeneficiariesPage extends Base {
    public ListOfBeneficiariesPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By beneficiariesListLink=By.xpath("//a[contains(@id,'ctl02_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By eidTextbox=By.xpath("//input[contains(@id,'SSPEmiratesID')]");
    private By moreFilter=By.xpath("//input[contains(@id,'MoreFilter')]");
    private By oppName=By.xpath("//select[contains(@id,'statusFilter11')]");

    public void openBeneficiariesList(){
        logManager.STEP("Open The List Of Beneficiaries","The user opens Beneficiaries page");
        ActionsHelper.retryClick(beneficiariesListLink,30);
        ActionsHelper.driverWait(2000);
    }

    public void searchBeneficiaryEID() {
        logManager.STEP("Search Beneficiary EID", "The user searches for specific Beneficiary EID");
        ActionsHelper.sendKeys(eidTextbox, TestData.pepUserEID+Keys.ENTER);
        ActionsHelper.driverWait(8000);
        ActionsHelper.sendKeys(eidTextbox, ""+Keys.ENTER);
        ActionsHelper.driverWait(8000);
        logManager.STEP("Search Beneficiary SSP", "The user searches for specific Beneficiary SSP");
        ActionsHelper.sendKeysWithClear(eidTextbox, StaticValues.SSP+Keys.ENTER);
        ActionsHelper.driverWait(8000);
        ActionsHelper.sendKeys(eidTextbox, ""+Keys.ENTER);
        ActionsHelper.driverWait(8000);
    }

    public void clickMoreFilter() {
        logManager.STEP("More Search Filter", "The user clicks on More Search to search for Opp name");
        ActionsHelper.retryClick(moreFilter,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(oppName);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(oppName,StaticValues.oppName);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(oppName,StaticValues.defaultOppName);
        ActionsHelper.driverWait(2000);
    }

}
