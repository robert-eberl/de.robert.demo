package de.robert.demo.itsv;

import de.robert.demo.WebDriverProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class WebsiteBrowserStepDefs {
    static WebDriver driver;

    @Given("I open the {string}-page")
    public void iOpenThePage(String page) {
        driver = WebDriverProvider.driver;
        String url = switch (page) {
            case "Home" -> "https://www.itsv.at/cdscontent/?contentid=10007.697193&portal=itsvportal";
            case "AboutUs" -> "https://www.itsv.at/cdscontent/?contentid=10007.834978&portal=itsvportal";
            case "WeAsEmployers" -> "https://www.itsv.at/cdscontent/?contentid=10007.838698&portal=itsvportal";
            default -> "unknown";
        };
        if (url.equals("unknown")) {
            Assert.fail("- Unknown Page -");
        } else {
            driver.get(url);
        }
    }

    @Then("The {string}-page is shown correctly")
    public void thePageIsShownCorrectly(String page) {
        StringBuilder errorMsg = new StringBuilder("Result: \n");
        int expectedErrorMsgLength = errorMsg.length();

        //check page title
        String actualTitle = driver.getTitle();
        String expectedTitle = switch (page) {
            case "Home" -> "ITSV - Home";
            case "AboutUs" -> "Unternehmen";
            case "WeAsEmployers" -> "Arbeitgeber";
            default -> "unknown";
        };
        if (expectedTitle.equals("unknown")) {
            errorMsg.append("Unknown title found. \n");
        } else if (!expectedTitle.equals(actualTitle)) {
            errorMsg.append("Expected and actual title do NOT match. \n");
        }

        // check if expected header and footer can be found
        try {
            driver.findElement(By.id("esv-header"));
        } catch (NoSuchElementException e) {
            errorMsg.append("Expected Header NOT found. \n");
        }
        try {
            driver.findElement(By.id("esv-footer"));
        } catch (NoSuchElementException e) {
            errorMsg.append("Expected Footer NOT found. \n");
        }

        //check if test was successful and provide errormessage
        if (expectedErrorMsgLength == errorMsg.length()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail(errorMsg.toString());
        }
    }

}
