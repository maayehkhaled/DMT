package com.qpros.pages.web.SSA;

import com.qpros.common.web.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AppealPage extends Base {
    public AppealPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }



}
