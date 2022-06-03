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
    Faker faker=new Faker();
    LocalDateTime datetime1 = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    AgentPage agentPage = new AgentPage(driver.get());
    public String opportunityName="Automation opp name " +faker.lorem().sentence(1);
    String formatDateTime = datetime1.format(format);

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
    private By successMsg=By.className("Feedback_Message_Success");
    private By EIDTextbox=By.xpath("//textarea");
    private By addMemberBtn=By.xpath("//input[contains(@id,'AddIndividual')]");
    private By thirdPage=By.xpath("//a[text()='3']");
    private By footer=By.xpath("//div[@class='Footer']");
    private By planFooter=By.xpath("//div[contains(@class,'Panel_footer')]");

    private By familyFile=By.xpath("//a[.='ملف الأسرة']");
    private By eidTextBox=By.xpath("//input[contains(@id,'EmiratesId')]");
    private By sspLink=By.xpath("//a[contains(@id,'wt71')]");
    private By eidLink=By.xpath("//a[contains(@id,'Expandview')]");
    private By tamkeenBtn=By.xpath("//div[contains(@id,'wt133_block_wtImageWrapper')]");
    private By termsLink=By.xpath("//a[contains(@id,'ViewTermsAndConditions')]");
    private By viewBtn=By.xpath("//span[contains(text(),'مقبول - تم استلام المستند')]/following::div[4]");

    private By editFirstTimeBtn;
    private By scrollUpToTable=By.xpath("//a[.='شروط ومعايير برامج التمكين']");
    private By editSecondTimeBtn=By.xpath("//a[contains(@id,'wtOpportunityActions18')]");
    private By opportunityStatusDDL=By.xpath("//select[contains(@id,'DDOpportunityStatus')]");
    private By communicationDDL=By.xpath("//select[contains(@id,'DDSourceofCommunication')]");
    private By commentTextarea=By.xpath("//textarea[contains(@id,'Comment')]");
    private By smsTextarea=By.xpath("//textarea[contains(@id,'Sms')]");
    private By editPartnerDDL=By.xpath("//select[contains(@id,'FeedbackCategory')]");
    private By partnerComment=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By viewDetailsBtn=By.xpath("//div[contains(@id,'ctl38_WebPatterns_wt133_block_wtSectionExpandableArea')]/div/div[2]/span");
    private By noAnswerMsg=By.xpath("//textarea[contains(@id,'wttxt_Sms')]");
    private By suggestedTask=By.xpath("//*[contains(text(),'موصى به')]");
    private By rowsCount=By.xpath("//div[contains(@class,'flexline')]");
    private By opportunityNameTextBox=By.xpath("//input[contains(@id,'OpportunityName')]");
    private By searchBtn=By.xpath("//input[contains(@id,'wtbtn_Search')]");

    public void clickAddOpportunity() {
        faker=new Faker();
        logManager.STEP("Add New Opportunities and recommend it to EID","The user adds tow Opportunities and recommend it to EID");
        //ActionsHelper.retryClick(addOpportunityBtn, 30);
        ActionsHelper.scrollupTo(driver.get().findElement(addOpportunityBtn));
        ActionsHelper.actionClickStepClick("*Click on add opportunities",addOpportunityBtn);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityTypyDDL, StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(partnerDDL, StaticValues.partner);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityNameTextbox,opportunityName);
        ActionsHelper.driverWait(2000);

        FileUtils.createFile("OpportunityFile.txt", opportunityName);

        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityDescriptionTextbox,"Automation description " +faker.lorem().sentence(2));
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(locationDDl,"1");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(programCapacityTextbox,"200");
        ActionsHelper.driverWait(2000);
        System.out.println(opportunityName);
        ActionsHelper.selectOption(genderDDL,StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(minAgeTextbox, StaticValues.minAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(maxAgeTextbox, StaticValues.maxAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("Click on Save ",saveBtn);
        ActionsHelper.driverWait(4000);
        logManager.INFO("Opportunity created successfully",false);
        ActionsHelper.sendKeysWithClear(EIDTextbox,TestData.opportunityEID + Keys.ENTER+ TestData.secondOpportunityEID);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addMemberBtn,30);
        ActionsHelper.driverWait(10000);
    }

    public void createdRequest(){
        logManager.STEP("Open the created opportunity","");
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeys(opportunityNameTextBox,opportunityName);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(searchBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(footer);
        logManager.INFO("Created opportunity",false);
    }

    public void openCreatedOpportunity(){
        logManager.STEP("Open the first created opportunity","");
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(familyFile,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eidTextBox,TestData.opportunityEID + Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(eidLink,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(tamkeenBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollupTo(driver.get().findElement(termsLink));
        ActionsHelper.driverWait(2000);
        logManager.INFO("Created Opportunity after assigning",false);
    }
    public void openSecondCreatedOpportunity(){
        logManager.STEP("Open the second created opportunity","");
        ActionsHelper.driverWait(4000);
        ActionsHelper.retryClick(familyFile,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eidTextBox,TestData.secondOpportunityEID + Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(sspLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(eidLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(tamkeenBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollupTo(driver.get().findElement(termsLink));
        ActionsHelper.driverWait(2000);
        agentPage.logOut2();
        ActionsHelper.driverWait(2000);
        logManager.INFO("logout screenshot",false);
    }

    /**
     *Select مقبول  from تحديد الاجراء
     * Select طؤسقة التواصل  e.x مناقشة هاتفيه
     * Insert تعليق والرساله
     */
    public void firstTimeEdit(){
        logManager.STEP("First Edit For The Opportunity","The user click edit to manage opportunity ");
        ActionsHelper.scrollTo(scrollUpToTable);
        List<WebElement> listTableElements = driver.get().findElements(rowsCount); //rows K
        WebElement lastOpportunityName = driver.get().findElement(By.cssSelector("span:nth-of-type(" + listTableElements.size() + ") .flexline > div:nth-of-type(1) > .incomelabel1")); //opportunity name
        WebElement editOpportunity=driver.get().findElement(By.cssSelector("span:nth-of-type(" + listTableElements.size() + ") .flexline .ThemeGrid_Width1"));
        WebElement viewOpportunity=driver.get().findElement(By.cssSelector("span:nth-of-type(" + listTableElements.size() + ") .Heading2>.fa"));
        editOpportunity.click();
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityStatusDDL, StaticValues.acceptOppStatus);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(communicationDDL, StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(commentTextarea, "Automation comment....");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(smsTextarea, "Automation MSG..");
        ActionsHelper.driverWait(2000);
        logManager.INFO("Click Save",false);
        ActionsHelper.retryClick(saveBtn, 30);
        ActionsHelper.driverWait(5000);
        ActionsHelper.retryClick(viewOpportunity, 30);
        ActionsHelper.driverWait(2000);
        logManager.INFO("Open After Edit",false);
    }

    public void secondTimeEdit(){
        logManager.STEP("Second Edit For The Opportunity","The user click edit for the second time to complete editing");
        ActionsHelper.scrollTo(scrollUpToTable);
        List<WebElement> listTableElements = driver.get().findElements(rowsCount); //rows K
        WebElement editOpportunity=driver.get().findElement(By.cssSelector("span:nth-of-type(" + listTableElements.size() + ") .flexline .ThemeGrid_Width1"));
        editOpportunity.click();
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.scrollTo(editPartnerDDL);
        ActionsHelper.selectByValue(driver.get().findElement(editPartnerDDL),StaticValues.joblessJan);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(partnerComment,"Partner Comment 24/2");
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(saveBtn);
        ActionsHelper.driverWait(2000);
        logManager.INFO("Click Save",false);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(2000);
        logManager.INFO("Open After Edit",false);
        ActionsHelper.driverWait(4000);
    }

    public void editStatusOnly(){
        logManager.STEP("Edit Opportunity Status","The user click edit to edit opportunity status ");
        ActionsHelper.scrollTo(scrollUpToTable);
        List<WebElement> listTableElements = driver.get().findElements(rowsCount); //rows K
        By editOpportunity=By.cssSelector("span:nth-of-type(" + listTableElements.size() + ") .flexline .ThemeGrid_Width1");
        ActionsHelper.actionClickStepClick("Click edit button",editOpportunity);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityStatusDDL,StaticValues.noAnswerOppStatus);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(commentTextarea,"Automation comment no attachments abc");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(noAnswerMsg,"No attachments till now please call us123 abc");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(4000);
    }
}

