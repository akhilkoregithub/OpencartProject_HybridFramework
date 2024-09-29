package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    // getting data provider from DataProviders class of utilities package
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven")
    public void verify_loginDDT(String email, String pwd, String exp) {

        logger.info("****** Starting TC_003_LoginDDT ******");

        try {

            // homepage
            HomePage hp = new HomePage(driver);
            hp.clickMyaccount();
            hp.clickLogin();

            // login page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            // my account page
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            /*
             * Data is valid - login success - test pass - logout Data is valid - login
             * failed - test fail
             */
            if (exp.equalsIgnoreCase("Valid")) { // data is valid, case-insensitive

                if (targetPage == true) { // login is successful

                	macc.clickLogout();
                    Assert.assertTrue(true, "Login successful for valid credentials.");
                } else {

                    Assert.assertTrue(false, "Login failed for valid credentials.");
                }

            } else if (exp.equalsIgnoreCase("Invalid")) { // handling invalid data

                if (targetPage == true) { // login is successful for invalid credentials

                	macc.clickLogout();
                    Assert.assertTrue(false, "Login should have failed, but it succeeded for invalid credentials.");
                } else {

                    Assert.assertTrue(true, "Login failed as expected for invalid credentials.");
                }
            }

        } catch (Exception e) {
            Assert.fail("An unexpected exception occurred: " + e.getMessage());
        }

        logger.info("****** Finished TC_003_LoginDDT ******");

    }

}
