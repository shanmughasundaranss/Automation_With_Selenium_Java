package ReUsable_Codes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static Browser_Factory.Browser_Drivers.driver;

public class Reusable_Library {
    public static Properties properties;
    public static FileInputStream FileInputStream;
    public static String propertyValue;


public static String Get_Value_From_Property_File(String Value) throws IOException {
        properties = new Properties();

        try {
            FileInputStream = new FileInputStream("C:\\Users\\Shanmugasundharam\\Git\\S2_Application\\Selenium\\src\\main\\resources\\Test_Data\\Framework.properties");
            properties.load(FileInputStream);
            propertyValue = properties.getProperty(Value);
            System.out.println("Value " + propertyValue);


        } finally {

        }
        return propertyValue;
    }

public static String Json_Extractor_Elements (String Path, String Body, String Value){
        ObjectMapper objectMapper = new ObjectMapper();
        String extractedValue = "";

        try {
            JsonNode rootNode = objectMapper.readTree(new File("C:\\Users\\Shanmugasundharam\\Git\\S2_Application\\Selenium\\src\\main\\resources\\Test_Data\\Web_Elements\\" + Path + ".json"));
            JsonNode usersNode = rootNode.path(Body);

            for (JsonNode userNode : usersNode) {
                extractedValue = userNode.path(Value).asText();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractedValue;
    }

 public static String Json_Extractor_Data (String Path, String Body, String Value){
        ObjectMapper objectMapper = new ObjectMapper();
        String extractedValue = "";

        try {
            JsonNode rootNode = objectMapper.readTree(new File("C:\\Users\\Shanmugasundharam\\Git\\S2_Application\\Selenium\\src\\main\\resources\\Test_Data\\Data\\" + Path + ".json"));
            JsonNode usersNode = rootNode.path(Body);

            for (JsonNode userNode : usersNode) {
                extractedValue = userNode.path(Value).asText();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractedValue;
    }

    public static void FindElementAndSentText(String Locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
    WebElement Element =  driver.findElement(By.xpath(Locator));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));
    Element.clear();
    Element.click();
    Element.sendKeys("shanmugasundharam");

}
    public static void SendKeys(String Locator,String Data) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
        WebElement Element =  driver.findElement(By.xpath(Locator));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));
        Element.clear();
        Element.click();
        Element.sendKeys(Data);

    }
public static void Click_Element(String Locator) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
    WebElement Element =  driver.findElement(By.xpath(Locator));
    Element.click();
}

public static void Explicit_Wait(String Locator) {
    Wait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
    wait.until((ExpectedConditions.elementToBeClickable(By.xpath(Locator))));

}

    }


