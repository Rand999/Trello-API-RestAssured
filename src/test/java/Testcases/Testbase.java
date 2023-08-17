package Testcases;

import groovy.lang.GString;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Testbase {

    public static String key = "2ad662f7bb7b15d002a47937f855f462";
    public static String token = "12b294f47feb8ef6dc3092fbc4f76fe03a22edb5447a7ca26b2218daa1166bf6";

    public static String BoardID ="";
    public static String Board_Name ="";
    public  static String  Board_Updated_Name ="";
    public  static String  Label_ID ="";
    public  static String  Label_Name ="";
    public static String Label_color="";

    public static  String updated_Label_color="";

    public static  String updated_Label_name="";

    public static  String List_ID="";

    public static  String List_Name="";

    public static String List_closed="";
    @BeforeMethod
    public void SetUrl(){

        RestAssured.baseURI = "https://api.trello.com/1";
    }
}
