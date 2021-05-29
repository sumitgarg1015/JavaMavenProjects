package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.TestBase;

public class WHReviewCommentsPage extends TestBase{
	By selectInsuranceBy, typeCommentsBy, submitCommentsBy, dropDownItemsBy;
	private WebElement selectInsurance;
	private List<WebElement> myList;
	
	public WHReviewCommentsPage(){
		
		selectInsuranceBy = By.xpath(OR.getProperty("insTypeDropdown"));
		typeCommentsBy = By.xpath(OR.getProperty("commentsArea"));
		submitCommentsBy = By.xpath(OR.getProperty("buttonSubmit"));
		dropDownItemsBy = By.xpath(OR.getProperty("itemsDropDown"));
	}
	
	
	public void selectInsuranceType(){
		
		String itemToBeSelected = config.getProperty("insuranceType");
		selectInsurance = driver.findElement(selectInsuranceBy);
		selectInsurance.click();
		
		myList = driver.findElements(dropDownItemsBy);

		for(int i=0;i<myList.size();i++) {
			
			if(myList.get(i).getText().equalsIgnoreCase(itemToBeSelected)) {
				act.moveToElement(myList.get(i)).click().build().perform();
				break;
			}
			
		}
		
	}
	
	public void typeComments() {
		String message = config.getProperty("reviewComments");
		driver.findElement(typeCommentsBy).click();
		driver.findElement(typeCommentsBy).sendKeys(message);
	}
	
	public void submitComments() {
		driver.findElement(submitCommentsBy).click();
	}
	
}
