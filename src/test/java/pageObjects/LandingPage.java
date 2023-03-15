package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseKeywords;

public class LandingPage extends BaseKeywords{
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchTextBox;
	
	@FindBy(xpath = "//button[@class='gsc-search-button gsc-search-button-v2']")
	private WebElement searchButton;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	public boolean searchText(String text)
	{
		try {
			setText(searchTextBox, text);
			clickButton(searchButton);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
