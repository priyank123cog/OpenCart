package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
	
	
	
	public HomePage(WebDriver driver) {    // I have created a Basepage and i am  for every page from that we call 
		
		super(driver);
	}   
	// constructor name should be same as class name.

  // with out creating the constructor we can not invoke parent class constructor.  this is related to inheritance concept.
	
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//li[1]")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement lnkLogin;
	
	
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
		
	}
	public void clickLogin() {
		lnkLogin.click();
		
	}
	
	
}
