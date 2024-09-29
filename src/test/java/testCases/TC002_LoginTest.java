package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		
		logger.info("***** startingbTc_002_LoginTest *****");
		
		try {
			
		//hmepage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//myaccountpage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		//target will pass giving true otherwise it will failed display "Login failed" massage
		//Assert.assertEquals(targetPage, true, "Login failed"); //expected true
	    Assert.assertTrue(targetPage);
		
		}
		catch(Exception e) {
			
			Assert.fail();
		} 
	    
	    logger.info("****** Finished TC_002_LoginTest *******");
	
	
	
	}

}
