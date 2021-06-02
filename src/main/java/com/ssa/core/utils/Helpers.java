package com.ssa.core.utils;

import com.qpros.common.web.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers extends Base {


    /**
     * Wrapper for driver.get().findElement
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public static WebElement element(final By by) {
        return driver.get().findElement(by);
    }
    public static boolean isElementPresent(By by) {
        JavascriptExecutor jse = (JavascriptExecutor)driver.get();

        try {
            jse.executeScript("arguments[0].style.borderColor='green'", element(by));
            (new WebDriverWait((WebDriver)driver.get(), 5000L)).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (NoSuchElementException | TimeoutException var3) {
            return false;
        }
    }
}
