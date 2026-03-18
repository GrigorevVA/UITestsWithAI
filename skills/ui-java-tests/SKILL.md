---
name: ui-java-selenide-tests
description: Generate UI automated tests using Java and Selenide. Use this skill when the user asks to create UI tests for web pages or user flows.
---

# Generate UI tests with Java and Selenide

## Purpose

This skill generates UI automated tests using Java and Selenide following the project's existing structure.

## Rules

- Use Java and Selenide.
- Follow the Page Object pattern if it exists in the project.
- Reuse existing page objects when possible.
- One test should validate one behavior.
- Use clear and descriptive test names.

## Selenide Guidelines

- Use Selenide conditions instead of Thread.sleep.
- Prefer stable selectors (id, data-testid).
- Avoid long fragile XPath locators.
- Use built-in waits from Selenide.

## Test Structure

Follow the pattern:

Arrange  
Act  
Assert

Example:

```java
@Test
void user_can_login() {

    loginPage.open();

    loginPage.enterEmail("user@test.com");
    loginPage.enterPassword("password");

    loginPage.submit();

    dashboardPage.shouldBeVisible();
}

package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmail);
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=permanentAddress]").setValue(permanentAddress);
        textBoxPage.submitForm();

        textBoxPage.checkField("name", userName);
        textBoxPage.checkField("email", userEmail);
        $("[id=output] [id=currentAddress]").shouldHave(text(currentAddress));
        $("[id=output] [id=permanentAddress]").shouldHave(text(permanentAddress));
    }

    @Test
    void successfulFillFormWithoutAddressTest() {
        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmail);
        textBoxPage.submitForm();

        textBoxPage.checkField("name", userName);
        textBoxPage.checkField("email", userEmail);
    }

    @Test
    void successfulFillFormWithoutAddressTest_chaining() {
        textBoxPage.openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail);
    }

    @Test
    void successfulFillFormWithoutAddressTest_old() {
        open("/text-box");
        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text(userName));
        $("[id=output] [id=email]").shouldHave(text(userEmail));
    }


//    @Test
//    void successfulFillFormTest() {
//        open("/text-box");
//
//        typeUserName(userName);
//        typeUserEmail(userEmail);
//    }
}

package utils;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    public static void main(String[] args) {
        System.out.println(getRandomString(8));
        System.out.println(getRandomEmail());
        System.out.println(getRandomNumber_bad_practice(11));
        System.out.println(getRandomInt(0, 999999));
        System.out.println(getRandomInt(111111111, 888888888));
        System.out.println(getRandomPhone());
        System.out.println(getRandomGender());

    }

    public static String getRandomString(int length) {
//        String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }

    public static String getRandomEmail() {
//        return getRandomString(8) + "@" + getRandomString(8) + ".com";
//        return String.format("%s@%s.com");
        return format("%s@%s.com", getRandomString(8), getRandomString(8));
    }

    public static String getRandomNumber_bad_practice(int length) { // BAD PRACTICE
        String LETTERS = "0123456789";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
//        SecureRandom rnd = new SecureRandom();
//        return rnd.nextInt(max + 1);
    }

    // +3 (263) 253 - 66 - 12
    public static String getRandomPhone() {
        String phoneTemplate = "+%s (%s) %s - %s - %s";

        return format(phoneTemplate, getRandomInt(1, 9), getRandomInt(111, 999), getRandomInt(111, 999)
                , getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender_bad_practice() {
        String[] genders = {"Male", "Female", "Other"};

        int randomIndex = getRandomInt(0, 2);

        return genders[randomIndex];
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromStringArray(genders);
    }

    public static String getRandomItemFromStringArray(String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);

        return stringArray[randomIndex];
    }
}