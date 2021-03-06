package com.epam.mentoring.pages;

import com.epam.mentoring.utils.Screenshoter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DiskMain extends AbstractPage{

    @FindBy(xpath = "//span[text()='1.txt']/ancestor::div[@class='listing-item__info']/preceding-sibling::div") private WebElement txtFile;
    @FindBy(xpath = "//span[text()='Корзина']") private WebElement trashCan;
    @FindBy(xpath = "//span[text()='Горы.jpg']") private WebElement file1;
    @FindBy(xpath = "//span[text()='Москва.jpg']") private WebElement file2;
    @FindBy(xpath = "//div[@class='resources-action-bar__body']/following-sibling::span") private WebElement closeX;

        public DiskMain deleteFile() {
        waitForElementVisible(txtFile);
        waitForElementVisible(trashCan);
        highlightWebElement(txtFile);
        Screenshoter.takeScreenshot();
        new Actions(driver).dragAndDrop(txtFile, trashCan).build().perform();
        Screenshoter.takeScreenshot();
        return this;
    }

    public DiskMain selectFiles() {
        waitForElementVisible(file1);
        waitForElementVisible(file2);
        highlightWebElement(file1);
        Screenshoter.takeScreenshot();
        new Actions(driver).clickAndHold(file1).moveToElement(file2).release().build().perform();
        waitForElementVisible(closeX);
        highlightWebElement(closeX);
        closeX.click();
        Screenshoter.takeScreenshot();
        unHighlightWebElement(file1);
        return this;
    }
}
