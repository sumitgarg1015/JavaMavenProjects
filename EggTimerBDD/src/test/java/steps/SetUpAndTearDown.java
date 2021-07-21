package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.SeleniumDriverUtil;

public class SetUpAndTearDown {

	@Before
	public void setUpBrowser() {

		SeleniumDriverUtil.setUpDriver();

	}

	@After
	public void tearDown(Scenario scenario) {

		WebDriver driver = SeleniumDriverUtil.getDriver();
		System.out.println(scenario.isFailed());
		if (scenario.isFailed()) {
			byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotBytes, "image/png", "screenshot");

		}
		SeleniumDriverUtil.tearDown();

	}

}
