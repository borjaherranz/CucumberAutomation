package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

    public WebDriver driver;

    public WebDriver webDriverManager() throws IOException {

        System.out.println("Working dir: " + System.getProperty("user.dir"));

        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/global.properties"
        );

        Properties prop = new Properties();
        prop.load(fis);

        String browserFromFile = prop.getProperty("browser");
        String url = prop.getProperty("QAUrl");

        // Maven parameter has priority
        String browserFromMaven = System.getProperty("browser");
        String browser = browserFromMaven != null ? browserFromMaven : browserFromFile;

        if (driver == null) {

            if (browser.equalsIgnoreCase("chrome")) {

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
				
                if (System.getenv("JENKINS_HOME") != null) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                }

                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(url);
        }

        return driver;
    }
}
