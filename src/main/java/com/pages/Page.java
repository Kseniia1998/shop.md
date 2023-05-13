package com.pages;

import com.core.PropertyReader;
import com.core.Utils;
import com.core.WebDriverHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public abstract class Page {
    private final WebDriver driver;
    private final String pageUrl;

    public Page(String pageUrl){
        this.pageUrl = pageUrl;
        this.driver = WebDriverHelper.getChromeDriver();
        PageFactory.initElements(this.driver, this);
    }

    public void navigate(){
        String absoluteUrl = PropertyReader.getConfigProperty("url") + this.pageUrl;
        log.info("Navigate to "+absoluteUrl);
        driver.get(absoluteUrl);
        this.pageLoaded();
    }

    public void pageLoaded(){
        Utils.waitForPageLoaded();
        log.info("Page "+this.pageUrl+" loaded");
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void waitForElementVisible(By locator){
        pageWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementVisible(By locator, int waitTime){
        pageWait(waitTime).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementVisible(WebElement element, int waitTime){
        pageWait(waitTime).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisible(WebElement element){
        pageWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementNotVisible(WebElement element){
        pageWait().until(ExpectedConditions.invisibilityOf(element));
    }


    public void waitForElementsVisible(List<WebElement> elements, int waitTime){
        pageWait(waitTime).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementsVisible(List<WebElement> elements){
        pageWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementsNotVisible(List<WebElement> elements){
        pageWait().until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    private WebDriverWait pageWait() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(45));
    }

    private WebDriverWait pageWait(int waitTime) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(waitTime));
    }
}
