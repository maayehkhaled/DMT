package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AcocTriggerPage extends Base {
    public AcocTriggerPage(WebDriver driver)
    {
        PageFactory.initElements(Base.driver.get(), this);

    }
    String refCode = "SSP-22864";
    private final By familyFile=By.xpath("//a[.='ملف الأسرة']");
    private final By searchOnSSP=By.xpath("//input[contains(@id,'Filter_ApplicationCode')]");
    private final By searchButton=By.xpath("//input[@class='Button Is_Default ThemeGrid_Width6']");
    private final By clickOnSSP=By.xpath("//a[contains(@id,'wtDCDBenefitTable')]");
    private final By contact=By.xpath("//input[@value='التواصل']");
    private final By emrIdList=By.xpath("//select");
    private final By choseEmrId=By.xpath("//option[text()='784199140643000']");
    private final By topic=By.xpath("//input[@class='OSFillParent']");
    private final By summary=By.xpath("//textarea[@class='OSFillParent']");
    private final By MSG=By.xpath("//textarea[@class='OSFillParent Mandatory']");
    private final By addButton=By.xpath("//input[@class='Button Is_Default ThemeGrid_Width3 ThemeGrid_MarginGutter']");
    private final By numberOfRecord=By.xpath("//div[@class='Counter_Message']");
    private final By addCommentButton=By.xpath("//input[@value='إضافة تعليقات ']");
    private final By commentType=By.xpath("//select[contains(@id,'CommentsCategory')]");
    private final By choseCommentType=By.xpath("//option[text()='Accept offer comment']");
    private final By numberOfComment=By.xpath("//*[@id='InternalPortalTheme_wt169_block_wtMainContent_wtDCDNoteTable_Wrapper']/div/div[3]/div");
    private final By changeCircumstancesButton=By.xpath("//input[@class='Button btn-blue']");
    private final By nameList=By.xpath("//select[contains(@id,'wtInput_wtddl_Emirated_Id')]");
    private final By choseName=By.xpath("//option[text()='???? ???? ??? ???? ???????']");
    private final By reasonCircumstances=By.xpath("//select[contains(@id,'wtInput_wtddl_ReasonValue')]");
    private final By choseReason=By.xpath("//option[text()='تم التعرف على دخل إضافي للأسرة أثناء المناقشات مع المستفيد']");
    private final By addCircumstancesAgreeButton=By.xpath("//input[@value='اضافة ']");
    /**
     * method to add التواصل
     */
    public void addCommunication()
    {
        logManager.STEP("add Communication method ","this method to add Communication information for ex EID/summery /msg AFTER Added this info system should added a record to the communication table");
        ActionsHelper.driverWait(4000);
        refCode= FileUtils.readFile("refCodeFile.txt").get(0);
        ActionsHelper.actionClickStepClick("click on ملف الاسره",familyFile);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeys(searchOnSSP,refCode);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("Click on البحث",searchButton);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("go to صفحة التواصل",clickOnSSP);
      ActionsHelper.actionClickScrollStepClick("click on التواصل ",contact);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on رقم الهويه",emrIdList);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose رقم الهويه",choseEmrId);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(topic,"test topic");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(summary,"test");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(MSG,"test msg");
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("click on اضافه",addButton);
        ActionsHelper.driverWait(6000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(numberOfRecord);
        ActionsHelper.driverWait(2000);
        char record=ActionsHelper.element(numberOfRecord).getText().charAt(0);
        ActionsHelper.driverWait(2000);
        if(record=='1')
        {
            logManager.INFO("sum of record  is equal 1 ",false);

        }
        else
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(numberOfRecord).getText().trim()+ "]  while expected result should 1  ", false);

        }
    }

    /**
     * method to add comment
     */
    public void addComment()
    {
        logManager.STEP("add Comment method ","this method to add comment  information for ex EID/comment type  /msg AFTER Added this info system should added a record to the comment table");

        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("click on اضافة تعليق ",addCommentButton);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on رقم الهويه",emrIdList);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose emrId",choseEmrId);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on نوع التعليقات",commentType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose نوع التعليقات",choseCommentType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(topic,"test topic");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(summary,"test");
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick("click on اضافه",addButton);
        ActionsHelper.driverWait(6000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(numberOfComment);
        ActionsHelper.driverWait(2000);
        char record=ActionsHelper.element(numberOfComment).getText().charAt(0);
        ActionsHelper.driverWait(2000);
        if(record=='1')
        {
            logManager.INFO("sum of comment  record  is equal 1 ",false);

        }
        else
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(numberOfComment).getText().trim()+ "]  while expected result should 1  ", false);

        }
    }

    /**
     * method to add تغيير الظروف
     */
    public void addChangeCircumstances()
    {
        logManager.STEP("add تغيير الظروف ","this method to add تغيير الظروف information for ex الاسم/سبب تغيير الظروف  AFTER Added this info system should تغيير الظروف button disabled");

        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickScrollStepClick("click on اضافة ظروف",changeCircumstancesButton);
        driver.get().switchTo().frame(0);
        ActionsHelper.actionClickStepClick("click on اختيار الاسم",nameList);
        ActionsHelper.actionClickStepClick("chose name",choseName);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on اختيار سبب تغير الظروف ",reasonCircumstances);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose reason",choseReason);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on الموافقة ",addCircumstancesAgreeButton);
        ActionsHelper.driverWait(7000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(changeCircumstancesButton);
        ActionsHelper.driverWait(5000);
        if (ActionsHelper.element(changeCircumstancesButton).isEnabled())
        {
            logManager.ERROR("must be disabled",false);
        }
        else
        {
            logManager.INFO("success",false);
        }
    }
}
