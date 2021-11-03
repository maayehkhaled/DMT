package com.qpros.pages.web.SSA.commonSSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


@Getter
public class Popups extends Base {


    public Popups(WebDriver driver){ PageFactory.initElements(Base.driver.get(), this); }

    private By approvalCheckboxesId = By.xpath("//form[@action=\"PopupDisclaimer2.aspx\"]//div//div//div//div[2]//div//div//div//div//div//input");
    private By agreeBtn = By.xpath("//input[@value=\"أوافق\"]");
    private By happyFace = By.xpath("//*[@id=\"CloneOfWebPatterns_wt24_block_wtMainContent_wt21\"]/img");
    private By feedbackTextBox = By.xpath("//textarea[@id=\"CloneOfWebPatterns_wt24_block_wtMainContent_wtCommentBox\"]");
    private By saveBtn = By.xpath("//input[@value=\"حفظ\"]");


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









}
