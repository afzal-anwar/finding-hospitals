package findinghospitals.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import findinghospitals.testobjects.InitializationClass;
import findinghospitals.testobjects.CorporateWellnessPage;
import findinghospitals.testobjects.DiagnosticPage;
import findinghospitals.testobjects.HospitalListPage;
import findinghospitals.testobjects.LandingPage;
import findinghospitals.utilities.DataProv;
import findinghospitals.utilities.ExcelUtils;
import findinghospitals.utilities.FoundationClass;
import findinghospitals.utilities.PropertiesFile;
import findinghospitals.utilities.Reporter;

public class Testcases extends FoundationClass {

	public LandingPage landingPage;
	HospitalListPage hospitalListPage;
	DiagnosticPage diagnosticPage;
	CorporateWellnessPage corporateWellnessPage;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserCode" })
	public void openTheBrowser(String browser) throws Exception {
		openBrowser(PropertiesFile.readProperties().getProperty(browser));
	}

	@Test(groups = { "Smoke" }, priority = 0)
	public void checkLandingPage() throws Exception {
		logger = report.createTest("Smoke_LandingPage");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.locationBoxEnabled();
		landingPage.searchBoxEnabled();
		landingPage.diagnosticButtonEnabled();
		landingPage.providersButtonEnabled();
		landingPage.clickProvidersButton();
		landingPage.corporateWellnessButtonEnabled();

	}

	@Test(groups = { "Smoke" }, priority = 1)
	public void checkHospitalPage() throws Exception {
		logger = report.createTest("Smoke_HospitalPage");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clearLocation();
		landingPage.fillLocation(PropertiesFile.readProperties().getProperty("location"));
		landingPage.fillSearchbox(PropertiesFile.readProperties().getProperty("search_keyword"));
		hospitalListPage = landingPage.clickSearch();
		hospitalListPage.twentyFourCheckBoxEnabled();
		hospitalListPage.allFiltersButtonEnabled();
		hospitalListPage.clickAllFilters();
		hospitalListPage.hasParkingButtonEnabled();

	}

	@Test(groups = { "Smoke" }, priority = 2)
	public void checkCorporateWellnessPage() throws Exception {
		logger = report.createTest("Smoke_CorporateWellnessPage");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clickProvidersButton();
		corporateWellnessPage = landingPage.clickCorporateWellnessButton();
		corporateWellnessPage.nameEnabled();
		corporateWellnessPage.organizationNameEnabled();
		corporateWellnessPage.emailEnabled();
		corporateWellnessPage.phoneNumberEnabled();
		corporateWellnessPage.scheduleButtonEnabled();

	}

	@Test(groups = { "Automation" }, priority = 0)
	public void printHospitalList() throws Exception {
		logger = report.createTest("Fetching_Hospital_List");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clearLocation();
		landingPage.fillLocation(PropertiesFile.readProperties().getProperty("location"));
		landingPage.fillSearchbox(PropertiesFile.readProperties().getProperty("search_keyword"));
		hospitalListPage = landingPage.clickSearch();
		hospitalListPage.addFilter();
		hospitalListPage.printHospitalList();
		landingPage = hospitalListPage.navigateBack();

	}

	@Test(groups = { "Automation" }, priority = 1)
	public void printTopCities() throws Exception {
		logger = report.createTest("Fetching_Top_Cities");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		diagnosticPage = landingPage.clickDiagnostic();
		diagnosticPage.printCityList();

	}

	@Test(groups = { "Automation" }, dataProvider = "formData", priority = 2)
	public void fillTheForm(String name, String org, String mail, String con, String testName, String message)
			throws Exception {
		logger = report.createTest(testName);
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clickProvidersButton();
		corporateWellnessPage = landingPage.clickCorporateWellnessButton();
		corporateWellnessPage.fillName(name);
		corporateWellnessPage.fillOrganizationName(org);
		corporateWellnessPage.fillEmail(mail);
		corporateWellnessPage.fillPhone(con);
		corporateWellnessPage.clickScheduleButton();
		corporateWellnessPage.handleAlert(message);

	}

	@Test(groups = { "Regression" }, priority = 0)
	public void regression_1() throws Exception {
		logger = report.createTest("Regression_HospitalSearch");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clearLocation();
		landingPage.fillLocation(PropertiesFile.readProperties().getProperty("location"));
		landingPage.fillSearchbox(PropertiesFile.readProperties().getProperty("search_keyword"));
		hospitalListPage = landingPage.clickSearch();
		hospitalListPage.addFilter();
		landingPage = hospitalListPage.navigateBack();

	}

	@Test(groups = { "Regression" }, priority = 1)
	public void regression_2() throws Exception {
		logger = report.createTest("Regression_TopCities");
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		diagnosticPage = landingPage.clickDiagnostic();

	}

	@Test(groups = { "Regression" }, dataProvider = "formData", priority = 2)
	public void regression_3(String name, String org, String mail, String con, String testName, String message)
			throws Exception {
		logger = report.createTest(testName);
		InitializationClass i = new InitializationClass(driver, logger);
		PageFactory.initElements(driver, i);
		landingPage = i.openWebsite((PropertiesFile.readProperties()).getProperty("url"));
		landingPage.clickProvidersButton();
		corporateWellnessPage = landingPage.clickCorporateWellnessButton();
		corporateWellnessPage.fillName(name);
		corporateWellnessPage.fillOrganizationName(org);
		corporateWellnessPage.fillEmail(mail);
		corporateWellnessPage.fillPhone(con);
		corporateWellnessPage.clickScheduleButton();

	}

	@DataProvider(name = "formData")
	public Object[][] formData(Method method) throws Exception {

		
		  if(method.getName().equals("fillTheForm")) return
		  DataProv.testData("SampleTest","Sheet1"); else
		 
		return DataProv.testData("RegressionTestData", "DataSheet");
	}

	@AfterTest(groups = "Automation")
	public void flushExcelReport() {
		ExcelUtils.writeToExcel(reportHospitalList, reportTopCities, reportAlerts);
		reportHospitalList.clear();
		reportTopCities.clear();
		reportAlerts.clear();
	}

	@AfterTest(alwaysRun = true)
	public void flushReport() throws Exception {
		report.flush();
	}

	

		

	@AfterMethod(alwaysRun = true)
	public void quitDriver() {
		driver.quit();
	}

}