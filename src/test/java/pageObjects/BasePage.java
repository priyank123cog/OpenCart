package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	
public BasePage(WebDriver driver) {    // created constructor.   //in this we are declaring local driver.
		
		this.driver=driver;                           // By using this base page we can call all the pages for PageFactory.

		PageFactory.initElements(driver, this);
}
}
