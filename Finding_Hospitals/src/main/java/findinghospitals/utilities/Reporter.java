package findinghospitals.utilities;

import java.io.IOException;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.template.utility.DateUtil;

public class Reporter {
	
	
	public static ExtentReports report;
	
	public static ExtentReports getReport(){

		Date date = new Date();
        String currentDate = date.toString().replaceAll(":", "_").replaceAll(" ", "_");
			
        if(report==null) {
			String reportName = currentDate+ ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReports\\" + reportName);
			report =  new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "1.0");
			
			htmlReporter.config().setDocumentTitle("Practo Automation Results");
			htmlReporter.config().setReportName("Functional Testing");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.STANDARD);

	}
		return report;
		
	}
}
