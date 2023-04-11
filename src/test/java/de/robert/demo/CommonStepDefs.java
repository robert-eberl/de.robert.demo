package de.robert.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static de.robert.demo.WebDriverProvider.driver;

public class CommonStepDefs {
    public static WebDriverWait wait;

    public static void clickElement(String searchCriteria, Selector selector) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = null;

        switch (selector) {
            case CSSSELCTOR:
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(searchCriteria)));
                element = driver.findElement(By.cssSelector(searchCriteria));
                break;
            case ID:
                wait.until(ExpectedConditions.elementToBeClickable(By.id(searchCriteria)));
                element = driver.findElement(By.id(searchCriteria));
                break;
            case CLASSNAME:
                wait.until(ExpectedConditions.elementToBeClickable(By.className(searchCriteria)));
                element = driver.findElement(By.className(searchCriteria));
                break;
            case XPATH:
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchCriteria)));
                element = driver.findElement(By.xpath(searchCriteria));
                break;
            default:
        }

        element.click();
    }

}
