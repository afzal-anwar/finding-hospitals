package findinghospitals.testobjects;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import findinghospitals.utilities.PropertiesFile;

public class DiagnosticPage extends InitializationClass {

	
	public DiagnosticPage(WebDriver driver, ExtentTest logger) throws IOException { 
		super(driver,logger);

	}

//	@FindBy(className="c-search__input--location")
//	public WebElement searchbox;
	@FindBy(xpath="//li[@class='u-text--center']/div[2]")
	public List<WebElement> cityList;
	
//	public void clickSearchBox() {
//		logger.log(Status.INFO, "clicking the searchbox");
//		searchbox.click();
//	}
	public void printCityList() {
		logger.log(Status.INFO, "Printing the list of cities:");
		reportTopCities.add("List Of Cities");
		printTheList(cityList,reportTopCities);
		logger.log(Status.PASS, "List of cities printed successfully");
	}
	
	
	
	
}
