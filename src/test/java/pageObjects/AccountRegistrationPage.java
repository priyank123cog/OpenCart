package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement firstName;
	@FindBy(xpath="//input[@name='lastname']")
	WebElement lastName;
	@FindBy(xpath="//input[@name='email']")
	WebElement Email;
	@FindBy(xpath="//input[@name='telephone']")
	WebElement telPhone;
	@FindBy(xpath="//input[@name='password']")
	WebElement pasWord;
	@FindBy(xpath="//input[@name='confirm']")
	WebElement cfPassword;
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement chdPolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgeConfirmation;
	
	
	public void setfirstName(String fName) {
		firstName.sendKeys(fName);
	}
	public void setlastName(String lName) {
			lastName.sendKeys(lName);
			}
	
	public void setEmail(String mail) {
		Email.sendKeys(mail);
		}
	public void settelephone(String phone) {
		telPhone.sendKeys(phone);
		}
	public void setPassword(String pword) {
		pasWord.sendKeys(pword);
		}
	public void setCfpassword(String cpassord) {
		cfPassword.sendKeys(cpassord);
		}
	public void selectCheckbox() {
		chdPolicy.click();
		}
	public void clickContinue() {
		continueButton.click();
		}
	
	public String getConfirmationmessage() {
		try {
			
			return(msgeConfirmation.getText());
		} catch(Exception e) {
			return(e.getMessage());
			
		}
	}


}
