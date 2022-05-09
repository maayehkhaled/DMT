package com.qpros.pages.web.SSA;

import com.github.javafaker.Faker;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ActivationOpportunitiesPage extends Base {
    public ActivationOpportunitiesPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }
    Faker faker=new Faker();
    LocalDateTime datetime1 = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formatDateTime = datetime1.format(format);

    private By addOpportunityBtn= By.xpath("//input[contains(@id,'AddOpportunity')]");
    private By opportunityTypyDDL=By.xpath("//select[contains(@id,'wtddl_Opportunity')]"); //2
    private By partnerDDL=By.xpath("//select[contains(@id,'wtddl_Partner')]");//2
    private By opportunityNameTextbox=By.xpath("//input[contains(@id,'name')]");
    private By opportunityDescriptionTextbox=By.xpath("//input[contains(@id,'DescriptionInput')]");
    private By startDateCalendar=By.xpath("//input[contains(@id,'wtDateandCity_block_wtColumn1_wtdt_SDate')]");
    private By locationDDl=By.xpath("//select[contains(@id,'City')]");
    private By programCapacityTextbox=By.xpath("//input[contains(@id,'Capacity2')]");
    private By genderDDL=By.xpath("//select[contains(@id,'Genderip')]");
    private By minAgeTextbox=By.xpath("//input[contains(@id,'wtMinAge_Input')]");
    private By maxAgeTextbox=By.xpath("//input[contains(@id,'maxage3')]");
    private By saveBtn=By.xpath("//input[contains(@id,'Save')]");
    private By successMsg=By.className("Feedback_Message_Success");
    private By EIDTextbox=By.xpath("//textarea");
    private By addMemberBtn=By.xpath("//input[contains(@id,'AddIndividual')]");
    private By thirdPage=By.xpath("//a[text()='3']");
    private By footer=By.xpath("//div[@class='Footer']");
    private By planFooter=By.xpath("//div[contains(@class,'Panel_footer')]");

    public void clickAddOpportunity() {
        faker=new Faker();
        logManager.STEP("Add New Opportunities and recommend it to EID","The user adds tow Opportunities and recommend it to EID");
        ActionsHelper.retryClick(addOpportunityBtn, 30);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.selectOption(opportunityTypyDDL, StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(partnerDDL, StaticValues.partner);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityNameTextbox,"Automation opp name " +faker.lorem().sentence(1));
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(opportunityDescriptionTextbox,"Automation description " +faker.lorem().sentence(2));
        ActionsHelper.driverWait(2000);
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(locationDDl,"1");
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(programCapacityTextbox,"200");
        ActionsHelper.driverWait(2000);
        ActionsHelper.selectOption(genderDDL,StaticValues.IELTS);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(minAgeTextbox, StaticValues.minAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(maxAgeTextbox, StaticValues.maxAge);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(saveBtn,30);
        ActionsHelper.driverWait(4000);
        ActionsHelper.sendKeysWithClear(EIDTextbox,TestData.opportunityEID);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(addMemberBtn,30);
        ActionsHelper.driverWait(10000);
    }

    public void createdRequest(){
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(footer);
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(thirdPage,30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(footer);
        logManager.INFO("Created opportunity",false);
    }

    public void openCreatedOpportunity(){
        driver.get().navigate().to("https://uat.ssa.gov.ae/DCD_Activation_AgentFrontEndN/ExpandedFamilyMemberViewPage.aspx?ApplicationStatus=2&ApplicationID=1397&NameInArabic=%d8%b5%d8%a7%d9%84%d8%ad+%d8%b3%d9%8a%d9%81+%d8%ad%d9%85%d8%af+%d8%b9%d9%84%d9%89+%d8%a7%d9%84%d8%b3%d9%86%d8%a7%d9%86%d9%89&ActivityId=0&IndividualIsActive=True&EmiratesId=784197821469414&(Not.Licensed.For.Production)=");
        ActionsHelper.driverWait(2000);
        ActionsHelper.retryClick(By.cssSelector("#InternalPortalTheme_wt24_block_wtMainContent_wt14_CloneOfWebPatterns_wtTabs_block_wtTab11_DCD_Activation_Theme_wt133_block_wtImage img"),30);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(planFooter);
        ActionsHelper.driverWait(8000);
        logManager.INFO("Created Opportunity after assigning",false);
    }
    }
