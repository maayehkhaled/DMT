package com.qpros.pages.web;

import com.qpros.common.web.Base;
import com.qpros.common.annotation.STEP;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage  extends Base {
    public ContactUsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By contactMenuItem=By.cssSelector("ul#menu-main-menu [href='https://q-pros.com/#Contact'] .item_text");
    public By user=By.name("your-name");
    public By email=By.name("your-email");
    public By subject=By.name("your-subject");
    public By messageBody=By.name("your-message");
    public By sendMessageBtn=By.cssSelector(".wpcf7-submit");
    public By notificationMessage=By.cssSelector(".wpcf7-response-output");
    public By errorTipMessage=By.cssSelector(".wpcf7-not-valid-tip");

    @STEP(name = "The User Navigate to Contact",description = "The User Clicks on Contact Menu Item") public void navigateToContact()  {
        try {
            ActionsHelper.isElementPresent(contactMenuItem);
        } catch (Exception e) {
            logManager.ERROR("The User is not able to detect the Contact Element");
        }
        ActionsHelper.retryClick(contactMenuItem, 30);
    }

    @STEP(name = "The User Fill Contact Use Form",description = "The User Fill contact US Form with Invalid Email") public void fillContactUsForm()  {
            ActionsHelper.isElementPresent(user);
            ActionsHelper.sendKeysWithClear(user,"QProsAutomation");
            ActionsHelper.isElementPresent(email);
            ActionsHelper.sendKeysWithClear(email,"QProsAutomation@q-pros");
            ActionsHelper.isElementPresent(subject);
            ActionsHelper.sendKeysWithClear(subject,"QPros Subject Automated Text");
            ActionsHelper.isElementPresent(messageBody);
            ActionsHelper.sendKeysWithClear(messageBody,"QPros Message Automated Text");
            ActionsHelper.isElementPresent(sendMessageBtn);
            ActionsHelper.driverWait(5000);
            ActionsHelper.retryClick(sendMessageBtn, 30);
    }

    @STEP(name = "The System Show User a notification Message",description = "The System Should Show error Message declare of Email Error")
        public void verifyTestOutCome()  {
        ActionsHelper.isElementPresent(notificationMessage);
        ActionsHelper.isElementPresent(errorTipMessage);
    }
}
