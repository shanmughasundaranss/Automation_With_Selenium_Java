package Execution_Engine;


import Utilities.Excel_Utilities;
import org.testng.Assert;
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
    private static final String LOG_FILE = "test-output/testng-logs.txt";



@BeforeSuite
public static void BeforeSuite() throws IOException {
    Reporter.log("BeforeSuite", true);
    System.out.println("BeforeSuite - Test Execution Started");


}
@BeforeTest
public static void BeforeTest() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    System.out.println("BeforeTest - Test Setup");
    Reporter.log("BeforeTest", true);
    Excel_Testcases();




    }
@BeforeClass
public static void BeforeClass(){

    Reporter.log("BeforeClass", true);

    }

@BeforeMethod
public static void BeforeMethod()  {
        Reporter.log("BeforeMethod", true);
        System.out.println("BeforeMethod - Test Execution Started");
    }




@Test(priority = 2)
public static void Initalization() {
    try {

    Testcase_Validator();
    System.out.println("Test Case Executed 2: Initalization");
    Reporter.log("Test Case Executed1", true);
    } catch (Exception e) {
        System.err.println("Test failed: " + e.getMessage());
        Assert.fail("Test Case Failed: " + e.getMessage()); // This marks test as failed
    }

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

