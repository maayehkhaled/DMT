package com.qpros.pages.web.SSA;

import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.qpros.helpers.FileUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class ActivationOpportunitiesPage extends Base {
    public ActivationOpportunitiesPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Faker faker = new Faker();
    LocalDateTime datetime1 = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    AgentPage agentPage = new AgentPage(driver.get());
    public static String opportunityName;
    String formatDateTime = datetime1.format(format);

    private By addOpportunityBtn = By.xpath("//input[contains(@id,'AddOpportunity')]");
    private By opportunityTypyDDL = By.xpath("//select[contains(@id,'wtddl_Opportunity')]"); //2
    private By partnerDDL = By.xpath("//select[contains(@id,'wtddl_Partner')]");//2
    private By opportunityNameTextbox = By.xpath("//input[contains(@id,'name')]");
    private By opportunityDescriptionTextbox = By.xpath("//input[contains(@id,'DescriptionInput')]");
    private By startDateCalendar = By.xpath("//input[contains(@id,'wtDateandCity_block_wtColumn1_wtdt_SDate')]");
    private By locationDDl = By.xpath("//select[contains(@id,'City')]");
    private By programCapacityTextbox = By.xpath("//input[contains(@id,'Capacity2')]");
    private By genderDDL = By.xpath("//select[contains(@id,'Genderip')]");
    private By minAgeTextbox = By.xpath("//input[contains(@id,'wtMinAge_Input')]");
    private By maxAgeTextbox = By.xpath("//input[contains(@id,'maxage3')]");
    private By saveBtn = By.xpath("//input[contains(@id,'Save')]");
    private By successMsg = By.className("Feedback_Message_Success");
    private By EIDTextbox = By.xpath("//textarea");
    private By addMemberBtn = By.xpath("//input[contains(@id,'AddIndividual')]");
    private By thirdPage = By.xpath("//a[text()='3']");
    private By footer = By.xpath("//div[@class='Footer']");
    private By planFooter = By.xpath("//div[contains(@class,'Panel_footer')]");

    private By familyFile = By.xpath("//a[.='ملف الأسرة']");
    private By eidTextBox = By.xpath("//input[contains(@id,'EmiratesId')]");
    private By sspLink = By.xpath("//a[contains(@id,'wt71')]");
    private By eidLink = By.xpath("//a[contains(@id,'Expandview')]");
    private By tamkeenBtn = By.xpath("//div[contains(@id,'wt133_block_wtImageWrapper')]");
    private By termsLink = By.xpath("//a[contains(@id,'ViewTermsAndConditions')]");
    //combine
    //private By viewBtn=By.xpath("//div[@class='PH Tabs__content active']//span[@class='ListRecords']/span[1]//span[@class='fa fa-fw fa-angle-down']");
    private By viewBtn = By.xpath("//span[contains(text(),'مقبول - تم استلام المستند')]/following::div[4]");
    //
    //private By editFirstTimeBtn=By.xpath("//a[contains(@id,'ctl00_WebPatterns_wt138_block_wtTitle_wtOpportunityActions21')]");
    //private By editFirstTimeBtn=By.xpath("//span[contains(text(),'موصى به')]/following::div[2]");
    private By editFirstTimeBtn;
    private By scrollUpToTable = By.xpath("//a[.='شروط ومعايير برامج التمكين']");
    private By editSecondTimeBtn = By.xpath("//a[contains(@id,'wtOpportunityActions18')]");
    private By opportunityStatusDDL = By.xpath("//select[contains(@id,'DDOpportunityStatus')]");
    private By communicationDDL = By.xpath("//select[contains(@id,'DDSourceofCommunication')]");
    private By commentTextarea = By.xpath("//textarea[contains(@id,'Comment')]");
    private By smsTextarea = By.xpath("//textarea[contains(@id,'Sms')]");
    //private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By saveBtn2 = By.xpath("//a[contains(@id,'ctl48_WebPatterns_wt139_block_wtTitle')]");
    private By editPartnerDDL = By.xpath("//select[contains(@id,'FeedbackCategory')]");
    private By partnerComment = By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By viewDetailsBtn = By.xpath("//div[contains(@id,'ctl38_WebPatterns_wt133_block_wtSectionExpandableArea')]/div/div[2]/span");
    private By noAnswerMsg = By.xpath("//textarea[contains(@id,'wttxt_Sms')]");
    //private By suggestedTask=By.xpath("//span[contains(text(),'موصى به')]");
    private By suggestedTask = By.xpath("//*[contains(text(),'موصى به')]");
    private By rowsCount = By.xpath("//div[contains(@class,'flexline')]");


    public void clickAddOpportunity() {
        faker = new Faker();
        logManager.STEP("Add New Opportunities and recommend it to EID", "The user adds tow Opportunities and recommend it to EID");
        ActionsHelper.retryClick(addOpportunityBtn, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityTypyDDL, StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(partnerDDL, StaticValues.partner);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityNameTextbox, "Automation opp name " + faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        //get opportunity name test and save it in the file FileUtils
        opportunityName = (driver.get().findElement(opportunityNameTextbox)).getText();
        FileUtils.createFile("OpportunityFile.txt", opportunityName);

        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityDescriptionTextbox, "Automation description " + faker.lorem().sentence(2));
        ActionsHelper.driverWait(2000);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(locationDDl, "1");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(programCapacityTextbox, "200");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(genderDDL, StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(minAgeTextbox, StaticValues.minAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(maxAgeTextbox, StaticValues.maxAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn, 30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(EIDTextbox, TestData.opportunityEID + Keys.ENTER + TestData.secondOpportunityEID);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addMemberBtn, 30);
        ActionsHelper.driverWait(10000);

    }

    public void createdRequest() {
        logManager.STEP("Open the created opportunity", "");
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(footer);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(thirdPage, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(footer);
        logManager.INFO("Created opportunity", false);
    }

    public void openCreatedOpportunity() {
        //driver.get().navigate().to("https://uat.ssa.gov.ae/DCD_Activation_AgentFrontEndN/ExpandedFamilyMemberViewPage.aspx?ApplicationStatus=2&ApplicationID=1397&NameInArabic=%d8%b5%d8%a7%d9%84%d8%ad+%d8%b3%d9%8a%d9%81+%d8%ad%d9%85%d8%af+%d8%b9%d9%84%d9%89+%d8%a7%d9%84%d8%b3%d9%86%d8%a7%d9%86%d9%89&ActivityId=0&IndividualIsActive=True&EmiratesId=784197821469414&(Not.Licensed.For.Production)=");
        logManager.STEP("Open the first created opportunity", "");
        ActionsHelper.actionClickStepClick("Click on ملف الأسرة", familyFile);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eidTextBox, TestData.opportunityEID + Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink, 30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(eidLink, 30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(tamkeenBtn, 30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollupTo(driver.get().findElement(termsLink));
        ActionsHelper.driverWait(2000);
        logManager.INFO("Created Opportunity after assigning", false);
    }

    public void openSecondCreatedOpportunity() {
        logManager.STEP("Open the second created opportunity", "");
        ActionsHelper.actionClickStepClick("Click on ملف الأسرة", familyFile);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eidTextBox, TestData.secondOpportunityEID + Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(eidLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(tamkeenBtn, 30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollupTo(driver.get().findElement(termsLink));
        ActionsHelper.driverWait(2000);
        logManager.INFO("logout", false);
        agentPage.logOut2();
        ActionsHelper.driverWait(2000);
        logManager.INFO("logout screenshot", false);
    }
}
