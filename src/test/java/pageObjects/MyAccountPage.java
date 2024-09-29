package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver){
		
		super(driver);
	}
	
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")     //my account page heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;

	
	
	public boolean isMyAccountPageExists() { //it is not a validation, just checking page exists or not
		
		try {
			
			return (msgHeading.isDisplayed());
		}
		catch (Exception e) {
			
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	

}
