package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By editEducationBtn=By.xpath("//a[contains(@id,'ctl03_wtlnk_EditEducationBackground')]");

    private By collegeDDL=By.xpath("//select[contains(@id,'wtddlcollege')]");
    private By deptDDL=By.xpath("//select[contains(@id,'Major')]");
    private By graduationAVG=By.xpath("//input[contains(@id,'Average')]");
    private By masterGraduationYear=By.xpath("//select[contains(@id,'GraduationalYear')]");
    private By saveGraduationInfo=By.xpath("//input[contains(@id,'Record')]");

    private By degreeDDL=By.xpath("//select[contains(@id,'degree')]");
    private By experienceRadioBtn=By.xpath("//input[contains(@id,'Yes_Radio3')]");
    private By joblessMonthDDL=By.xpath("//select[contains(@id,'wtMonthUnemployedsince')]");
    private By joblessYearDDL=By.xpath("//select[contains(@id,'wtYears')]");
    private By lastMonthAppJobDDL=By.xpath("//select[contains(@id,'wtMonthsLastAppliedJob')]");
    private By lastYearAppJobDDL=By.id("InternalPortalTheme_wt24_block_wtMainContent_wt14_CloneOfWebPatterns_wtTabs_block_wtContent6_wt28_wtAdditionPersonalInfo_WebPatterns_wt201_block_wtContent_wtYear");
    private By ministryApproval=By.xpath("//input[contains(@id,'Yes_Radio4')]");

    //Work experience
    private By addWorkExperienceLink=By.xpath("//a[contains(@id,'AddRow')]");
    private By workExperienceTypeDDL=By.xpath("//select[contains(@id,'rows_ctl00_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp')]");
    private By secondWorkExperienceTypeDDL=By.xpath("//select[contains(@id,'rows_ctl02_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp')]");
    private By jobDescription=By.xpath("//input[contains(@id,'ctl00_wttxtSSA_PreAssessmentForm_PreviousExperience_Job_T')]");
    private By secondJobDescription=By.xpath("//input[contains(@id,'ctl02_wttxtSSA_PreAssessmentForm_PreviousExperience_Job_T')]");
    private By workLocation=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_Emplo')]");
    private By secondWorkLocation=By.xpath("//input[contains(@id,'ctl02_wttxt_SSA_PreAssessmentForm_PreviousExperience_Emplo')]");
    private By yearsOfExperience=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_YearO')]");
    private By secondYearsOfExperience=By.xpath("//input[contains(@id,'ctl02_wttxt_SSA_PreAssessmentForm_PreviousExperience_YearO')]");
    private By monthsOfExperience=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_Month')]");
    private By secondMonthsOfExperience=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_Month')]");
    private By countryDDL=By.xpath("//select[contains(@id,'ctl00_wtddl_SSA_PreAssessmentForm_PreviousExperience_Count')]");
    private By secondCountryDDL=By.xpath("//select[contains(@id,'ctl02_wtddl_SSA_PreAssessmentForm_PreviousExperience_Count')]");
    private By saveJobExperience=By.xpath("//a[@class='SaveRowAction']");
    private By moreInfoTextarea=By.xpath("//textarea[contains(@id,'Input')]");
    //More Info
    private By lengthTextbox=By.xpath("//input[contains(@id,'wt153')]");
    private By weightTextbox=By.xpath("//input[contains(@id,'wt98')]");
    private By driveLicenseRadioBtn=By.xpath("//input[contains(@id,'wtDriverLicense_Yes_Radio')]");
    private By driveLicenseTypeDDL=By.xpath("//select[contains(@id,'type')]");
    private By driveLicenseSaveBtn=By.xpath("//a[contains(@id,'saveBtn')]");
    private By englishCertificate=By.xpath("//select[contains(@id,'wtEnglishLangCert_input')]");
    private By multiLangDDL=By.xpath("//button[@class='ms-choice']");
    private By saveEditedCareerInfo=By.xpath("//input[contains(@id,'wtSubmitButton')]");


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
    }

    public void editJobStatus(){
    logManager.STEP("Edit Job Status","The user Edit all enabled info in Job Status section");
    ActionsHelper.retryClick(underProcessingCheckbox,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(graduationMonth, StaticValues.graduationMonthOption);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(graduationYear,StaticValues.graduationYearOption);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(degreeDDL,StaticValues.masterDegree);
    ActionsHelper.driverWait(2000);
    }

    public void editQualificationInfo(){
        logManager.STEP("Edit Qualification Info","The user will choose Master Degree from Qualification section");
        ActionsHelper.retryClick(editEducationBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(degreeDDL,StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(collegeDDL,StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(deptDDL,StaticValues.csDept);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(graduationAVG,"4");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(masterGraduationYear,StaticValues.masterGraduation);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveGraduationInfo,30);
    }

    public void editExperienceJobInfo() {
        logManager.STEP("Edit Experience Info", "The user Edit all enabled info in Experience section");
        ActionsHelper.retryClick(experienceRadioBtn, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessMonthDDL, StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessYearDDL, StaticValues.joblessY);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastMonthAppJobDDL, StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastYearAppJobDDL, StaticValues.joblessY);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(ministryApproval, 30);
        ActionsHelper.driverWait(2000);
    }

    public void addFirstWorkExperience(){
        logManager.STEP("Add First Experience Info", "The user add first work experience");
        ActionsHelper.retryClick(addWorkExperienceLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(workExperienceTypeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(jobDescription, "Software Quality Engineer");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(workLocation, "Q-Pros");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(yearsOfExperience, "5");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(monthsOfExperience, "3");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(countryDDL, StaticValues.countryPalestine);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveJobExperience, 30);
        ActionsHelper.driverWait(2000);
    }
    public void addSecondWorkExperience(){
        logManager.STEP("Add Second Experience Info", "The user add second work experience");
        ActionsHelper.retryClick(addWorkExperienceLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(secondWorkExperienceTypeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(secondJobDescription, "Software Quality Manager");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(secondWorkLocation, "Second Q-Pros");
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(secondYearsOfExperience, "20");
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeys(secondMonthsOfExperience, "7");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(secondCountryDDL, StaticValues.secondCountryEgypt);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveJobExperience, 30);
        ActionsHelper.driverWait(2000);
    }
    public void editMoreInfo(){
        logManager.STEP("Edit More Info About The User","The user Edit all enabled info in More Info section");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(lengthTextbox,"80");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(weightTextbox,"180");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driveLicenseRadioBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(driveLicenseTypeDDL,StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driveLicenseSaveBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(englishCertificate,StaticValues.IELTS);
        ActionsHelper.driverWait(4000);
        //Other Languages DDL
        ActionsHelper.retryClick(multiLangDDL,30);
        ActionsHelper.scrollTo(By.xpath("//input[@value='25']"));
        ActionsHelper.retryClick(By.xpath("//input[@value='25']"),30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.xpath("//input[@value='59']"),30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(By.xpath("//input[@value='56']"));
        ActionsHelper.retryClick(By.xpath("//input[@value='56']"),30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(moreInfoTextarea,"Test Automation");
        ActionsHelper.driverWait(2000);
        //ActionsHelper.retryClick(saveEditedCareerInfo,30);
        ActionsHelper.driverWait(10000);
    }
}
