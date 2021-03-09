package findinghospitals.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	// Using the properties file to store the key value pairs
	public static Properties readProperties() throws IOException {
		Properties prop = null; // Creating new properties file
		try {
			prop = new Properties();
			InputStream ReadFile = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\findinghospitals\\testobjects\\config.properties"); // Reading the config
																								// properties file

			prop.load(ReadFile); // Loading the file to the properties
		} catch (Exception e) {

			System.out.println("Error reading properties file: " + e.getMessage());
		}

		return prop; // Returning the properties object

	}

}
