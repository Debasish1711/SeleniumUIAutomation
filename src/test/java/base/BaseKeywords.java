package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseKeywords {
	Properties prop;
	WebDriver driver;
	
	public BaseKeywords(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setText(WebElement element,String text)
	{
		element.sendKeys(text);
	}
	
	public void clickButton(WebElement element)
	{
		element.click();
	}
	
	

}
