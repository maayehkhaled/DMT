package com.qpros.assertion;

import com.qpros.assertion.matcher.CustomMatchers;
import com.qpros.common.LogManager;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.StringUtils;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collection;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class AssertService {
    private static Object actualObj;
    private static Object expectedObj;
    private static LogManager logManager;

    private static final AssertController assertController = new AssertController();
    private static boolean enableHardAssertions = false;
    private static String outputMessage;

    public AssertService() {
        logManager= new LogManager(AssertService.class.getSimpleName());

    }

    /**
     * Constructor to enable or disable assertions globally.
     *
     * @param enableHardAssertions enable/disable setting
     */
    public AssertService(final boolean enableHardAssertions) {

        AssertService.enableHardAssertions = enableHardAssertions;
    }

    /**
     * Base method for all assertions.
     *
     * @param message       message description
     * @param actual        actual value to assert upon
     * @param actualMatcher Matcher to use for the assertion
     */
    public static void assertThat(String message, Object actual, Matcher actualMatcher) {

        StringDescription stringDescription = new StringDescription();
        stringDescription.appendText(message + " ");
        actualMatcher.describeTo(stringDescription);
        actualMatcher.describeMismatch(actual, stringDescription.appendText(" "));
        outputMessage = StringUtils.replace(stringDescription.toString(), "\"", "'").trim();
        if (!actualMatcher.matches(actual)) {
            if (enableHardAssertions) {
                Assert.fail(outputMessage);
            } else {
                assertFail(outputMessage);
            }
        } else {
            assertPass(outputMessage);
        }
    }

    public static void assertThat(final Object actual, final Matcher actualMatcher) {
        assertThat("", actual, actualMatcher);
    }

    public static void assertEqualsIgnoreCase(final String actual, final String expected) {
        assertThat(actual, CoreMatchers.is(equalToIgnoringCase(expected)));
    }

    public static void assertEqualsIgnoreCase(final String message, final String actual, final String expected, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(equalToIgnoringCase(expected)));
    }

    public static void assertContainsInOrder(final String message, final String source, String... target) {
        assertThat(message, source, CoreMatchers.is(stringContainsInOrder(Arrays.asList(target))));
    }

    public static void assertContainsNotInOrder(final String message, final String source, String... target) {
        assertThat(message, source, CoreMatchers.is(new CustomMatchers.StringContainsNotInOrderMatcher(Arrays.asList(target))));
    }
    public static void assertContainsIgnoreCase(final String source, final String target) {
        assertTrue(StringUtils.containsIgnoreCase(source, target));
    }

    public static void assertContainsIgnoreCase(final String message, final String source, final String target, final Object... messageArgs) {
        assertTrue(message, StringUtils.containsIgnoreCase(source, target), messageArgs);
    }

    public static void assertFalse(final boolean actual) {
        assertThat(actual, CoreMatchers.is(false));
    }

    public static void assertFalse(final String message, final boolean actual, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(false));
    }

    public static void assertTrue(final boolean actual) {
        assertThat(actual, CoreMatchers.is(true));
    }

    public static void assertTrue(final String message, final boolean actual, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(true));
    }

    public static void assertGreaterThan(final Object actual, final Object expected) {
        assertThat(actual, CoreMatchers.is(greaterThan((Comparable) expected)));
    }

    public static void assertGreaterThan(final String message, final Object actual, final Object expected, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(greaterThan((Comparable) expected)));
    }

    public static void assertGreaterThanOrEqual(final Object actual, final Object expected) {
        assertThat(actual, CoreMatchers.is(greaterThanOrEqualTo((Comparable) expected)));
    }

    public static void assertGreaterThanOrEqual(final String message, final Object actual, final Object expected, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(greaterThanOrEqualTo((Comparable) expected)));
    }

    public static void assertLessThan(final Object actual, final Object expected) {
        assertThat(actual, CoreMatchers.is(lessThan((Comparable) expected)));
    }

    public static void assertLessThan(final String message, final Object actual, final Object expected, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(lessThan((Comparable) expected)));
    }

    public static void assertLessThanOrEqual(final Object actual, final Object expected) {
        assertThat(actual, CoreMatchers.is(lessThanOrEqualTo((Comparable) expected)));
    }

    public static void assertLessThanOrEqual(final String message, final Object actual, final Object expected, final Object... messageArgs) {
        assertThat(LogManager.formatMessage(message, messageArgs), actual, CoreMatchers.is(lessThanOrEqualTo((Comparable) expected)));
    }

    public static void assertCollectionInOrder(final String message, final Collection actualSources, Object... elements) {
        assertThat(message, actualSources, CoreMatchers.is(contains(elements)));
    }

    public static void assertCollectionContains(final String message, final Collection actualSources, final Object expected) {
        assertThat(message, actualSources, CoreMatchers.is(new CustomMatchers.CollectionContainsMatcher(expected)));
    }


    public static void assertRegexTrue(final String message, final Object actual, final String regex) {
        assertThat(message, actual, CoreMatchers.is(new CustomMatchers.RegexMatcher(regex)));
    }


    public static void assertJsonFieldEquals(final JsonPath actualSource, final Object expectedField, final Object expected) {
        assertJsonFieldEquals("", actualSource, expectedField, expected);
    }

    public static void assertJsonFieldEquals(final String message, final JsonPath actualSource, final Object expectedField, final Object expected) {
        assertThat(message, actualSource, CoreMatchers.is(new CustomMatchers.JsonMatcher(expectedField, expected)));
    }

    public static void assertJsonContainsField(final JsonPath actualSource, final String expectedField) {
        assertJsonContainsField("", actualSource, expectedField);
    }

    public static void assertJsonContainsField(final String message, final JsonPath actualSource, final Object expectedField) {
        assertThat(message, actualSource, CoreMatchers.is(new CustomMatchers.JsonMatcher(expectedField, null)));
    }

    public static void assertSimilarity(final String message, final String actualSource, final String target, final double minimalSimilarityRating) {
        assertThat(message, actualSource, CoreMatchers.is(new CustomMatchers.StringSimilarMatcher(actualSource, target, minimalSimilarityRating)));
    }

    public static void assertSimilarity(final String actualSource, final String target, final double minimalSimilarityRating) {
        assertSimilarity("", actualSource, target, minimalSimilarityRating);
    }

    public static void assertPass(String message, final Object... messageArgs) {
        message = LogManager.formatMessage(message, messageArgs);

        assertController.pass(message);
    }

    public static void assertFail(String message, final Object... messageArgs) {
        message = LogManager.formatMessage(message, messageArgs);
        assertController.fail(message);
    }

    public static void assertFail(Throwable e) {
        assertController.fail(e.getMessage(), e);
    }

    public boolean isEnableHardAssertions() {
        return enableHardAssertions;
    }

    public void setEnableHardAssertions(boolean enableHardAssertions) {
        AssertService.enableHardAssertions = enableHardAssertions;
    }

    public static String getOutputMessage() {
        return outputMessage;
    }

}
