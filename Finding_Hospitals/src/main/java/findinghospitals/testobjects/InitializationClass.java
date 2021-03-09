package findinghospitals.testobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import findinghospitals.utilities.DriverClass;
import findinghospitals.utilities.FoundationClass;

public class InitializationClass extends FoundationClass{

	
	public InitializationClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	

	
	public LandingPage openWebsite(String Url) throws Exception {
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get(Url);
		waitForPageLoad();
		logger.log(Status.PASS, "Opened the Website Successfully");
		logger.log(Status.INFO, "Verifying the title of the page");
		checkTitle("Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		LandingPage landingPage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}
	
	
	
}
