package com.qpros.pages.web.SSA.payments;

import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.FileUtils;
import com.qpros.pages.web.SSA.AgentPage;
import com.qpros.pages.web.SSA.LoginPage;
import com.qpros.pages.web.SSA.UserType;
import com.ssa.core.common.data.StaticValues;
import com.ssa.core.common.data.TestData;
import com.ssa.core.common.locators.urls;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Getter
public class AssessmentPaymentPage extends Base {

    public AssessmentPaymentPage(WebDriver driver) {
        PageFactory.initElements(Base.driver.get(), this);
    }
    LoginPage loginPage = new LoginPage(driver.get());
    AgentPage agentPage = new AgentPage(driver.get());
    Random rng = new java.util.Random();


    //Locators
    private final By paymentMenuItem = By.xpath("//a[.='المدفوعات']");
    private final By paymentsTableSubMenuItem = By.xpath("//div[.='جداول الدفع']");
    private final By applicationIDSearchBox = By.cssSelector("[placeholder='رقم الطلب']");
    private final By applicationIDSearchButton = By.cssSelector("[value='بحث']");
    private final By applicationTableResult = By.xpath("//table[contains(@id,'wtSummarizedInfoTable')]");
    private final By applicationTableCardResult = By.xpath("//table[contains(@id,'wtDCDCreditCardTable')]");
    private final By applicationPaymentAction=By.xpath("//div[.='إجراءات الدفع']");
    private final By applicationExpendedView=By.linkText("عرض موسع");
    private final By applicationFirstDate=By.xpath("//input[contains(@id,'wtInput_wtDateToInput2')]");
    private final By applicationEndDate=By.xpath("//input[contains(@id,'wtInput_wtDateFromInput2')]");
    private final By numberOfRecord=By.className("Counter_Message");
    private final By applicationValue=By.xpath("//*[@id='DCDTheme_wt78_block_wtMainContent_wtFullInformationTable']/tbody/tr[1]/td[8]/div");
    private final By applicationCard=By.xpath("//a[text()='بطاقات']");
    private final By applicationCardProcedures=By.xpath("//div[.='إجراءات البطاقة']");
    private final By applicationRequestManualCard=By.xpath("//div[.='طلب بطاقة يدويا']");
    private final By applicationAddNew=By.xpath("//span[@class='fa fa-fw fa-plus-circle fa-lg']");
    private final By applicationCardId=By.cssSelector("[maxlength='16']");
    private final By applicationCardNumber=By.cssSelector("[maxlength='13']");
    private final By applicationCardStatus=By.xpath("//select[contains(@id,'wtDCD_PrepaidCard_StatusId')]");
    private final By applicationTaskFromDate=By.xpath("//input[contains(@name,'DateFromInput')]");
    private final By applicationTaskToDate=By.xpath("//input[contains(@name,'DateToInput')]");
    private final By applicationCardActive=By.xpath("//option[text()='Active']");
    private final By applicationSubmitButton=By.xpath("//input[@class='Button Is_Default']");
    private final By applicationsRegularPayment=By.xpath("//table[@class='TableRecords OSFillParent']//tr[1]//div[.='Regular Payment']");
    private final By applicationsOriginalApplication=By.xpath("//td[text()='Original Application']");
    private final By applicationReleasePayment=By.xpath("//span[@class='fa fa-fw fa-refresh']");
    private final By applicationTaskList=By.xpath("//a[.='قائمة المهام']");
    private final By applicationTaskSource=By.xpath("//span[contains(@id,'TaskSource-container')]");
    private final By applicationTaskSearch=By.xpath("//input[@class='select2-search__field']");
    private final By applicationChoseTask=By.xpath("//li[text()='Approval of Card Request']");
    private final By applicationTaskStatus=By.xpath("//span[contains(@id,'wtcb_Status-container')]");
    private final By applicationChoseTaskStatus=By.xpath("//li[text()='New']");
    private final By applicationTaskReceivedFromList=By.xpath("//span[contains(@id,'ReceivedFrom-container')]");
    private final By applicationChoseTaskReceivedFrom=By.xpath("//li[text()='PaymentSeniorSpecialist']");
    private final By applicationTakeAction=By.xpath("//span[@class='fa fa-fw fa-edit']");
    private final By applicationApproveButton = By.cssSelector("[value='الموافقة']");
    private final By applicationApproveInstruction=By.cssSelector("[value='تقديم للحصول على موافقة']");
    private final By applicationCardSummary=By.xpath("//div[.='ملخص البطاقة']");
    private final By applicationSchedulePage=By.xpath("//div[.='جداول الدفع']");
    private final By applicationPaymentInstruction=By.xpath("//div[.='تعليمات الدفع']");
    private final By applicationNewInstruction=By.xpath("//div[.='إنشاء تعليمات']");
    private final By applicationPaymentCardStatus=By.xpath("//select[@id='DCDTheme_wtLayoutWB_block_wtFilters_wtcb_CardStatus']");
    private final By applicationChosePaymentCardStatus=By.xpath("//option[text()='فعال']");
    private final By applicationPaymentList=By.xpath("//select[@id='DCDTheme_wtLayoutWB_block_wtFilters_wtcb_NextMonth']");
    private final By applicationChoseInclude=By.xpath("//option[text()='تتضمن']");
    private final By applicationInformationGeneration = By.xpath("//input[contains(@id,'wtGenerateInstructionBtn')]");
    private final By userNameBeforeLogout = By.className("logoutBorder1");
    private final By logo=By.xpath("//div[contains(@class,'Header_Menu_Container')]");
    private final By logout2 = By.xpath("//div[contains(@id,'Logout')]");
    private final By applicationIsInProcess = By.xpath("//select[contains(@id,'wtFilters_wt113')]");
    private final By applicationChoseInProcess=By.xpath("//option[text()='In Process']");
    private final By applicationChoseTaskApprovalOfInstruction=By.xpath("//li[text()='Approval of Instructions']");
    private final By applicationFullData=By.xpath("//table[contains(@id,'wtFullInformationTable')]");
    private final By applicationTableCard = By.xpath("//table[contains(@id,'wtCardsTable')]");
    private final By applicationCardStatusList=By.xpath("//select[contains(@id,'wtCardsTable')]");
    private final By applicationChoseUpdateCardStatus=By.xpath("//option[text()='Stolen']");
    private final By applicationSubmit=By.xpath("//input[@class='Button Is_Default']");
    private final By applicationChoseApprovalOfCardStatus=By.xpath("//li[text()='Approval of Card Status']");
    private final By cardStatusValue=By.xpath("//option[@selected]");
    private final By cardTable=By.xpath("//select[contains(@id,'wtCardsTable')]");
    private final By tableClass=By.className("TableRecords_OddLine");
    private final By closeCardTable=By.xpath("//table[contains(@id,'wtDCDPrepaidCardTable')]");
    private final By applicationCloseCard=By.xpath("//span[contains(@class,'fa-times fa-lg')]");
    private final By applicationChoseChangeCardStatusAPI=By.xpath("//li[text()='Change Card Status API']");
    private final By applicationChoseApprovalOfOverPayment=By.xpath("//option[text()='Approval of OverPayment']");
    private final By applicationChoseApprovalOfUnderPayment=By.xpath("//option[text()='Approval of UnderPayment']");
    private final By applicationApprovalOfPaymentStatus=By.xpath("//option[text()='Approval of Payment Status']");
    private final By applicationSubmitCloseCard = By.cssSelector("[value='تأكيد']");
    private final By applicationBlockCardButton=By.xpath("//span[@class='fa fa-fw fa-clock-o fa-lg']");
    private final By applicationManualCardTable=By.xpath("//table[contains(@id,'wtIndividualTable')]");
    private final By applicationVariancePayment=By.xpath("//div[.='مدفوعات التباين']");
    private final By applicationAddVariancePayment=By.xpath("//span[contains(@class,'fa fa-fw fa-plus fa-lg')]");
    private final By applicationVariancePaymentType=By.xpath("//select[contains(@id,'wtVarianceTypeWidget')]");
    private final By applicationChosePaymentType=By.xpath("//option[text()='Dependent']");
    private final By applicationReason=By.xpath("//textarea");
    private final By applicationMinusIcon=By.xpath("//span[contains(@class,'fa fa-fw fa-minus fa-lg')]");
    private final By applicationMinusValue=By.xpath("//input[contains(@id,'wtVariancePaymentsUnderpayment_Amount')]");
    private final By applicationMinusMonthValue=By.xpath("//input[contains(@id,'wtVariancePaymentsUnderpayment_NrMonths')]");
    private final By applicationMinusVarianceType=By.xpath("//select[contains(@id,'wtVariancePaymentsUnderpayment_Variancetype')]");
    private final By applicationMinusChoseType=By.xpath("//option[text()='Dependent']");
    private final By applicationUpdateInstruction=By.xpath("//div[.='تحديث التعليمات']");
    private final By applicationStatusByUpdate=By.xpath("//select[contains(@class,'OSFillParent')]");
    private final By applicationTableUpdate=By.xpath("//table[contains(@id,'wtDCDPaymentInstructionTable')]");
    private final By applicationChoseInterfacedToERP=By.xpath("//option[text()='Interfaced to ERP']");
    private final By applicationSavedUpdate=By.xpath("//span[contains(@class,'fa fa-fw fa-save fa-lg')]");
    private final By applicationChoseFailed=By.xpath("//option[text()='Failed']");
    private final By applicationHoldPaymentButton=By.xpath("//span[contains(@class,'fa fa-fw fa-pause')]");
    private final By applicationPaymentActionTable=By.xpath("//table[contains(@id,'wtDCDBenefitRequestTable')]");
    private final By applicationHoldPaymentPowerOff=By.xpath("//*[@id='DCDTheme_wt131_block_wtMainContent_wtDCDBenefitRequestTable_ctl03_wtTerminateLink3']/span");
    private final By reSearchButton=By.xpath("//input[@value='إعادة تعيين']");
    private final By addOverPaymentValue=By.className("ThemeGrid_Width4");
    private final By clickONSchudelType=By.xpath("//div[@class='hiddenFilters']/div[@class='ThemeGrid_Width3']/select[@class='OSFillParent']");
    private final By choseSchdulePayment=By.xpath("//option[text()='Variance Payment']");

    //Actions
    public void differenceBetweenDate() throws ParseException {
        logManager.STEP("payment sceniro 1 ","this method to create payment sceniro 1");
        ActionsHelper.driverWait(2000);
        clickOnSchedulePayment();
        ActionsHelper.scrollTo(applicationTableResult);
        ActionsHelper.driverWait(3000);
        List<WebElement> tableBody=driver.get().findElements(By.className("TableRecords_OddLine"));

        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(convertDate(tableBody.get(3).getText()))),
                YearMonth.from(LocalDate.parse(convertDate(tableBody.get(4).getText())).plus(1, ChronoUnit.DAYS).atStartOfDay())
        );

        if (monthsBetween != StaticValues.yearsFullMonths) {
            logManager.ERROR("Actual Date Differance [" + monthsBetween + "] months while expected result should [12] months  between the dates [" + tableBody.get(3).getText() + "]-[" + tableBody.get(4).getText() + "]", false);
        }else {
            ActionsHelper.isElementPresent(tableBody.get(3));
            ActionsHelper.isElementPresent(tableBody.get(4));
            logManager.INFO("Checking for the differance for the Payment schedule is 12 Months ",false);
        }
        if(!TestData.EID.equalsIgnoreCase(tableBody.get(2).getText()))
        {
            logManager.ERROR("Actual Date  [" +tableBody.get(2).getText()+ "]  while expected result should ["+TestData.EID+"] the  [" +tableBody.get(2).getText()+"] does not equal  ["+TestData.EID+"]  ", false);

        }
        else
        {
            logManager.INFO("Checking for the EID  [" +tableBody.get(2).getText()+"] for the Payment Table Data is correct ",false);

        }
        if(!StaticValues.refCode.equalsIgnoreCase(tableBody.get(0).getText()))
        {
            logManager.ERROR("Actual Date  [" +tableBody.get(0).getText()+ "]  while expected result should ["+StaticValues.refCode+"] the  [" +tableBody.get(0).getText()+"] does not equal  ["+StaticValues.refCode+"]  ", false);

        }
        else
        {
            logManager.INFO("Checking for the SSP [" +tableBody.get(0).getText()+ "] for the Payment Table Data is correct ",false);

        }
            String removeComma=tableBody.get(6).getText().replace("،","");
            double allAmount = Double.parseDouble(removeComma.substring(StaticValues.currencySubString,removeComma.length()));
            System.out.println(allAmount);


        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(applicationExpendedView);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(6000);


        System.out.println(ActionsHelper.element(applicationValue).getText());
         removeComma=ActionsHelper.element(applicationValue).getText().replace("،","");
        System.out.println(removeComma);
        double value=Double.parseDouble(removeComma.substring(StaticValues.currencySubString,removeComma.length()));
        double sumOfAmount=value*StaticValues.yearsFullMonths;
        System.out.println(sumOfAmount);

        if(allAmount !=sumOfAmount)
        {
            logManager.ERROR("Actual Date  [" +sumOfAmount+ "]  while expected result should ["+allAmount+"]  ", false);

        }
        else
        {
            logManager.INFO("sum of القيمه المطلوبه is equal sum of  المبلغ الاجماليcorrect ",false);

        }
        if(ActionsHelper.element(numberOfRecord).getText().contains(" 12 "))
        {
            logManager.INFO("sum of record  is equal 12 ",false);

        }
        else
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(numberOfRecord).getText().trim()+ "]  while expected result should 12  ", false);

        }
        if(allAmount/StaticValues.yearsFullMonths!=value)
        {
            logManager.ERROR("Actual Date  [" +allAmount/StaticValues.yearsFullMonths+ "]  while expected result should  [" +value+ "]   ", false);

        }
        else
        {
            System.out.println(allAmount/StaticValues.yearsFullMonths);
            logManager.INFO("the value return correct  is equal ["+allAmount/StaticValues.yearsFullMonths+"] ",false);

        }
        if(!ActionsHelper.element(applicationsRegularPayment).getText().equalsIgnoreCase("Regular Payment"))
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(applicationsRegularPayment).getText()+ "]  while expected result should   Regular Payment    ", false);

        }
        else
        {
            logManager.INFO("the schedule type is    ["+ActionsHelper.element(applicationsRegularPayment).getText()+"] ",false);

        }
        if(!ActionsHelper.element(applicationsOriginalApplication).getText().equalsIgnoreCase("Original Application"))
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(applicationsOriginalApplication).getText()+ "]  while expected result should  Original Application    ", false);

        }
        else
        {
            logManager.INFO("the schedule type is ["+ActionsHelper.element(applicationsOriginalApplication).getText()+"] ",false);
        }
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(applicationCard);
        requestManualCard();


        addTaskAndCheckFromData();
        clickOnSchedulePayment();
        ActionsHelper.scrollTo(applicationTableResult);
        ActionsHelper.driverWait(3000);
        List<WebElement> paymentTableBodyData=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(paymentTableBodyData.get(5).getText().equalsIgnoreCase("No Active or Inactive Card Available"))
        {
            logManager.ERROR("Actual Date  [" +paymentTableBodyData.get(5).getText()+ "]  while expected result should card id  ", false);

        }
        else
        {
            logManager.INFO("the card Id is    ["+paymentTableBodyData.get(5).getText()+"] ",false);

        }
        ActionsHelper.driverWait(2000);
        logOut();
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(3000);
       createInstruction();
        clickOnSchedulePayment();//from 47 to 50
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on status to chose in process",applicationIsInProcess);
        ActionsHelper.driverWait(2000);

        ActionsHelper.actionClickStepClick("chose in Process",applicationChoseInProcess);
        ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
        ActionsHelper.scrollTo(applicationFullData);
        List<WebElement> paymentTableBodyDataAfterInProcess=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!paymentTableBodyDataAfterInProcess.get(8).getText().equalsIgnoreCase("In Process"))
        {

            logManager.ERROR("Actual Date  [" + paymentTableBodyDataAfterInProcess.get(8).getText() + "]  while expected result should In Process  ", false);
        }

        else {
            logManager.INFO("the status is   [" + paymentTableBodyDataAfterInProcess.get(8).getText() + "] ", false);

        }
        ActionsHelper.driverWait(3000);
        //52+53
        logOut();
       loginByPaymentSectionHead();
       createTaskInstruction();
       ActionsHelper.actionClickStepClick("click on عرض موسع ",applicationExpendedView);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(applicationFullData);
        List<WebElement> tableBodyDataIsTerminated=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(5000);
        if(!tableBodyDataIsTerminated.get(8).getText().equalsIgnoreCase("Pending"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsTerminated.get(8).getText()+ "]  while expected result should [Pending]   ", false);
        }
        else
        {
            logManager.INFO("the  status is    ["+tableBodyDataIsTerminated.get(8).getText()+"]" ,false);

        }
    }
    public void createTaskInstruction()
    {
        logManager.STEP("method to create task","this method to create task instruction from PaymentSectionHead user");
        ActionsHelper.actionClickStepClick("click on قائمة المهام ",applicationTaskList);//step 55
        ActionsHelper.driverWait(4000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.driverWait(1000);
        ActionsHelper.sendKeys(applicationTaskSearch,"Approval of Instructions");
        ActionsHelper.actionClickStepClick("chose Approval of Instructions",applicationChoseTaskApprovalOfInstruction);
        ActionsHelper.driverWait(2000);
        clickOnTakeAction();
        clickOnSchedulePayment();//from 59 to 61
    }
    public void createInstruction()
    {
        logManager.STEP("create task method","this task for create a task from PaymentSeniorSpecialist and توليد المعلومات");
        ActionsHelper.actionClickStepClick("click on المدفوعات",paymentMenuItem);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on تعليمات الدفع",applicationPaymentInstruction);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on انشاء التعليمات",applicationNewInstruction);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on   حالة البطاقة ",applicationPaymentCardStatus);
        ActionsHelper.actionClickStepClick("chose card فعال ",applicationChosePaymentCardStatus);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on دفعة السداد",applicationPaymentList);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on تتضمن",applicationChoseInclude);
        ActionsHelper.driverWait(5000);
        ActionsHelper.scrollTo(applicationInformationGeneration);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("click on توليد المعلومات",applicationInformationGeneration);
        ActionsHelper.driverWait(8000);
    }
    public void addTaskAndCheckFromData()
    {
        logManager.STEP("create task and check from ملخص البطاقه","this method to create task for card sceniro using PaymentSectionHead user ");
        ActionsHelper.actionClickStepClick("click on قائمة المهام ",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch,"Approval of card request");
        ActionsHelper.actionClickStepClick("click on Approval of Card Request",applicationChoseTask);
        ActionsHelper.driverWait(2000);
        fillTaskSourceAndSearchAndClickSubmit();
        ActionsHelper.driverWait(7000);
        ActionsHelper.actionClickStepClick("click on card",applicationCard);
        ActionsHelper.driverWait(3000);

        ActionsHelper.actionClickStepClick("click on ملخص البطاقة ",applicationCardSummary);
        searchOnApplication();
        ActionsHelper.scrollTo(applicationTableCardResult);
        List<WebElement> tableBodyDataToCard=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataToCard.get(10).getText().equalsIgnoreCase("Active"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataToCard.get(10).getText()+ "]  while expected result should Active   ", false);

        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataToCard.get(10).getText()+"] ",false);

        }
        if(tableBodyDataToCard.get(5).getText().equalsIgnoreCase("No Active or Inactive Card Available"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataToCard.get(7).getText()+ "]  while expected result should card id  ", false);

        }
        else
        {
            logManager.INFO("the card Id is    ["+tableBodyDataToCard.get(7).getText()+"] ",false);

        }
        ActionsHelper.driverWait(2000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);

    }
    public void requestManualCard()
    {
        logManager.STEP("card crated successfuly","this method to create new card using PaymentSeniorSpecialist user");
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("click on card",applicationCard);

        ActionsHelper.driverWait(3000);


        ActionsHelper.navigate(urls.requestNewCard);

        ActionsHelper.driverWait(4000);
        searchOnApplication();
        ActionsHelper.actionClickStepClick("click on + icon",applicationAddNew);
        ActionsHelper.driverWait(4000);
        driver.get().switchTo().frame(0);

        long first14 = (rng.nextLong() % 100000000000000L) + 5200000000000000L;
        String rand=Long.toString(first14);
        ActionsHelper.sendKeys(applicationCardId,rand);
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(applicationCardNumber,rand);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on CARD Status",applicationCardStatus);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on Active status",applicationCardActive);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on submit button",applicationSubmitButton);
        ActionsHelper.driverWait(10000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        logOut();
       loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
    }
    public void clickOnTakeAction()
    {
        logManager.STEP("take the action ","this method to Accepet Task AND Agree to the Take action");
        ActionsHelper.actionClickStepClick("click on Task status",applicationTaskStatus);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("chose task status",applicationChoseTaskStatus);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("click on  مستلم من",applicationTaskReceivedFromList);
        ActionsHelper.actionClickStepClick("chose مستلم من ",applicationChoseTaskReceivedFrom);
        ActionsHelper.actionClickStepClick("click on البحث  ",applicationIDSearchButton);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on اتخذ الاجراء",applicationTakeAction);
        ActionsHelper.driverWait(5000);
        ActionsHelper.scrollTo(applicationApproveButton);
        ActionsHelper.actionClickStepClick("click on الموافقة",applicationApproveButton);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().alert().accept();
    }
    public void fillTaskSourceAndSearchAndClickSubmit()
    {
        ActionsHelper.actionClickStepClick("click on Task status",applicationTaskStatus);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("chose task status",applicationChoseTaskStatus);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on  مستلم من",applicationTaskReceivedFromList);
        ActionsHelper.actionClickStepClick("chose مستلم من ",applicationChoseTaskReceivedFrom);
        ActionsHelper.actionClickStepClick("click on البحث  ",applicationIDSearchButton);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on اتخذ الاجراء",applicationTakeAction);
        ActionsHelper.driverWait(5000);
        driver.get().switchTo().frame(0);
        ActionsHelper.actionClickStepClick("click on الموافقة",applicationApproveButton);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().alert().accept();
    }
    public void openNewTap()
    {
        logManager.STEP("open new payment on new tap","this method to check from card status on new tap");
        ActionsHelper.driverWait(3000);
        ((JavascriptExecutor)driver.get()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.get().getWindowHandles());
        driver.get().switchTo().window(tabs.get(1));
        ActionsHelper.navigate(urls.paymentList);
        loginPage.loginWithUser(UserType.PaymentSectionHead);
        clickOnSchedulePayment();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(applicationFullData);
        List<WebElement> tableBodyDataIsPayment=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(3000);
        if(!tableBodyDataIsPayment.get(8).getText().equalsIgnoreCase("in progress"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsPayment.get(8).getText()+ "]  while expected result should [in progress]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsPayment.get(8).getText()+"]" ,false);

        }
        ActionsHelper.driverWait(3000);
        driver.get().navigate().refresh();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);

        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(applicationFullData);
        List<WebElement> tableBodyDataIsBending=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(3000);
        if(!tableBodyDataIsBending.get(8).getText().equalsIgnoreCase("pending"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsBending.get(8).getText()+ "]  while expected result should [pending]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsBending.get(8).getText()+"]" ,false);

        }
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().window(tabs.get(0));
    }

    public void clickOnSchedulePayment()
    {
        logManager.STEP("search on application","this method for search on application");
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("Click on المدفوعات", paymentMenuItem);
        ActionsHelper.actionClickStepClick("click on جداول الدفع ", paymentsTableSubMenuItem);
        searchOnApplication();
    }
    public void clickOnCardAndCardProcedures()
    {
        logManager.STEP("go to اجراءات البطاقات","this method for go to اجراءات البطاقات");

        ActionsHelper.actionClickStepClick("click on card",applicationCard);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on اجراءات البطاقات",applicationCardProcedures);
        ActionsHelper.driverWait(4000);
    }

    public void paymentScenario2()
    {
        logManager.STEP("change card status","this method to changed card status");

        requestManualCard();

        addTaskAndCheckFromData();

        logOut();
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
       clickOnCardAndCardProcedures();
        //TODO move to urls
        ActionsHelper.navigate(urls.UpdateCardSummery);
        ActionsHelper.driverWait(3000);
        searchOnApplication();
        ActionsHelper.scrollTo( applicationTableCard);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on حالة البطاقة",applicationCardStatusList);
       ActionsHelper.driverWait(2000);
       ActionsHelper.actionClickStepClick("update crd status",applicationChoseUpdateCardStatus);
        ActionsHelper.driverWait(2000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on submit",applicationSubmit);
        ActionsHelper.driverWait(5000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(3000);
        logOut();
        loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on قائمة المهام",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch,"Approval of card Status");
        ActionsHelper.actionClickStepClick("chose Approval of card Status",applicationChoseApprovalOfCardStatus);
        ActionsHelper.driverWait(2000);
        fillTaskSourceAndSearchAndClickSubmit();

        ActionsHelper.driverWait(7000);
        ActionsHelper.actionClickStepClick("click on البطاقات",applicationCard);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on ملخص البطاقة ",applicationCardSummary);
        searchOnApplication();

        ActionsHelper.scrollTo(applicationTableCardResult);
        ActionsHelper.driverWait(4000);
        List<WebElement> tableBodyDataToCard=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(3000);
        if(!tableBodyDataToCard.get(10).getText().equalsIgnoreCase("Stolen"))
        {

            logManager.ERROR("Actual Date  [" +tableBodyDataToCard.get(10).getText()+ "]  while expected result should [Stolen]   ", false);

        }
        else
        {

            logManager.INFO("the card status is    ["+tableBodyDataToCard.get(10).getText()+"] ",false);

        }
        if(tableBodyDataToCard.get(5).getText().equalsIgnoreCase("No Active or Inactive Card Available"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataToCard.get(7).getText()+ "]  while expected result should card id  ", false);

        }
        else
        {
            logManager.INFO("the card Id is    ["+tableBodyDataToCard.get(7).getText()+"] ",false);

        }


    }
    public void paymentScenario3()
    {
        logManager.STEP("close card ","this method to close card");

      requestManualCard();
        addTaskAndCheckFromData();

        logOut();
      loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        clickOnCardAndCardProcedures();
        ActionsHelper.driverWait(2000);
        ActionsHelper.navigate(urls.closeCard);
        searchOnApplication();
        ActionsHelper.scrollTo(closeCardTable);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on close card",applicationCloseCard);
        ActionsHelper.driverWait(4000);
        driver.get().switchTo().frame(0);
        ActionsHelper.actionClickStepClick("click on تاكيد",applicationSubmitButton);
        ActionsHelper.driverWait(5000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        logOut();
        loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on قائمة المهام",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch,"Change card status API");
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose Change card status API",applicationChoseChangeCardStatusAPI);
        ActionsHelper.driverWait(2000);
        fillTaskSourceAndSearchAndClickSubmit();
        ActionsHelper.driverWait(7000);
       clickOnCardAndCardSummary();
        ActionsHelper.scrollTo(applicationTableCardResult);
        List<WebElement> tableBodyDataAfterClose=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataAfterClose.get(10).getText().equalsIgnoreCase("Pending Closure From Bank"))
        {

            logManager.ERROR("Actual Date  [" +tableBodyDataAfterClose.get(10).getText()+ "]  while expected result should [Pending Closure From Bank]   ", false);

        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataAfterClose.get(10).getText()+"]" ,false);
        }

      clickOnRequestManualCardAndCheckFromPlusIcon();
    }
    public void clickOnRequestManualCardAndCheckFromPlusIcon()
    {
        ActionsHelper.driverWait(4000);
        ActionsHelper.navigate(urls.requestNewCard);
        ActionsHelper.driverWait(4000);
        searchOnApplication();
        if(ActionsHelper.element(applicationAddNew).isDisplayed())
        {
            logManager.INFO("i can add a new card " ,false);

        }
        else
        {
            logManager.ERROR("Actual Date ican't add anew card while expected result should be able to click on + icon  ", false);

        }
    }
public void paymentScenario5()
{
    logManager.STEP("Create Variances – Overpayment  ","this method to Create Variances – Overpayment  for card");


    requestManualCard();
    addTaskAndCheckFromData();
    logOut();
    loginByPaymentSeniorSpecialist();
    ActionsHelper.driverWait(4000);
    paymentAndVariancePayment();
    ActionsHelper.actionClickStepClick("add Variance Payment",applicationAddVariancePayment);
    ActionsHelper.driverWait(3000);
    driver.get().switchTo().alert().accept();
    ActionsHelper.driverWait(5000);
    ActionsHelper.sendKeys(addOverPaymentValue,"100");
ActionsHelper.driverWait(2000);
    addReasonAndType();
            logOut();
            loginByPaymentSectionHead();
    ActionsHelper.driverWait(4000);
    ActionsHelper.actionClickStepClick("click on قائمة المهام",applicationTaskList);
    ActionsHelper.driverWait(5000);
    selectCurrentDateAndSendItToTask();
    ActionsHelper.sendKeys(applicationTaskSearch,"Approval of OverPayment");
    ActionsHelper.actionClickStepClick("chose  Approval of over Payment",applicationChoseApprovalOfOverPayment);
    ActionsHelper.driverWait(2000);
    clickOnTakeAction();
    ActionsHelper.driverWait(7000);
    clickOnSchedulePayment();
    ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
    ActionsHelper.driverWait(3000);
    ActionsHelper.actionClickStepClick("click on نوع الجدول",clickONSchudelType);
    ActionsHelper.driverWait(1000);
    ActionsHelper.actionClickStepClick("chose payment",choseSchdulePayment);
    ActionsHelper.driverWait(3000);
    ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
    ActionsHelper.driverWait(2000);
    ActionsHelper.scrollTo(applicationFullData);
    ActionsHelper.driverWait(2000);
    List<WebElement> tableBodyDataAfterClose=driver.get().findElements(By.className("TableRecords_OddLine"));
    if(!tableBodyDataAfterClose.get(7).getText().equalsIgnoreCase("د.إ. -100.00"))
    {

        logManager.ERROR("Actual Date  [" +tableBodyDataAfterClose.get(7).getText()+ "]  while expected result should [د.إ. -100.00]   ", false);

    }
    else
    {
        logManager.INFO("the payment value  is    ["+tableBodyDataAfterClose.get(7).getText()+"]" ,false);
    }

}
    public void addReasonAndType()
    {
        ActionsHelper.scrollTo(applicationVariancePaymentType);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("click on نوع التباين",applicationVariancePaymentType);
        ActionsHelper.driverWait(2000);

        ActionsHelper.actionClickStepClick("chose payment type",applicationChosePaymentType);
        String generatedString = rng.ints(StaticValues.leftLimit, StaticValues.rightLimit + 1)
                .limit(StaticValues.targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(applicationReason,generatedString);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on submit",applicationSubmit);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(applicationCard);
        ActionsHelper.driverWait(2000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
    }
    public void paymentAndVariancePayment()
    {
        logManager.STEP("go to مدفوعات التباين","this method to go المدفوعات التباين");
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on المدفوعات",paymentMenuItem);
        ActionsHelper.actionClickStepClick("click on مدفوعات التباين",applicationVariancePayment);
        searchOnApplication();

    }
    public void paymentScenario6()
    {
        logManager.STEP("Create Variances – downpayment  ","this method to Create Variances – downpayment  for card");

        requestManualCard();
        addTaskAndCheckFromData();
        logOut();
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        paymentAndVariancePayment();

        ActionsHelper.actionClickStepClick("click on minus icon Variance Payment",applicationMinusIcon);

        ActionsHelper.driverWait(4000);
        driver.get().switchTo().frame(0);

        ActionsHelper.driverWait(2000);
        ActionsHelper.sendKeys(applicationMinusValue,"1000");
        ActionsHelper.driverWait(1000);

        ActionsHelper.sendKeys(applicationMinusMonthValue,"4");
        String generatedString = rng.ints(StaticValues.leftLimit, StaticValues.rightLimit + 1)
                .limit(StaticValues.targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        ActionsHelper.sendKeys(applicationReason,generatedString);
        ActionsHelper.actionClickStepClick("click on type",applicationMinusVarianceType);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("chose type",applicationMinusChoseType);
        ActionsHelper.actionClickStepClick("click on submit",applicationSubmit);
        ActionsHelper.driverWait(5000);
        ActionsHelper.navigate(urls.paymentList);
        ActionsHelper.driverWait(3000);

        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        logOut();

        loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on قائمة المهام",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch," Approval of UnderPayment");
        ActionsHelper.actionClickStepClick("chose  Approval of UnderPayment",applicationChoseApprovalOfUnderPayment);
        ActionsHelper.driverWait(2000);
        fillTaskSourceAndSearchAndClickSubmit();
        ActionsHelper.driverWait(7000);
        clickOnSchedulePayment();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(5000);
        ActionsHelper.actionClickStepClick("click on نوع الجدول",clickONSchudelType);
        ActionsHelper.driverWait(1000);
        ActionsHelper.actionClickStepClick("chose payment",choseSchdulePayment);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
        ActionsHelper.driverWait(2000);
        System.out.println(ActionsHelper.element(numberOfRecord).getText());
        System.out.println(ActionsHelper.element(numberOfRecord).getText().charAt(0));
        ActionsHelper.scrollTo(numberOfRecord);
    char record=ActionsHelper.element(numberOfRecord).getText().charAt(0);
        ActionsHelper.driverWait(6000);
        if(record=='4')
        {
            logManager.INFO("sum of record  is equal 4 ",false);

        }
        else
        {
            logManager.ERROR("Actual Date  [" +ActionsHelper.element(numberOfRecord).getText().trim()+ "]  while expected result should 12  ", false);

        }
    }
    public void loginByPaymentSectionHead()
    {
        logManager.STEP("login by PaymentSectionHead ","login by PaymentSectionHead");
    ActionsHelper.driverWait(5000);

    loginPage.loginWithUser(UserType.PaymentSectionHead);
    }
    public void loginByPaymentSeniorSpecialist()
    {
        logManager.STEP("login by PaymentSeniorSpecialist ","login by PaymentSeniorSpecialist");
        loginPage.loginWithUser(UserType.PaymentSeniorSpecialist);
    }

    public void selectCurrentDateAndSendItToTask()
    {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(StaticValues.DateTimeFormatDayMonthYear);
    LocalDateTime now = LocalDateTime.now();
    System.out.println(dtf.format(now));
    ActionsHelper.driverWait(4000);
    ActionsHelper.actionClickStepClick("click",applicationTaskFromDate);
        ActionsHelper.driverWait(4000);

        ActionsHelper.sendKeys(applicationTaskFromDate,dtf.format(now));
        ActionsHelper.driverWait(4000);
    ActionsHelper.actionClickStepClick("click on مصدر المهمه",applicationTaskSource);
    ActionsHelper.driverWait(1000);
    }
    private static String convertDate(String strDate) {

        DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern(StaticValues.DateTimeFormatDayMonthYear)
                .toFormatter();

        LocalDate parsedDate = LocalDate.parse(strDate, f);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern(StaticValues.DateTimeFormatYearMonthDay);

        String newDate = parsedDate.format(f2);

        return newDate;
    }

    public void searchOnApplication()
    {
        logManager.STEP("search on Application(ssp)","this method to search on ssp");
        ActionsHelper.element(applicationIDSearchBox).clear();
        ActionsHelper.driverWait(5000);
        ActionsHelper.sendKeys(applicationIDSearchBox, StaticValues.refCode);
        ActionsHelper.retryClick(applicationIDSearchBox, 30);
        ActionsHelper.actionClickStepClick("click on search ", applicationIDSearchButton);
        ActionsHelper.driverWait(5000);



    }
    public void paymentScenario4()
    {
    logManager.STEP("block card","this method to check block card sceniro");
        requestManualCard();
        addTaskAndCheckFromData();
        logOut();
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        clickOnCardAndCardProcedures();
        ActionsHelper.driverWait(2000);
        ActionsHelper.navigate(urls.closeCard);
        searchOnApplication();
        ActionsHelper.scrollTo(closeCardTable);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on close card",applicationBlockCardButton);
        ActionsHelper.driverWait(4000);
        driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on تاكيد",applicationSubmitButton);
        ActionsHelper.driverWait(5000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        logOut();
        loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on قائمة المهام",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch,"Change card status API");
        ActionsHelper.actionClickStepClick("chose Change card status API",applicationChoseChangeCardStatusAPI);
        ActionsHelper.driverWait(2000);
        fillTaskSourceAndSearchAndClickSubmit();
        ActionsHelper.driverWait(7000);
        clickOnCardAndCardSummary();
        ActionsHelper.scrollTo(applicationTableCardResult);

        List<WebElement> tableBodyDataAfterBlock=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataAfterBlock.get(10).getText().equalsIgnoreCase("Blocked By Customer"))
        {

            logManager.ERROR("Actual Date  [" +tableBodyDataAfterBlock.get(10).getText()+ "]  while expected result should [Pending Closure From Bank]   ", false);

        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataAfterBlock.get(10).getText()+"]" ,false);
        }
        clickOnRequestManualCardAndCheckFromPlusIcon();
    }
    public void clickOnCardAndCardSummary()
    {
        logManager.STEP("go to ملخص البطاقة","this method to go to ملخص البطاقه");
        ActionsHelper.actionClickStepClick("click on البطاقات",applicationCard);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on ملخص البطاقة ",applicationCardSummary);
        searchOnApplication();
    }
    public void paymentScenario7()
    {
        logManager.STEP("Update Payments Instructions ","this method to Update Payments Instructions ");
        requestManualCard();
        addTaskAndCheckFromData();
        logOut();
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        createInstruction();
        ActionsHelper.driverWait(7000);
        ActionsHelper.scrollTo(applicationApproveInstruction);
        ActionsHelper.driverWait(2000);
        ActionsHelper.actionClickStepClick("click on تقديم للموافقة",applicationApproveInstruction);
        ActionsHelper.driverWait(10000);
        driver.get().switchTo().alert().accept();
        ActionsHelper.driverWait(5000);
        openNewTap();
        logOut();
        ActionsHelper.driverWait(4000);
        loginByPaymentSectionHead();
        createTaskInstruction();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(applicationFullData);
        List<WebElement> tableBodyDataIsBending=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataIsBending.get(8).getText().equalsIgnoreCase("pending"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsBending.get(8).getText()+ "]  while expected result should [pending]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsBending.get(8).getText()+"]" ,false);

        }
        updateInstruction();
        ActionsHelper.actionClickStepClick("chose the Interfaced to ERP",applicationChoseInterfacedToERP);

        saveStatusAndGoToPayment();

        List<WebElement> tableBodyDataIsPaid=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataIsPaid.get(8).getText().equalsIgnoreCase("paid"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsPaid.get(8).getText()+ "]  while expected result should [Paid]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsPaid.get(8).getText()+"]" ,false);

        }
        ActionsHelper.driverWait(3000);
        updateInstruction();
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("chose the Failed",applicationChoseFailed);
        saveStatusAndGoToPayment();
        List<WebElement> tableBodyDataIsBendingAfterFailed=driver.get().findElements(By.className("TableRecords_OddLine"));
        if(!tableBodyDataIsBendingAfterFailed.get(8).getText().equalsIgnoreCase("pending"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsBendingAfterFailed.get(8).getText()+ "]  while expected result should [pending]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsBendingAfterFailed.get(8).getText()+"]" ,false);

        }
    }
    public void saveStatusAndGoToPayment()
    {
        logManager.STEP("save card status and go to payment","this method to save status and go to payment ");
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on save",applicationSavedUpdate);
        ActionsHelper.driverWait(3000);
        driver.get().switchTo().frame(0);
        ActionsHelper.actionClickStepClick("click on تاكيد ",applicationSubmit);
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(paymentMenuItem);
        clickOnSchedulePayment();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(2000);
        ActionsHelper.scrollTo(applicationFullData);
    }
    public void updateInstruction()
    {
        logManager.STEP("update instruction","this method too go update instruction page and click on it ");
       ActionsHelper.navigate(urls.updateInstruction);
        ActionsHelper.driverWait(5000);
        searchOnApplication();
        ActionsHelper.scrollTo(applicationTableUpdate);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on status",applicationStatusByUpdate);
    }
    public void paymentScenario10()
    {
       /* logManager.STEP("Update Payments Status – Terminate payment ","this method to Update Payments Status – Terminate payment ");
        requestManualCard();
        ActionsHelper.driverWait(3000);
        addTaskAndCheckFromData();
        logOut();
        ActionsHelper.driverWait(5000);
        loginByPaymentSeniorSpecialist();*/
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("CLICK on المدفوعات",paymentMenuItem);
        ActionsHelper.actionClickStepClick("click on اجراءات الدفع ",applicationPaymentAction);
        searchOnApplication();
        ActionsHelper.actionClickStepClick("click on ايقاف المدفوعات",applicationHoldPaymentPowerOff);
        ActionsHelper.driverWait(4000);
        acceptPaymentAndCheckFromStatus();

        ActionsHelper.driverWait(3000);
        List<WebElement> tableBodyDataIsTerminated=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(5000);
        if(!tableBodyDataIsTerminated.get(8).getText().equalsIgnoreCase("Terminated"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsTerminated.get(8).getText()+ "]  while expected result should [Suspended]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsTerminated.get(8).getText()+"]" ,false);

        }
    }

    public void paymentScenario8()
    {
        logManager.STEP("Update Payments Status – Hold payment  ","this method to Update Payments Status – Hold payment  ");
        requestManualCard();

        addTaskAndCheckFromData();
        logOut();
        ActionsHelper.driverWait(5000);
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("CLICK on المدفوعات",paymentMenuItem);
        ActionsHelper.actionClickStepClick("click on اجراءات الدفع ",applicationPaymentAction);
        searchOnApplication();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on حجز المدفوعات",applicationHoldPaymentButton);

       ActionsHelper.driverWait(3000);
        acceptPaymentAndCheckFromStatus();
        ActionsHelper.driverWait(3000);
        List<WebElement> tableBodyDataIsPending=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(3000);
        if(!tableBodyDataIsPending.get(8).getText().equalsIgnoreCase("Suspended"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsPending.get(8).getText()+ "]  while expected result should [Suspended]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsPending.get(8).getText()+"]" ,false);

        }
    }
    public void acceptPaymentAndCheckFromStatus()
    {
        logManager.STEP("accept paymet ","this method to continue accepet payment and check from status ");
     driver.get().switchTo().frame(0);
        ActionsHelper.driverWait(3000);
        ActionsHelper.actionClickStepClick("click on تاكيد",applicationSubmit);


            ActionsHelper.driverWait(4000);
            ActionsHelper.navigate(urls.paymentList);
        ActionsHelper.driverWait(4000);
        logOut();

        loginByPaymentSectionHead();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on قائمة المهام ",applicationTaskList);
        ActionsHelper.driverWait(5000);
        selectCurrentDateAndSendItToTask();
        ActionsHelper.sendKeys(applicationTaskSearch," Approval of Payment Status");
        ActionsHelper.actionClickStepClick("chose  Approval of Payment Status",applicationApprovalOfPaymentStatus);
        ActionsHelper.driverWait(2000);
        clickOnTakeAction();
        ActionsHelper.driverWait(4000);
        ActionsHelper.scrollTo(paymentMenuItem);
        ActionsHelper.driverWait(5000);
        clickOnSchedulePayment();
        ActionsHelper.actionClickStepClick("click on عرض موسع",applicationExpendedView);
        ActionsHelper.driverWait(5000);
        ActionsHelper.scrollTo(applicationFullData);
        ActionsHelper.driverWait(3000);
    }
    public void paymentScenario9()
    {
        logManager.STEP("Update Payments Status – Release payment ","this method to Update Payments Status – Release payment ");
        ActionsHelper.driverWait(2000);
        driver.get().navigate().refresh();
        ActionsHelper.driverWait(2000);
        logOut();
        ActionsHelper.driverWait(5000);
        loginByPaymentSeniorSpecialist();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on المدفوعات",paymentMenuItem);
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on اجراءات الدفع",applicationPaymentAction);
        searchOnApplication();
        ActionsHelper.driverWait(4000);
        ActionsHelper.actionClickStepClick("click on نشر الدفعات",applicationReleasePayment);
        ActionsHelper.driverWait(4000);
        acceptPaymentAndCheckFromStatus();
        ActionsHelper.driverWait(3000);
        List<WebElement> tableBodyDataIsPendingAfterS9=driver.get().findElements(By.className("TableRecords_OddLine"));
        ActionsHelper.driverWait(3000);
        if(!tableBodyDataIsPendingAfterS9.get(8).getText().equalsIgnoreCase("Pending"))
        {
            logManager.ERROR("Actual Date  [" +tableBodyDataIsPendingAfterS9.get(8).getText()+ "]  while expected result should [Pending]   ", false);
        }
        else
        {
            logManager.INFO("the card status is    ["+tableBodyDataIsPendingAfterS9.get(8).getText()+"]" ,false);

        }
    }
    public void logOut() {

        try {

            ActionsHelper.driverWait(6000);
            ActionsHelper.scrollTo(logo);
            ActionsHelper.driverWait(5000);
            ActionsHelper.actionClickStepClick("click on logout",userNameBeforeLogout);
            ActionsHelper.driverWait(4000);

        } catch (NoSuchElementException e) {//
           logManager.WARN("dose not interact with logout");
        }
}}
