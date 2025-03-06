package Testcase;

import ReUsable_Codes.Login;
import java.io.IOException;
import static Browser_Factory.Browser_Drivers.Initailize_Driver;
import static Browser_Factory.Browser_Drivers.driver;

public class S2_Login {

public static void Login_With_Valid_Credentials() throws IOException {
    Initailize_Driver();
    Login.Login_With_Valid_User_Name_And_Password();
    driver.quit();
}
}

