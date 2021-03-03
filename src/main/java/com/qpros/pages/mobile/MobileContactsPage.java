package com.qpros.pages.mobile;

import com.qpros.common.LogManager;
import com.qpros.common.annotation.STEP;
import com.qpros.common.mobile.MobileBase;
import com.qpros.helpers.MobileActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MobileContactsPage extends MobileBase {
    public MobileContactsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    LogManager logManager = new LogManager(MobileContactsPage.class.getSimpleName());

    private By addContactButton = By.xpath("//android.widget.Button[@content-desc=\"Add Contact\"]");
    private By contactName = By.id("com.example.android.contactmanager:id/contactNameEditText");
    private By contactPhone = By.id("com.example.android.contactmanager:id/contactPhoneEditText");
    private By contactEmail = By.id("com.example.android.contactmanager:id/contactEmailEditText");

    private By saveButton = By.id("com.example.android.contactmanager:id/contactSaveButton");

    @STEP(name = "The User Navigate to Home Page", description = "The User Click on Add Contact Button") public void clickOnAddContact() {
        logManager.DEBUG("The User click on add contact button");
        MobileActionHelper.clickElement(addContactButton);
    }

    @STEP(name = "The User Fill Contact Us Form", description = "The User Should be able to fill the contact Form") public void fillForm() throws InterruptedException {
        logManager.DEBUG("The User Fill the contact form to be saved");
        MobileActionHelper.waitForSeconds(10);
        MobileActionHelper.sendKeys(contactName,"QProsAutomation");
        MobileActionHelper.waitForSeconds(10);
        MobileActionHelper.sendKeys(contactPhone,"123456789");
        MobileActionHelper.waitForSeconds(10);
        MobileActionHelper.sendKeys(contactEmail,"test@test.com");
        MobileActionHelper.waitForSeconds(10);
        logManager.DEBUG("The User finished Fill the contact form to be saved");

    }

    @STEP(name = "The User Click on Save Button", description = "The User Click on Save Button") public void saveContact() throws InterruptedException {
        MobileActionHelper.waitForSeconds(10);
        logManager.DEBUG("The User click on Save  button");
        MobileActionHelper.clickElement(saveButton);
    }
}
