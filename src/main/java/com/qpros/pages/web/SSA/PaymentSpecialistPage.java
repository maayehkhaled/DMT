package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.locators.urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentSpecialistPage extends Base {
    public PaymentSpecialistPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    public String paymentSchedulesUrl = urls.paymentSchedule;

    private By searchRef = By.id("DCDTheme_wt78_block_wtFilters_wt46");
//block_wtFilters
    private By searchButton = By.id("DCDTheme_wt78_block_wtFilters_wt173");

    private By firstResult = By.cssSelector("tbody > tr:nth-of-type(1) > td:nth-of-type(1)");


    private By expandResults = By.xpath("//a[.='عرض موسع']");

    private By countPayments = By.xpath("//div[@class='Counter_Message']");


    //*****TABLE FIRST ROW INFORMATION BEGIN********
    private By totalPaymentAmount = By.xpath("//td[7]/div");


    private By firstPaymentDueDate = By.xpath("//td[7]/div");

    private By firstPaymentAmount = By.xpath("//td[8]/div");


    private By firstPaymentEndDate = By.xpath("//td[6]/div");

    private By firstPaymentStartDate = By.xpath("//td[5]/div");

    private By scheduleType = By.xpath("//td[10]/div");
    private By firstPaymentReason = By.xpath("//td[11]");
    //******TABLE FIRST ROW INFORMATION END*********


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
