package ReUsable_Codes.Manage_Work_Space;


import static ReUsable_Codes.Reusable_Library.*;

public class Shot {

    public static void Fetch_Show() {

        Click_Element(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Manage_Work"));

        Explicit_Wait(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_Show_DropDown"));

        Click_Element(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "FFetch_Show_DropDown"));
        Explicit_Wait(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_My_Show_Toggel"));

        Click_Element(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_My_Show_Toggel"));
        Explicit_Wait(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_Show_Search_Bar"));

        Click_Element(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_Show_Search_Bar"));

        SendKeys(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Fetch_Show_Search_Bar"),Json_Extractor_Data("Manage_Work_Shot","Show","Show_name1"));

        Explicit_Wait(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Select_Show_Dropdown"));

        Click_Element(Json_Extractor_Elements("Manage_Work_Shot", "Shot", "Select_Show_Dropdown"));


    }
}




