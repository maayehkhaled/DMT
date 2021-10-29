package com.qpros.pages.web.SSA;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

@Getter
public class AppealPage extends Base {
    public AppealPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By buttonShowDetails = By.xpath("//*[contains(@id,'wtbtn_ShowSpecificCode')]");
    private By applicationRef = By.xpath("//*[contains(@id,'wttxt_CodesToRelease')]");
    private By validateButton = By.xpath("//*[contains(@id,'wtbtn_Validate')]");
    private By startProcessButton = By.xpath("//*[contains(@id,'wtbtn_RunSpecificCodes')]");
    private By reviseSettingsConditiosnMain = By.id("DCDTheme_wt22_block_wtMainContent_DCD_Activation_CommonModules_CW_wt35_block_wtText");
//wtbtn_RunSpecificCodes

    private By startAppeal = By.xpath("//*[contains(@id,'wtAppeal')]");
    private By startAppeal2 = By.xpath("//*[contains(@id,'btn_Appeal')]");
    private By mainCommentField = By.id("DCDWebPortalTheme_wtLayout_block_wtMainContent_wt41_wt32_WebPatterns_wt28_block_wtContent_wttxt_SummaryInput");
    private By arrayField = By.xpath("//*[contains(@id,'btn_Appeal')]");
    private By arrayFieldBankAccount = By.xpath("//*[contains(@id,'HasNoBankAccount')]"); //multiple
    private By arrayFieldEmployment = By.xpath("//*[contains(@id,'IsUnemployed')]"); //multiple
    private By arrayFieldFileUplaod = By.cssSelector("#DCDWebPortalTheme_wtLayout_block_wtMainContent_wt41_wt32_WebPatterns_wt28_block_wtContent_wtDCDBenefitRequestTable_ctl03_DCDUIPatterns_CW_wtPopup_Upload_DCDBenefitRequestTable_List_Current_block_wtAFile .button"); //multiple
    private By arrayFieldComment = By.xpath("//*[contains(@id,'Description')]"); //multiple
                                                    //#DCDWebPortalTheme_wtLayout_block_wtMainContent_wt41_wt32_WebPatterns_wt28_block_wtContent_wtDCDBenefitRequestTable_ctl04_DCDUIPatterns_CW_wtPopup_Upload_DCDBenefitRequestTable_List_Current_block_wtAFile .button

    private By reviewConditionsAndStandards = By.xpath("//div[@class='HomePageRow']//div[@class='text']/div[.='مراجعة شروط ومعايير البرنامج والمدفوعات']");
    private By applyApplication = By.xpath("//input[@value='تقديم طلب تظلم']");
    private By applyApplicationBtn = By.xpath("//input[@value=\"طلب تظلم\"]");
    private By appealApplication =  By.id("DCDWebPortalTheme_wtLayout_block_wtMainContent_wtbtn_Appeal");
    private By osFillParent  = By.xpath("//textarea[@class='OSFillParent']");
    @FindBy(xpath = "//label[contains(text(),'رفع المستند')]")
    private List<WebElement> salaryElementList;
    @FindBy(xpath = "//textarea[contains(@id,wttxt_Description)]")
    private List<WebElement> fillcommentTestBoxs;
    @FindBy(xpath = "//input[@type=\"checkbox\"]")
    private List<WebElement> familyMembersCheckBoxes;
    private By sendAppealRequest =  By.xpath("//input[@value=\"إرسال طلب التظلم\"]");
    private By approvalPopUp = By.xpath("//div[@class=\"MainPopup\"]");
    private By approveOnAppeal = By.xpath("//input[@value=\"أوافق\"]");
    private By profilePage =  By.xpath("//input[@class='Button MenuButton UserProfileButton OSFillParent']");
    private By logout = By.cssSelector("[tabindex='4'] > .OvalIcon");
    private By commentTextBox = By.xpath("//textarea[contains(@id,wttxt_Description)]");
    private By uploadBankStatement = By.xpath("//label[contains(text(),'رفع المستند')]");
    private By checkbox = By.xpath("//input[@type=\"checkbox\"]");
    private By approvalCheckboxesId = By.xpath("//form[@action=\"PopupDisclaimer2.aspx\"]//div//div//div//div[2]//div//div//div//div//div//input");
    public static void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void uploadDocument(WebElement element){
        //put path to your image in a clipboard
        ActionsHelper.retryClick(element, 30);
        ActionsHelper.driverWait(3000);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        try {
            Util.typeString("1.pdf");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            ActionsHelper.driverWait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        /*
        try {
            setClipboardData("C://Users//RawaQaffaf//Desktop//testpdf.pdf");
            Robot robot=new Robot();
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(90);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }

         */
        //driver.get().findElement(By.xpath("//input[contains(@id,fileinputPopup_AddMemberIncome)]")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
//        ActionsHelper.driverWait(2000);
        driver.get().switchTo().defaultContent();
    }



    public void appealApprove(){
        //1st assessment - Approve
        ActionsHelper.driverWait(8000);
        ActionsHelper.actionClickStepClick("مراجعة شروط و معايير البرنامج و المدفوعات", reviewConditionsAndStandards);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("تقديم طلب تظلم", applyApplication);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick(" طلب تظلم", applyApplicationBtn);
        ActionsHelper.driverWait(4000);
        //ActionsHelper.scrollTo(osFillParent);
        //ActionsHelper.sendKeys(osFillParent, "test appeal text");
        List<WebElement> fillCommentTextBoxes = driver.get().findElements(commentTextBox);
        List<WebElement> uploadStatementsList = driver.get().findElements(uploadBankStatement);
        List<WebElement> familyMembersCheckBoxList = driver.get().findElements(checkbox);
        familyMembersCheckBoxList.stream().forEachOrdered(checkbox -> ActionsHelper.clickAction(checkbox));
        uploadStatementsList.stream().forEachOrdered(salElemnt -> uploadDocument(salElemnt));
        fillCommentTextBoxes.stream().forEachOrdered(commnt -> ActionsHelper.sendKeys(commnt, "test appeal text"));
        ActionsHelper.driverWait(7000);
        ActionsHelper.actionClickStepClick("apply Appeal Request",sendAppealRequest);
        ActionsHelper.driverWait(8000);
        driver.get().switchTo().frame(0);
        List<WebElement> approvalCheckboxeslist = driver.get().findElements(approvalCheckboxesId);
        approvalCheckboxeslist.stream().forEachOrdered(checkbox -> ActionsHelper.clickAction(checkbox));
        //ActionsHelper.scrollTo(lastApprovalCheckboxId);
        //ActionsHelper.clickAction(lastApprovalCheckboxId);
        approvalCheckboxeslist.stream().forEachOrdered(agreeitem -> {
            if (!agreeitem.isSelected()) {
                agreeitem.click();
            }
        });

        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click approve on appeal", approveOnAppeal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("click on profile page ", profilePage);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on logout", logout);
        ActionsHelper.driverWait(5000);

    }

    public void appealReject(){
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("مراجعة شروط و معايير البرنامج و المدفوعات", reviewConditionsAndStandards);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("تقديم طلب تظلم", applyApplication);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick(" طلب تظلم", applyApplication);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(osFillParent);
        ActionsHelper.sendKeys(osFillParent, "test appeal text");
        salaryElementList.stream().forEachOrdered(salElemnt -> ActionsHelper.sendKeys(salElemnt, "C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf"));
        fillcommentTestBoxs.stream().forEachOrdered(commnt -> ActionsHelper.sendKeys(commnt, "test appeal text"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("apply Appeal Request",sendAppealRequest);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        /*
        listOfAgree.stream().forEachOrdered(agreeitem -> {
            if (!agreeitem.isSelected()) {
                agreeitem.click();
            }
        });

         */
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click approve on appeal", approveOnAppeal);
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("click on profile page ", profilePage);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on logout", logout);
        ActionsHelper.driverWait(5000);

    }



    public void rmiAppeal(){
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("مراجعة شروط و معايير البرنامج و المدفوعات", By.xpath("//div[@class='HomePageRow']//div[@class='text']/div[.='مراجعة شروط ومعايير البرنامج والمدفوعات']"));
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickScrollStepClick("تقديم طلب تظلم", By.xpath("//input[@value='تقديم طلب تظلم']"));
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickScrollStepClick(" طلب تظلم", By.id("DCDWebPortalTheme_wtLayout_block_wtMainContent_wtbtn_Appeal"));
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(By.xpath("//textarea[@class='OSFillParent']"));
        ActionsHelper.sendKeys(By.xpath("//textarea[@class='OSFillParent']"), "test appeal text");
        List<WebElement> salaryElementList = driver.get().findElements(By.xpath("//input[contains(@id,fileinputPopup_Upload)]"));
        salaryElementList.stream().forEachOrdered(salElemnt ->
                ActionsHelper.sendKeys(salElemnt, "C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf"));
        List<WebElement> fillcommentTestBoxs = driver.get().findElements(By.xpath("//textarea[contains(@id,wttxt_Description)]"));
        fillcommentTestBoxs.stream().forEachOrdered(commnt ->
                ActionsHelper.sendKeys(commnt, "test appeal text"));

        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("apply Appeal Request", By.xpath("//input[@class='Button ForwardButton Is_Default']"));

        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        List<WebElement> listOfAgree = driver.get().findElements(By.xpath("//input[contains(@id,wtchk_canProceed)]"));
        listOfAgree.stream().forEachOrdered(agreeitem -> {
            if (!agreeitem.isSelected()) {
                agreeitem.click();
            }
        });
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click approve on appeal", By.xpath("//div[@class='PopupActionsContainer']"));
        ActionsHelper.driverWait(10000);
        ActionsHelper.actionClickStepClick("click on profile page ",By.xpath("//input[@class='Button MenuButton UserProfileButton OSFillParent']"));
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on logout",By.cssSelector("[tabindex='4'] > .OvalIcon"));
        ActionsHelper.driverWait(5000);
    }


    public void doAddressSelections() throws AWTException {

    }
}


