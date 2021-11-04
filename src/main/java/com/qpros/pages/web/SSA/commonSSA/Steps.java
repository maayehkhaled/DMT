package com.qpros.pages.web.SSA.commonSSA;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.web.SSA.AgentPage;
import com.qpros.pages.web.SSA.modules.ApproveApplicationModule;
import com.ssa.core.common.locators.urls;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


@Getter
public class Steps extends Base{

    public Steps(WebDriver driver){ PageFactory.initElements(Base.driver.get(), this);}

    AgentPage agentPage = new AgentPage(driver.get());
    private By allApllicationsSearchInput = By.xpath("//*[contains(@id,'wtFilterContainer_wttxt_SearchFrom')]"); //Only one action was needed



    public String refreshTheListOfApplications(String refCode){
        logManager.STEP("Refresh","Refreshes the page");
        ActionsHelper.driverWait(10000);
        String specialist = agentPage.getAssigneeNameForSpecialist(refCode);
        driver.get().navigate().to(urls.allApplications);
        return specialist;
    }









}
