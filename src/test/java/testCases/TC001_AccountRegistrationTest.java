package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registartion() {
		
		
		logger.info("***** Starting TC001_AccountRegistrationTest ****");
		
		try {
			
		//create object of homepage class to call homepage methods from pageObjects package
			
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details...");
		regpage.setFirstName(randomeString().toUpperCase()); //cretae firstname is randomely 
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
		
		regpage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumberic();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		//validation
		logger.info("Validating expected message..");
		String confmsg = regpage.getConfirmationMsg(); //msg in string format
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
		catch (Exception e) {
			logger.error("Test failed..");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
	}
	
	

}
