package com.qpros.pages.web.dmt;

import com.dmt.core.common.data.Data;
import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Login extends Base {
    public Login(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);

    }

    public By loginButton = new By.ByXPath("//span[contains(.,'Log In')]");
    public By SignInWithUaePassButton = new By.ByXPath("//img[@alt='sign in with UAEPass']");
    public By SignInWithUaePassTextBox = new By.ByXPath("//input[@id='username']");
    public By SignInWithUaePassLoginButton = new By.ByXPath("//input[@id='username']");

    public By LoginConfirm = new By.ByXPath("//h1[@class='field-title']");

    public By requestPhotocopyOfPropertyDocument = new By.ByXPath("//a[@href='/en/aspects-of-life/HousingProperty/PropertyOwnership/PropertyOwnershipDocuments/RequestaPhotocopyofPropertyDocument']");
    public By requestPhotocopyOfPropertyDocumentHeader = new By.ByXPath("//h1[.='Request a Photocopy of Property Document']");
    public By requestPhotocopyOfPropertyDocumentStart = new By.ByXPath("//button[@class='ui-lib-button qa-button ui-lib-button_primary ui-lib-button_medium ui-lib-button_icon-end']");
    public By requestPhotocopyOfPropertyDocumentStartType = new By.ByXPath("//form/div/div/div/div/div/div/div/div/div/div/div/div");
    public By requestPhotocopyOfPropertyDocumentStartTypeTenancyContracts = new By.ByXPath("//div[2]/div/div/div/div[4]");
    public By requestPhotocopyOfPropertyDocumentStartTypeCheckBox = new By.ByXPath("//div[2]/div/div/div/div[4]");

    public By terminateLeaseContractByCourtOrderRadioBox = new By.ByXPath("//*[contains(@id,'uil_radio_button')]");
    public By terminateLeaseContractByCourtOrder = new By.ByXPath("(//a[contains(text(),'Apply')])[137]");
    public By terminateLeaseContractByCourtOrderDateTimePicker = new By.ByName("datePicker");
    public By terminateLeaseContractByCourtOrderDateTimePickerDate = new By.ByXPath("//tr[5]/td[7]/div");
    public By terminateLeaseContractByCourtOrderSelectFile = new By.ByXPath("//span[contains(.,'or drop file(s) here')]");
    public By terminateLeaseContractByCourtOrderCheckBox = new By.ByCssSelector(".ui-lib-checkbox__box");
    public By terminateLeaseContractByCourtOrderNext = new By.ByXPath("//button[contains(.,'NEXT')]");
    public By terminateLeaseContractByCourtOrderSummaryHeader = new By.ByXPath("//h3[.='Application Summary']");
    public By terminateLeaseContractByCourtOrderSummaryCancel = new By.ByXPath("//span[.='Cancel']");
    public By terminateLeaseContractByCourtOrderSummaryCancelYes = new By.ByXPath("//div[3]/button[2]/div");


    public void performLogin() {
//        ActionsHelper.logManger.STEP("User perform Login", "The user enter Emirates ID");
//        ActionsHelper.isElementPresent(loginButton);
//        ActionsHelper.clickAction(loginButton);
//        ActionsHelper.isElementPresent(SignInWithUaePassButton);
//        ActionsHelper.actionClickStepClick("click on Sign in with uae pass", SignInWithUaePassButton);
//        ActionsHelper.waitForExpectedElement(SignInWithUaePassTextBox);
//        ActionsHelper.sendKeys(SignInWithUaePassTextBox, Data.EMIRATES_ID);
//        ActionsHelper.retryClick(SignInWithUaePassLoginButton,30);
//        while (!ActionsHelper.isElementPresent(LoginConfirm)) {
//            System.out.println("waiting..");
//        }
        ActionsHelper.navigate("https://www.tamm.abudhabi/en/aspects-of-life/HousingProperty/PropertyOwnership/PropertyOwnershipDocuments/RequestaPhotocopyofPropertyDocument");
        // ActionsHelper.actionClickScrollStepClick("Navigate to service Request PhotoCopy of Property",requestPhotocopyOfPropertyDocument);
        ActionsHelper.driverWait(5000);
        ActionsHelper.waitVisibility(ActionsHelper.element(requestPhotocopyOfPropertyDocumentHeader), 30);
        ActionsHelper.driverWait(5000);
        while(!ActionsHelper.isElementPresent(requestPhotocopyOfPropertyDocumentStart)){
            System.out.println("Waiting x2");
        }
        ActionsHelper.retryClick(requestPhotocopyOfPropertyDocumentStart,30);
        ActionsHelper.driverWait(5000);
        while(!ActionsHelper.isElementPresent(requestPhotocopyOfPropertyDocumentStartType)){
            System.out.println("Waiting x2");
        }
        ActionsHelper.retryClick(requestPhotocopyOfPropertyDocumentStartType, 30);
        ActionsHelper.clickAction(requestPhotocopyOfPropertyDocumentStartTypeTenancyContracts);
        ActionsHelper.retryClick(requestPhotocopyOfPropertyDocumentStartTypeCheckBox, 30);


        ActionsHelper.navigate("https://www.tamm.abudhabi/en/tamm-centers-services/department-of-municipalities-and-transport");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ActionsHelper.navigate("https://www.tamm.abudhabi/services/housing/tawtheeq/wb/terminate-lease-contract/select-contract");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!ActionsHelper.isElementPresent(terminateLeaseContractByCourtOrderRadioBox)) {
        }
        ActionsHelper.retryClick(terminateLeaseContractByCourtOrderRadioBox,30);
        ActionsHelper.driverWait(3000);
        ActionsHelper.clickAction(terminateLeaseContractByCourtOrderDateTimePicker);
        ActionsHelper.driverWait(3000);
        ActionsHelper.clickAction(terminateLeaseContractByCourtOrderDateTimePickerDate);
        ActionsHelper.driverWait(3000);
        ActionsHelper.retryClick(terminateLeaseContractByCourtOrderSelectFile, 30);
        try {
            Robot robot = new Robot();
//Create instance of Clipboard class

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//Set the String to Enter

            StringSelection stringSelection = new StringSelection("C:\\Users\\maaye\\OneDrive\\Desktop\\groceryfoodapp-190\\laravel_application\\vendor\\org_heigl\\ghostscript\\tests\\support\\test.pdf");
//Copy the String to Clipboard

            clipboard.setContents(stringSelection, null);
//Use Robot class instance to simulate CTRL+C and CTRL+V key events :

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
//Simulate Enter key event
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception ex) {

        }
        ActionsHelper.driverWait(3000);

        ActionsHelper.retryClick(terminateLeaseContractByCourtOrderCheckBox,30);
        ActionsHelper.driverWait(5000);
        ActionsHelper.isElementPresent(terminateLeaseContractByCourtOrderSummaryHeader);
        ActionsHelper.scrollTo(terminateLeaseContractByCourtOrderSummaryCancel);
        ActionsHelper.retryClick(terminateLeaseContractByCourtOrderSummaryCancel,30);
        ActionsHelper.retryClick(terminateLeaseContractByCourtOrderSummaryCancelYes,30);
        ActionsHelper.driverWait(5000);
        logManager.INFO("Bug Found with redirected url :"+ActionsHelper.driver.get().getCurrentUrl(),false);


    }

}

