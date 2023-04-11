package de.robert.demo.angular;

import de.robert.demo.CommonStepDefs;
import de.robert.demo.Selector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static de.robert.demo.WebDriverProvider.driver;

public class StackblitzStepDefs {

    //stackblitz
    HashMap<String, String> browserTabs = new HashMap<>();
    WebElement editor;


    @Given("stackblitz link has been clicked on the material.angular.io page")
    public void stackblitzLinkHasBeenClickedOnTheMaterialAngularIoPage() {
        // open website
        driver.get("https://material.angular.io/");

        //click the "Components" link
        CommonStepDefs.clickElement("a[href='/components']", Selector.CSSSELCTOR);

        //click on the "Table" section
        WebElement element = driver.findElement(By.cssSelector("a[href='/components/table']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        //getWindowHandle of the Angular tab and save it as key-value-pair
        browserTabs.put("Angular", driver.getWindowHandle());

        //click on the stackblitz button
        element = driver.findElement(By.id("table-sorting")).findElement(By.cssSelector("button[aria-label='Edit this example in StackBlitz']"));
        element.click();

        // getWindowsHandles iterate over them identify new tab and add it to browserTabs
        // get the window handles and loop through them
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {

            if (!handle.equals(browserTabs.get("Angular"))) {
                // Switch to the tab whose title matches the expected value
                driver.switchTo().window(handle);
                browserTabs.put("Stackblitz", handle);
                break;
            }
        }
    }

    @When("template is changed")
    public void templateIsChanged() {
        //open HTML-file
        clickOnFileInFileTree("table-sorting-example.html");

        //wait till iframe of the portrait exists
        new WebDriverWait(driver, Duration.ofSeconds(90)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='Preview page']")));

        //access code editor
        editor = driver.findElement(By.xpath("//textarea[@class='inputarea monaco-mouse-cursor-text']"));

        //select all text
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        //write Hello Friendo in the line before the table
        editor.sendKeys("<h2>Hello Friendo<h2>");
        editor.sendKeys(Keys.ENTER);

        //enter modified code
        editor.sendKeys("<table mat-table [dataSource]=\"dataSource\" matSort (matSortChange)=\"announceSortChange($event)\"\n" +
                "       class=\"mat-elevation-z8\">\n" +
                "\n" +
                "  <!-- Position Column -->\n" +
                "  <ng-container matColumnDef=\"position\">\n" +
                "    <th mat-header-cell *matHeaderCellDef mat-sort-header sortActionDescription=\"Sort by number\">\n" +
                "      No.\n" +
                "    </th>\n" +
                "    <td mat-cell *matCellDef=\"let element\"> {{element.position}} </td>\n" +
                "  </ng-container>\n" +
                "\n" +
                "  <!-- Name Column -->\n" +
                "  <ng-container matColumnDef=\"name\">\n" +
                "    <th mat-header-cell *matHeaderCellDef mat-sort-header sortActionDescription=\"Sort by name\">\n" +
                "      Name\n" +
                "    </th>\n" +
                "    <td mat-cell *matCellDef=\"let element\"> {{element.name}} </td>\n" +
                "  </ng-container>\n" +
                "\n" +
                "  <!-- Symbol Column -->\n" +
                "  <ng-container matColumnDef=\"symbol\">\n" +
                "    <th mat-header-cell *matHeaderCellDef mat-sort-header sortActionDescription=\"Sort by symbol\">\n" +
                "      Symbol\n" +
                "    </th>\n" +
                "    <td mat-cell *matCellDef=\"let element\"> {{element.symbol}} </td>\n" +
                "  </ng-container>\n" +
                "\n" +
                "  <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\n" +
                "  <tr mat-row *matRowDef=\"let row; columns: displayedColumns;\"></tr>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "<!-- Copyright 2023 Google LLC. All Rights Reserved.\n" +
                "    Use of this source code is governed by an MIT-style license that\n" +
                "    can be found in the LICENSE file at https://angular.io/license --");

        //Format Document
        editor.sendKeys(Keys.SHIFT, Keys.ALT, "f");
    }

    @And("style is changed")
    public void styleIsChanged() {
        //open CSS-file
        clickOnFileInFileTree("table-sorting-example.css");

        //access code editor
        editor = driver.findElement(By.xpath("//textarea[@class='inputarea monaco-mouse-cursor-text']"));

        //select all text
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        //enter modified code
        editor.sendKeys("table {\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "  color: green;\n" +
                "  font-size: large;\n" +
                "}\n" +
                "\n" +
                "th.mat-sort-header-sorted {\n" +
                "  color: black;\n");

        //Format Document
        editor.sendKeys(Keys.SHIFT, Keys.ALT, "f");
    }

    @And("typescript logic is changed")
    public void typescriptLogicIsChanged() {
        //open TS-file
        clickOnFileInFileTree("table-sorting-example.ts");

        //access code editor
        editor = driver.findElement(By.xpath("//textarea[@class='inputarea monaco-mouse-cursor-text']"));

        //select all text
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        //enter modified code
        editor.sendKeys("import {LiveAnnouncer} from '@angular/cdk/a11y';\n" +
                "import {AfterViewInit, Component, ViewChild} from '@angular/core';\n" +
                "import {MatSort, Sort} from '@angular/material/sort';\n" +
                "import {MatTableDataSource} from '@angular/material/table';\n" +
                "\n" +
                "export interface PeriodicElement {\n" +
                "  name: string;\n" +
                "  position: number;\n" +
                "  weight: number;\n" +
                "  symbol: string;\n" +
                "}\n" +
                "const ELEMENT_DATA: PeriodicElement[] = [\n" +
                "  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},\n" +
                "  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},\n" +
                "  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},\n" +
                "  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},\n" +
                "  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},\n" +
                "  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},\n" +
                "  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},\n" +
                "  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},\n" +
                "  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},\n" +
                "  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},\n" +
                "];\n" +
                "/**\n" +
                " * @title Table with sorting\n" +
                " */\n" +
                "@Component({\n" +
                "  selector: 'table-sorting-example',\n" +
                "  styleUrls: ['table-sorting-example.css'],\n" +
                "  templateUrl: 'table-sorting-example.html',\n" +
                "})\n" +
                "export class TableSortingExample implements AfterViewInit {\n" +
                "  displayedColumns: string[] = ['position', 'name', 'symbol'];\n" +
                "  dataSource = new MatTableDataSource(ELEMENT_DATA);\n" +
                "\n" +
                "  constructor(private _liveAnnouncer: LiveAnnouncer) {}\n" +
                "\n" +
                "  @ViewChild(MatSort) sort: MatSort;\n" +
                "\n" +
                "  ngAfterViewInit() {\n" +
                "    this.dataSource.sort = this.sort;\n" +
                "  }\n" +
                "\n" +
                "  /** Announce the change in sort state for assistive technology. */\n" +
                "  announceSortChange(sortState: Sort) {\n" +
                "    // This example uses English messages. If your application supports\n" +
                "    // multiple language, you would internationalize these strings.\n" +
                "    // Furthermore, you can customize the message to add additional\n" +
                "    // details about the values being sorted.\n" +
                "    if (sortState.direction) {\n" +
                "      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);\n" +
                "    } else {\n" +
                "      this._liveAnnouncer.announce('Sorting cleared');\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/**  Copyright 2023 Google LLC. All Rights Reserved.\n" +
                "    Use of this source code is governed by an MIT-style license that\n" +
                "    can be found in the LICENSE file at https://angular.io/license */");

        //Format Document
        editor.sendKeys(Keys.SHIFT, Keys.ALT, "f");
    }

    @Then("it will be portrait properly")
    public void itWillBePortraitProperly() {

        //find and switch to preview iframe
        WebElement previewIframe = driver.findElement(By.xpath(
                "//iframe[@title='Preview page']"));
        driver.switchTo().frame(previewIframe);

        //check title above the table
        WebElement h2 = driver.findElement(By.tagName("h2"));
        Assert.assertEquals("Hello Friendo" + "\n" + h2.findElement(By.tagName("h2")).getText(), h2.getText());

        //check number of columns -> fail: ts does not work or changes haven't been portrait
        List<WebElement> tableHeads = driver.findElements(By.tagName("th"));
        Assert.assertEquals(3, tableHeads.size());

        //check if column name "Weight" isn't shown anymore -> fail: html (maybe also ts) changes are not portrait properly
        for (WebElement th : tableHeads) {
            Assert.assertNotEquals("Weight",th.getText());
        }

        //check if th styles match -> fail: css changes are not portrait properly
        String color = tableHeads.get(0).getCssValue("color");
        String fontSize = tableHeads.get(0).getCssValue("font-size");

        //compares color and font size with expected values and prints error message
        assert color.equals("rgba(0, 128, 0, 1)") && fontSize.equals("18px")
                : "Expected green color and large font-size";
    }

    //open the file from the file tree at the sidebar by using its title
    private void clickOnFileInFileTree(String fileTitle) {
        //searches fileTree for the first match and clicks on it
        new WebDriverWait(driver, Duration.ofSeconds(15) ).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-cy='src/app/" + fileTitle + "']")));
        driver.findElement(By.xpath("//div[@data-cy='src/app/" + fileTitle + "']")).click();

        //just for fun
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var fileTitle = '" + fileTitle + "';"
                + "var div = document.querySelector(\"div[data-cy='src/app/\" + fileTitle + \"']\");"
                + "var colors = ['red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet'];"
                + "var index = 0;"
                + "setInterval(function() {"
                + "div.style.color = colors[index];"
                + "index = (index + 1) % colors.length;"
                + "}, 1000);");
    }
}
