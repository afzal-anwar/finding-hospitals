package findinghospitals.testobjects;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import findinghospitals.utilities.PropertiesFile;
import findinghospitals.utilities.ScreenShots;

public class CorporateWellnessPage extends InitializationClass  {

	
	
	public CorporateWellnessPage(WebDriver driver, ExtentTest logger) throws IOException { 
		super(driver, logger);
	}

	@FindBy(id="name")
	public WebElement nameElement;
	@FindBy(id="organization_name")
	public WebElement organizationNameElement;
	@FindBy(id="official_email_id")
	public WebElement officialEmailElement;
	@FindBy(id="official_phone_no")
	public WebElement phoneNumberElement;
	@FindBy(id="button-style")
	public WebElement scheduleButtonElement;
	
	
	 public void nameEnabled() {
	    	logger.log(Status.INFO, "Enablement of Name Text Box");
	    	isElementEnabled(nameElement);
	    }
	 
	 public void organizationNameEnabled() {
	    	logger.log(Status.INFO, "Enablement of Organization Name Textbox");
	    	isElementEnabled(organizationNameElement);
	    }
	 public void emailEnabled() {
	    	logger.log(Status.INFO, "Enablement of Email Text Box");
	    	isElementEnabled(officialEmailElement);
	    }
	 public void phoneNumberEnabled() {
	    	logger.log(Status.INFO, "Enablement of Phone Number Text Box");
	    	isElementEnabled(phoneNumberElement);
	    }
	 public void scheduleButtonEnabled() {
	    	logger.log(Status.INFO, "Enablement of Schedule Button");
	    	isElementEnabled(scheduleButtonElement);
	    }
	 
	 
	 
	 
	public void fillName(String name) {
		//clearTheTextbox(nameElement);
		logger.log(Status.INFO, "Filling the name textbox");
		sendTheKeys(nameElement, name);
	}
	public void fillEmail(String email) {
		//clearTheTextbox(officialEmailElement);
		logger.log(Status.INFO, "Filling the email textbox");
		sendTheKeys(officialEmailElement, email);
	}
	public void fillOrganizationName(String organizationName) {
		//clearTheTextbox(organizationNameElement);
		logger.log(Status.INFO, "Filling the organization name textbox");
		sendTheKeys(organizationNameElement, organizationName);
	}
	public void fillPhone(String phone) {
		//clearTheTextbox(phoneNumberElement);
		logger.log(Status.INFO, "Filling the phone number textbox");
		sendTheKeys(phoneNumberElement, phone);
	}
	public void clickScheduleButton() throws HeadlessException, IOException, AWTException {
		logger.log(Status.INFO, "Clicking the Schedule a Demo textbox");
		jsClickTheElement(scheduleButtonElement);
	}
	public void handleAlert(String Message) throws Exception {
		Thread.sleep(2000);
		if(Message.equalsIgnoreCase("Alert should Pop Up")) {
		if(!isAlertPresent()) {
			reportAlerts.add("Alert was Expected but not received- FAILED");
			reportFail("Field Validation Failed");
		}
		else {
			logger.log(Status.PASS, "Field Validation Successful");
			reportAlerts.add("Alert Received successfully as: "+getAlertText()+"- PASSED");
			ScreenShots.takeScreenshot(driver);
			logger.log(Status.INFO, "Accepting the alert");
			acceptAlert();
		}
	}
		else{
			if(!isAlertPresent()) {
				reportAlerts.add("No Alert was Expected- PASSED");
				logger.log(Status.PASS, "Field Validation Successful");
			}
			else {
				reportAlerts.add("No Alert was Expected but Received as: "+ getAlertText()+"- FAILED");
				ScreenShots.takeScreenshotOnFailure(driver);
				logger.log(Status.INFO, "Accepting the alert");
				acceptAlert();
				reportFail("Field Validation Failed");
			}
		}
			
	}
	
}
