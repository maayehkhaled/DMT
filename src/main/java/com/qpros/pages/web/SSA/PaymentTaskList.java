package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentTaskList {
    public PaymentTaskList(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }




    /* SELECT TASK TYPE:
        driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt139_block_wtColumn2_WebPatterns_wt23_block_wtColumn1_wtcb_TaskSource")).click();
    {
      WebElement dropdown = driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt139_block_wtColumn2_WebPatterns_wt23_block_wtColumn1_wtcb_TaskSource"));
      dropdown.findElement(By.xpath("//option[. = 'Approval of Card Request']")).click();
    }

    SELECT STATUS TYPE:
    driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt113_block_wtColumn1_wtcb_Status")).click();
    {
      WebElement dropdown = driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt113_block_wtColumn1_wtcb_Status"));
      dropdown.findElement(By.xpath("//option[. = 'New']")).click();
    }

    driver.findElement(By.cssSelector("#DCDTheme_wt117_block_wtFilters_WebPatterns_wt113_block_wtColumn2 div:nth-child(1) > .OSFillParent")).click();

    SELECT RECEIVED FROM:
    driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt113_block_wtColumn2_wtcb_ReceivedFrom")).click();
    {
      WebElement dropdown = driver.findElement(By.id("DCDTheme_wt117_block_wtFilters_WebPatterns_wt113_block_wtColumn2_wtcb_ReceivedFrom"));
      dropdown.findElement(By.xpath("//option[. = 'PaymentSeniorSpecialist']")).click();
    }
     */

    private By searchButton = By.xpath("xpath=//div[3]/input");

    private By takeActionButton = By.xpath("//a/span");

    //Change frame:
    ////Switch frame: driver.get().switchTo().frame(0);
    //

    private By approveCardButton = By.xpath("=//div[2]/div/input[4]");


}
