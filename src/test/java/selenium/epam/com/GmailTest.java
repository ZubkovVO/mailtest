package selenium.epam.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pf.epam.com.ComposeMessage;
import pf.epam.com.LoginPage;
import pf.epam.com.MainMailPage;
import pf.epam.com.PasswordPage;

public class GmailTest {
    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions(); //Опции для запуска firefox
        //options.addArguments("disable-infobars");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); //во весь экран
    }

    @Test(description = "Login test")
    public void gmailLoginTest() {
        new LoginPage(driver).openPage().fillLoginInput("selenium.tester80@gmail.com").pressNextButton();
        new PasswordPage(driver).fillPasswordInput("Administratum41").pressNextButton();
        //Assert.assertTrue();
        new ComposeMessage(driver).findCompose().sendEmailTo("newTestAddressee@gmail.com").emailSubject("Hello world!").emailText("Some text");
        new ComposeMessage(driver).emailClose();

    }

    /*@Test(description = "Compose draft")
    public void gmailComposeDraft(){

        new MainMailPage(driver).openInbox();
    }*/


    @AfterClass(description = "Close browser")
    public void browserQuit() {
        driver.quit();
    }
}
