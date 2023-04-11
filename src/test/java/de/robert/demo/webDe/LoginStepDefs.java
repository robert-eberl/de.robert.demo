package de.robert.demo.webDe;

import de.robert.demo.WebDriverProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginStepDefs {
    WebDriver driver = WebDriverProvider.driver;

    @Given("{string} has been loaded")
    public void hasBeenLoaded(String website) {

        //open the website
        driver.get(website);

        // click the button to close the overlay
        // switch to the iFrame
        WebElement iFrame = WebDriverProvider.driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);
        WebElement innerIFrame = WebDriverProvider.driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(innerIFrame);
        // find button to close the overlay
        driver.findElement(By.id("save-all-pur")).click();

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }

    @When("I enter {string} and {string}")
    public void iEnterAnd(String username, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(By.id("freemailLoginUsername")));
        // enter username
        driver.findElement(By.id("freemailLoginUsername")).sendKeys(username);
        // enter password
        driver.findElement(By.id("freemailLoginPassword")).sendKeys(password);
        // press login button
        driver.findElement(By.xpath("//button[@type='submit' and text()='Login']")).click();
    }

    @Then("I am logged in as {string}")
    public void iAmLoggedIn(String username) {
        //select relevant iframe and switch to it
        WebElement iFrame = WebDriverProvider.driver.findElement(By.id("thirdPartyFrame_home"));
        driver.switchTo().frame(iFrame);

        // check if logged in
        // get the actualValue and modify it for comparison
        WebElement parentElement = driver.findElement(By.id("identity_ZnJvY2twaXQvZGVzay9jb2NrcGl0L3dpZGdldHMvbWFpbC50d2ln"));
        String actualValue = parentElement.findElement(By.className("username")).getText();
        actualValue = actualValue.substring(0, actualValue.length() - 7);
        // compare expected username and actualValue
        assertEquals(username, actualValue);

        // switch back to default
        driver.switchTo().defaultContent();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-notification-type='error-light']")));
        WebElement errorMessage = driver.findElement(By.xpath("//div[@data-notification-type='error-light']"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
