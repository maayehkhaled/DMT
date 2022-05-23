package com.qpros.pages.web.SSA;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PEPPage extends Base {
    public PEPPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By familyFileTab= By.xpath("//a[contains(@id,'ctl04_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By sspLink=By.xpath("//a[contains(@id,'ctl03_wt71')]");
    private By firstUserEIDLink=By.xpath("//a[contains(@id,'ctl03_wtlnk_Expandview')]");
    private By secondUserEID=By.xpath("//a[contains(@id,'ctl04_wtlnk_Expandview')]");
    private By jobInfoBtn=By.xpath("//div[contains(@id,'wt9_block_wtImageWrapper')]");
    //Edit Screen
    private By editBtn=By.xpath("//input[contains(@id,'EnableEdit')]");
    private By underProcessingCheckbox=By.xpath("//input[contains(@id,'wtIsUnderRecruitment_Input')]");
    private By currentEducation=By.xpath("//select[contains(@id,'wtIsStudying_input')]");
    private By socialCareCheckBox=By.xpath("//input[contains(@id,'wtIsCaregiver_Input')]");
    private By careDDL=By.xpath("//select[contains(@id,'wtCare_Input')]");
    private By graduationMonth=By.xpath("//select[contains(@id,'wtGraduationMonth')]");
    private By graduationYear=By.xpath("//select[contains(@id,'wtGraduationYears')]");

    //
    private By deleteCurrentEducation=By.xpath("//a[contains(@id,'wt27')]");
    private By editEducationBtn=By.xpath("//a[contains(@id,'ctl03_wtlnk_EditEducationBackground')]");
    private By degreeDDL=By.xpath("//select[contains(@id,'degree')]");
    private By collegeDDL=By.xpath("//select[contains(@id,'wtddlcollege')]");
    private By deptDDL=By.xpath("//select[contains(@id,'Major')]");
    private By institutionDDL=By.xpath("//select[contains(@id,'EducationalInstitute')]");
    private By graduationAVG=By.xpath("//input[contains(@id,'Average')]");
    private By masterGraduationYear=By.xpath("//select[contains(@id,'GraduationalYear')]");
    private By saveGraduationInfo=By.xpath("//input[contains(@id,'Record')]");


    private By experienceRadioBtn=By.xpath("//input[contains(@id,'Yes_Radio3')]");
    private By joblessMonthDDL=By.xpath("//select[contains(@id,'wtMonthUnemployedsince')]");
    private By joblessYearDDL=By.xpath("//select[contains(@id,'wtYears')]");
    private By lastMonthAppJobDDL=By.xpath("//select[contains(@id,'wtMonthsLastAppliedJob')]");
    private By lastYearAppJobDDL=By.xpath("//div[@class='additional-personal-info']//div[@class='SectionExpandable_content']/div[3]//div[3]//select");
    private By ministryApproval=By.xpath("//input[contains(@id,'Yes_Radio4')]");

    //Work experience
    private By addWorkExperienceLink=By.xpath("//a[contains(@id,'AddRow')]");
    private By workExperienceTypeDDL=By.xpath("//select[contains(@id,'rows_ctl00_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp')]");
    private By secondWorkExperienceTypeDDL=By.xpath("//select[contains(@id,'rows_ctl02_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp')]");
    private By jobDescription=By.xpath("//input[contains(@id,'ctl00_wttxtSSA_PreAssessmentForm_PreviousExperience_Job_T')]");
    //alternative locator //input[@class='OSFillParent Mandatory SmartInput InEditMode ReadOnly']
    private By secondJobDescription=By.xpath("//input[contains(@id,'ctl02_wttxtSSA_PreAssessmentForm_PreviousExperience_Job_T')]");
    private By workLocation=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_Emplo')]");
    private By secondWorkLocation=By.xpath("//input[contains(@id,'ctl02_wttxt_SSA_PreAssessmentForm_PreviousExperience_Emplo')]");
    private By yearsOfExperience=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_YearO')]");
    private By secondYearsOfExperience=By.xpath("//input[contains(@id,'ctl02_wttxt_SSA_PreAssessmentForm_PreviousExperience_YearO')]");
    private By monthsOfExperience=By.xpath("//input[contains(@id,'ctl00_wttxt_SSA_PreAssessmentForm_PreviousExperience_Month')]");
    private By secondMonthsOfExperience=By.xpath("//input[contains(@id,'ctl02_wttxt_SSA_PreAssessmentForm_PreviousExperience_Month')]");
    private By countryDDL=By.xpath("//select[contains(@id,'ctl00_wtddl_SSA_PreAssessmentForm_PreviousExperience_Count')]");
    private By secondCountryDDL=By.xpath("//select[contains(@id,'ctl02_wtddl_SSA_PreAssessmentForm_PreviousExperience_Count')]");
    private By saveJobExperience=By.xpath("//a[@class='SaveRowAction']");
    private By moreInfoTextarea=By.xpath("//textarea[contains(@id,'Input')]");
    //delete this first
    private By deleteFirstExperience=By.xpath("//span[contains(@id,'ctl00_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp-container')]");
    private By deleteBtn=By.xpath("//a[@class='DeleteRowAction']");
    private By deleteSecondExperience=By.xpath("//span[contains(@id,'ctl02_wtSSA_PreAssessmentForm_PreviousExperience_TypeOfExp-container')]");

    //More Info
    private By lengthTextbox=By.cssSelector("[placeholder='ادخل الطول بالسنتيمتر']");
    private By weightTextbox=By.cssSelector("[placeholder='ادخل الوزن بالكيلوغرام']");
    private By nationalService=By.id("InternalPortalTheme_wt24_block_wtMainContent_wt14_CloneOfWebPatterns_wtTabs_block_wtContent6_wt28_wtAdditionPersonalInfo_WebPatterns_wt326_block_wtContent_wtNationalService_I_Finished");
    private By driveLicenseRadioBtn=By.xpath("//input[contains(@id,'wtDriverLicense_Yes_Radio')]");
    private By driveLicenseTypeDDL=By.xpath("//select[contains(@id,'type')]");
    private By driveLicenseSaveBtn=By.xpath("//a[contains(@id,'saveBtn')]");
    private By englishCertificate=By.xpath("//select[contains(@id,'wtEnglishLangCert_input')]");
    private By multiLangDDL=By.xpath("//button[@class='ms-choice']");
    private By saveEditedCareerInfo=By.xpath("//input[contains(@id,'wtSubmitButton')]");
    private By acceptBtn=By.xpath("//input[contains(@id,'wtButton1_wt15')]");
    private By arabicLang=By.xpath("//input[@value='25']");
    private By athyobLang=By.xpath("//input[@value='59']");
    private By aramLang=By.xpath("//input[@value='56']");

    public void openJobInfo() {
        logManager.STEP("Open job information المعلومات المهنية", "The user will open job information section for specific user");
        ActionsHelper.retryClick(familyFileTab, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink, 30);
    }

    public void clickOnFirstEID(){
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(firstUserEIDLink,30);
        ActionsHelper.driverWait(2000);
    }

    public void clickEditJobInfo(){
        logManager.STEP("Click Edit تحرير استمارة التقييم المسبق","The user click on Edit button");
        ActionsHelper.retryClick(jobInfoBtn,30);
        ActionsHelper.retryClick(editBtn,30);
        ActionsHelper.driverWait(2000);
    }

    public void editJobStatus(){
    logManager.STEP("Edit Job Status الحالة الوظيفية","The user Edit all enabled info in Job Status section");
    if(driver.get().findElement(underProcessingCheckbox).isSelected()){

    }
    ActionsHelper.retryClick(underProcessingCheckbox,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(currentEducation,StaticValues.enforcementLogType);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(graduationMonth, StaticValues.graduationMonthOption);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(graduationYear,StaticValues.graduationYearOption);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(socialCareCheckBox,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.selectOption(careDDL,StaticValues.enforcementLogType);
    ActionsHelper.driverWait(2000);
    }

    public void newQualification() {
        ActionsHelper.selectOption(degreeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(collegeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(deptDDL, StaticValues.csDept);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(graduationAVG, StaticValues.masterDegree);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(institutionDDL, StaticValues.csDept);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(masterGraduationYear, StaticValues.masterGraduation);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveGraduationInfo, 30);
        ActionsHelper.driverWait(2000);
    }

    public void editQualificationInfo(){
        logManager.STEP("Edit Qualification Info بيانات المؤهلات المنجزة","The user will choose Master Degree from Qualification section");
        //ActionsHelper.retryClick(editEducationBtn,30);
        //to cancel previous edu
        if((driver.get().findElement(deleteCurrentEducation)).isEnabled()){
            ActionsHelper.retryClick(deleteCurrentEducation,30);
            ActionsHelper.driverWait(3000);
            newQualification();
        }
        else{
            newQualification();
        }
    }

    public void addNewExperience(){
        logManager.STEP("Edit Experience Info الخبرات الوظيفية", "The user Edit all enabled info in Experience section");
        ActionsHelper.retryClick(experienceRadioBtn, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessMonthDDL, StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(joblessYearDDL, StaticValues.joblessY);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastMonthAppJobDDL, StaticValues.joblessJan);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(lastYearAppJobDDL, StaticValues.graduationYearOption);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(ministryApproval, 30);
        ActionsHelper.driverWait(2000);
    }

    public void editExperienceJobInfo() {
        //if(driver.get().findElement(experienceRadioBtn).isSelected()){
        if(driver.get().findElement(deleteFirstExperience).isDisplayed()) {
            ActionsHelper.retryClick(deleteFirstExperience, 30);
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(deleteBtn, 30);
            ActionsHelper.driverWait(4000);
        }
            else if(driver.get().findElement(deleteSecondExperience).isDisplayed()){
            ActionsHelper.retryClick(deleteSecondExperience,30);
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(deleteBtn,30);
            ActionsHelper.driverWait(4000);
        }
        addNewExperience();

        //method
    }

    public void addFirstWorkExperience(){
        logManager.STEP("Add First Experience Info", "The user add first work experience");
        ActionsHelper.retryClick(addWorkExperienceLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(workExperienceTypeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(jobDescription, "Software Quality Engineer");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(workLocation, "Q-Pros");
        ActionsHelper.driverWait(4000);
        /*ActionsHelper.sendKeysWithClear(yearsOfExperience, StaticValues.graduationMonthOption);
        ActionsHelper.driverWait(3000);*/
        ActionsHelper.sendKeysWithClear(monthsOfExperience, StaticValues.masterDegree);
        ActionsHelper.driverWait(3000);
        ActionsHelper.selectOption(countryDDL, StaticValues.countryPalestine);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(saveJobExperience, 30);
        ActionsHelper.driverWait(3000);
    }
    public void addSecondWorkExperience(){
        logManager.STEP("Add Second Experience Info", "The user add second work experience");
        ActionsHelper.retryClick(addWorkExperienceLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(secondWorkExperienceTypeDDL, StaticValues.masterDegree);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(secondJobDescription, "Software Quality Manager");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(secondWorkLocation, "Second Q-Pros");
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(secondYearsOfExperience, StaticValues.graduationMonthOption);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeysWithClear(secondMonthsOfExperience, StaticValues.masterDegree);
        ActionsHelper.driverWait(3000);
        ActionsHelper.selectOption(secondCountryDDL, StaticValues.secondCountryEgypt);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(saveJobExperience, 30);
        ActionsHelper.driverWait(3000);
    }

    public void editMoreInfo(){
        logManager.STEP("Edit More Info About The User","The user Edit all enabled info in More Info section");
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeysWithClear(lengthTextbox, StaticValues.length);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(weightTextbox, StaticValues.weight);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(nationalService,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driveLicenseRadioBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.selectOption(driveLicenseTypeDDL,StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(driveLicenseSaveBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(englishCertificate,StaticValues.IELTS);
        ActionsHelper.driverWait(4000);
        //Other Languages DDL
        ActionsHelper.retryClick(multiLangDDL,30);
        ActionsHelper.scrollTo(arabicLang);
        ActionsHelper.retryClick(arabicLang,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(athyobLang,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(aramLang);
        ActionsHelper.retryClick(aramLang,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeysWithClear(moreInfoTextarea,"Test Automation");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveEditedCareerInfo,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.retryClick(acceptBtn,30);
        ActionsHelper.driverWait(6000);
    }

    public void openCreatedRequest(){
        ActionsHelper.driverWait(6000);
        ActionsHelper.scrollTo(jobInfoBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick(" التفاصيل المهنية",jobInfoBtn);
        ActionsHelper.driverWait(6000);
        ActionsHelper.scrollTo(graduationYear);
        ActionsHelper.driverWait(4000);
        logManager.INFO("الحالة الوظيفية",false);
        ActionsHelper.scrollTo(experienceRadioBtn);
        ActionsHelper.driverWait(4000);
        logManager.INFO("المؤهلات",false);
        ActionsHelper.scrollTo(ministryApproval);
        ActionsHelper.driverWait(4000);
        logManager.INFO("الخبرات",false);
        ActionsHelper.scrollTo(lengthTextbox);
        ActionsHelper.driverWait(4000);
        logManager.INFO("المعلومات الأخرى",false);
    }
}
