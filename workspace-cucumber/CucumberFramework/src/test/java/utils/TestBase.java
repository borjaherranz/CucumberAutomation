package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	// Attributes
	public WebDriver driver;
	
	public WebDriver webDriverManager() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		
		/** We convert the properties in a FileInputStream before sending to the properties */
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"//src//test//resources//global.properties"
				);
		
		Properties prop = new Properties();
		prop.load(fis);
		
		/** Get the properties from the file */
		String browser = (String) prop.get("browser");
		String url = prop.getProperty("QAUrl");
		
		/** In case we inject the browser by the commands line */
		String browser_maven = System.getProperty("browser");
		
		String result = browser_maven!=null? browser_maven : browser;
		
		if (driver == null) {
			if(result.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromeDriver().setup();
								
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
			} else if(result.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
			}
			implicitWait();
			driver.get(url);
		}

		return driver;
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

}
