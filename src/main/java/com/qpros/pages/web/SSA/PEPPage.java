package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PEPPage extends Base {
    public PEPPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By familyFileTab= By.xpath("//a[contains(@id,'ctl04_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By sspLink=By.xpath("//a[contains(@id,'ctl03_wt71')]");
    private By userEIDLink=By.xpath("//a[contains(@id,'ctl03_wtlnk_Expandview')]");
    private By jobInfoBtn=By.xpath("//div[contains(@id,'wt9_block_wtImageWrapper')]");
    //Edit Screen
    private By editBtn=By.xpath("//input[contains(@id,'EnableEdit')]");
    private By underProcessingCheckbox=By.xpath("//input[contains(@id,'wtIsUnderRecruitment_Input')]");
    private By graduationMonth=By.xpath("//select[contains(@id,'wtGraduationMonth')]");
    private By graduationYear=By.xpath("//select[contains(@id,'wtGraduationYears')]");
    private By degreeDDL=By.xpath("//select[contains(@id,'degree')]");
    private By experienceRadioBtn=By.xpath("//input[contains(@id,'Yes_Radio3')]");
    private By joblessMonthDDL=By.xpath("//select[contains(@id,'wtMonthUnemployedsince')]");
    private By joblessYearDDL=By.xpath("//select[contains(@id,'wtYears')]");
    private By lastMonthAppJobDDL=By.xpath("//select[contains(@id,'wtMonthsLastAppliedJob')]");
    private By lastYearAppJobDDL=By.id("InternalPortalTheme_wt24_block_wtMainContent_wt14_CloneOfWebPatterns_wtTabs_block_wtContent6_wt28_wtAdditionPersonalInfo_WebPatterns_wt201_block_wtContent_wtYear");
    private By ministryApproval=By.xpath("//input[contains(@id,'Yes_Radio4')]");
    private By lengthTextbox=By.xpath("//input[contains(@id,'wt153')]");
    private By weightTextbox=By.xpath("//input[contains(@id,'wt98')]");
    private By driveLicenseRadioBtn=By.xpath("//input[contains(@id,'wtDriverLicense_Yes_Radio')]");
    private By englishCertificate=By.xpath("//select[contains(@id,'wtEnglishLangCert_input')]");
    private By multiLangDDL=By.xpath("//select[contains(@id,'wtOtherLanguage_input')]");

    public void openJobInfo(){
        logManager.STEP("Open job information","The user will open job information section for specific user");
        ActionsHelper.retryClick(familyFileTab,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(userEIDLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(jobInfoBtn,30);
    }

    public void editJobInfo(){
        logManager.STEP("Click Edit","The user click on Edit button");
        ActionsHelper.retryClick(editBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(underProcessingCheckbox,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(graduationMonth, StaticValues.graduationMonthOption);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(graduationYear,StaticValues.graduationYearOption);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(degreeDDL,StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
    }

    public void experienceJobInfo(){
        ActionsHelper.retryClick(experienceRadioBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessMonthDDL,StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessYearDDL,StaticValues.joblessY);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastMonthAppJobDDL,StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastYearAppJobDDL,StaticValues.joblessY);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(ministryApproval,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(lengthTextbox,"80");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(weightTextbox,"180");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driveLicenseRadioBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(englishCertificate,StaticValues.IELTS);
        ActionsHelper.driverWait(2000);

        Select select = new Select(driver.get().findElement(multiLangDDL));
        select.getOptions();

    }
}
