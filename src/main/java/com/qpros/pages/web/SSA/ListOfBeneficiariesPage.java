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

    private By beneficiariesListLink=By.xpath("//a[.='قائمة المستفيدين']");  //a[contains(@id,'ctl02_RichWidgets_wt6_block_wtMenuItem_wt11')]
    private By eidTextbox=By.xpath("//input[contains(@id,'SSPEmiratesID')]");
    private By moreFilter=By.xpath("//input[contains(@id,'MoreFilter')]");
    private By oppName=By.xpath("//div[@id='InternalPortalTheme_wt35_block_wtMainContent_OutSystemsUIWeb_wt234_block_wtContent_WebPatterns_wt55_block_wtColumn1_wtStatus13']/span/span/span");    //span[@title='اختر اسم']
    private By eidLink=By.xpath("//a[contains(@id,'ExpandedFamilyMemberViewPage')]");
    private By sspLink=By.xpath("//a[contains(@id,'wt179')]");
    ////span[text()='رقم الطلب']
    private By searchBtn=By.xpath("//input[@value='بحث']");
    private By countPersons=By.xpath("//a[contains(@id,'wtisCalculate55')]");
    private By countPersonsWFiles=By.xpath("//a[contains(@id,'wtisCalculate66')]");
    private By countFamilies=By.xpath("//a[contains(@id,'wtisCalculate33')]");
    private By countFamiliesWFiles=By.xpath("//a[contains(@id,'wtisCalculate44')]");
    private By countEmiratis=By.xpath("//a[contains(@id,'wtisCalculate22')]");
    private By countOffers=By.xpath("//a[contains(@id,'wtisCalculate11')]");

    public void countAll(){
        ActionsHelper.scrollupTo(driver.get().findElement(sspLink));
        ActionsHelper.driverWait(3000);
        ActionsHelper.clickAction(By.xpath("//span[text()='حساب أرقام كافة البطاقات']"));
        ActionsHelper.driverWait(2000);

        logManager.INFO("All calculations",false);
    }

    public void openBeneficiariesList(){
        logManager.STEP("Open The List Of Beneficiaries","The user opens Beneficiaries page");
        ActionsHelper.retryClick(beneficiariesListLink,30);
        logManager.INFO("Click on List Of Beneficiaries",Boolean.FALSE);
        ActionsHelper.driverWait(2000);
    }

    public void searchBeneficiaryEID() {
        logManager.STEP("Search Beneficiary EID", "The user searches for specific Beneficiary EID");
        ActionsHelper.sendKeys(eidTextbox, TestData.beneficiaryEID+Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(eidLink);
        logManager.INFO("Scroll to Beneficiary EID",Boolean.FALSE);
        ActionsHelper.driverWait(1000);
    }
    public void searchBeneficiarySSP() {
        logManager.STEP("Search Beneficiary SSP", "The user searches for specific Beneficiary SSP");
        driver.get().findElement(eidTextbox).clear();
        ActionsHelper.sendKeys(eidTextbox, StaticValues.SSP+Keys.ENTER);
        ActionsHelper.driverWait(6000);
        ActionsHelper.scrollTo(sspLink);
        logManager.INFO("Scroll to Beneficiary EID",Boolean.FALSE);
        ActionsHelper.driverWait(4000);
    }

    public void clickMoreFilter() {
        logManager.STEP("More Search Filter", "The user clicks on More Search to search for Opp name");
        /*ActionsHelper.retryClick(removeBtn,30);
        ActionsHelper.driverWait(4000);*/
        ActionsHelper.retryClick(moreFilter,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(By.xpath("//span[@title=\"اختر مدة آخر طلب وظيفة\"]"));
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(oppName,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeys(By.xpath("//input[@class='select2-search__field']"),"11/7 tets"+Keys.ENTER);
        ActionsHelper.driverWait(4000);
        //ActionsHelper.selectOption(oppName,StaticValues.oppName);
        ActionsHelper.driverWait(6000);
        //ActionsHelper.selectOption(oppName,StaticValues.defaultOppName);
        ActionsHelper.retryClick(searchBtn,30);
        ActionsHelper.driverWait(6000);
    }

}
