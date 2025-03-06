package Utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import Config.Action_Keywords;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ReUsable_Codes.Reusable_Library;
import org.testng.Assert;
import org.testng.Reporter;

public class Excel_Utilities {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    public static String Sheet_Name = "Test_case";
    public static List<String> Get_Testcases_From_Excel = new ArrayList<String>();
    public static List<String> Get_Testcases_From_ActionKeyword = new ArrayList<String>();
    public static String Trimmed_Excel_Testcases_Name;
    public static String Trimmed_Action_Keyword_Method_Name;


    public static void setExcelFile(String path) throws IOException {

        FileInputStream fs = new FileInputStream(path);
        workbook = new XSSFWorkbook(fs);

    }

    public static void Excel_Testcases() throws IOException {
        String Get_Driver_File_Path = Reusable_Library.Get_Value_From_Property_File("Exection_Sheet_Location");
        Excel_Utilities.setExcelFile(Get_Driver_File_Path);
        sheet = workbook.getSheet(Sheet_Name);
        int row = sheet.getLastRowNum();
        System.out.println("Total Count Of the Values in the ACTION KEYWORD Row : " + row);
        Get_Testcases_From_Excel.clear();
        ActionKeyword_Test_Cases();
        for (int a = 1; a <= row; a++) {
            String Row_Cell = sheet.getRow(a).getCell(8) != null
                    ? sheet.getRow(a).getCell(8).toString()
                    : "Empty";
            //System.out.println("Excel Data Before added to list: " + Row_Cell);

            Get_Testcases_From_Excel.add(Row_Cell);
        }
    }

    public static void ActionKeyword_Test_Cases() {
        try {
            Class<?> clazz = Action_Keywords.class;
            Method[] methods = clazz.getDeclaredMethods();
            if (Get_Testcases_From_ActionKeyword == null) {
                Get_Testcases_From_ActionKeyword = new ArrayList<String>();
            }

            for (int i = 0; i < methods.length; i++) {
                String Listofmethods = methods[i].toString();
                String Trim_Class_Name = Listofmethods.replace("public static void Config.Action_Keywords.", "").trim();
                //   System.out.println("Method Name Before Adding to List: " + Trim_Class_Name);
                Get_Testcases_From_ActionKeyword.add(Trim_Class_Name);
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    public static void Testcase_Validator() {
        boolean methodExecuted = false;

        Method[] methods = Action_Keywords.class.getMethods();
        for (String Excel_Values : Get_Testcases_From_Excel) {
            Reporter.log("Loop Started", true);
            if (Excel_Values != null) {
                String Trimmed_Excel_Testcases_Name = Excel_Values.trim().replace("()", "");
                if (Get_Testcases_From_ActionKeyword.contains(Excel_Values)) {
                    try {
                        Method method = Action_Keywords.class.getMethod(Trimmed_Excel_Testcases_Name);
                        method.invoke(null);
                        methodExecuted = true;
                    } catch (NoSuchMethodException e) {
                        Reporter.log("No such method: " + Trimmed_Excel_Testcases_Name, true);
                    } catch (Exception e) {
                        Reporter.log("Error executing method: " + Trimmed_Excel_Testcases_Name + " - " + e.getMessage(), true);
                       Assert.fail("Test case failed: " + e.getMessage()); // This marks test as failed
                    }
                } else {
                    Reporter.log("Test case not found in Action_Keywords: " + Trimmed_Excel_Testcases_Name, true);
                }
            }
        }

        if (!methodExecuted) {
            Assert.fail("No test cases were executed.");
        }
    }
}