package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NewCard {
    public NewCard(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By serachNumberFilter = By.xpath("//div/div/input");

    private By searchButton = By.xpath("//div[2]/div[2]/input");

    private By plusButton = By.cssSelector(".fa-plus-circle");

    //Switch frame: driver.get().switchTo().frame(0);

    private By cardIdentifier = By.xpath("//div/div/input");

    private By cardNumber = By.xpath("//div/div[2]/input");

    private By selectActiveInactive = By.xpath("//select"); //Label = Active

    private By selectActive = By.xpath("//option[. = 'Active']");// AFTER WEBDRIVER selectActiveInactive WebElement SELECT ACTIVE/INACTIVE WEB ELEMENT

    private By logoutButton = By.cssSelector("css=.logoutBorder1");
    /*
   {
      WebElement dropdown = driver.findElement(By.id("CloneOfWebPatterns_wt21_block_wtMainContent_DCDTheme_wt3_block_wtContent_wtDCD_PrepaidCard_StatusId"));
      dropdown.findElement(By.xpath("//option[. = 'Active']")).click();
    }

     */

    private By clickSubmit = By.xpath("//div[3]/div/div/input");





}
