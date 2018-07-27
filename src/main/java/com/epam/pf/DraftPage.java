package com.epam.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DraftPage extends MainMailPage {



   /* @FindBy(xpath = "//span[text()='This text is for testing purposes']")
    private WebElement emailText;*/



    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public DraftPage emailTextFind(String textCheck) {
        By textLocator = By.xpath("//span[text()='" + textCheck + "']");
        waitForElementVisibleBy(textLocator);
        driver.findElement(textLocator).click();
        return this;
    }






}