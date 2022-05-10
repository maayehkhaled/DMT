package com.qpros.pages.web.SSA.applicationreview;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.TableUtils;
import com.ssa.core.model.GetFamilyDataModel;
import com.ssa.core.model.Household;
import org.openqa.selenium.By;

public class ContactInformation {

    private By emiratesID= By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent3_wt304_wtContactInfo\"]/tbody/tr[1]/td[1]");
    private By name= By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent3_wt304_wtContactInfo\"]/tbody/tr[1]/td[2]");

    private By email= By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent3_wt304_wtContactInfo\"]/tbody/tr[1]/td[3]");

    private By landPhone= By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent3_wt304_wtContactInfo\"]/tbody/tr[1]/td[4]");

    private By mobilePhone= By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent3_wt304_wtContactInfo\"]/tbody/tr[1]/td[5]");


    public void checkContactInformation(GetFamilyDataModel getFamilyDataModel){
        Household household;
        TableUtils.getWebElementTableRowCount(ActionsHelper.element(emiratesID));

    }
}
