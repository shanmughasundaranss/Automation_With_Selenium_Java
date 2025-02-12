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

public class Excel_Utilities {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    public static String Sheet_Name = "Test_case";
    public static List<String> Get_Testcases_From_Excel = new ArrayList<String>();
    public static List<String> Get_Testcases_From_ActionKeyword = new ArrayList<String>();
    public static  String Trimmed_Method_Name;


public static void setExcelFile(String path) throws IOException {

        FileInputStream fs = new FileInputStream(path);
        workbook = new XSSFWorkbook(fs);

    }

public static void Excel_Testcases() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String Get_Driver_File_Path = Reusable_Library.Get_Value_From_Property_File("Exection_Sheet_Location");
        Excel_Utilities.setExcelFile(Get_Driver_File_Path);
        sheet = workbook.getSheet(Sheet_Name);
        int row = sheet.getLastRowNum();
        System.out.println("Step 2 :Total Count Of the Values in the ACTION KEYWORD Row : " + row);
        Get_Testcases_From_Excel.clear();
        ActionKeyword_Test_Cases();
        for (int a = 1; a <= row; a++) {
            String Row_Cell = sheet.getRow(a).getCell(8) != null
                    ? sheet.getRow(a).getCell(8).toString()
                    : "Empty";
            System.out.println("Excel Data Before added to list: " + Row_Cell);

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
                System.out.println("Method Name Before Adding to List: " + Trim_Class_Name);
                Get_Testcases_From_ActionKeyword.add(Trim_Class_Name);
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    public static void Testcase_Validator() {

        Method[] methods = Action_Keywords.class.getMethods();
        for (Method m : methods) {
            System.out.println("Available method: " + m.getName());

        }
        if (Get_Testcases_From_Excel != null && Get_Testcases_From_ActionKeyword != null) {
            for (String value : Get_Testcases_From_Excel) {
               System.out.println( "Replaced:"+ value);
               // Trim any extra spaces

                // Check if the method exists in Action_Keywords class
                if (Get_Testcases_From_ActionKeyword.contains(value)) {
                     Trimmed_Method_Name  = value.trim().replace("()", "");

                    System.out.println("Value " + Trimmed_Method_Name + " from Column_Values1 is present in aList.");

                    try {
                        // Use reflection to check for the method
                        Method method = Action_Keywords.class.getMethod(Trimmed_Method_Name); // Get the method by name
                        System.out.println("Check: " + method);

                        // Call the method via reflection (assuming it's static)
                        method.invoke(null); // 'null' because it's a static method

                    } catch (NoSuchMethodException e) {
                        System.err.println("No such method: " + Trimmed_Method_Name + " in Action_Keywords.");
                    } catch (IllegalAccessException e) {
                        System.err.println("Illegal access to method: " + Trimmed_Method_Name + " in Action_Keywords.");
                    } catch (InvocationTargetException e) {
                        System.err.println("Error invoking method " + Trimmed_Method_Name + ": " + e.getCause());
                    }
                } else {
                    System.out.println("Value " + Trimmed_Method_Name + " from Column_Values1 is NOT present in aList.");
                }
            }
        }
    }
}
