package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {
	

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")  //getting data provider from different class.
	public void Verify_LoginDDT(String email,String pwd,String exp) {
		
		//logger.info("******Starting TC_003_LoginTest****");
		try {
		HomePage HP=new HomePage(driver);
		HP.clickMyAccount();
		HP.clickLogin();
		
		LoginPage LP=new LoginPage(driver);
		LP.setEmail(email);
		LP.setPassword(pwd);
		LP.clkLogin();
		
		
		
		MyAccountPage MAP=new MyAccountPage(driver);
		boolean targetpage=MAP.isMyAccountPageExists();
		
		
		/*Data is valid  - login success - test pass  - logout
		login failed - test fail

         Data is invalid - login success - test fail  - logout
		login failed - test pass
*/
		
		if(exp.equalsIgnoreCase("valid")) {
			
			if(targetpage==true) {
				MAP.clkLogout();//clicking logout
				Assert.assertTrue(true);
				
			}
			else {
				
				Assert.assertFalse(false);
			}
			
			
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				MAP.clkLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		//logger.info("******Completed TC_003_LoginTest****");
	}
	}
	