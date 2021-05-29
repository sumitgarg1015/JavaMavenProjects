package assignments;

import base.TestBase;
import pages.WHReviewCommentsPage;
import pages.WHReviewConfirmationPage;
import pages.WHUserProfilePage;
import pages.WHHomePage;
import pages.WHLoginPage;

public class Assignment2 extends TestBase {

	private static WHHomePage walletHomePage;
	private static WHLoginPage walletHubLoginPage;
	private static WHReviewCommentsPage reviewComments;
	private static WHReviewConfirmationPage reviewConfirmation;
	private static String confirmationMessage;
	private static WHUserProfilePage uProfilePage;
	
	public static void main(String[] args) throws Exception {

	
		int ratingCount;
		
		try {
			if (driver == null) {
				setUp();
			}

			// Step 1
			driver.get(config.getProperty("walletHubUrl"));
			
			reviewComments = new WHReviewCommentsPage();
			reviewConfirmation = new WHReviewConfirmationPage();
			uProfilePage = new WHUserProfilePage();
			
			
			login();
			
			if (walletHomePage.verifyWalletIsDisplayed()) {
				System.out.println("User is Logged in!");
			}
			
			walletHomePage.clickReviewTab();
			
			//Step 2(a)
			walletHomePage.hoverMouseOverRating();
			
			//Step 2(b,c)
			if (walletHomePage.verifyRatingIsGlowing()) {
				walletHomePage.performRatingSelection();
			}
			
			
			reviewComments.selectInsuranceType();
			reviewComments.typeComments();
			reviewComments.submitComments();
			
			confirmationMessage = config.getProperty("reviewMessage");
			
			if(reviewConfirmation.getConfirmationMessage().equalsIgnoreCase(confirmationMessage)){
				System.out.println("Review has been successfully Posted!");
			}
			
			reviewConfirmation.hoverMouseOverUserID();
			reviewConfirmation.clickProfileLink();
			
			ratingCount = Integer.parseInt(config.getProperty("userRatingCount"));
			
			if(uProfilePage.getStarRatingCount()==ratingCount) {
				System.out.println("Review feed got updated");
			}
			
			

		} catch (Exception e) {
			System.out.println("Execution Stopped due to "+e.getMessage());
		}finally {
			driver.quit();
		}

	}

	public static void login() {

		walletHomePage = new WHHomePage();
		walletHubLoginPage = new WHLoginPage();

		walletHomePage.clickLoginLink();

		walletHubLoginPage.typeUserEmail();
		walletHubLoginPage.typeUserPass();
		walletHubLoginPage.clickLoginButton();

	}

}
