package Execution_Engine;

import Listners.RetryAnalyzer;
import Utilities.Excel_Utilities;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import static Config.Action_Keywords.TestSuite1;
import static Config.Action_Keywords.TestSuite2;

public class TestNG_Run extends Excel_Utilities {

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


    }
@BeforeClass
public static void BeforeClass(){

        Reporter.log("BeforeClass", true);

    }

@BeforeMethod
public static void BeforeMethod(){
        Reporter.log("BeforeMethod", true);

    }
@Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
public static void TestCase1() {
        TestSuite1();
        Reporter.log("Test Case Executed 1", true);

    }

@Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
public static void TestCase2() {
        TestSuite2();
        Reporter.log("Test Case Executed 2", true);

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

