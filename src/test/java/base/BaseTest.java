package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;
import pageObjects.ResultPage;



public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	Properties prop = new Properties();
	
	
	public WebDriver intializeDriver() throws IOException
	{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//globalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("url"));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",  System.getProperty("user.dir")+"//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("url"));
		}
		else
		{
			driver = null;
		}
		
		
		return driver;
				
	}
	
	public HashMap<String, String> getDataFromJson(String funcName) throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//globalData.properties");
		prop.load(fis);
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+prop.getProperty(funcName)),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> data = mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){});
		return data;
	}
	
@BeforeClass
public LandingPage launchApplication() throws IOException
{
	driver = intializeDriver();
	landingPage= new LandingPage(driver);
	return landingPage;
	}
	

}
