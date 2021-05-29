package assignments;

import base.TestBase;
import pages.FacebookHomePage;
import pages.FacebookWelcomePage;

public class Assignment1 extends TestBase{

	public static void main(String[] args) throws Exception {
		
		FacebookHomePage lPage = new FacebookHomePage();
		FacebookWelcomePage pPage = new FacebookWelcomePage();
		
		driver.get(config.getProperty("facebookUrl"));
		
		// Please goto config.properties file in properties package to change the loginId and password
		
		lPage.typeUserId();
		lPage.typePass();
		lPage.clickLoginButton();
		
		try {
			if(pPage.verifyLogo()){
				System.out.println("Hello World");
			}
		}catch(Exception e) {
			System.out.println("Logo not found!");
		}
		finally {
			driver.quit();
		}
		
	}

}
