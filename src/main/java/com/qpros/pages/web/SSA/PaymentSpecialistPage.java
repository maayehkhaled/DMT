package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentSpecialistPage extends Base {
    public PaymentSpecialistPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public String paymentSchedulesUrl = "https://10.231.1.100/DCDPaymentsFrontEnd/PaymentsScheduleSummary.aspx";

    private By paymentsTab = By.id("DCDTheme_wt136_block_wtMenu_DCDTheme_wt20_block_RichWidgets_wt217_block_wtMenuItem_wt17");

    private By searchRef = By.id("DCDTheme_wt80_block_wtFilters_wt47");

    private By searchButton = By.id("DCDTheme_wt80_block_wtFilters_wt174");

    private By firstResult = By.cssSelector("tbody > tr:nth-of-type(1) > td:nth-of-type(1)");

    public boolean checkPaymentExistence(String refNo){
        driver.get().navigate().to(paymentSchedulesUrl);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Input SSP " + refNo, searchRef);
        ActionsHelper.driverWait(1000);
        ActionsHelper.sendKeys(searchRef,refNo);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click search", searchButton);
        ActionsHelper.driverWait(1000);
        System.out.println(driver.get().findElement(firstResult).getText());
        if(driver.get().findElement(firstResult).getText().equals(refNo)) return true;
        return false;

    }

}
