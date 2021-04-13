package com.qpros.pages.web.DUAE;
import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qpros.pages.web.common.Forms;
import com.qpros.pages.web.data.TestData;
import com.qpros.pages.web.data.URL;
import java.util.List;

import static com.qpros.helpers.ActionsHelper.*;

//@Getter
public class DuPage extends  Base {
    public DuPage(WebDriver driver){
        PageFactory.initElements(Base.driver.get(), this);
    }

    @FindBy(css=".slick-current .carousel-thumb-title")
    private WebElement CarouselItemTitle;

    @FindBy(linkText="Support")
    private WebElement supportLinkText;

    @FindBy(linkText="Stores")
    private WebElement storesLinkText;

    @FindBy(linkText="Careers")
    private WebElement careersLinkText;

    @FindBy(linkText="Contact us")
    private WebElement contactUsLinkText;

    @FindBy(linkText="Login")
    private WebElement loginLinkText;

    @FindBy(className="btn")
    private WebElement buyOrLearnButtonOnCarousel;

    @FindBy(linkText="Shop postpaid plans")
    private WebElement shopPostpaidPlansButtonOnLatestOffers;

    @FindBy(id="companyname")
    private WebElement companyname;

    @FindBy(id="account_number")
    private WebElement account_number;

    @FindBy(id="fullname")
    private WebElement fullname;

    @FindBy(id="contactNumber")
    private WebElement contactNumber;

    @FindBy(id="emailAddress")
    private WebElement emailAddress;

    @FindBy(id="tax_number")
    private WebElement tax_number;

    @FindBy(id="trncertificate")
    private WebElement trncertificateBtn;

    @FindBy(id="emidfront")
    private WebElement emidfrontBtn;

    @FindBy(id="emidback")
    private WebElement emidbackBtn;

    @FindBy(css=".recaptcha-checkbox-border")
    private WebElement recaptchaCheckbox;

    @FindBy(className="sicon")
    private WebElement searchIcon;

    @FindBy(id="main-search-input")
    private WebElement mainsearchinputTextBox;

    @FindBy(id="main-search-btn")
    private WebElement mainSearchBtn;

    @FindBy(className="clickOnDatasimButtonGTM")
    private WebElement selectButton;

    @FindBy(className="clickAddtoCartOnDatasimButtonGTM")
    private WebElement continueButton;

    @FindBy(className="cartSummaryGenerateOTPButton")
    private WebElement confirmButton;

    @FindBy(xpath = "//*[@id=\"eid-reconfirmation\"]/div/div/div/div[2]/div/div/div/div[3]/a[2]")
    private WebElement yesMatch;

    @FindBy(css = ".du-customer-details-continue")
    private WebElement continueBtn;

    @STEP(name = "Verify Carousel",description = "Verify Carousel ")
    public  boolean Carousel()  {
        // each tab will opend after 8 seconds
        String carousalText;
        String ItemUrlTitle;
        String DIV;
        carousalText = CarouselItemTitle.getText();
        System.out.println(CarouselItemTitle.getText());
        if(CarouselItemTitle.getText().contains("Samsung"))
            carousalText = carousalText.replaceAll( "Samsung" , "Galaxy" );
        retryClick(buyOrLearnButtonOnCarousel,60);
        ItemUrlTitle = driver.get().getTitle();
        driverWait(10000);
        if(driver.get().findElements(By.className("no-gutters")).size()>0){
            DIV=driver.get().findElement(By.className("no-gutters")).getText();//postedpowerplan
            System.out.println(DIV);
        }else if(driver.get().findElements(By.className("spotlight__header")).size()>0){
             DIV=driver.get().findElement(By.className("spotlight__header")).getText();//prepaid + homeplan +raoming
            System.out.println(DIV);
        }else {
            DIV=ItemUrlTitle;
            System.out.println(ItemUrlTitle);
        }

        System.out.println(ItemUrlTitle);
        return DIV.replaceAll("\\s+", "").contains(carousalText.replaceAll("\\s+", ""));

    }
    @STEP(name = "Verify the latest Offers",description = "Verify the latest offers ")
    public boolean LatestOffers() {//cardsList-list
        boolean IsCorrect= true;
        List<WebElement> LatestOfferCardsList = driver.get().findElements(By.className("cardsList-list"));
        List<WebElement> ListOfOfferCards = LatestOfferCardsList.get(0).findElements(By.className("slick-slide"));
        String deviceName,deviceNameInDetailsPage;
        int index=0;
        for(int i=0 + index; i<ListOfOfferCards.size() -1; i++){// -1  to exclude Shop Post Paid Plan
            deviceName =  ListOfOfferCards.get(i).findElement(By.className("card-title")).getText();
            System.out.println(deviceName);
            retryClick(ListOfOfferCards.get(i).findElement(By.className("btn")),60);
            driverWait(5000);
            deviceNameInDetailsPage = driver.get().findElement(By.cssSelector(".text-h3 > span")).getText();
            System.out.println(deviceNameInDetailsPage);
            if(deviceName.replaceAll("\\s+","").contains(deviceNameInDetailsPage.replaceAll("\\s+","")))  // if(DeviceName != DeviceNameIndetailsPage)
            {
                index++;
                navigate(URL.DU_HOME_PAGE_URL);
                LatestOfferCardsList = driver.get().findElements(By.className("cardsList-list"));
                ListOfOfferCards = LatestOfferCardsList.get(0).findElements(By.className("slick-slide"));
            }
            else{
                System.out.println("this "+ deviceName +"offer failed to navigate to specific url");
                IsCorrect= false;
                break;
            }

        }

        return IsCorrect;

    }

    @STEP(name = "Latest Offers Shop PostPaid Plan",description = "Latest Offers Shop PostPaid Plan")
    public   boolean LatestOffersShopPostPaidPlan() {
        navigate(URL.DU_HOME_PAGE_URL);
        List<WebElement> SliderButtons = driver.get().findElements(By.xpath("//button[contains(@id,'slick-slide-control')]"));
        moveToElement(SliderButtons.get(1));
        retryClick(SliderButtons.get(1),60);
        retryClick(shopPostpaidPlansButtonOnLatestOffers,60);
        System.out.println(driver.get().getTitle());
        // if(DeviceName != DeviceNameindetailspage)
        return driver.get().getTitle().replaceAll("\\s+", "").contains("12 Months / No Contract".replaceAll("\\s+", ""));

    }

    @STEP(name = "click Login link text",description = "click Login link text ")
    public  String clickLoginlinktext()  {
        navigate(URL.DU_HOME_PAGE_URL);
        ScrollToTheEndOfThePage();
        retryClick(loginLinkText,60);
        driverWait(1000);
        return   driver.get().getCurrentUrl();

    }

    @STEP(name = "click Contactus link text",description = "click Contactus link text ")
    public  String clickContactuslinktext()  {
        navigate(URL.DU_HOME_PAGE_URL);
        ScrollToTheEndOfThePage();
        retryClick(contactUsLinkText,60);
        driverWait(1000);
        return driver.get().getCurrentUrl();
    }

    @STEP(name = "click Careers link text",description = "click Careers link text ")
    public  String clickCareersinktext()  {
        navigate(URL.DU_HOME_PAGE_URL);
        ScrollToTheEndOfThePage();
        retryClick(careersLinkText,60);
        driverWait(1000);
        return driver.get().getCurrentUrl();

    }

    @STEP(name = "click Stores link text",description = "click Stores link text ")
    public  String clickStoreslinktext()  {
        navigate(URL.DU_HOME_PAGE_URL);
        ScrollToTheEndOfThePage();
        retryClick(storesLinkText,60);
        driverWait(1000);
        return driver.get().getCurrentUrl();

    }

    @STEP(name = "click Support link text",description = "click Support link text")
    public  String clickSupportlinktext()  {
        navigate(URL.DU_HOME_PAGE_URL);
        ScrollToTheEndOfThePage();
        retryClick(supportLinkText,60);
        driverWait(1000);
        return driver.get().getCurrentUrl();

    }

    @STEP(name = "Home Page Personal to Business",description = "Home Page Personal to Business")
    public  String HomePagePersonaltoBusiness(){
        navigate(URL.DU_HOME_PAGE_URL);
        driver.get().navigate().to(URL.BUSINESS_URL);
        String title=driver.get().getTitle();
        driver.get().navigate().to(URL.DU_HOME_PAGE_URL);
        return title;
    }

    @STEP(name = "VAT Registration",description = "VAT Registration")
    public  void VAT(){
        navigate(URL.REGISTRATION_URL);
        companyname.sendKeys(TestData.COMPANY_NAME);
        account_number.sendKeys(TestData.ACCOUNT_NUMBER);
        fullname.sendKeys(TestData.FULL_NAME);
        contactNumber.sendKeys(TestData.CONTACT_NUMBER);
        emailAddress.sendKeys(TestData.EMAIL_ADDRESS);
        tax_number.sendKeys(TestData.TAX_NUMBER);
        trncertificateBtn.sendKeys("D:\\QProsCore\\src\\main\\resources\\Images\\s1.jpg");
        emidfrontBtn.sendKeys("D:\\QProsCore\\src\\main\\resources\\Images\\s1.jpg");
        emidbackBtn.sendKeys("D:\\QProsCore\\src\\main\\resources\\Images\\s1.jpg");
    }

    @STEP(name = "Scroll To The End Of The Page",description = "Scroll To The End Of The Page")
    public void ScrollToTheEndOfThePage()  {
        ((JavascriptExecutor) driver.get()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driverWait(1000);
    }

    @STEP(name = "Help and support",description = "Help and support")
    public  boolean Helpandsupport()  {
        String SearchText = "Postpaid";
        Boolean Result= true;
        navigate(URL.DU_HOME_PAGE_URL);
        retryClick(searchIcon,60);
        mainsearchinputTextBox.sendKeys(SearchText);
        retryClick(mainSearchBtn,60);
        driverWait(1000);
        System.out.println(driver.get().findElements(By.xpath("//h6")).size());
        if(driver.get().findElements(By.xpath("//h6")).size()>0)
        {
            String SearchResult= driver.get().findElements(By.xpath("//h6")).get(0).getText().toLowerCase();
            Result= SearchResult.contains(SearchText.toLowerCase());
          //  if(SearchResult.replaceAll("\\s+","").equalsIgnoreCase(SearchText.replaceAll("\\s+","")))
           // Result =  driver.get().findElements(By.xpath("//h6")).get(0).getText().contains(SearchText);
            System.out.println(SearchResult);
           // Result = SearchResult.replaceAll("\\s+","").contains(SearchText.replaceAll("\\s+",""));

        }else{
            System.out.println("there is not any result of your search");
        }
        System.out.println(Result);
        return Result;
        }

    @STEP(name = "Data Sim Only",description = "Data Sim Only")
    public  void DataSimOnly() {
        waitVisibility(element(By.xpath("//a[@href='https://www.du.ae/personal/shop/postpaid']")),30);
        retryClick(By.xpath("//a[@href='https://www.du.ae/personal/shop/postpaid']"),30);

        retryClick(selectButton,60);
        driverWait(1000);
        retryClick(continueButton,60);//Continue button take alot of time to be enabled
        waitVisibility(confirmButton,90);
        retryClick(confirmButton,60);
        Forms form=  new Forms(driver.get());
        form.fillForm();
        waitVisibility(yesMatch, 60);
        yesMatch.click();
        waitToBeClickable(continueBtn, 60);
        continueBtn.click();


    }
}
