package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import locators.TimeCounterPageLocators;

import static locators.TimeCounterPageLocators.*;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import utils.SeleniumDriverUtil;

public class CountDownTimerPage {

	private static String timer;
	private static int retry = 0;
	private static int maxCount, seconds, newSeconds, targetSeconds;
	private static LocalDateTime now;

	public CountDownTimerPage() {
		PageFactory.initElements(SeleniumDriverUtil.getDriver(), TimeCounterPageLocators.class);
	}

	public static int getCounter() {
		timer = countDown.getText().split(" ")[0];
		System.out.println(timer);
		return Integer.parseInt(timer);
	}

	public void verifyCountDownTimer(){

		maxCount = SeleniumDriverUtil.getTimer();
		now = LocalDateTime.now();
		seconds = now.getSecond();

		while (maxCount > 0) {

			newSeconds = now.getSecond() - seconds;
			targetSeconds = maxCount - getCounter();
			
			if (newSeconds == targetSeconds) {

				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					int i = 0;

					public void run() {
						i++;
						if(i>0) {
							timer.cancel();
						}
					}
				}, 0, 1000);
				
				System.out.println(maxCount);
				maxCount--;
				
			}else {
				ExtentCucumberAdapter.getCurrentStep().fail("Timer is not working correctly");
			}

		}

		if (maxCount == 0) {
			System.out.println(maxCount);
			System.out.println("Timing Finished!");
		}
        manageAlert();
	}

	public static void manageAlert() {

		if (isAlertPresent()) {
			Alert alt = SeleniumDriverUtil.getDriver().switchTo().alert();
			alt.accept();
			SeleniumDriverUtil.getDriver().switchTo().defaultContent();
		}

	}

	public static boolean isAlertPresent() {

		try {
			SeleniumDriverUtil.getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

}
