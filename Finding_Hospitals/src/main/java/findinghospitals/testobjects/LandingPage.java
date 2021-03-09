package findinghospitals.testobjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import findinghospitals.utilities.FoundationClass;
import findinghospitals.utilities.PropertiesFile;

public class LandingPage extends InitializationClass {
	
	public LandingPage(WebDriver driver, ExtentTest logger) throws IOException { 
		super(driver,logger);
	}

	
	@FindBy(xpath="//input[@data-qa-id='omni-searchbox-locality']")
	public WebElement locationTextbox;
	@FindBy(xpath="//input[@data-qa-id='omni-searchbox-keyword']")
	public WebElement searchbox;
    @FindBy(xpath="//div[@data-qa-id='omni-suggestion-main' and text()='Hospital']")
    public WebElement firstSuggestionSearchBox; 
    @FindBy(xpath="//div[@class='navbar desktop en']//a[@title='tests']")
	public WebElement diagnosticButton;
    @FindBy(xpath="//*[text()='For Providers']/following-sibling::span")
	public WebElement providersButton;
    @FindBy(xpath="//*[text()='Corporate wellness']")
	public WebElement corporateWellnessButton;
    @FindBy(xpath="//div[@data-qa-id='omni-suggestions-list']//div[@class='c-omni-suggestion-group']/div/span/div[text()='Bangalore']")
    public WebElement firstSuggestionLocation;
    
    public void locationBoxEnabled() {
    	logger.log(Status.INFO, "Enablement of Location Textbox");
    	isElementEnabled(locationTextbox);
    }
    
    public void searchBoxEnabled() {
    	logger.log(Status.INFO, "Enablement of Search Box");
    	isElementEnabled(searchbox);
    }
    public void diagnosticButtonEnabled() {
    	logger.log(Status.INFO, "Enablement of Diagnostic Button");
    	isElementEnabled(diagnosticButton);
    }
    public void providersButtonEnabled() {
    	logger.log(Status.INFO, "Enablement of Providers Button");
    	isElementEnabled(providersButton);
    }
    public void corporateWellnessButtonEnabled() {
    	logger.log(Status.INFO, "Enablement of Corporate Wellness Button");
    	isElementEnabled(corporateWellnessButton);
    }
    
    
    
    public void clearLocation() {
    	logger.log(Status.INFO, "Clearing the locationTextbox");
    	clearWithBackspace(locationTextbox);

	}
	public void fillLocation(String location) throws InterruptedException {
		logger.log(Status.INFO, "Filling the location textbox");
		
		sendTheKeys(locationTextbox,location);
		//Thread.sleep(2000);
//		WebElement element=driver.findElement(By.xpath("//*[@id='c-omni-container']/div/div[1]/div[1]/span[2]/span)"));
//		jsClickTheElement(element);
		//clearTheTextbox(locationTextbox);
	//	sendTheKeys(locationTextbox,location);
		wait.until(ExpectedConditions.visibilityOf(firstSuggestionLocation));
		waitForPageLoad();
		logger.log(Status.INFO, "Clicking the First Suggestion");
		clickTheElement(firstSuggestionLocation);;
	}
	public void fillSearchbox(String searchKeys) {
		logger.log(Status.INFO, "Filling the SearchBox");
		sendTheKeys(searchbox, searchKeys);
		wait.until(ExpectedConditions.visibilityOf(firstSuggestionSearchBox));
		logger.log(Status.INFO, "Clicking the First Suggestion");
		clickTheElement(firstSuggestionSearchBox);
	}
	
	public HospitalListPage clickSearch() throws IOException {
		logger.log(Status.INFO, "Navigating to Next Page");
    	searchbox.sendKeys(Keys.ENTER);
    	waitForPageLoad();
    	logger.log(Status.INFO, "Verifying the title of the page");
    	checkTitle("Best Hospitals in Bangalore - Book Appointment Online, View Fees, Reviews | Practo");
    	logger.log(Status.PASS, "Navigated to next page Successfully");
    	HospitalListPage hospitalListPage = new HospitalListPage(driver, logger);
		PageFactory.initElements(driver, hospitalListPage);
		return hospitalListPage;
	}
	
	public DiagnosticPage clickDiagnostic() throws IOException, InterruptedException {
		logger.log(Status.INFO, "Clicking the diagnostic button");
		jsClickTheElement(diagnosticButton);
		Thread.sleep(3000);
		waitForPageLoad();
		logger.log(Status.INFO, "Verifying the title of the page");
		checkTitle("Blood Tests | Book Diagnostic Tests from Home at Best Prices | Practo");
		DiagnosticPage diagnosticPage=new DiagnosticPage(driver, logger);
		PageFactory.initElements(driver, diagnosticPage);
		return diagnosticPage;
		
	}
	
	public void clickProvidersButton() {
		logger.log(Status.INFO, "Clicking the Providers button");
		clickTheElement(providersButton);
	}
	public  CorporateWellnessPage clickCorporateWellnessButton() throws IOException {
		logger.log(Status.INFO, "Clicking the CorporateWellness Button");
		clickTheElement(corporateWellnessButton);
		waitForPageLoad();
		Set<String> windowHandles =driver.getWindowHandles();
		Iterator<String> itr=windowHandles.iterator();
		itr.next();
		driver.switchTo().window(itr.next());
		waitForPageLoad();
		logger.log(Status.INFO, "Switching to the new Tab");
		logger.log(Status.INFO, "Verifying the title of the page");
		checkTitle("Employee Health | Corporate Health & Wellness Plans | Practo");
		CorporateWellnessPage corporateWellnessPage=new CorporateWellnessPage(driver,logger);
		PageFactory.initElements(driver, corporateWellnessPage);
		return corporateWellnessPage;
		
		
		
		
	}
	
	
}
