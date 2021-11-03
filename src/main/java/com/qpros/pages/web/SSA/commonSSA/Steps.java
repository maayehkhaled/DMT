package com.qpros.pages.web.SSA.commonSSA;

import com.qpros.common.web.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


@Getter
public class Steps extends Base{

    public Steps(WebDriver driver){ PageFactory.initElements(Base.driver.get(), this);}










}
