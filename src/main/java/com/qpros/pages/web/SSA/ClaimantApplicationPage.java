package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.commonSSA.Popups;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ClaimantApplicationPage extends Base {
    public ClaimantApplicationPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By nextStep = By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']");
    private By checkBill = By.cssSelector(".ThemeGrid_Width8 > .OSInline:nth-child(1) > span");
    private By addNewBillDropDownMenu = By.xpath("//select[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wtddl_company\"]");
    private By accountNumberField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_AccountNumber\"]");
    private By emiratesIdField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_inputemirates\"]");
    private By premiseNumber = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_InputPremiseNumber\"]");
    private By personalInformationNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent1_wtNext18\"]");
    private By familyInformationNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent2_wtbtn_Next15\"]");
    private By reasonNotToPayTextField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_Reason2\"]");
    private By premiseNumberDropDownMenu = By.xpath("//select[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtHouseholdIndividualsWithPremiseRecord3_ctl00_wtddl_PremiseLivingOn\"]");
    private By addressNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtbtn_Next14\"]");
    private By salaryAndPensionNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtbtn_Next13\"]");
    private By supportIncomeNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent5_wtbtn_Next11\"]");
    private By businessIncomeNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent6_wtbtn_Next10\"]");
    private By assetsNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent7_wtbtn_Next12\"]");
    private By finishApplication = By.xpath("//input[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent8_wtbtn_ab7Submit\"]");
    private By uploadPersonalDetailsDocument = By.xpath("//div[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent1_wtDivocrceDoc_wtImage\"]");
    private By uploadDocumentBtn = By.xpath("//label[@class=\"button custom-file-upload\"]");
    private By addBillSaveBtn = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wtbtn_Submit\"]");
    private By sendAnApplicationBusinessRecord = By.xpath("//input[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent6_wtbtn_Tab5Submit\"]");
    private By approvalCheckboxesId = By.xpath("//form[@action=\"PopupDisclaimer2.aspx\"]//div//div//div//div[2]//div//div//div//div//div//input");
    private By agreeBtn = By.xpath("//input[@value=\"أوافق\"]");
    private By updateFamilyData =  By.xpath("//*[@id='DCDTheme_wt24_block_wtMainContent_DCD_Activation_CommonModules_CW_wt46_block_wtIcon']");

    BusinessParametersPage businessParametersPage = new BusinessParametersPage(driver.get());
    Popups popUp = new Popups(driver.get());

    public void uploadDocument(By element){
        //put path to your image in a clipboard
        ActionsHelper.retryClick(element, 30);
        ActionsHelper.driverWait(3000);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        try {
            Util.typeString("test.pdf");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            ActionsHelper.driverWait(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //driver.get().findElement(By.xpath("//input[contains(@id,fileinputPopup_AddMemberIncome)]")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
//        ActionsHelper.driverWait(2000);
        ActionsHelper.clickAction(By.xpath("//input[@value=\"حفظ\"]"));
    }


    public void uploadDocumentPersonalInfo(By element){
        //put path to your image in a clipboard
        ActionsHelper.retryClick(element, 30);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        ActionsHelper.retryClick(uploadDocumentBtn, 30);
        ActionsHelper.driverWait(3000);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        try {
            Util.typeString("test.pdf");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            ActionsHelper.driverWait(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //driver.get().findElement(By.xpath("//input[contains(@id,fileinputPopup_AddMemberIncome)]")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
//        ActionsHelper.driverWait(2000);
        ActionsHelper.clickAction(By.xpath("//input[@value=\"حفظ\"]"));
        driver.get().switchTo().defaultContent();
    }


    public void uploadBill() throws AWTException {
        //if (!ActionsHelper.isElementPresent(checkBill)) {
            ActionsHelper.driverWait(1000);
            // 3 | click | css=#DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtoldaddressinfo > .Button |
            driver.get().findElement(By.cssSelector("#DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtoldaddressinfo > .Button")).click();
            // 4 | selectFrame | index=0 |
            ActionsHelper.driverWait(20000);
            driver.get().switchTo().frame(0);
            ActionsHelper.selectOption(addNewBillDropDownMenu, "ADDC");
            ActionsHelper.sendKeys(accountNumberField, "8296115007");
            ActionsHelper.sendKeys(emiratesIdField, "784198585861937");
            ActionsHelper.sendKeys(premiseNumber, "1409065300");
            ActionsHelper.sendKeys(reasonNotToPayTextField, "dhsdjfjdshds");
            uploadDocument(By.cssSelector(".button"));
            ActionsHelper.waitForExpectedElement(By.xpath("//input[@value=\"حفظ\"]"));
            ActionsHelper.retryClick(By.xpath("//input[@value=\"حفظ\"]"), 30);
            //ActionsHelper.clickAction(By.xpath("//input[@value=\"حفظ\"]"));
            //driver.get().switchTo().defaultContent();
            ActionsHelper.driverWait(8000);
            ActionsHelper.selectOption(premiseNumberDropDownMenu, "4");
            ActionsHelper.driverWait(10000);
        }
   // }

    public void doAddressSelections() throws AWTException {
        ActionsHelper.driverWait(10000);

        List<WebElement> addressSelections = driver.get().findElements(By.xpath("//*[contains(@id,'DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtHouseholdIndividualsWithPremiseRecord3_ct1')]|//*[contains(@id,'wtLivingOn2')]"));

        driver.get().switchTo().defaultContent();

        if(addressSelections.size() >= 0) {
            for (int i = 0; i <= addressSelections.size(); i++) {
                try {
                    addressSelections.get(i).click();
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                } catch (Exception e) {
                }
            /*
            addressSelections.get(i).findElement(By.xpath());
            Select residency = new Select(addressSelections.get(i));

            residency.selectByIndex(5);*/
                ActionsHelper.driverWait(2000);
                //dropdown.findElement(By.xpath("//option[. = '8354787951']")).click();
            }
        }


    }

    public void doPensionCheckboxes() throws AWTException {
        ActionsHelper.driverWait(10000);

        List<WebElement> checkBoxes = driver.get().findElements(By.xpath("//*[contains(@id,'DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetMemberList_ctl')]|//*[contains(@id,'_wt214_wtchkbox')]"));
        System.out.println(checkBoxes.size());
        for (int i = 0; i <= checkBoxes.size(); i = i + 6) {
            ActionsHelper.driverWait(2000);
            try {
                checkBoxes.get(i).click();
            }
            catch (Exception e){}
        }
        //id=DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetMemberList_ctl00_wt214_wtchkbox
        //id=DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetMemberList_ctl02_wt214_wtchkbox

    }

    public void doSalaryCertifications() throws AWTException {
        ActionsHelper.driverWait(1000);
        List<WebElement> salaryCerts = driver.get().findElements(By.xpath("//value[التصريح الذاتي للحالة الوظيفية]"));
        System.err.println(salaryCerts.size() + "Salary certs seen");
        for (int i = 1; i <= salaryCerts.size(); i = i + 1) {
            try {

                driver.get().switchTo().defaultContent();
                salaryCerts = driver.get().findElements(By.xpath("//*[contains(@id,'DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetSalaryList_ctl')]|//*[contains(@id,'_wtuploadsalary')]"));
                salaryCerts.get(i).click();
                uploadSalary();
            }
            catch (Exception e){}
        }
        driver.get().switchTo().defaultContent();
        //DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetSalaryList_ctl00_wtselfdeclareno
        //DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetSalaryList_ctl02_wtoptional


        //DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetSalaryList_ctl00_wtuploadsalary
    }

    public void uploadSalary() throws AWTException {
        ActionsHelper.driverWait(10000);
        // 1 | selectFrame | index=0 |
        driver.get().switchTo().frame(0);
        // 2 | click | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus |
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus")).click();
        // 3 | select | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus | label=موظف
        {
            WebElement dropdown = driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus"));
            dropdown.findElement(By.xpath("//option[. = 'موظف']")).click();
        }
        // 4 | click | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus |
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobStatus")).click();
        // 5 | click | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource |
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource")).click();
        // 6 | select | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource | label=جهة حكومية - أبوظبي
        {
            WebElement dropdown = driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource"));
            dropdown.findElement(By.xpath("//option[. = 'جهة حكومية - أبوظبي']")).click();
        }
        // 7 | click | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource |
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtDDJobSource")).click();
        // 8 | type | id=CloneOfWebPatterns_wt34_block_wtMainContent_wt1 | بليسلبيسيسيبل
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wt1")).sendKeys("بليسلبيسيسيبل");
        // 9 | type | id=CloneOfWebPatterns_wt34_block_wtMainContent_wt12 | سيبلسيبلس
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wt12")).sendKeys("سيبلسيبلس");
        // 10 | type | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtAmountTB | 10
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wtAmountTB")).sendKeys("10");
        // 11 | type | id=CloneOfWebPatterns_wt34_block_wtMainContent_wt21 | شسيب
        driver.get().findElement(By.id("CloneOfWebPatterns_wt34_block_wtMainContent_wt21")).sendKeys("شسيب");
        // 12 | selectFrame | index=0 |
        // 13 | click | css=.button |
        driver.get().findElement(By.cssSelector(".button")).click();


        ActionsHelper.driverWait(4000);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyRelease(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);

        ActionsHelper.driverWait(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        ActionsHelper.driverWait(5000);
        // 14 | click | id=CloneOfWebPatterns_wt34_block_wtMainContent_wtSubmit |
        driver.get().findElement(By.name("CloneOfWebPatterns_wt34$block$wtMainContent$wtSubmit")).click();

        ActionsHelper.driverWait(5000);
    }


    public void otherUploads(){

    }


    public void clickOnChangeInLivingCircumstances(){
     //   logManager.STEP("3. Click on التغير في الظروف المعيشية box", "the Beneficiary User Click on التغير في الظروف المعيشية box" + TestData.EID);
     ActionsHelper.driverWait(8000);
      ActionsHelper.actionClickStepClick("Click on update family Data", updateFamilyData);
        ActionsHelper.driverWait(3000);
        ActionsHelper.driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(By.id("CloneOfWebPatterns_wt20_block_wtMainContent_wtddl_WebPortalLocation2"), "2");
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"), 30);
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(35000);
        ActionsHelper.driverWait(6000);
        ActionsHelper.actionClickStepClick("click on next Button",nextStep);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on next Button",nextStep);

    }

    public void personalInformation(){
        logManager.STEP("4. Click on التالي in البيانات الشخصيه tab", "Click on التالي in البيانات الشخصيه tab");
        //ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.driverWait(20000);
        ActionsHelper.waitForExpectedElement(uploadPersonalDetailsDocument, 30);
        uploadDocumentPersonalInfo(uploadPersonalDetailsDocument);
        ActionsHelper.actionClickScrollStepClick("Click on التالي in البيانات الشخصيه tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
    }

    public void familyInformation(){
        ActionsHelper.driverWait(4000);
        logManager.STEP("5. Click on التالي in بيانات العائلة tab", "Click on التالي in بيانات العائلة tab");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
    }

    public void addressAndContactInformation() throws AWTException {
        logManager.STEP("6. Fill address information and contact information in العنوان tab (make sure that the tab marked as completed)", "Fill address information and contact information in العنوان tab (make sure that the tab marked as completed) ");
        ActionsHelper.driverWait(3000);
        uploadBill();
        ActionsHelper.scrollTo(By.xpath("//span[.='تفاصيل مكان السكن']"));
        ActionsHelper.isElementPresent(By.xpath("//span[.='تفاصيل مكان السكن']"));
        java.util.List<WebElement> residentList = driver.get().findElements(By.xpath("//select"));
        residentList.stream().forEachOrdered(selectElement -> {
            ActionsHelper.driverWait(2000);
            Select select = new Select(selectElement);
            select.selectByIndex(select.getOptions().size() - 1);
        });

        logManager.STEP("7. click on التالي", "click on التالي");
        ActionsHelper.driverWait(15000);
        ActionsHelper.actionClickScrollStepClick("next", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));

    }

    public void incomeAndPensionData(){
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on next step ",nextStep);
        logManager.STEP("8. Fill the mandatory information for income in the income table and select all check boxes under pension table in بيانات الدخل tab (make sure that the tab marked as completed)", "Fill the mandatory information for income in the income table and select all check boxes under pension table in بيانات الدخل tab (make sure that the tab marked as completed) ");
        ActionsHelper.driverWait(3000);
        java.util.List<WebElement> incomeList = driver.get().findElements(By.xpath("//*[contains(@id,\"SelftAddSalaryDetails\")]"));
        incomeList.stream().forEachOrdered(income -> {
            ActionsHelper.driverWait(2000);
            ActionsHelper.clickAction(income);
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().frame(0);
            ActionsHelper.retryClick(By.cssSelector("[tabindex='2']"), 30);
            ActionsHelper.selectOption(By.cssSelector("[tabindex='2']"), "__ossli_1");
            ActionsHelper.driverWait(2000);
            ActionsHelper.sendKeys(By.xpath("//textarea[@class='Form_control ThemeGrid_Width6']"),"Bcoc  Test");
            ActionsHelper.driverWait(3000);
            ActionsHelper.retryClick(By.xpath("//label[@class='button custom-file-upload']"),5);
            ActionsHelper.driverWait(3000);
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
            //driver.get().findElement(By.xpath("//input[contains(@id,fileinputPopup_AddMemberIncome)]")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
            ActionsHelper.driverWait(2000);
            ActionsHelper.clickAction(By.xpath("//input[@class='Button Is_Default']"));
            driver.get().switchTo().defaultContent();


        });
        ActionsHelper.scrollTo(By.xpath("//span[.='لائحة التقاعد']"));
        ActionsHelper.isElementPresent(By.xpath("//span[.='لائحة التقاعد']"));
        java.util.List<WebElement> salarySetList = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtApplicantIncomeInfo_wtGetMemberList\")]"));
        salarySetList.stream().forEachOrdered(salaryElememnt -> {
            ActionsHelper.driverWait(2000);
            if (!salaryElememnt.isSelected()) {
                ActionsHelper.retryClick(salaryElememnt, 30);

            }
        });
        logManager.STEP("9. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
    }


    public void support(){
        logManager.STEP("10. Check all boxes under بيانات الدعم  tab (make sure that the tab marked as completed)", "Check all boxes under بيانات الدعم  tab (make sure that the tab marked as completed)");
        java.util.List<WebElement> incomeElements = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent5_wtSupportIcomeTab_wtGetFamilyMemberList\")]"));
        incomeElements.stream().forEachOrdered(incomeElement -> {
            ActionsHelper.driverWait(2000);
            if (!incomeElement.isSelected()) {
                ActionsHelper.retryClick(incomeElement, 30);
            }
        });
        logManager.STEP("11. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickStepClick("click on next button",nextStep);

    }

    public void businessRecord(){
        logManager.STEP("12. Fill the mandatory information in دخل الاعمال التجارية  tab (make sure that the tab marked as completed)", "Fill the mandatory information in دخل الاعمال التجارية  tab (make sure that the tab marked as completed)");
        List<WebElement> listforTrade= driver.get().findElements(By.xpath("//*[contains(@id,\"wtadd_trade\")]"));
        listforTrade.stream().forEachOrdered(tradeItem->{
            ActionsHelper.driverWait(2000);
            ActionsHelper.clickAction(tradeItem);
            ActionsHelper.driverWait(2000);
            driver.get().switchTo().frame(0);
            ActionsHelper.selectOption(By.id("CloneOfWebPatterns_wt21_block_wtMainContent_wtddl_FrequencyType"),"3");
            ActionsHelper.sendKeys(By.xpath("//input[@class='Form_control ThemeGrid_Width6']"),"500");
            ActionsHelper.driverWait(3000);
            uploadDocument(By.xpath("//label[@class='button custom-file-upload']"));
            /*
            ActionsHelper.retryClick(By.xpath("//label[@class='button custom-file-upload']"),5);
            ActionsHelper.driverWait(3000);
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
            //driver.get().findElement(By.xpath("//input[contains(@id,fileinputPopup_AddMemberIncome)]")).sendKeys("C:\\Users\\KhaledMa'ayeh\\Downloads\\pdf-test.pdf");
            ActionsHelper.driverWait(2000);
            ActionsHelper.clickAction(By.xpath("//input[@class='Button Is_Default']"));
             */
            driver.get().switchTo().defaultContent();
        });
        java.util.List<WebElement> listStoreIncome = driver.get().findElements(By.xpath("//input[contains(@id,\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent6_wtBusinessIncomeInfo_wtApplicationIndividualByProperty\")]"));
        listStoreIncome.stream().forEachOrdered(storeIncome -> {
            ActionsHelper.driverWait(2000);
            ActionsHelper.retryClick(storeIncome, 30);
        });
        logManager.STEP("13. Click on التالي", "Click on التالي");
        ActionsHelper.driverWait(3000);
        ActionsHelper.waitForExpectedElement(By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.actionClickScrollStepClick("Click on التالي in بيانات العائلة tab", By.xpath("//div[@class='PH Tabs__content active']//div[@class='card']"));
        ActionsHelper.retryClick(By.id("DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent8_wtbtn_ab7Submit"), 30);
        driver.get().switchTo().frame(0);
        List<WebElement> complianceWith = driver.get().findElements(By.xpath("//input[contains(@id,\"CloneOfWebPatterns_wt9_block_wtMainContent_WebPatterns_wt\")]"));
        complianceWith.stream().forEachOrdered(comp -> {
            ActionsHelper.driverWait(2000);
            if (!comp.isSelected()) {
                ActionsHelper.retryClick(comp, 30);
            }
        });

        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"), 30);
        driver.get().switchTo().defaultContent();
        ActionsHelper.driverWait(5000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.xpath("//input[@class='Button Is_Default']"), 30);
        ActionsHelper.driverWait(5000);
        ActionsHelper.isElementPresent(By.xpath("//*[contains(@id,\"DCDWebPortalTheme_wt90_block_wtMainContent_wt193_wtSSPCode3\")]"));
    }

    public void sendAnApplicationForBusinessRecord(){
        ActionsHelper.waitForExpectedElement(sendAnApplicationBusinessRecord, 30);
        ActionsHelper.clickAction(sendAnApplicationBusinessRecord);
        driver.get().switchTo().frame(0);
        popUp.agreeToTermsPopUp();
        popUp.feedbackPopUp();

    }




    public void bcocApprove() throws AWTException {
        clickOnChangeInLivingCircumstances();
       addressAndContactInformation();
        incomeAndPensionData();
        support();
        //personalInformation();
        //familyInformation();
        //businessRecord();
        /*
        ActionsHelper.waitForExpectedElement(personalInformationNextBtn);
        ActionsHelper.clickAction(personalInformationNextBtn);
        ActionsHelper.waitForExpectedElement(familyInformationNextBtn);
        ActionsHelper.clickAction(familyInformationNextBtn);
        uploadBill();
        ActionsHelper.waitForExpectedElement(addressNextBtn);
        ActionsHelper.clickAction(addressNextBtn);
        doPensionCheckboxes();
        ActionsHelper.waitForExpectedElement(salaryAndPensionNextBtn);
        ActionsHelper.clickAction(salaryAndPensionNextBtn);
        ActionsHelper.waitForExpectedElement(supportIncomeNextBtn);
        ActionsHelper.clickAction(supportIncomeNextBtn);
        ActionsHelper.waitForExpectedElement(businessIncomeNextBtn);
        ActionsHelper.clickAction(businessIncomeNextBtn);
        ActionsHelper.waitForExpectedElement(assetsNextBtn);
        ActionsHelper.clickAction(assetsNextBtn);
        ActionsHelper.waitForExpectedElement(finishApplication);
        ActionsHelper.clickAction(finishApplication);

         */



    }

    public void bcocReject(){



    }



}
