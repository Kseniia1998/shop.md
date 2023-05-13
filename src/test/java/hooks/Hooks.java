package hooks;

import com.core.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import com.core.WebDriverHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class Hooks {

    @BeforeSuite
    public void beforeScenario(Scenario scenario){
        log.info("Starting scenario: {}", scenario.getName());
        log.info("Scenario tag {}", scenario.getSourceTagNames());
    }

    @BeforeSuite
    public void startBrowser(){
        String baseUrl = PropertyReader.getConfigProperty("url");
        log.info("Opening the main page");
        WebDriverHelper.getChromeDriver().get(baseUrl);
    }

    @AfterStep
    public void addScreenshot(Scenario scenario){
        if(scenario.isFailed()) {
            log.info("Creating page screen shot");
            final byte[] screenshot = ((TakesScreenshot) WebDriverHelper.getChromeDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario){
        log.info("Finished scenario: {}", scenario.getName());
    }

    @AfterSuite
    public void driverTearDown(){
        log.info("Quitting the browser");
        WebDriverHelper.getChromeDriver().close();
        WebDriverHelper.getChromeDriver().quit();
    }
}
