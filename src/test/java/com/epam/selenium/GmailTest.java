package com.epam.selenium;

import com.epam.pf.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GmailTest{
    private WebDriver driver;

    String emailTextString = "Some text";
    String emailSubjectString = "Hello world!";
    String sendEmailToString = "newTestAddressee@gmail.com";

    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions(); //Опции для запуска firefox
        //options.addArguments("disable-infobars");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); //во весь экран
    }

    @Test(description = "Login test")
    public void gmailLoginTest() throws FileNotFoundException, InterruptedException {

        String password = new Scanner(new File("/C:/Pwd/pwd.txt")).useDelimiter("\\Z").next();
        new LoginPage(driver).openPage().fillLoginInput("selenium.tester80@gmail.com").pressNextButton();
        new PasswordPage(driver).fillPasswordInput(password).pressNextButton();
        //Assert.assertTrue();
    }

    @Test(description = "Compose Email",dependsOnMethods = {"gmailLoginTest"})
    public void testGmailSend() throws InterruptedException {
        new ComposeMessage(driver).findCompose().sendEmailTo(sendEmailToString).emailSubject(emailSubjectString).emailText(emailTextString);
        new ComposeMessage(driver).emailClose();
    }

    @Test(description = "Check and Send email", dependsOnMethods = {"testGmailSend"})
    public void testCheckAndSend() throws InterruptedException {
        new MainMailPage(driver).openDrafts();
        new DraftPage(driver).emailTextFind(emailTextString);
        //Assert.assertTrue();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElements(By.xpath("//span[@email='"+sendEmailToString+"']")).size()>0);
        Assert.assertTrue(driver.findElements(By.xpath("//div[text()='"+emailSubjectString+"']")).size()>0);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).getText(),emailTextString);
        new ComposeMessage(driver).sendEmail();
        new MainMailPage(driver).openSent();
        new ProfilePopup(driver).signOutOptions().signOut();
    }

    @AfterClass(description = "Close browser")
    public void browserQuit() {
        driver.quit();
    }
}