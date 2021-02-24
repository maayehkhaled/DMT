package com.qpros.helpers;

import com.qpros.common.Base;
import com.qpros.common.LogManger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ActionsHelper extends Base {
    LogManger LOGGER = new LogManger();
    //protected static Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
    public static WebDriverWait wait;
    public static final LogManger logManger= new LogManger(ActionsHelper.class.getSimpleName());


    public static void waitForSeconds(Integer timeWait) {
        driver.manage().timeouts().implicitlyWait(timeWait, TimeUnit.SECONDS);
    }

    public static void navigate(String url) {
       driver.navigate().to(url);
    }
    public static boolean waitVisibility(WebElement element, int time) {
        boolean isElementPresent = false;
        try {
            wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOf(element));
            isElementPresent = element.isDisplayed();
        } catch (Exception e) {
        }
        return isElementPresent;

    }

    public static void selectByIndex(WebElement element, int index) {
        try {
            org.openqa.selenium.support.ui.Select make = new org.openqa.selenium.support.ui.Select(element);
            make.selectByIndex(index);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void selectByValue(WebElement element, String value) {
        try {
            org.openqa.selenium.support.ui.Select make = new org.openqa.selenium.support.ui.Select(element);
            make.selectByValue(value);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void scrollTo(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element(by));
    }

    public static void scrollTo(By by,WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element(by));
    }

    public void safeJavaScriptClick(WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                LOGGER.INFO("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            LOGGER.INFO("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            LOGGER.INFO("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            LOGGER.INFO("Unable to click on element " + e.getStackTrace());
        }
    }

    public String getImagePath(String imageName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/images/" + imageName;
        return path;
    }

    public String getXMLPath(String xmlFileName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/xmlfiles/" + xmlFileName;
        return path;
    }

    public static String getTodayDate() {
        LocalDate localDate = LocalDate.now();
        String GetTodayDate = localDate.toString();
        return GetTodayDate;
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitle(String title) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.titleIs(title));
    }

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitleContains(String title) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.titleContains(title));
    }

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    public boolean checkPageUrlToBe(String url) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.urlToBe(url));
    }

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    public boolean checkPageUrlContains(String fraction) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */

    public boolean checkPageUrlMatches(String regex) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.urlMatches(regex));
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement waitForExpectedElement(final By by) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
        driverWait(3000);
        wait = new WebDriverWait(driver, 10);
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
            WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logManger.ERROR(e.getMessage());
            }
            WebElement element = driver.findElement(by);
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
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.textToBePresentInElement(element, text));
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
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
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
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.textToBePresentInElementValue(element, text));
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
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }


    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }


    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param by used to find the frame
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final By by) {
        return new WebDriverWait(driver, 5000).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }


    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param by used to find the element
     */
    public boolean invisibilityOfElementLocated(By by) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * An expectation for checking that an element with text is either invisible
     * or not present on the DOM.
     *
     * @param by   used to find the element
     * @param text of the element
     */
    public boolean invisibilityOfElementWithText(final By by, final String text) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param by used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(By by) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */

    public WebElement elementToBeClickable(final WebElement element) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true
     * otherwise.
     */
    public boolean stalenessOf(final WebElement element) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final By by) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementToBeSelected(by));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final WebElement element) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final WebElement element, final boolean selected) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final By by,
                                             final boolean selected) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    public void waitForAlert() {
        (new WebDriverWait(driver, 5000)).until(ExpectedConditions.alertIsPresent());
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
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
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
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    /**
     * An expectation for checking that there is at least one element present on a
     * web page.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> presenceOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

//    /**
//     * An expectation for checking that an element, known to be present on the DOM
//     * of a page, is visible. Visibility means that the element is not only
//     * displayed but also has a height and width that is greater than 0.
//     *
//     * @param element the WebElement
//     * @return the (same) WebElement once it is visible
//     */
//
//    public WebElement visibilityOf(final WebElement element) {
//        return (new WebDriverWait(driver, 5000)).until(ExpectedConditions.visibilityOf(element));
//    }

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
            (new WebDriverWait(driver, 5000)).until(ExpectedConditions.visibilityOf(element));
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
    public static boolean isElementPresent(final By by)  {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));

            new WebDriverWait(driver, 5000).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException exception) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            //System.out.println(exception.getMessage());
            return false;
        }
        logManger.INFO("The expected element is Present with Value " + element(by).getText());
        return true;
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public static boolean isElementPresent(final By by,WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by,driver));

            new WebDriverWait(driver, 5000).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException exception) {
            logManger.ERROR("The expected element is not Present with Value " + element(by,driver).getText());

            //jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            //System.out.println(exception.getMessage());
            return false;
        }
        logManger.INFO("The expected element is Present with Value " + element(by,driver).getText());
        return true;
    }


    public WebDriver getBrowserByPageTitle(String pageTitle) {
        for (String windowHandle : driver.getWindowHandles()) {
            driver = driver.switchTo().window(windowHandle);
            if (pageTitle.equalsIgnoreCase(driver.getTitle())) {
                return driver;
            }
        }
        return null;
    }

    /**
     * perform Select operation from drop down list
     */
    public static void selectOption(By dropDownElement, String Option) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (!Option.isEmpty()) {
            try {
                Select s = new Select(driver.findElement(dropDownElement));
                s.selectByValue(Option);

            } catch (Exception ex) {
                //jse.executeScript("arguments[0].style.border='3px solid red'", dropDownElement);

                logManger.ERROR("Unable to make Option : [" + Option + "] where element is < " + dropDownElement.toString() + "> ");
            }
        }

    }

    public void navigateToPreviousPageUsingBrowserBackButton() {
        driver.navigate().back();
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        return ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

    /**
     * Wrapper for driver.findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement element(final By by) {
        return driver.findElement(by);
    }

    /**
     * Wrapper for driver.findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement element(final By by,WebDriver driver) {
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
        logManger.INFO("The User Enter : [" + inputText + "] inside Text Box <" + element(by).getAttribute("placeholder") + ">");
        element(by).sendKeys(inputText);
    }

    /**
     * Wrapper for clear data and sendKeys in Input Text box
     *
     * @param by Element location found by css, xpath, id etc...
     **/

    protected void clickElement(By by) {
        logManger.INFO("The User Click on : " + element(by).getText());
        element(by).click();
    }

    protected void moveToAndClickElement(By by) {
        scrollTo(element(by));
        logManger.INFO("The User Click on : " + element(by).getText());
        element(by).click();
    }

    /**
     * @param myelement  WebElement to click
     * @param maxSeconds When to Timeout
     */
    public static void retryClick(By myelement, int maxSeconds) {
        int i = 0;
        JavascriptExecutor jse = (JavascriptExecutor) driver;

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
                Thread.sleep(1000);
            } catch (Exception e) {

                logManger.ERROR("Element : <" + myelement.toString() + "> is not being able to click on",e);

            }
        }
        if (!result) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(myelement));
            Assert.fail("Failed to click element: " + myelement.toString());
        } else {
            logManger.INFO("User Clicks on <" + myelement.toString() + "> ");
        }


    }

    /**
     * Wrapper for wait, clear data and sendKeys in Input Text box
     * <p>
     * * @param by Element location found by css, xpath, id etc...
     *
     * @param inputText text to be entered
     **/
    protected void waitClearEnterText(By by, String inputText) throws InterruptedException {
        waitForExpectedElement(by).clear();
        element(by).sendKeys(inputText);

    }

    /**
     * perform Select operation from drop down list
     */
    public void selectOption(By dropDownElement, By searchBox, String textOption) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            clickAction(element(dropDownElement));
            sendKeysWithClear(element(searchBox), textOption + Keys.ENTER);

        } catch (Exception ex) {
            //jse.executeScript("arguments[0].style.border='3px solid red'", dropDownElement);

            logManger.ERROR("Unable to make Option : [" + textOption + "] where element is < " + dropDownElement.toString() + "> ");
        }

    }

    /**
     * perform click action on accordion or clickable elements
     *
     * @param element
     * @throws Exception
     */

    public static void clickAction(WebElement element) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            Thread.sleep(300);
            Actions action = new Actions(driver);
            //action.moveToElement(element);
            action.click(element).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element);
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + element.getText());
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element);
            logManger.ERROR(ex.getMessage());
        }
    }

    public static void clickAction(By by) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            Thread.sleep(300);
            Actions action = new Actions(driver);
            action.click(element(by)).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + element(by).getText());
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage());
        }
    }

    public void clickAction(By by, String textMessage) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        try {
            Thread.sleep(300);
            Actions action = new Actions(driver);
            action.click(element(by)).perform();
            // highlight the element with green border 3px width
            jse.executeScript("arguments[0].style.border='3px solid green'", element(by));
            // added sleep to give little time for browser to respond
            logManger.INFO("User Clicks on " + textMessage);
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage());
        }
    }

    /**
     * send keys in specific test box with clearing original value
     *
     * @param wElement
     * @param text
     * @throws Exception
     */
    public static void sendKeysWithClear(WebElement wElement, String text) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            clickAction(wElement);
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wElement);
            wElement.clear();
            wElement.sendKeys(text);
            logManger.INFO("Send keys with Clear [" + text + "] inside element <" + text + ">");
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", wElement);
            logManger.ERROR(ex.getMessage());
            throw ex;
        }
    }

    /**
     * send keys in specific test box with clearing original value
     *
     * @param by
     * @param text
     * @throws Exception
     */
    public static void sendKeysWithClear(By by, String text) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            clickAction(by);
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wElement);
            element(by).clear();
            element(by).sendKeys(text);
            logManger.INFO("Send keys with Clear [" + text + "] inside element [" + element(by).toString().substring(element(by).toString().lastIndexOf(":") + 2));
        } catch (Exception ex) {
            jse.executeScript("arguments[0].style.border='3px solid red'", element(by));
            logManger.ERROR(ex.getMessage());
            throw ex;
        }
    }


    public static void driverWait(long millSecond) {

        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            logManger.ERROR("an issue has been initiated with following error : " + e.getMessage());
        }
    }

    public void actionsClick(WebElement element, String EnterText) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(EnterText, Keys.ENTER);
        actions.build().perform();

    }

    public static String takeScreenShot() throws Exception {
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return encodeFileToBase64Binary(file);

    }

    private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

}
