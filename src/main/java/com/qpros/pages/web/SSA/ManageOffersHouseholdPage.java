package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ManageOffersHouseholdPage extends Base {
    public ManageOffersHouseholdPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By houseHoldingTab=By.xpath("//a[text()='ملف الأسرة']");  //("//a[contains(@id,'ctl04_RichWidgets_wt6_block_wtMenuItem_wt11')]");
    private By eidTextbox= By.xpath("//input[contains(@id,'EmiratesId')]");
    private By userIdLink=By.xpath("//a[contains(@id,'ctl03_wt71')]");
    private By oppUserEIDLink=By.xpath("//a[contains(@id,'ctl03_wtlnk_Expandview')]");
    private By planBtn=By.xpath("//div[contains(@id,'wt133_block_wtImageWrapper')]");
    private By editPlanBtn=By.xpath("//a[contains(@id,'ctl38_WebPatterns_wt133_block_wtTitle_wtOpportunityActions21')]");
    //private By viewBtn=By.xpath("//div[@class='PH Tabs__content active']//span[@class='ListRecords']/span[1]//span[@class='fa fa-fw fa-angle-down']");
    private By viewBtn=By.xpath("//span[contains(text(),'موصى به')]/following::div[4]");
    //private By editFirstTimeBtn=By.xpath("//a[contains(@id,'ctl00_WebPatterns_wt138_block_wtTitle_wtOpportunityActions21')]");
    private By editFirstTimeBtn=By.xpath("//span[contains(text(),'موصى به')]/following::div[2]");
    private By scrollUpToTable=By.xpath("//a[.='شروط ومعايير برامج التمكين']");
    private By editSecondTimeBtn=By.xpath("//a[contains(@id,'wtOpportunityActions18')]");
    private By opportunityStatusDDL=By.xpath("//select[contains(@id,'DDOpportunityStatus')]");
    private By communicationDDL=By.xpath("//select[contains(@id,'DDSourceofCommunication')]");
    private By commentTextarea=By.xpath("//textarea[contains(@id,'Comment')]");
    private By smsTextarea=By.xpath("//textarea[contains(@id,'Sms')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By saveBtn2 =By.xpath("//a[contains(@id,'ctl48_WebPatterns_wt139_block_wtTitle')]");
    private By partnerDDL=By.xpath("//select[contains(@id,'FeedbackCategory')]");
    private By partnerComment=By.xpath("//textarea[contains(@id,'FeedbackTextArea')]");
    private By viewDetailsBtn=By.xpath("//div[contains(@id,'ctl38_WebPatterns_wt133_block_wtSectionExpandableArea')]/div/div[2]/span");
    private By noAnswerMsg=By.xpath("//textarea[contains(@id,'wttxt_Sms')]");
    //private By suggestedTask=By.xpath("//span[contains(text(),'موصى به')]");
    private By suggestedTask=By.xpath("//*[contains(text(),'موصى به')]");

    private By rowsCount=By.xpath("//div[contains(@class,'flexline')]");



    public void searchForEID(){
        logManager.STEP("Search For EID","The user searches for EID then click Tamkeen");
        ActionsHelper.retryClick(houseHoldingTab,30);
        ActionsHelper.driverWait(2000);
        //ActionsHelper.sendKeys(eidTextbox, TestData.pepUserEID+ Keys.ENTER);
        ActionsHelper.sendKeys(eidTextbox, TestData.opportunityEID+ Keys.ENTER);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(userIdLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(oppUserEIDLink,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(planBtn,30);
        ActionsHelper.driverWait(2000);
    }
public void catchFromSpan(){

        List<WebElement> listtableelements=driver.get().findElements(rowsCount); //rows K
    //List<WebElement> listtableelements=driver.get().findElements(By.xpath("//span[contains(@id,'wtOpportunitiesToApproveTable1') and @class='ListRecords']/span"));
    //List<WebElement>internaldivData=listtableelements.get(listtableelements.size()-1).findElements(By.xpath("//div[contains(@class,'ThemeGrid_Width')]")); //K
    WebElement internaldivData=driver.get().findElement(By.cssSelector("span:nth-of-type("+listtableelements.size()+") .flexline > div:nth-of-type(1) > .incomelabel1"));
   driver.get().findElement(By.cssSelector("span:nth-of-type("+listtableelements.size()+") .flexline .ThemeGrid_Width1")).click();
    //--List<WebElement> internaldivData=listtableelements.get(listtableelements.size()-1).findElements(By.xpath("//span[contains(@id,'wtOpportunitiesToApproveTable1') and @class='ListRecords']/span//div[@class='flexline']/div"));

       // String s=internaldivData.get(listtableelements.size()).getText();//the name
    String ss=internaldivData.getText();
    ActionsHelper.driverWait(2000);
    System.out.println(ss);
    ActionsHelper.driverWait(3000);
    //internaldivData.get(0).click();//click on the icon
    ////listtableelements.get(6).click();//click on the icon

    //internaldivData.get(8).click();//will open down arrow to take screenshot
    listtableelements.get(5).click();//wrong
    ActionsHelper.driverWait(4000);

/*
       // WebElement webSpanTable=driver.get().findElement(By.xpath("//div[@class='flexline']"));
    WebElement webSpanTable=driver.get().findElement(By.xpath("//span[contains(@id,'wtOpportunitiesToApproveTable1') and @class='ListRecords']"));
        //List<WebElement> spanRows=webSpanTable.findElements(By.xpath("//div[@class='flexline']"));
    List<WebElement> spanRows=webSpanTable.findElements(By.xpath("//span[contains(@id,'wtOpportunitiesToApproveTable1') and @class='ListRecords']/span"));
        int spanRowSize=spanRows.size();
    System.out.println("number of rows here "+spanRowSize);

    for(int i=0;i<spanRowSize;i++){
        List<WebElement> spanColumns=spanRows.get(i).findElements(By.xpath("//span[contains(@id,'wtOpportunitiesToApproveTable1') and @class='ListRecords']/span//div[@class='flexline']/div"));
        int spanColumnsCount=spanColumns.size();
        //System.out.println("number of columns here "+spanColumnsCount);
        for(int j=0;j<spanColumnsCount;j++){
            String cellText=spanColumns.get(j).getText();
            //System.out.println("here"+cellText);
            if(cellText.equals("Automation opp name Corporis eveniet non.")){
                System.out.println("active text"+cellText);
                //spanColumns.get(j).getText()
            }
        }
    }
    /*for(WebElement e : spanRows) {
        System.out.println(e.getText());
    }*/
}
    public void catchElementInTable(){
        WebElement webTable=driver.get().findElement(By.xpath("//table[@class='TableRecords OSFillParent']//tbody"));
        List<WebElement> rows=webTable.findElements(By.tagName("tr"));
        //
        /*WebElement webRow=driver.get().findElement(By.xpath("//table[@class='TableRecords OSFillParent']//tr[3]//td[1]//div"));
        String firstCell=rows.get(2).getText();
        System.out.println(firstCell);*/
        int rowCount=rows.size();
        //System.out.println("rows equal"+rowCount);
        //System.out.println(rowCount-1);
        for(int i=0;i<rowCount;i++){

            List<WebElement> columns=rows.get(i).findElements(By.tagName("td"));
            int columnsCount=columns.size();
            //System.out.println("cols equal"+columnsCount);
            for(int j=0;j<columnsCount;j++){

                String cellText=columns.get(j).getText();
                //System.out.println("here"+cellText);
                if(cellText.equals("Automation opp name Ullam aperiam.")){
                System.out.println("active text"+columns.get(1).getText());
                }
            }
        }
    }

    public void firstTimeEdit(){
        logManager.STEP("First Edit For The Opportunity","The user click edit to manage opportunity ");
        //ActionsHelper.scrollTo(scrollUpToTable);
        WebElement suggestedElement=driver.get().findElement(suggestedTask);
        if(suggestedElement.isDisplayed()) {
            ActionsHelper.retryClick(editFirstTimeBtn, 30);
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
            ActionsHelper.retryClick(saveBtn, 30);
            ActionsHelper.driverWait(2000);

            ActionsHelper.actionClickStepClick("View the edited info",viewBtn);
            ActionsHelper.driverWait(3000);
        }
        else {
            System.out.println("No New Opportunity For This EID");
            driver.get().close();
        }
    }
    public void editStatusOnly(){
        logManager.STEP("Edit Opportunity Status","The user click edit to edit opportunity status ");
        ActionsHelper.retryClick(editSecondTimeBtn,30);
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

    public void secondTimeEdit(){
        logManager.STEP("Second Edit For The Opportunity","The user click edit for the second time to complete editing");
        ActionsHelper.retryClick(editSecondTimeBtn,30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        //ActionsHelper.selectOption(opportunityStatusDDL,StaticValues.acceptNoDocOppStatus);
        ActionsHelper.scrollTo(partnerDDL);
        ActionsHelper.selectByValue(driver.get().findElement(partnerDDL),StaticValues.joblessJan);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(partnerComment,"Partner Comment 24/2");
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(saveBtn);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(4000);
       /* ActionsHelper.actionClickStepClick("View the edited info",viewBtn);
        ActionsHelper.driverWait(3000);*/
    }
}
