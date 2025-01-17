package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})

	public void setUp(String os, String br) throws IOException {
		
		//Loading config.properties file
		
		FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
		//FileReader file2=new FileReader("./src//test//resources//config.properties");  // we can read config.properties file by using file reader and file inputstream.
		p=new Properties();
		p.load(file);         // this is for config.properties file.
		
		logger= LogManager.getLogger(this.getClass());  // this is for logs
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) // if we want to execute the code through remote then we will use this code(Selenium Grid)
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
				
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
				
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))  // if we want to execute the code through local then we will use this code.
		{

			
		
		switch(br.toLowerCase()) {
		case "chrome": driver=new ChromeDriver();break;     
		case "edge": driver=new EdgeDriver();break;               // if you want to do parallel execution, with different browsers.
		case "firefox": driver=new FirefoxDriver();break; 
		default: System.out.println("Invalid browser name");return;
		}
		
			}
		
		driver.manage().deleteAllCookies();
		WebDriverManager.chromedriver().setup();
		driver.get(p.getProperty("appURL")); // by using config.properties i am reading URL.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void tearDowntest() {

		driver.quit();

	}

	public String randomeString() {

		String generatedString= RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomeNumber() {

		String generatednumber= RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomeAlphaNumeric() {

		String generatedString= RandomStringUtils.randomAlphabetic(4);
		String generatednumber= RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatednumber);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
