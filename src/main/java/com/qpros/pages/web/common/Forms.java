package com.qpros.pages.web.common;

import com.qpros.common.Utils;
import com.qpros.common.annotation.STEP;
import com.qpros.common.web.Base;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.qpros.pages.web.data.TestData;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.qpros.common.Utils.uploadfile;
import static com.qpros.helpers.ActionsHelper.*;

@Getter
public class Forms extends Base {
    public Forms(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "customerEmail")
    private static WebElement customerEmailTextBox;

    @FindBy(id = "phone")
    private static WebElement phoneTextBox;

    @FindBy(id = "address.idNumber")
    private static WebElement addressedNumberTextBox;

    @FindBy(id = "inputFullAddress")
    private static WebElement inputFullAddressTextBox;


    @FindBy(id = "emirates")
    private static WebElement citiesSelect;

    @FindBy(id = "inputLandmark")
    private static WebElement inputLandmarkTextBox;

    @FindBy(id = "checkValidate")
    private static WebElement checkValidate;

    @FindBy(id = "address.idNumber")
    private static WebElement addressidNumber;

    @FindBy(id = "address.firstName")
    private static WebElement addressfirstName;

    @FindBy(id = "nationality")
    private static WebElement nationality;

    @FindBy(id = "gender")
    private static WebElement gender;

    @FindBy(id = "expiryDatePd")
    private static WebElement expiryDatePd;

    @FindBy(id = "dateOfBirthPd")
    private static WebElement dateOfBirthPd;

    @FindBy(id = "openImageFrontOcrBtn")
    private static WebElement openImageFrontOcrBtn;

    @FindBy(id = "openImageBackOcrBtn")
    private static WebElement openImageBackOcrBtn;

    @FindBy(xpath = "//*[@id=\"eid-reconfirmation\"]/div/div/div/div[2]/div/div/div/div[3]/a[2]")
    private static WebElement yesMatchCheckBox;

    @FindBy(css = ".pill-buttons .radio-group:nth-child(1)")
    private static WebElement maleradio;//.pill-buttons .radio-group:nth-child(1) > label

    @FindBy(name = "dob")
    private static WebElement dateOfBirthDatePicker;

    @FindBy(name = "address-floorno")
    private static WebElement floorNumberTxtBox;

    @FindBy(css = ".group:nth-child(4) .du-custom-radio:nth-child(3) .du-radio-details-val")
    private static WebElement TechnicalVisitTimeSlot1;

    @FindBy(css = ".group:nth-child(5) .du-custom-radio:nth-child(1) .du-radio-details-val")
    private static WebElement TechnicalVisitTimeSlot2;

    @FindBy(css = ".module-icon:nth-child(1) > .icon")
    private static WebElement bookTechnicianVisitGroupBox;

    @FindBy(css = ".checkbox-group > label")
    private static WebElement agreeCheckBox;

    @FindBy(css = ".checkbox-group > label")
    private WebElement agree;

    @FindBy(name = "address-aptno")
    private WebElement departmentNumberTextBox;

    @FindBy(xpath = "//*[@id=\"sticky1\"]/div[2]/fieldset[4]/div/div/div[1]/input")
    private WebElement nationality2;

    @FindBy(name = "fullname")
    private WebElement fullnameTextBox;

    @FindBy(name = "contact")
    private WebElement contactTextBox;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name = "fullAddress")
    private WebElement fullAddressTextBox;

    @FindBy(name = "emirate")
    private WebElement emirateDropdown;

    @FindBy(id = "emirates-selectized")
    private WebElement emiratesDropdown;

    @FindBy(id = "other-zone-area")
    private WebElement otherAreaTextBox;

    @FindBy(name = "building_name")
    private WebElement buildingNameTextBox;

    @FindBy(name = "floor_number")
    private WebElement floorNumberTextBox;

    @FindBy(name = "apartment_number")
    private WebElement apartmentNumberTextBox;

    @FindBy(id = "relocation_landing")
    private WebElement relocationLanding;

    @FindBy(id = "file-drop-single")
    private WebElement tenancyContracTtitleDeedDiv;

    @FindBy(id = "js-fileNameUploaded")
    private WebElement fileNameUploaded;

    @FindBy(name = "contact")
    private WebElement contactNumberTextBox;

    @FindBy(name = "file-drop-button")
    private WebElement selectFile;

    @FindBy(name = "inzone-area-selectized")
    private WebElement inZoneDropDown;


    @STEP(name = "fill Form ", description = "fill Form ")
    public synchronized  void fillForm() { // 971551499312 postpaid
        logManger.STEP("Enter all mandatory details on the personal details page", "");
        driverWait(6000);
        logManger.INFO("The User Enter Order Details on checkOut", false);
        sendKeys(customerEmailTextBox, TestData.CUSTOMER_EMAIL);
        sendKeys(phoneTextBox, TestData.PHONE_NUMBER);
        addressedNumberTextBox.sendKeys("784-1991-4063247-4");
        scrollTo(customerEmailTextBox);
        ActionsHelper.sendKeys(addressfirstName, TestData.ADDRESS_FIRST_NAME);
        Select nationalities = new Select(nationality);
        nationalities.selectByVisibleText(TestData.NATIONALITY);
        Select genders = new Select(gender);
        genders.selectByVisibleText(TestData.GENDER);
        isElementPresent(dateOfBirthPd);
        dateOfBirthPd.click();
        dateOfBirthPd.sendKeys(TestData.BIRTHDAY);
        isElementPresent(expiryDatePd);
        expiryDatePd.click();
        expiryDatePd.sendKeys(TestData.EXPIRY_DATE);
        sendKeys(inputFullAddressTextBox, TestData.FULL_ADDRESS);
        Select cities = new Select(citiesSelect);
        cities.selectByVisibleText(TestData.CITY);
        scrollTo(checkValidate);
        driverWait(1000);
        ActionsHelper.waitToBeClickable(element(By.xpath("//*[@id=\"personalDetailsForm\"]/div[1]/fieldset[6]/div")), 5000);
        isElementPresent(By.cssSelector("label:nth-child(2)"));
        retryClick(driver.get().findElement(By.cssSelector("label:nth-child(2)")), 60);


    }

    /**
     * this Fill form used to fill the forms related to Postpaid1200NocontractNational and MobilePhonesNewCustomerFlexiorNational
     * @throws Exception
     */
    @STEP(name = "fill Form With Uploader", description = "fill Form With Uploader")
    public synchronized void fillFormWithUploaders(){
        sendKeys(customerEmailTextBox, TestData.CUSTOMER_EMAIL);
        ActionsHelper.sendKeys(phoneTextBox, TestData.PHONE_NUMBER);
        ActionsHelper.sendKeys(inputFullAddressTextBox, TestData.FULL_ADDRESS);
        Select cities = new Select(citiesSelect);
        cities.selectByVisibleText(TestData.CITY);
        scrollTo(openImageFrontOcrBtn);
        uploadfile(driver.get(), "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\s1.jpg", openImageFrontOcrBtn);
        uploadfile(driver.get(), "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\s2.jpg", openImageBackOcrBtn);
        driverWait(5000);
        moveToElement(checkValidate);
        moveToElement(driver.get().findElement(By.id("checkInvoice")));
        //moveToElement(driver.get().findElement(By.id("checkDqFlag")));


    }

    @STEP(name = "fill Zone Form", description = "fill Zone Form")
    public synchronized void fillZoneForm() {
        sendKeys(By.name("email"), TestData.CUSTOMER_EMAIL);
        sendKeys(By.name("contact"), TestData.PHONE_NUMBER);
        sendKeys(By.id("emiratesId"), TestData.ID_NUMBER);
        sendKeys(By.name("fullName"), TestData.FULL_NAME);
        retryClick(By.name("emiratesIdExpiry"), 30);
        sendKeys(By.name("emiratesIdExpiry"), "11/11/2023" + Keys.RETURN);
        scrollTo(nationality2);
        sendKeys(nationality2, TestData.NATIONALITY + Keys.RETURN);
        retryClick(maleradio, 60);
        moveToElement(dateOfBirthDatePicker);
        retryClick(dateOfBirthDatePicker, 60);
        dateOfBirthDatePicker.sendKeys(TestData.BIRTHDAY);
        floorNumberTxtBox.sendKeys(TestData.FLOOR_NUMBER);
        departmentNumberTextBox.sendKeys(TestData.DEPARTMENT_NUMBER);
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", bookTechnicianVisitGroupBox);
        ActionsHelper.driverWait(1000);
        retryClick(TechnicalVisitTimeSlot1, 30);
        retryClick(TechnicalVisitTimeSlot2, 30);
        List<WebElement> uploader = driver.get().findElements(By.className("file-drop-single"));
        moveToElement(uploader.get(0));
        AtomicInteger indexOfElement= new AtomicInteger();
        uploader.stream().forEachOrdered(webElement -> {
            indexOfElement.getAndIncrement();
            scrollTo(webElement);
            uploadfile(driver.get(),"E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\" + indexOfElement + ".PNG",webElement);
            driverWait(3000);
        });

//        for (int i = 0; i < uploader.size(); i++) {
//            int k = i + 1;
//            scrollupTo(uploader.get(i));// manditory
//            uploadfile(driver.get(), "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\resources\\images\\" + k + ".PNG", uploader.get(i));
//            driverWait(3000);
//        }

        ((JavascriptExecutor) driver.get()).executeScript("document.getElementsByClassName(\"du-new-nav\")[0].style.zIndex=0;");
        driverWait(1000);
        scrollTo(agreeCheckBox);
        retryClick(agreeCheckBox, 30);

    }

    @STEP(name = "fill Form2", description = "fill Form2")
    public synchronized void fillForm2(WebDriver driver) {
        waitVisibility(fullnameTextBox, 90);
        fullnameTextBox.sendKeys(TestData.FULL_NAME);
        contactTextBox.sendKeys("508101131");//971508101131
        emailTextBox.sendKeys(TestData.CUSTOMER_EMAIL);
        fullAddressTextBox.sendKeys(TestData.FULL_ADDRESS);
        scrollTo(emirateDropdown);
        Select emirates = new Select(emirateDropdown);
        emirates.selectByVisibleText(TestData.CITY);
        List<WebElement> uploaders = driver.findElements(By.className("file-drop-button"));
        for (int i = 0; i < uploaders.size(); i++) {
            int k = i + 1;
            ActionsHelper.scrollToEndOfPage();
            uploadfile(driver, "E:\\workspace\\DU-Rana\\Q-Pros Automation Framework -Rana-The Latest one\\src\\main\\resources\\images\\" + k + ".PNG", uploaders.get(i));
            driverWait(5000);
        }

        moveToElement(agree);


    }

    @STEP(name = "fill New Address Form", description = "fill New Address Form")
    public synchronized void fillNewAddressForm(WebDriver driver, String Zone) {
        String Location = (Zone == "IN") ? "Dubai" : "Ajman";
        waitVisibility(emiratesDropdown, 60);
        String CurrentAddress = driver.findElements(By.xpath("//p")).get(0).getText();
        System.out.println(CurrentAddress.substring(CurrentAddress.lastIndexOf(",") + 1));
        String CurrentCity = CurrentAddress.substring(CurrentAddress.lastIndexOf(",") + 1);
        emiratesDropdown.sendKeys(Location + Keys.RETURN);
        moveToElement(otherAreaTextBox);
        if (Zone == "OUT") {
            waitVisibility(otherAreaTextBox, 60);
            otherAreaTextBox.sendKeys("TEST");
        } else {
            waitVisibility(inZoneDropDown, 60);
            while (isElementPresent(inZoneDropDown)) {
                inZoneDropDown.sendKeys("Al Barsha 1");
                break;
            }
        }

        buildingNameTextBox.sendKeys("TEST");
        floorNumberTextBox.sendKeys("7"); // random number with one digit
        apartmentNumberTextBox.sendKeys("" + Utils.GenerateRandomNumberWith3digits());// 3 digits must be random number
        driver.findElement(By.id("comments")).sendKeys("TEST");
        contactNumberTextBox.sendKeys(Keys.RETURN);


    }
}
