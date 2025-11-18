package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;



public class BaseClass {
    public static Properties prop;
    public static WebDriver driver;
    public static ExtentReports extent;

    public BaseClass() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Browser setup before every test
    @BeforeMethod
    public void setUp() {
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");

        // Launch browser based on config value
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid browser name in config file: " + browserName);
        }

        // Browser settings
        driver.manage().window().maximize();
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/CustomReport.html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("My Project Tests");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Viji");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(prop.getProperty("implicitWait"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Integer.parseInt(prop.getProperty("pageLoadTimeout"))));

        // Open URL
        driver.get(url);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
