package com.qpros.pages.web.SSA;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class COCPage extends Base {
    public COCPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By searchField = By.id("DCDWebPortalTheme_wt15_block_wtMainContent_wt1");



    public void navigateToCoc(){
        logManager.STEP("Navigate to COC", "Navigate to https://10.231.1.100/DCDBusinessParameters/CoC.aspx");
        driver.get().navigate().to("https://10.231.1.100/DCDBusinessParameters/CoC.aspx");
    }



}
