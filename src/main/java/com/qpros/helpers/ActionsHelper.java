package com.qpros.helpers;

import com.qpros.common.LogManager;
import com.qpros.common.web.Base;
import com.qpros.reporting.QuantaTestManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class ActionsHelper extends Base {
    public static WebDriverWait wait;
    public static final LogManager logManger = new LogManager(ActionsHelper.class.getSimpleName());


    public static void waitForSeconds(Integer timeWait) {
        driver.get().manage().timeouts().implicitlyWait(timeWait, TimeUnit.SECONDS);
    }

    public static void navigate(String url) {
        logManger.STEP("Navigation to Page with URL : " + url, "");
        logManger.INFO("Navigation to Page with URL : " + url, false);
        driver.get().navigate().to(url);
    }

    public static boolean waitVisibility(WebElement element, int time) {
        boolean isElementPresent = false;
        try {
            wait = new WebDriverWait(driver.get(), time);
            wait.until(ExpectedConditions.visibilityOf(element));
            isElementPresent = element.isDisplayed();
        } catch (Exception e) {
            logManger.ERROR("Expected Element is not Visiable",false);
        }

        return isElementPresent;

    }

    public static void selectByIndex(WebElement element, int index) {
        try {
            Select make = new Select(element);
            make.selectByIndex(index);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void selectByValue(WebElement element, String value) {
        try {
            Select make = new Select(element);
            make.selectByValue(value);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollTo(By by) {
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", element(by));
    }

    public static void scrollTo(By by, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element(by));
    }



    public void safeJavaScriptClick(WebElement element) {
        try {
            if (element.isEnabled() && element.isDisplayed()) {

                ((JavascriptExecutor) driver.get()).executeScript("arguments[0].click();", element);
            } else {
                logManger.INFO("Unable to click on element", false);
            }
        } catch (StaleElementReferenceException e) {
            logManger.INFO("Element is not attached to the page document " + e.getLocalizedMessage(), false);
        } catch (NoSuchElementException e) {
            logManger.INFO("Element was not found in DOM " + e.getLocalizedMessage(), false);
        } catch (Exception e) {
            logManger.INFO("Unable to click on element " + e.getLocalizedMessage(), false);
        }
    }

    public String getImagePath(String imageName) {
        return System.getProperty("user.dir") + "/src/main/resources/images/" + imageName;
    }

    public String getXMLPath(String xmlFileName) {
        return System.getProperty("user.dir") + "/src/main/resources/xmlfiles/" + xmlFileName;
    }

    public static String getTodayDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

    public static String getCurrentUrl() {
        return driver.get().getCurrentUrl();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        return driver.get().getTitle();
    }

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitle(String title) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.titleIs(title));
    }

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitleContains(String title) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.titleContains(title));
    }

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    public boolean checkPageUrlToBe(String url) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.urlToBe(url));
    }

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    public boolean checkPageUrlContains(String fraction) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */

    public boolean checkPageUrlMatches(String regex) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.urlMatches(regex));
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement waitForExpectedElement(final By by) {

        JavascriptExecutor jse = (JavascriptExecutor) driver.get();
        jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
        driverWait(3000);
        wait = new WebDriverWait(driver.get(), 10);
        return wait.until(visibilityOfElementLocated(by));

    }

    /**
     * Find the dynamic element wait until its visible for a specified time
     *
     * @param by                Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     **/

    public static WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
        return driver -> {
            driverWait(500);
            WebElement element = Objects.requireNonNull(driver).findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }


    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    public boolean textToBePresentInElement(WebElement element, String text) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    /**
     * An expectation for checking if the given text is present in the element that matches
     * the given locator.
     *
     * @param by   used to find the element
     * @param text to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    public boolean textToBePresentInElementLocated(final By by, final String text) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }


    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    public boolean textToBePresentInElementValue(final WebElement element, final String text) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }


    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     * @param by   used to find the element
     * @param text to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains
     * the given text
     */
    public boolean textToBePresentInElementValue(final By by, final String text) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }


    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }


    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param by used to find the frame
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final By by) {
        return new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }


    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param by used to find the element
     */
    public boolean invisibilityOfElementLocated(By by) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * An expectation for checking that an element with text is either invisible
     * or not present on the DOM.
     *
     * @param by   used to find the element
     * @param text of the element
     */
    public boolean invisibilityOfElementWithText(final By by, final String text) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param by used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(By by) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */

    public WebElement elementToBeClickable(final WebElement element) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true
     * otherwise.
     */
    public boolean stalenessOf(final WebElement element) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final By by) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementToBeSelected(by));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final WebElement element) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final WebElement element, final boolean selected) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final By by,
                                             final boolean selected) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    public void waitForAlert() {
        (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.alertIsPresent());
    }

    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElements(final List<WebElement> elements) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    /**
     * An expectation for checking that there is at least one element present on a
     * web page.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> presenceOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM
     * of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */

    public boolean visibilityOf(final WebElement element) {
        try {
            (new WebDriverWait(driver.get(), 5000)).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        System.out.println("The expected element is Present with Value " + element.getText());
        return true;
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public static boolean isElementPresent(final By by) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.get();

        try {
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));

            new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public static boolean isElementPresent(final WebElement by) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.get();

        try {
           jse.executeScript("arguments[0].style.border='3px solid green'", by);

            new WebDriverWait(driver.get(), 5000).until(ExpectedConditions.visibilityOfAllElements(by));

        } catch (TimeoutException | NoSuchElementException exception) {
            //jse.executeScript("arguments[0].style.border='3px solid red'", by);
            QuantaTestManager.getTestNode().error("The expected Element is not present "+ by);
            return false;
        }
        //TODO :  handle elements

        return true;
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public static boolean isElementPresent(final By by, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by, driver));

            new WebDriverWait(driver, 5000).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException | NoSuchElementException exception) {
            logManger.ERROR("The expected element is not Present with Value " + element(by, driver).getText(), false);

            return false;
        }
        logManger.INFO("The expected element is Present with Value \"" + element(by, driver).getText() + "\"", false);
        return true;
    }


    public WebDriver getBrowserByPageTitle(String pageTitle) {
        for (String windowHandle : driver.get().getWindowHandles()) {
            driver.set(driver.get().switchTo().window(windowHandle));
            if (pageTitle.equalsIgnoreCase(driver.get().getTitle())) {
                return driver.get();
            }
        }
        return null;
    }

    /**
     * perform Select operation from drop down list
     */
    public static void selectOption(By dropDownElement, String Option) {
        if (!Option.isEmpty()) {
            try {
                Select s = new Select(driver.get().findElement(dropDownElement));
                s.selectByValue(Option);

            } catch (Exception ex) {

                logManger.ERROR("Unable to make Option : [" + Option + "] where element is < " + dropDownElement.toString() + "> ", false);
            }
        }

    }

    public void navigateToPreviousPageUsingBrowserBackButton() {
        driver.get().navigate().back();
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(driver.get());
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        return ((JavascriptExecutor) driver.get()).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) driver.get()).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

    /**
     * Wrapper for driver.get().findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement element(final By by) {
        return driver.get().findElement(by);
    }

    /**
     * Wrapper for driver.get().findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement element(final By by, WebDriver driver) {
        return driver.findElement(by);
    }

    /**
     * Wrapper for clear data and sendKeys in Input Text box
     *
     * @param by        Element location found by css, xpath, id etc...
     * @param inputText text to be entered
     **/

    protected void clearEnterText(By by, String inputText) {
        element(by).clear();
        logManger.INFO("The User Enter : [" + inputText + "] inside Text Box <" + element(by).getAttribute("placeholder") + ">", false);
        element(by).sendKeys(inputText);
    }

    /**
     * Wrapper for clear data and sendKeys in Input Text box
     *
     * @param by Element location found by css, xpath, id etc...
     **/

    protected void clickElement(By by) {
        logManger.INFO("The User Click on : " + element(by).getText(), false);
        element(by).click();
    }

    protected void moveToAndClickElement(By by) {
        scrollTo(element(by));
        logManger.INFO("The User Click on : " + element(by).getText(), false);
        element(by).click();
    }

    /**
     * @param myelement  WebElement to click
     * @param maxSeconds When to Timeout
     */
    public static void retryClick(By myelement, int maxSeconds) {
        int i = 0;

        boolean result = false;
        while (i <= maxSeconds) {
            try {
                element(myelement).click();
                result = true;
                break;
            } catch (Exception e) {
                result = false;
            }
            i++;
            try {
                driverWait(1000);
            } catch (Exception e) {

                logManger.ERROR("Element : <" + myelement.toString() + "> is not being able to click on", e);

            }
        }
        if (!result) {
            try {
                logManger.ERROR("Failed to click element: " + myelement.toString(), false);
                //QuantaTestManager.getTest().fail("Failed to click element: " + myelement.toString());
            } catch (NoSuchElementException exception) {
                logManger.ERROR("Element not Found ", exception);
                QuantaTestManager.getTest().skip("Failed to click element: " + myelement + " Because " + exception.getLocalizedMessage());

            }

        } else {
            //LOG_MANAGER.INFO("User Clicks on <" + myelement.toString() + "> ", false);
        }


    }


    /**
     * @param myelement  WebElement to click
     * @param maxSeconds When to Timeout
     */
    public static void retryClick(WebElement myelement, int maxSeconds) {
        int i = 0;
        boolean result = false;
        while (i <= maxSeconds) {
            try {
                myelement.click();
                result = true;
                break;
            } catch (Exception e) {
                result = false;
            }
            i++;
            try {
                driverWait(1000);
            } catch (Exception e) {

            }
        }
        if (!result) {
        }


    }


    /**
     * Wrapper for wait, clear data and sendKeys in Input Text box
     * <p>
     * * @param by Element location found by css, xpath, id etc...
     *
     * @param inputText text to be entered
     **/
    protected void waitClearEnterText(By by, String inputText) {
        waitForExpectedElement(by).clear();
        element(by).sendKeys(inputText);

    }

    /**
     * perform Select operation from drop down list
     */
    public void selectOption(By dropDownElement, By searchBox, String textOption) {

        try {
            clickAction(element(dropDownElement));
            sendKeysWithClear(element(searchBox), textOption + Keys.ENTER);

        } catch (Exception ex) {

            logManger.ERROR("Unable to make Option : [" + textOption + "] where element is < " + dropDownElement.toString() + "> ", false);
        }

    }

    /**
     * perform click action on accordion or clickable elements
     *
     * @param element web element
     */

    public static void clickAction(WebElement element) {

        JavascriptExecutor jse = (JavascriptExecutor) driver.get();

        try {
            driverWait(300);
            Actions action = new Actions(driver.get());
            //action.moveToElement(element);
            action.click(element).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element);
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + element.getText(), false);
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element);
            logManger.ERROR(ex.getMessage(), false);
        }
    }

    public static void clickAction(By by) {

        JavascriptExecutor jse = (JavascriptExecutor) driver.get();

        try {
            driverWait(300);
            Actions action = new Actions(driver.get());
            action.click(element(by)).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + element(by).getText(), false);
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage(), false);
        }
    }

    public static void storeCookiesInfo(){

        File file = new File("src/main/resources/Cookies.data");
        try
        {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);

            // loop for getting the cookie information
            for(Cookie ck : driver.get().manage().getCookies())
            {
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                Bwrite.newLine();
            }
            Bwrite.close();
            fileWrite.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public static void readSavedCookiesData(){
        try{

            File file = new File("src/main/resources/Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while((strline=Buffreader.readLine())!=null){
                StringTokenizer token = new StringTokenizer(strline,";");
                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if(!(val=token.nextToken()).equals("null"))
                    {
                        SimpleDateFormat format= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                        expiry = format.parse(val);
                    }
                    boolean isSecure = Boolean.parseBoolean(token.nextToken());
                    Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
                    System.out.println(ck);
                    driver.get().manage().deleteAllCookies();
                    driver.get().manage().addCookie(ck); // This will add the stored cookie to your current session
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void clickAction(By by, String textMessage) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            driverWait(300);
            Actions action = new Actions(driver.get());
            action.click(element(by)).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + textMessage, false);
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage(), false);
        }
    }

    /**
     * send keys in specific test box with clearing original value
     *
     * @param wElement webElement
     * @param text text to be sent
     */
    public static void sendKeysWithClear(WebElement wElement, String text) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.get();
        try {
            clickAction(wElement);
            wElement.clear();
            wElement.sendKeys(text);
            logManger.INFO("Send keys with Clear [" + text + "] inside element <" + text + ">", false);
        } catch (Exception ex) {
            //jse.executeScript("arguments[0].style.border='3px solid red'", wElement);
            logManger.ERROR(ex.getLocalizedMessage(), false);
            throw ex;
        }
    }

    /**
     * send keys in specific test box with clearing original value
     *
     * @param by element locator
     * @param text text to be used
     */
    public static void sendKeysWithClear(By by, String text) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.get();
        try {
            clickAction(by);
            // ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", wElement);
            element(by).clear();
            element(by).sendKeys(text);
            logManger.INFO("Send keys with Clear [" + text + "] inside element [" + element(by).toString().substring(element(by).toString().lastIndexOf(":") + 2), false);
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage(), false);
            throw ex;
        }
    }


    public static void driverWait(long millSecond) {

        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            logManger.ERROR("an issue has been initiated with following error : " + e.getMessage(),false);
        }
    }


    public static void actionsClick(WebElement element, String EnterText) {
        Actions actions = new Actions(driver.get());
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(EnterText, Keys.ENTER);
        actions.build().perform();

    }


    public static boolean waitToBeClickable(WebElement element, int time) {
        boolean isElementClickable;
        wait = new WebDriverWait(driver.get(), time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        isElementClickable = element.isEnabled();
        return isElementClickable;

    }

    public static String takeScreenShot() throws UnsupportedEncodingException {
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        return encodeFileToBase64Binary(file);

    }

    private static String encodeFileToBase64Binary(File file) throws UnsupportedEncodingException {
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[(int) file.length()];
        try {
            fileInputStreamReader.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
    }

    public static void moveToElementByActions(WebElement wElement) {
        try {
            Actions actions = new Actions(driver.get());
            actions.moveToElement(wElement);
            actions.perform();
        } catch (JsonException ex) {
            logManger.ERROR(ex.getLocalizedMessage(), false);
        }
    }


    public static void moveToElement(WebElement wElement){
        try {
            ((JavascriptExecutor) driver.get()).executeScript("arguments[0].click();", wElement);
        } catch (Exception ex) {
            //
            logManger.ERROR(ex.getMessage(), false);
            throw ex;
        }
    }


    public static void clickElementActions(WebElement element)  {
        try {
            Actions action = new Actions(driver.get());
            action.click(element).perform();
        } catch (Exception ex) {
            logManger.ERROR(ex.getMessage(), false);
            //
            throw ex;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementlocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementlocator));
        } catch (Exception e) {
            //
            logManger.ERROR("Class ActionHelper| Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementlocator.toString() + ". ERROR: " + e.getMessage(), false);
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementlocator, int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeSec);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementlocator));
        } catch (Exception e) {
            //
            logManger.ERROR("Class ActionHelper| Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementlocator.toString() + ". ERROR: " + e.getMessage(), false);
            throw e;
        }
    }

    public static void sendKeys(By locator, String text) {
        try {
            WebElement wElement = element(locator);
            if (wElement != null) {
                wElement.sendKeys(text);
                logManger.INFO("The User Enter in   " + text, false);
            } else
                throw new Exception("Can't find the Element, Locator: " + locator.toString());
        } catch (Exception ex) {
            logManger.ERROR(ex.getMessage(), false);
        }
    }

    public static void sendKeys(WebElement wElement, String text)  {
        try {
            wElement.sendKeys(text);
            logManger.INFO("The User Enter in  " + text, false);
        } catch (Exception ex) {
            logManger.ERROR(ex.getMessage(), false);

            throw ex;
        }
    }


    public static void scrollupTo(WebElement element){
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(false);", element);
        driverWait(500);
    }


    public static void scrollToEndOfPage() {
        ((JavascriptExecutor) driver.get()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driverWait(1000);
    }

    public static void actionClickStepClick(String step, By locator) {
        logManger.STEP(step, "the user " + step);
        waitVisibility(element(locator), 30);
        isElementPresent(locator);
        logManger.INFO("the user " + step, false);
        retryClick(locator, 30);

    }

    public static void actionClickScrollStepClick(String step, By locator) {
        ActionsHelper.driverWait(3000);
        logManger.STEP(step, "the user " + step);
        scrollTo(locator);
        waitVisibility(element(locator), 30);
        isElementPresent(locator);
        logManger.INFO("the user " + step, false);
        retryClick(locator, 30);

    }
}
