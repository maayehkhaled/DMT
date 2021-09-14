package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ClaimantApplicationPage extends Base {
    public ClaimantApplicationPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By nextStep = By.xpath("//*[contains(@id,'wtbtn_Next')]");
    private By checkBill = By.cssSelector(".ThemeGrid_Width8 > .OSInline:nth-child(1) > span");
    private By addNewBillDropDownMenu = By.xpath("//select[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wtddl_company\"]");
    private By accountNumberField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_AccountNumber\"]");
    private By emiratesIdField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_inputemirates\"]");
    private By premiseNumber = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_InputPremiseNumber\"]");
    private By personalInformationNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent1_wtNext18\"]");
    private By familyInformationNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent2_wtbtn_Next15\"]");
    private By reasonNotToPayTextField = By.xpath("//input[@id=\"CloneOfWebPatterns_wt16_block_wtMainContent_wttxt_Reason2\"]");
    private By premiseNumberDropDownMenu = By.xpath("//div[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtHouseholdIndividualsWithPremiseRecord3_ctl00_wtcombobox\"]");
    private By addressNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtbtn_Next14\"]");
    private By salaryAndPensionNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent4_wtbtn_Next13\"]");
    private By supportIncomeNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent5_wtbtn_Next11\"]");
    private By businessIncomeNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent6_wtbtn_Next10\"]");
    private By assetsNextBtn = By.xpath("//*[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent7_wtbtn_Next12\"]");
    private By finishApplication = By.xpath("//input[@id=\"DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent8_wtbtn_ab7Submit\"]");


    public void uploadBill() throws AWTException {
        if (!ActionsHelper.isElementPresent(checkBill)) {
            ActionsHelper.driverWait(1000);
            // 3 | click | css=#DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtoldaddressinfo > .Button |
            driver.get().findElement(By.cssSelector("#DCDWebPortalTheme_wtClaimant_block_wtMainContent_CloneOfWebPatterns_wtVerticalTabsContainer_block_wtContent3_wtAddressInfo_wtoldaddressinfo > .Button")).click();
            // 4 | selectFrame | index=0 |
            ActionsHelper.driverWait(20000);
            driver.get().switchTo().frame(0);
            ActionsHelper.selectOption(addNewBillDropDownMenu, "ADDC");
            ActionsHelper.sendKeys(accountNumberField, "5299625382");
            ActionsHelper.sendKeys(emiratesIdField, "784196896907175");
            ActionsHelper.sendKeys(premiseNumber, "8354787951");
            ActionsHelper.sendKeys(reasonNotToPayTextField, "dhsdjfjdshds");


            /*
            // 5 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany |
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany")).click();
            // 6 | select | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany | label=ADDC
            {
                WebElement dropdown = driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany"));
                dropdown.findElement(By.xpath("//option[. = 'ADDC']")).click();
            }
            // 7 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany |

            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtcompany")).click();
            // 8 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtAccountNumber |
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtAccountNumber")).click();
            // 9 | type | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtAccountNumber | 5299625382
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtAccountNumber")).sendKeys("5299625382");
            // 10 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtinputemirates |
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtinputemirates")).click();
            // 11 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtInoutPremiseNumber |
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtInoutPremiseNumber")).click();
            // 12 | type | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtInoutPremiseNumber | 8354787951
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtInoutPremiseNumber")).sendKeys("8354787951");
            // 13 | click | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtReason2 |
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtReason2")).click();
            // 14 | type | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtReason2 | asdf
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtReason2")).sendKeys("asdf");
            // 15 | click | css=.button |
            // 16 | type | id=CloneOfWebPatterns_wt8_block_wtMainContent_wtinputemirates | 784-1968-9690717-5
            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtinputemirates")).sendKeys("196896907175");

             */
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

            driver.get().findElement(By.id("CloneOfWebPatterns_wt8_block_wtMainContent_wtSubmit")).click();

            driver.get().switchTo().defaultContent();

            ActionsHelper.selectOption(premiseNumberDropDownMenu, "354787951");

        }
    }

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

    public void bcocApprove() throws AWTException {
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



    }

    public void bcocReject(){



    }



}
