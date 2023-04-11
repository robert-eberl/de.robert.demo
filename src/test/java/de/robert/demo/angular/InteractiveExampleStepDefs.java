package de.robert.demo.angular;

import de.robert.demo.CommonStepDefs;
import de.robert.demo.Selector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

import static de.robert.demo.WebDriverProvider.driver;

public class InteractiveExampleStepDefs {

    WebElement interactiveExampleContainer;

    @Given("Website opened and navigated to Autocomplete section")
    public void websiteOpenedAndNavigatedToAutocompleteSection() {
        // open website
        driver.get("https://material.angular.io/");

        // navigate to Autocomplete section
        // click the "Get Started" link
        CommonStepDefs.clickElement("a[routerlink='/guide/getting-started']", Selector.CSSSELCTOR);

        // click the "Components" link
        CommonStepDefs.clickElement("a[href='/components']", Selector.CSSSELCTOR);

        // click the "Autocomplete" link
        CommonStepDefs.clickElement("a[href='/components/autocomplete']", Selector.CSSSELCTOR);
    }

    @When("I enter a {string} related to a possible option")
    public void iEnterARelatedToAPossibleOption(String searchWord) {
        interactiveExampleContainer = driver.findElement(By.id("autocomplete-filter"));
        WebElement inputElement = interactiveExampleContainer.findElement(By.tagName("input"));
        inputElement.sendKeys(searchWord);
    }

    @Then("the autocomplete options will show {string}")
    public void theAutocompleteOptionsWillShow(String optionsString) {
        //prepare expected options array
        String[] expectedOptions = optionsString.split(",");
        for (int i = 0; i < expectedOptions.length; i++) {
            expectedOptions[i] = expectedOptions[i].trim().toLowerCase();
        }
        Arrays.sort(expectedOptions);

        //prepare actual options array
        List<WebElement> optionElements = interactiveExampleContainer.findElements(By.xpath("//mat-option[@role='option']"));
        String[] actualOptions = new String[optionElements.size()];
        for (int i = 0; i < optionElements.size(); i++) {
            actualOptions[i] = optionElements.get(i).findElement(By.className("mdc-list-item__primary-text")).getText().trim().toLowerCase();
        }
        Arrays.sort(actualOptions);

        //compare expected and actual options
        Assert.assertArrayEquals(expectedOptions, actualOptions);
    }
}