package pageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import base.BaseKeywords;

public class ResultPage extends BaseKeywords{
	
	WebDriver driver;
	
	
	public ResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkBrokenLink() throws MalformedURLException, IOException
	{
		driver.switchTo().frame("master-1");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		SoftAssert sa= new SoftAssert();
		for(int i=0;i<links.size();i++)
		{
			String link = links.get(i).getAttribute("href");
			System.out.println(link);
			HttpURLConnection con = (HttpURLConnection)new URL(link).openConnection();
			con.setRequestMethod("HEAD");
			con.connect();
			System.out.println(con.getResponseCode());
			if(con.getResponseCode()>=400)
			{
				sa.assertTrue(false);
			}
			else
			{
				sa.assertTrue(true);
			}
		
		}
		sa.assertAll();
	}

}
