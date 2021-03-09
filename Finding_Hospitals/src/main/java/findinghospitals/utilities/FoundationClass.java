package findinghospitals.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;





public class FoundationClass extends DriverClass{
	public static ArrayList<String> reportHospitalList = new ArrayList<String>();
	public static ArrayList<String> reportTopCities = new ArrayList<String>();
	public static ArrayList<String> reportAlerts = new ArrayList<String>();
	public ExtentReports report=Reporter.getReport();

	public ExtentTest logger;
	public WebDriver driver;
	
	public void openBrowser(String browserName) throws Exception {
		 driver=DriverClass.setupDriver(browserName);	 
	}	
	
	

	 

	public void checkTitle(String title){
		try {
		String verify= driver.getTitle();
		if(verify.equals(title)) 
			reportPass("Title verified successfully");
		else
			reportFail("Title not matching");
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}	
	}
	public void jsClickTheElement(WebElement element){
		try {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		reportPass("Element clicked successfully");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}	
	}
	
	public void isElementEnabled(WebElement element) {
		try {
			if(element.isEnabled())
				reportPass("Element is Enabled");
			else
				reportFail("Element not Enabled");
			
		}catch(Exception e) {
			reportFail(e.getMessage());
		}	
	}
	
	
	public void clearWithBackspace (WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			int elementTextSize= (element.getAttribute("value")).length();
			while(elementTextSize!=0) {
				element.sendKeys(Keys.BACK_SPACE);
				elementTextSize--;
			}
			
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}	
		
	}

	// Clicking on an element taking locator key as input
		public void clearTheTextbox(WebElement element) {
			try {			
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.clear();
				reportPass("Textbox cleared successfully");
			}catch(Exception e) {
				reportFail(e.getMessage());
			}	
		}

		
		public boolean isAlertPresent() 
		{ 
		    try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }    
		    catch (Exception ex) 
		    { 
		        return false; 
		    }   // catch 
		}   // isAlertPresent()
	// Clicking on an element taking locator key as input
	public void clickTheElement(WebElement element) {
		try {			
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportPass("Element clicked successfully");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}	
	}

//	 Sending Keys to an element taking locator key and the String to be sent as
//	 input
	public void sendTheKeys(WebElement element, String keys)  {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(keys); // sending keys to the element
			reportPass("TextBox filled successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//Prining the list taking element list as input
	
	public void printTheList(List<WebElement> elementsList, ArrayList<String> list)
	{
		
		try {
		
		for(WebElement element : elementsList)
		{
			list.add(element.getText());
			System.out.println(element.getText());
		}
	}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
		
	}

	 //Checking the visibility of an element taking locator key as input
	public void checkVisibilityOfTheElement(WebElement element)  {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			if(element.isDisplayed())
				reportPass("Element is visible");
			else
				reportFail("Element is not visible");
		}
			 catch (Exception e) {
					reportFail(e.getMessage());
				}
	

	}
	
	public String getTheText(WebElement element)  {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			reportPass("Text retrieved successfully");// Using the explicit wait to wait till the expected condition
			return element.getText();  // Getting text from the element and returning it
		} catch (Exception e) {
			reportFail(e.getMessage());
			return null;
		}
	}

	





	// Scroll to End Of Page
	public void ScrollToEndOfPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Scrolling tom end of the page
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Highlight the Element before Taking the Screenshot
	public void highlightTheElement(WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
		jse.executeScript("arguments[0].style.border='5px solid red'", element); // Highlighting an
																									// element using its																							// locator
	}

	// Function to wait for the page to load completely
	public void waitForPageLoad() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
			int i = 0;
			while (i != 180) {
				String pageState = (String) js.executeScript("return document.readyState;"); // Storing the state of
																								// page in a string
				if (pageState.equalsIgnoreCase("Complete")) { // Checking if the state of page is complete
					break;
				} else {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void reportFail(String message) {
		logger.log(Status.FAIL,message);
		ScreenShots.takeScreenshotOnFailure(driver);
		Assert.fail(message);
	}
	public void reportPass(String message) {
		logger.log(Status.PASS,message);
	}
	
	public void acceptAlert() throws Exception{
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.log(Status.PASS, "Alert Accepted");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	public String getAlertText() throws Exception{
		try {
			Alert alert = driver.switchTo().alert();
			logger.log(Status.PASS, "Alert Text Retrieved");
			return alert.getText();
		} catch (Exception e) {
			reportFail(e.getMessage());
			return null;
		}
		
	}
	
	
}

	

