package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass {
	
	//this class is base class for all the test case classes under tesecases package
    //base class contains the re-usebility methods, what ever is required for all the test cases, those methods we will keep inside the base class
	
public static WebDriver driver;
public Logger logger; //Log4j 
public Properties p;
	
	@BeforeClass(groups={"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		//fetch the automatically log4j2 xml file
		logger = LogManager.getLogger(this.getClass()); //LogManager is a fre-defined class
		
		
		//selenium grid for testcase execution environment on remote through os & browser
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if (os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN10);
			}
            else if (os.equalsIgnoreCase("linux")) {
				
				capabilities.setPlatform(Platform.LINUX);
			}
			else if (os.equalsIgnoreCase("mac")) {
				
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				
				System.out.println("no matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("no matching browser"); return; 
			}
			
			//launching webdriver 
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
		
		
		//testcase execution environment on local through os & browser
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(p.getProperty("appURL1"));  //reading url from properties file
	    driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity", "Regression", "Master"})
	public void tearDown() {
		
		driver.quit();	
	}
	
	
	//randomly generate the data at run time
	public String randomeString() { //email and first name and last name for randomly
			
		//RandomStringUtils class from commons-lang3 dependency
		String generatedstring =  RandomStringUtils.randomAlphanumeric(9);
		return generatedstring;	
	}
		
	public String randomeNumber() { //telephone number randomely
			
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
		
	public String randomeAlphaNumberic() {  //password for randomely
			
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname)  {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
	
	
}
