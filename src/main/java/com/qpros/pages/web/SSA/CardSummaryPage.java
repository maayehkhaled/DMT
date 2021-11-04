package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CardSummaryPage {

    public CardSummaryPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

    private By applicationNumberFilter = By.xpath("//div/div[2]/input");

    private By cardStatus = By.xpath("//td[11]/div/div");


}
