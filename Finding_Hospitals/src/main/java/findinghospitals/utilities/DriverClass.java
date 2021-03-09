package findinghospitals.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;
public class DriverClass {
	
		public static WebDriver driver = null;
		public static WebDriverWait wait = null;

		// Loading the driver and Loading the URL in the driver
	
		public static WebDriver setupDriver(String browser) {
			String path = System.getProperty("user.dir");
			try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", path + "\\Resources\\Drivers\\chromedriver.exe"); // Setting
																												// the path
																												// for the
																							// browser
				driver = new ChromeDriver(); // Opening the Chrome Browser

			} else if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", path + "\\Resources\\Drivers\\geckodriver.exe"); // Setting the
																												// path for
																												// the
																												// browser
				driver = new FirefoxDriver(); // Opening the Firefox Browser
				

			} else if (browser.equalsIgnoreCase("edge")) {
				
				System.setProperty("webdriver.edge.driver",path + "\\Resources\\Drivers\\msedgedriver.exe");
				driver = new EdgeDriver(); // Opening the Edge Browser
				
			}
				
				else if(browser.equalsIgnoreCase("chromegrid")) {
					String Node = "http://localhost:4444/wd/hub";
					String URL="https://www.facebook.com";
					DesiredCapabilities cap = DesiredCapabilities.chrome();

					try {
						driver = new RemoteWebDriver(new URL(Node), cap);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			 else {
				System.out.println("Wrong Browser");
				
			}

			driver.manage().window().maximize(); // Maximizing the browser window
					// Loading the URL to the browser
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // Adding an implicit wait to the driver
																				// instance
			wait = new WebDriverWait(driver, 60); 
			driver.manage().deleteAllCookies();// Initializing an Explicit wait
			
		
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return driver;
		}
		
	

	

}
