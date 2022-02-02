package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ManageOffersHouseholdPage extends Base {
    public ManageOffersHouseholdPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }

}
