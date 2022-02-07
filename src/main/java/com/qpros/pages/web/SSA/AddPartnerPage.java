package com.qpros.pages.web.SSA;

import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class AddPartnerPage extends Base {
    public AddPartnerPage(WebDriver webDriver)
    {
        PageFactory.initElements(Base.driver.get(), this);

    }
    Faker faker=new Faker(new Random(24));
    public final By partnerMenu=By.xpath("//a[.='الشركاء']");
    private final By addPartnerButton=By.xpath("//input[@value='إضافة شريك']");
    private final By partnerName=By.xpath("//input[contains(@id,'wtInput_wttxt_Name')]");
    private final By simpleExplanation=By.xpath("//textarea[contains(@class,'OSFillParent Mandatory SmartInput')]");
    private final By partnerType=By.xpath("//select");
    private final By chosePartnerType=By.xpath("//option[@value='1']");
    private final By typeProgram=By.xpath("//input[contains(@class,'select2')]");
    private final By choseProgram=By.xpath("//li[text()='فرصة وظيفية']");
    private final By saveButton=By.xpath("//input[@class='Button redButton Is_Default']");
    public void addPartner()
    {
        ActionsHelper.actionClickStepClick("click on الشركاء",partnerMenu);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on اضافة شريك",addPartnerButton);
        ActionsHelper.driverWait(5000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(3000);
        ActionsHelper.sendKeys(partnerName,faker.name().firstName());
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(simpleExplanation,faker.lorem().sentence(10));
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on نوع الجهة",partnerType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose نوع الجهة ",chosePartnerType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on نوع الرامج",typeProgram);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose نوع البرامج",choseProgram);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on الحفظ",saveButton);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(5000);
    }
}