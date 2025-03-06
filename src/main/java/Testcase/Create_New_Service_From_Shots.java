package Testcase;

import ReUsable_Codes.Login;
import ReUsable_Codes.Manage_Work_Space.Shot;
import org.testng.Reporter;
import java.io.IOException;
import static Browser_Factory.Browser_Drivers.Initailize_Driver;
import static Browser_Factory.Browser_Drivers.driver;


public class Create_New_Service_From_Shots {

public static void Create_New_Shot_From_Manage_Work_Shot_Screen() throws IOException {
    Initailize_Driver();
    Login.Login_With_Valid_User_Name_And_Password();
    Reporter.log("Quitting the browser", true);
    Shot.Fetch_Show();
    driver.quit();



}





}
