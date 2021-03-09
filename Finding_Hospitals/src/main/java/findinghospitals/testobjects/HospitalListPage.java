package findinghospitals.testobjects;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import findinghospitals.utilities.PropertiesFile;

public class HospitalListPage extends InitializationClass {

	
	public HospitalListPage(WebDriver driver, ExtentTest logger) throws IOException { 
		super(driver,logger);
	
	}
	
	
	
	@FindBy(xpath="//*[@data-qa-id='star_rating' and @title>3.5]/ancestor::div[@class='pure-u-6-24']/preceding-sibling::div//a//h2")
	public List<WebElement> hospitalList;
	@FindBy(xpath="//*[@data-qa-id='Open_24X7_label']/span")
	public WebElement twentyFourCheckBox;
	@FindBy(xpath="//*[@data-qa-id='all_filters_icon']")
	public WebElement allFiltersButton;
	@FindBy(xpath="//*[@data-qa-id='Has_Parking_checkbox']")
	public WebElement hasParkingButton;
	
	 public void twentyFourCheckBoxEnabled() {
	    	logger.log(Status.INFO, "Enablement of 24X7 Checkbox");
	    	isElementEnabled(twentyFourCheckBox);
	    }
	 public void hasParkingButtonEnabled() {
	    	logger.log(Status.INFO, "Enablement of Has Parking Button");
	    	isElementEnabled(hasParkingButton);
	    }
	 public void allFiltersButtonEnabled() {
	    	logger.log(Status.INFO, "Enablement of All Filters Button");
	    	isElementEnabled(allFiltersButton);
	    	
	    }
	 
	 public void clickAllFilters() {
		 logger.log(Status.INFO, "Clicking on AllFilters button");
		 jsClickTheElement(allFiltersButton);
		 waitForPageLoad();
	 }
	 
	 
	public void addFilter() {
		
		
		 clickAllFilters();
		 logger.log(Status.INFO, "Clicking Has Parking Checkbox");
		 jsClickTheElement(hasParkingButton);
		 waitForPageLoad();
		 logger.log(Status.INFO, "Clicking on 24X7 checkbox");
		 jsClickTheElement(twentyFourCheckBox);
		 waitForPageLoad();
		 
	}
		
	public void printHospitalList() throws InterruptedException {
	Thread.sleep(5000);
	logger.log(Status.INFO, "Printing the list of Hospitals:");
	reportHospitalList.add("List Of Hospitals");
	printTheList(hospitalList, reportHospitalList);
	logger.log(Status.PASS, "List of hospitals printed successfully");
	}
	
	public LandingPage navigateBack() throws IOException {
		logger.log(Status.INFO, "Navigating to previous page");
		driver.navigate().to(PropertiesFile.readProperties().getProperty("url"));
		waitForPageLoad();
		logger.log(Status.PASS, "Navigated to previous page Successfully");
		logger.log(Status.INFO, "Verifying the title of the page");
		checkTitle("Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		LandingPage landingPage=new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
		
	}
	
}
