import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class Testbase {

    public static String key = "2ad662f7bb7b15d002a47937f855f462";
    public static String token = "12b294f47feb8ef6dc3092fbc4f76fe03a22edb5447a7ca26b2218daa1166bf6";
    public static String BoardID="";
    @BeforeMethod
    public void SetUrl(){

        RestAssured.baseURI = "https://api.trello.com/1";
    }
}
