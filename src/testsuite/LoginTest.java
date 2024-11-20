package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "https://courses.ultimateqa.com/";

    //Open Browser
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    //User should navigate to login page successfully
    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){

        //Click on the 'Sign In' link
        WebElement signIn = driver.findElement(By.linkText("Sign In"));
        signIn.click();

        //Verify the text 'Welcome Back'
        String expectedText = "Welcome Back!";
        WebElement welcomeTextElement = driver.findElement(By.xpath("//h2[@class= 'page__heading']"));
        String actualText = welcomeTextElement.getText();
        Assert.assertEquals("Not redirected to sign in page", expectedText, actualText);

    }

    //Verify Error message
    @Test
    public void verifyTheErrorMessage(){

        //click on the ‘Sign In’ link
        WebElement signIn = driver.findElement(By.linkText("Sign In"));
        signIn.click();

        //Enter the invalid username
        WebElement emailField = driver.findElement(By.id("user[email]"));
        emailField.sendKeys("Pinal@gmail.com");

        //Enter the invalid password
        WebElement passwordField = driver.findElement(By.name("user[password]"));
        passwordField.sendKeys("12345");

        //Click on the ‘Sign in’ button
        driver.findElement(By.xpath("//button[@class='button button-primary g-recaptcha']")).click();

        //Verify the error message ‘Invalid email or password.’
        String expectedErrorMessage = "Invalid email or password.";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@id='notice']/ul/li")).getText();
        Assert.assertEquals("Error message not displayed",expectedErrorMessage,actualErrorMessage);

    }

    //Close the browser
    @After
    public void tearDown(){
        closeBrowser();
    }


}
