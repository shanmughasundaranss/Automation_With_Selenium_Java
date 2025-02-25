package Execution_Engine;


import Utilities.Excel_Utilities;
import org.openqa.selenium.devtools.v129.network.model.CookiePriority;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class Driver_Script extends Excel_Utilities {

    public static List<String> Return_value_of_Test_case_Name;
    public static String Return_Value_of_Test_case_Execution_Type;
    public static List<String> aList;
    public static String Replaced_Value;
    public static String value;



@BeforeSuite
public static void BeforeSuite() throws IOException {
    Reporter.log("BeforeSuite", true);


}
@BeforeTest
public static void BeforeTest() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Reporter.log("BeforeTest", true);
    Excel_Testcases();




    }
@BeforeClass
public static void BeforeClass(){

    Reporter.log("BeforeClass", true);

    }

@BeforeMethod
public static void BeforeMethod(){
    Reporter.log("BeforeMethod", true);

    }

@Test(priority = 2)
public static void Initalization() {
    Testcase_Validator();
    Reporter.log("Test Case Executed1", true);

}

@AfterMethod
public static void AfterMethod(){
    Reporter.log("AfterMethod", true);

    }

@AfterClass
public static void AfterClass(){
    Reporter.log("AfterClass", true);

}
@AfterTest
public static void AfterTest(){
    Reporter.log("AfterTest", true);
    }

@AfterSuite
public static void AfterSuite(){

    Reporter.log("AfterSuite", true);

    }

}

