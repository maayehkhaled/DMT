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

    public String paymentSchedulesUrl = "https://uat.ssa.gov.ae/DCDPaymentsFrontEnd/PaymentsScheduleSummary.aspx";

    private By searchRef = By.id("DCDTheme_wt80_block_wtFilters_wt47");
//block_wtFilters
    private By searchButton = By.id("DCDTheme_wt80_block_wtFilters_wt174");

    private By firstResult = By.cssSelector("tbody > tr:nth-of-type(1) > td:nth-of-type(1)");


    private By expandResults = By.xpath("//a[.='عرض موسع']");

    private By countPayments = By.xpath("//div[@class='Counter_Message']");



    public boolean checkPaymentExistence(String refNo){
        driver.get().navigate().to(paymentSchedulesUrl);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Expand results", expandResults);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Input SSP " + refNo, searchRef);
        ActionsHelper.driverWait(1000);
        ActionsHelper.sendKeys(searchRef,refNo);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("Click search", searchButton);
        ActionsHelper.driverWait(1000);
        System.out.println(countPayments.toString());

        System.out.println(driver.get().findElement(firstResult).getText());
        if(driver.get().findElement(firstResult).getText().equals(refNo)) return true;
        return false;

    }

}
