package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Getter
@Setter
public class EnforcementCasePage extends Base {
    public EnforcementCasePage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    Popups PopupsPage=new Popups(driver.get());

    private By enforcementCaseLink = By.xpath("//a[contains(@id,'ctl24_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By NewenforcementCaseLink = By.xpath("//div[contains(@class,'Longbutton')]"); //a[contains(@id,'AddAction')]

    private By emiratesIdTextbox = By.xpath("//input[contains(@id,'wtBeneficiaryData_wttxt_emiratesid')]");
    private By fetchBtn = By.xpath("//input[contains(@id,'wtButton1_wtbtn_FetchDetails')]");
    private By enforcementTypeDDL = By.xpath("//select[contains(@id,'EnforcementType')]");//select
    private By infoSourceDDL = By.xpath("//select[contains(@id,'EnforcementSource')]");//select
    private By caseDescriptionTextbox = By.xpath("//textarea[contains(@id,'wtinput_description')]"); //Test Automation
    private By recomendedTextbox = By.xpath("//input[contains(@id,'RecommendedAction')]"); //Recommendation Action Automation
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
    private By successMsg=By.xpath("//span[contains(@id,'wtSanitizedHtml3')]");
            //By.className("Feedback_Message_Success");
    //Add action Locators
    private By caseID=By.xpath("//a[contains(@id,'ctl03_wtlnk_CaseId')]");
    private By addActionBtn=By.xpath("//a[contains(@id,'AddAction')]");
    private By actionAssignedToDDl=By.xpath("//select[contains(@id,'AssignedTo')]") ;
    private By actionDueDate=By.xpath("//input[contains(@id,'wtDueDateToInput')]");

    private By actionTodayBtn=By.xpath("//button[(@class='pika-go-today')]");
    private By actionDescriptionTextbox=By.xpath("//textarea[contains(@id,'ActionDescription')]");
    private By saveActionBtn=By.xpath("//input[contains(@id,'wtbtnCreateAction')]");
    //Edit Action Locators
    private By editActionBtn=By.id("InternalPortalTheme_wt247_block_wtMainContent_wtActionTable_ctl03_wtlnk_editaction");
    private By agreeRdionBtn=By.xpath("//input[contains(@id,'wt12')]");
    private By saveEditAction=By.xpath("//input[contains(@id,'wtbtnCreateAction')]");
    //Delete Action Locators
    private By deleteActionBtn=By.xpath("//a[contains(@id,'ctl04_wt667')]");
    private By deletedRecord=By.xpath("//td[contains(@id,'ctl04_wtActionTableRow1')]");
    private By actionTable=By.xpath("//table[contains(@id,'wtActionTable')]");

    //Add Logs Locators
    private By addLogLink=By.xpath("//a[contains(@id,'AddLog')]");
    private By logTypeDDL=By.xpath("//select[contains(@id,'LogType')]");
    private By logDescriptionText=By.xpath("//textarea[contains(@id,'LogDescription')]");
    private By actionNumber=By.xpath("//select[contains(@id,'ActionId')]");
    private By saveLogBtn=By.xpath("//input[contains(@id,'wtbtnCreateLog')]");
    //Delete Logs Locators
    private By deleteLogBtn=By.xpath("//a[contains(@id,'wt143')]");
    public void deleteLogs(){
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addLogLink,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.retryClick(deleteLogBtn,30);
        ActionsHelper.driverWait(2000);
    }
    public void addLogs(){
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addLogLink,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(logTypeDDL,"43");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(logDescriptionText,"Automation Log Description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(actionTodayBtn,"184");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveLogBtn,30);
        ActionsHelper.driverWait(2000);
    }
    public void deleteAction() {
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(deleteActionBtn,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(5000);
    }
    public int checkDeletedTableSize(){
        WebElement actionsTable = driver.get().findElement(actionTable);
        List<WebElement> tableRows = actionsTable.findElements(By.tagName("tr"));
        return tableRows.size();
        //return ActionsHelper.isElementPresent(deletedRecord);//not correct assertion
    }

    public void editAction() {
        ActionsHelper.retryClick(caseID,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(editActionBtn, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.retryClick(agreeRdionBtn, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveActionBtn, 30);
        ActionsHelper.driverWait(2000);
    }

    /**
     *
     */
    public void addAction(){
    ActionsHelper.retryClick(caseID,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(addActionBtn,30);
    ActionsHelper.driverWait(2000);
    driver.get().switchTo().frame(0);
    ActionsHelper.selectOption(actionAssignedToDDl,"1708");
    ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(actionDueDate,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(actionTodayBtn,30);
    ActionsHelper.driverWait(2000);
    ActionsHelper.sendKeys(actionDescriptionTextbox,"action automation new");
    ActionsHelper.driverWait(2000);
    ActionsHelper.retryClick(saveActionBtn,30);
    ActionsHelper.driverWait(2000);
    }

    public void openEnforcementCase(){
        ActionsHelper.retryClick(enforcementCaseLink, 30);
        ActionsHelper.driverWait(8000);
    }

    public void createEnforcementCase() {
        ActionsHelper.retryClick(NewenforcementCaseLink, 30);
        ActionsHelper.driverWait(8000);

        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(8000);

        ActionsHelper.sendKeys(emiratesIdTextbox,"784195843653981");
        ActionsHelper.retryClick(fetchBtn,30);
        ActionsHelper.driverWait(8000);

        ActionsHelper.scrollTo(enforcementTypeDDL);
        ActionsHelper.driverWait(8000);

        ActionsHelper.selectOption(enforcementTypeDDL,"21");
        ActionsHelper.driverWait(2000);

        ActionsHelper.selectOption(infoSourceDDL,"54");
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeys(caseDescriptionTextbox,"My new Test Automation");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(recomendedTextbox,"Recommendation Action Automation");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(justificationTextbox,"Insert Justification for action recommendation Automation");
        ActionsHelper.driverWait(2000);


        ActionsHelper.retryClick(actionDueDateCalendar,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(todayDate,30);
        ActionsHelper.driverWait(2000);

        ActionsHelper.selectOption(enforcementLogTypeDDL,"43");
        ActionsHelper.driverWait(2000);

        ActionsHelper.sendKeysWithClear(logDateCalendar,"30/12/2021");
        ActionsHelper.driverWait(2000);

        ActionsHelper.clickAction(logDescriptionTextbox);
        ActionsHelper.sendKeys(logDescriptionTextbox,"Log Description Automation ");
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
        ActionsHelper.scrollTo(editCaseBtn);
        ActionsHelper.retryClick(editCaseBtn, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(8000);
        ActionsHelper.scrollTo(caseDescriptionTextbox);
        ActionsHelper.sendKeysWithClear(caseDescriptionTextbox, "Automation Edit Case Description");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveChanges, 30);
        ActionsHelper.driverWait(8000);
    }

    public void searchByEID(){
        ActionsHelper.retryClick(searchRequestLink, 30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(eIdTexebox, "784195843653981");
        ActionsHelper.retryClick(searchBtn, 30);
        ActionsHelper.driverWait(8000);
    }

        public String getEnforcementSuccessMsg(){
        ActionsHelper.driverWait(8000);
        String successMsgTxt=driver.get().findElement(successMsg).getText();
        return(successMsgTxt);
    }
}