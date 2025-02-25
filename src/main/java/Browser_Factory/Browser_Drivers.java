package Browser_Factory;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import static ReUsable_Codes.Reusable_Library.Get_Value_From_Property_File;




public class Browser_Drivers {
    public static WebDriver driver;

public static void Choose_Browser(String Run_Browser) throws IOException {
        String browser = Run_Browser; // Change this to "firefox" to launch Firefox

        driver = null;


        if (browser.equalsIgnoreCase("chrome")) {
            // Set up WebDriver for Chrome
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito");
            String Headless_mode = Get_Value_From_Property_File("HeadLess_Mode");
            System.out.println("Headless_mode: " + Headless_mode);

            if (Headless_mode.equals("Yes") == true) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu"); // Optional: Disables GPU acceleration for headless mode
                options.addArguments("--window-size=1920x1080"); // Optional: To avoid errors related to screen size in headless mode
            } else {
                System.out.println("Not in headless mode");
            }
            driver = new ChromeDriver(options);


        } else if (browser.equalsIgnoreCase("firefox")) {
            // Set up WebDriver for Firefox
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (Get_Value_From_Property_File("HeadLess_Mode").equals("Yes") == true) {
                options.addArguments("--headless");
            }

            driver = new FirefoxDriver(options);
        } else {
            System.out.println("Unsupported browser: " + browser);
            return;
        }

        // Now your browser is ready in headless mode if configured.
    }

    // Method to check if the application should run in headless mode
    private static boolean isHeadlessMode() {
        // You can modify this method to check a property or environment variable
        // For now, it returns true as an example to always run in headless mode
        return true; // Change this to conditionally enable/disable headless mode
    }

public static void  Initailize_Driver() throws IOException {
    Browser_Drivers.Choose_Browser(Get_Value_From_Property_File("Browser"));
    Reporter.log("Browser Initailized", false);
    driver.get(Get_Value_From_Property_File("URL"));
    Reporter.log("Extracted Application URL from Property File", true);
    driver.manage().window().maximize();

}

            }






