package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
   @Test(groups={"Regression","Master"})
	public void verify_Account_Registration() {
	   
	   logger.info("**** Starting TC001_AccountRegistrationTest *****");
		try {
		HomePage HP= new HomePage(driver);
		HP.clickMyAccount();
		logger.info("Clicked on My Account link");
		HP.clickRegister();
		logger.info("Clicked on Register link");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing Customer details");
		//regpage.setfirstName("Priyank");
		//regpage.setlastName("chunduru");
		regpage.setfirstName(randomeString().toUpperCase());
		regpage.setlastName(randomeString().toUpperCase());
		//regpage.setEmail("bhanu11@gmail.com");
		regpage.setEmail(randomeString()+"@gmail.com");  // by using this random string format every time it will generate different random email Id.
		//regpage.settelephone("7013222242");
		regpage.settelephone(randomeNumber());
		//regpage.setPassword("Bhanu@#99123");
		//regpage.setCfpassword("Bhanu@#99123");
		
		String password=randomeAlphaNumeric();  // if we call password it will enter same password for password and confirm password fields.
		regpage.setPassword(password);
		regpage.setCfpassword(password);
		regpage.selectCheckbox();
		regpage.clickContinue();
		logger.info("Validating expected Result");
		String confmesage=regpage.getConfirmationmessage();
		
		Assert.assertEquals(confmesage, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest *****");
		
	}
	
	

}
