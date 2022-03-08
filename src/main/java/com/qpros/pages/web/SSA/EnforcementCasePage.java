package com.qpros.pages.web.SSA;

import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class EnforcementCasePage extends Base {
    public EnforcementCasePage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Popups PopupsPage=new Popups(driver.get());
    Faker faker=new Faker(new Random());

    private By enforcementCaseLink = By.xpath("//a[contains(@id,'ctl24_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By NewEnforcementCaseLink = By.xpath("//div[contains(@class,'Longbutton')]");

    private By emiratesIdTextbox = By.xpath("//input[contains(@id,'wtBeneficiaryData_wttxt_emiratesid')]");
    private By fetchBtn = By.xpath("//input[contains(@id,'wtButton1_wtbtn_FetchDetails')]");
    private By enforcementTypeDDL = By.xpath("//select[contains(@id,'EnforcementType')]");//select
    private By infoSourceDDL = By.xpath("//select[contains(@id,'EnforcementSource')]");//select
    private By caseDescriptionTextbox = By.xpath("//textarea[contains(@id,'wtinput_description')]");
    private By recommendedTextbox = By.xpath("//input[contains(@id,'RecommendedAction')]");
    private By justificationTextbox=By.xpath("//textarea[contains(@id,'wtinput_ActionDescription')]");
    private By actionDueDateCalendar=By.xpath("//input[contains(@id,'wtMainContent_wtActionDueDateInput')]");

    private By enforcementLogTypeDDL=By.xpath("//select[contains(@id,'EnforcementLogType2')]");
    private By logDateCalendar=By.xpath("//input[contains(@id,'wtEnforcementLogDateInput2')]");
    private By logTodayDate=By.xpath("//div[contains(@class,'pika-button')]");

    private By todayDate =By.className("pika-go-today");
    private By logDescriptionTextbox=By.xpath("//textarea[contains(@id,'EnforcementLogDescription2')]");
    //Log Description Automation
    private By uploadFile=By.xpath("//div[contains(@class,'emptyMargin')]");
    private By fileDescription=By.id("InternalPortalTheme_wt107_block_wtMainContent_wtCacheFiles_ctl00_wttxt_Comment");
    private By createCaseBtn = By.xpath("//input[contains(@id,'wtButton1_wtbtnCreateCase')]");

    private By searchRequestLink=By.xpath("//div[contains(@id,'wtCont_searchsection')]");
    private By eIdTexebox=By.xpath("//input[contains(@id,'SSPEmiratesID')]");
    private By searchBtn=By.xpath("//input[contains(@id,'wtbtn_Search')]");
    private By editCaseBtn=By.xpath("//a[contains(@id,'ctl03_wtlnk_editcase')]");
    private By saveChanges=By.xpath("//input[contains(@id,'wtbtnCreateCase')]");
    private By successMsg=By.xpath("//span[@class='Feedback_Message_Text']");

    //Add action Locators
    private By caseID=By.xpath("//a[contains(@id,'ctl03_wtlnk_CaseId')]");
    private By addActionBtn=By.xpath("//a[contains(@id,'AddAction')]");
    private By actionAssignedToDDl=By.xpath("//select[contains(@id,'AssignedTo')]") ;
    private By actionDueDate=By.xpath("//input[contains(@id,'wtDueDateToInput')]");

    private By actionTodayBtn=By.xpath("//button[(@class='pika-go-today')]");
    private By actionDescriptionTextbox=By.xpath("//textarea[contains(@id,'ActionDescription')]");
    private By saveActionBtn=By.xpath("//input[contains(@id,'wtbtnCreateAction')]");
    //Edit Action Locators
    private By editActionBtn=By.xpath("//a[@id='InternalPortalTheme_wt251_block_wtMainContent_wtActionTable_ctl03_wtlnk_editaction']");
    private By agreeRadioBtn=By.xpath("//input[contains(@id,'wt12')]");
    private By saveEditAction=By.xpath("//input[contains(@id,'wtbtnCreateAction')]");
    //Delete Action Locators
    private By deleteActionBtn=By.xpath("//a[contains(@id,'ctl03_wt679')]");
    private By deletedRecord=By.xpath("//td[contains(@id,'ctl04_wtActionTableRow1')]");
    private By actionTable=By.xpath("//table[contains(@id,'wtActionTable')]");
    //Add Logs Locators
    private By addLogLink=By.xpath("//a[contains(@id,'AddLog')]");
    private By logTypeDDL=By.xpath("//select[contains(@id,'LogType')]");
    private By logDescriptionText=By.xpath("//textarea[contains(@id,'LogDescription')]");
    private By actionNumber=By.xpath("//select[contains(@id,'ActionId')]");
    private By saveLogBtn=By.xpath("//input[contains(@id,'wtbtnCreateLog')]");
    //Delete Logs Locators
    private By  deleteLogBtn=By.xpath("//a[contains(@id,'ctl03_wt146')]");

    public void deleteLogs(){
        logManager.STEP("Delete Enforcement Case Log","The User Will Delete The Created Log For Existent Enforcement Case");
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(deleteLogBtn,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(2000);
    }
    public void addLogs(){
        logManager.STEP("Add Enforcement Case Log","The User Will Add New Log For Existent Enforcement Case");
        faker=new Faker();
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addLogLink,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(logTypeDDL,StaticValues.enforcementLogType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(logDescriptionText,"Automation Log Description"+faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(actionTodayBtn,StaticValues.actionToday);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveLogBtn,30);
        ActionsHelper.driverWait(2000);
    }
    public void deleteAction() {
        logManager.STEP("Delete The Created Action","The User Will Delete The Action Created Before For Existent Enforcement Case");
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(deleteActionBtn,30);
        ActionsHelper.driverWait(6000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(5000);
    }
    public int checkDeletedTableSize(){
        logManager.STEP("Check the table size","Check the table size after delete record");
        WebElement actionsTable = driver.get().findElement(actionTable);
        List<WebElement> tableRows = actionsTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public void editAction() {
        logManager.STEP("Edit Action To The Enforcement Case","The user will click on edit icon for existent enforcement case to mark this action as completed");
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(editActionBtn, 30);
        ActionsHelper.driverWait(8000);
        driver.get().switchTo().frame(0);
        ActionsHelper.retryClick(agreeRadioBtn, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveActionBtn, 30);
        ActionsHelper.driverWait(2000);
    }

    /**
     * 2.	Click on Case ID
     * 3.	Click on Add action
     * 4.	Select Action Assigned To
     * 5.	Select Action Due Date
     * 6.	Add Action Description, text: action automation new
     * 7.	Click on Add Action button
     * 8.	Successful MSG should be displayed: Action created successfully
     */
    public void addAction(){
        logManager.STEP("Add new action","The user will add new action for the created enforcement");
        faker=new Faker();
        ActionsHelper.retryClick(caseID,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(addActionBtn,30);
    ActionsHelper.driverWait(2000);
    driver.get().switchTo().frame(0);
    ActionsHelper.selectOption(actionAssignedToDDl, StaticValues.actionAssignedAutomationOption);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(actionDueDate,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(actionTodayBtn,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.sendKeys(actionDescriptionTextbox,"Action Description " +faker.lorem().sentence(1));
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(saveActionBtn,30);
    ActionsHelper.driverWait(2000);
    }

    /**
     * To open existing enforcement case
     */
    public void openEnforcementCase(){
        logManager.STEP("open existing enforcement case","The user will open existing enforcement case");
        ActionsHelper.retryClick(enforcementCaseLink, 30);
        ActionsHelper.driverWait(8000);
    }

    /**
     * To create new enforcement case
     */
    public void createEnforcementCase() {
        logManager.STEP("create new enforcement case","The user will create new enforcement case");
        faker=new Faker();
        ActionsHelper.retryClick(NewEnforcementCaseLink, 30);
        ActionsHelper.driverWait(2000);

        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(8000);

        ActionsHelper.sendKeys(emiratesIdTextbox, TestData.enforcementCaseEID);
        ActionsHelper.retryClick(fetchBtn,30);
        ActionsHelper.driverWait(8000);

        ActionsHelper.scrollTo(enforcementTypeDDL);
        ActionsHelper.driverWait(8000);

        ActionsHelper.selectOption(enforcementTypeDDL,StaticValues.enforcementType);
        ActionsHelper.driverWait(2000);

        ActionsHelper.selectOption(infoSourceDDL,StaticValues.infoSource);
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeys(caseDescriptionTextbox,"Case Description "+faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(recommendedTextbox,"Recommended "+faker.lorem().sentence(2));
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(justificationTextbox,"Justification "+faker.lorem().sentence(3));
        ActionsHelper.driverWait(2000);

        ActionsHelper.retryClick(actionDueDateCalendar,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(todayDate,30);
        ActionsHelper.driverWait(2000);

        ActionsHelper.selectOption(enforcementLogTypeDDL,StaticValues.enforcementLogType);
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeysWithClear(logDateCalendar,"30/12/2021");
        ActionsHelper.driverWait(2000);

        ActionsHelper.clickAction(logDescriptionTextbox);
        ActionsHelper.sendKeys(logDescriptionTextbox,"Log Description "+faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        getPopupsPage().uploadDocuments(driver.get().findElement(uploadFile),"test.pdf");
        ActionsHelper.driverWait(2000);
        ActionsHelper.waitUntilElementIsDisplayed(fileDescription,30);
        ActionsHelper.moveToElement(driver.get().findElement(fileDescription));
        ActionsHelper.sendKeys(fileDescription,"Uploaded File Description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(createCaseBtn,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(createCaseBtn,30);
    }

    public void editEnforcementCase() {
        logManager.STEP("Click on edit btn","The user will edit existing enforcement case");
        faker=new Faker();
        ActionsHelper.scrollTo(editCaseBtn);
        ActionsHelper.retryClick(editCaseBtn, 30);
        ActionsHelper.driverWait(6000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(8000);
        ActionsHelper.scrollTo(caseDescriptionTextbox);
        ActionsHelper.sendKeysWithClear(caseDescriptionTextbox, "Case Description "+faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveChanges, 30);
        ActionsHelper.driverWait(8000);
    }

    public void searchByEID(){
        logManager.STEP("Search by EID","The user is searching for EID in the enforcement page");
        ActionsHelper.retryClick(searchRequestLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eIdTexebox, TestData.enforcementCaseEID);
        ActionsHelper.retryClick(searchBtn, 30);
        ActionsHelper.driverWait(8000);
    }
}