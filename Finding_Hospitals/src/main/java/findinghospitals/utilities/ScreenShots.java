package findinghospitals.utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

public class ScreenShots {
	public static void takeScreenshotOnFailure(WebDriver driver)  {
		
		
		try {
		//CAPTURING THE SCREENSHOT OF THE VISIBLE BROWSER SCREEN
		BufferedImage image= new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); 	
		
		//WRITING THE SCREENSHOT FILES WITH A .jpg EXTENSION
		ImageIO.write(image, "jpg", new File(System.getProperty("user.dir")+"\\ScreenShotsOnFailure\\"+System.currentTimeMillis()+".jpg"));
		}
		catch(Exception e) {
			System.out.println("Screenshot Capturing Failed: "+e.getMessage());
		}
		
	} 
	
	public static void takeScreenshot(WebDriver driver) {
		try {
			//CAPTURING THE SCREENSHOT OF THE VISIBLE BROWSER SCREEN
			BufferedImage image2= new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); 	
			
			//WRITING THE SCREENSHOT FILES WITH A .jpg EXTENSION
			ImageIO.write(image2, "jpg", new File(System.getProperty("user.dir")+"\\ScreenShots\\"+System.currentTimeMillis()+".jpg"));
			}
			catch(Exception e) {
				System.out.println("Screenshot Capturing Failed: "+e.getMessage());
			}
		
	}
}
