package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class AppealPage extends Base {
    public AppealPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By buttonShowDetails = By.xpath("//*[contains(@id,'wtbtn_ShowSpecificCode')]");

    private By applicationRef = By.xpath("//*[contains(@id,'wttxt_CodesToRelease')]");

    private By validateButton = By.xpath("//*[contains(@id,'wtbtn_Validate')]");
    //
    private By startProcessButton = By.xpath("//*[contains(@id,'wtbtn_RunSpecificCodes')]");

    //
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


    public void doAddressSelections() throws AWTException {

    }
}


