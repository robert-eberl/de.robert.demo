package de.robert.demo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class WebDriverProvider {

    public static WebDriver driver;

    @BeforeStep
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void init(Scenario scenario) {

        // set system property browser by scenario tag
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag : tags) {
            if (tag.equals("@firefox")||tag.equals("@chrome")) {
                System.setProperty("browser", tag.substring(1));
                break;
            }
        }

        // start browser based on system property browser
        String browser = System.getProperty("browser", "chrome");
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        // set window size
        driver.manage().window().setSize(new Dimension(1500, 1000));
    }

    @After
    public void shutDown() {
        driver.quit();
    }
}
