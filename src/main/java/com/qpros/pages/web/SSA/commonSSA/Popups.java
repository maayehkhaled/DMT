package com.qpros.pages.web.SSA.commonSSA;

import com.qpros.common.web.Base;
import com.qpros.common.web.Util;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;


@Getter
public class Popups extends Base {


    public Popups(WebDriver driver){ PageFactory.initElements(Base.driver.get(), this); }

    private By approvalCheckboxesId = By.xpath("//form[@action=\"PopupDisclaimer2.aspx\"]//div//div//div//div[2]//div//div//div//div//div//input");
    private By agreeBtn = By.xpath("//input[@value=\"أوافق\"]");
    private By happyFace = By.xpath("//*[@id=\"CloneOfWebPatterns_wt24_block_wtMainContent_wt21\"]/img");
    private By feedbackTextBox = By.xpath("//textarea[@id=\"CloneOfWebPatterns_wt24_block_wtMainContent_wtCommentBox\"]");
    private By saveBtn = By.xpath("//input[@value=\"حفظ\"]");
    @FindBy(xpath = "//div[@class=\"Feedback_Message_Error\"]//span[@class=\"Feedback_Message_Text\"]")
    private WebElement feedbackMessageError;
    @FindBy(xpath = "//div[@class=\"Feedback_Message_Success\"]//span[@class=\"Feedback_Message_Text\"]")
    private WebElement feedbackMessageSuccess;
    private By feedbackMessage = By.xpath("//span[@class=\"Feedback_Message_Text\"]");



    public void agreeToTermsPopUp(){
        List<WebElement> approvalCheckboxeslist = driver.get().findElements(approvalCheckboxesId);
        approvalCheckboxeslist.stream().forEachOrdered(checkbox -> ActionsHelper.clickAction(checkbox));
        //ActionsHelper.scrollTo(lastApprovalCheckboxId);
        //ActionsHelper.clickAction(lastApprovalCheckboxId);
        approvalCheckboxeslist.stream().forEachOrdered(agreeitem -> {
            if (!agreeitem.isSelected()) {
                agreeitem.click();
            }
        });

        ActionsHelper.waitForExpectedElement(agreeBtn, 30);
        ActionsHelper.clickAction(agreeBtn);
    }

    public void feedbackPopUp(){
        ActionsHelper.clickAction(happyFace);
        ActionsHelper.sendKeys(feedbackTextBox, "Adding Feedback");
        ActionsHelper.clickAction(saveBtn);


    }


    public void uploadDocument(WebElement element){
        //put path to your image in a clipboard
        ActionsHelper.retryClick(element, 30);
        ActionsHelper.driverWait(3000);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        try {
            Util.typeString("test.pdf");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            ActionsHelper.driverWait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        driver.get().switchTo().defaultContent();
    }


    public String feedbackMessage(){
        ActionsHelper.waitForExpectedElement(feedbackMessage);
        String message =  driver.get().findElement(feedbackMessage).getText();
        return message;
    }










}
