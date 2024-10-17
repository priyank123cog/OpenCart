package testCases;

import org.testng.annotations.Test;

import static org.testng.Assert.fail;

import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	
	@Test(groups={"Sanity","Master"})
	public void Verify_Login() {
		try {
		HomePage HP=new HomePage(driver);
		HP.clickMyAccount();
		HP.clickLogin();
		
		LoginPage LP=new LoginPage(driver);
		LP.setEmail(p.getProperty("email"));
		LP.setPassword(p.getProperty("password"));
		LP.clkLogin();
		
		
		
		MyAccountPage MAP=new MyAccountPage(driver);
		boolean targetpage=MAP.isMyAccountPageExists();
		Assert.assertTrue(targetpage);
		//Assert.assertEquals(targetpage, true,"Login failed");
		
		
	}
		catch(Exception e) {
			Assert.fail();
		}
	

	}
	}
