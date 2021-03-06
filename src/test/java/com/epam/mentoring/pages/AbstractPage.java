package com.epam.mentoring.pages;

import com.epam.mentoring.utils.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractPage {
    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 10;

    public AbstractPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
        PageFactory.initElements(this.driver, this);
    }

    public void waitForElementVisible(WebElement locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(locator));
    }

    public void waitForElementClickable(WebElement locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    //пришлось сделать public для возможности использовать внутри теста GmailTest
    public void waitForElementVisibleBy(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

    }

    public boolean elementExists(String element){
        boolean exists = driver.findElements(By.xpath(element)).isEmpty();
        return exists;
    }


    protected void highlightWebElement(WebElement locator){
       ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", locator);
    }

    protected void switchToFrame(int locator){
        driver.switchTo().frame(locator);
    }

    protected void unHighlightWebElement(WebElement locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", locator);
    }

    private ExpectedCondition<Boolean> isAjaxFinished() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        };
    }

    protected void waitForAjaxProcessed() {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(isAjaxFinished());
    }

}
