package tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.ResultPage;

public class SearchFunctions extends BaseTest {
	
	public ResultPage resultPage;
	
	@BeforeMethod
	public void initializeTest()
	{
		resultPage = new ResultPage(driver);
	}
	
	@Test(dataProvider = "retriveData")
	public void searchFullWord(HashMap<String, String> data) throws MalformedURLException, IOException
	{
		
		
		Assert.assertTrue(landingPage.searchText(data.get("searchFullKey")));
		resultPage.checkBrokenLink();
		
		
	}
	
	@DataProvider
	public Object[] retriveData() throws IOException{
		HashMap<String,String> data = getDataFromJson("searchFunc");
		return new Object[] {data};
	}

}
