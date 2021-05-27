package com.qpros.pages.web.SSA.applicationreview;

import com.qpros.assertion.AssertService;
import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.ssa.core.model.GetFamilyData;
import com.ssa.core.model.Household;
import com.ssa.core.service.GetFamilyDataService;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PersonalInformation extends Base {
    private By personalInformationTab = By.id("DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtTab1");
    private By form = By.id("DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm");
    private By emiratesId = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[1]/span");
    private By nameAR = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[2]/span");
    private By nameEN = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[3]/span");
    private By dateOfBirth = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[4]/span");
    private By sex = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[5]/span");
    private By socialCondition = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[6]/span");
    private By nationality = By.xpath("//*[@id=\"DCDAgentPortalTheme_wt362_block_wtMainContent_CloneOfWebPatterns_wttabs_block_wtContent1_wt243_wtDCDApplicationIndividualForm\"]/div[8]/span");


    public void checkPersonalInformation(GetFamilyData getFamilyData) {
        Household household;
        household = getFamilyData.household.stream().filter(household1 -> Boolean.toString(household1.isHoFB).equalsIgnoreCase("true")
        ).findAny().get();
        try {
            Assert.assertEquals(driver.get().findElement(emiratesId).getText(), household.emiratesId);
        } catch (AssertionError ex) {
            this.logManager.ERROR("Emirates ID does not Match", ex);
        }
        try {
            Assert.assertEquals(driver.get().findElement(nameAR).getText(), household.fullNameAR);
        } catch (AssertionError ex) {
            this.logManager.ERROR("Arabic Name  does not Match", ex);
        }
        try {
            Assert.assertEquals(driver.get().findElement(nameEN).getText(), household.fullNameEN);
        } catch (AssertionError ex) {

            this.logManager.ERROR("English Name does not Match", ex);
        }
        try {
            Assert.assertEquals(driver.get().findElement(sex).getText(), household.genderAR);
        } catch (AssertionError ex) {
            this.logManager.ERROR("Gender  does not Match", ex);
        }
        try {
            Assert.assertEquals(driver.get().findElement(socialCondition).getText(), household.maritalStatusAR);
        } catch (AssertionError ex) {
            this.logManager.ERROR("Martial Condition  does not Match", ex);
        }
        try {
            Assert.assertEquals(driver.get().findElement(nationality).getText(), household.nationalityAR);
        } catch (AssertionError ex) {
            this.logManager.ERROR("nationality does not Match", ex);
        }
    }


}
