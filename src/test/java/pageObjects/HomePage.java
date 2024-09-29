package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) { //constructor will try to invoke at the run time
		
		//passing driver to the parent class constructor, and the parent class constructor receive driver and initiate the driver
		super(driver); //super keyword invoke parent class (BasePage) constructor
	}
	
	
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement lnkMyaccount;
	
@FindBy(xpath="//a[normalize-space()='Register']")
WebElement lnkRegister;

@FindBy(xpath="//a[normalize-space()='Login']")
WebElement linkLogin;

public void clickMyaccount() {
	
	lnkMyaccount.click();
	}
	
public void clickRegister() {
	
	lnkRegister.click();
}

public void clickLogin() {
	
	linkLogin.click();
}




}
