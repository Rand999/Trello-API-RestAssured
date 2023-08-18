package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC11_ChecCreatingList extends Testbase{

    Response list_create;


    @Test(priority = 1, description = "Check creating list with valid data")
    public void Check_creat_ListWithValidData_P() {



        list_create = given().log().all().queryParam("name", "listone").queryParam("key", key)
                .queryParam("token", token).queryParam("idBoard",BoardID).body("")
                //.header("Host", "")
                .when().post("/lists").then().log().all().statusCode(200).extract().response();


        List_ID = list_create.jsonPath().getString("id");
        List_Name = list_create.jsonPath().getString("name");
    }

    //invalid request path
    @Test(priority = 2 ,description = "check creating list with invalid request path")
    public  void Check_create_ListWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).queryParam("idBoard",BoardID).body("")
                //.header("Host","")
                .when().post("/lists/test").then().statusCode(404);
    }

    //invalid header
    /*@Test(priority = 3 , description = "Check update list with invalid header")
    public void CheckCreatingListWithInvalidHeader(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).queryParam("idBoard",BoardID).body("")
                .header("Content-Type","application/xml").when()
                .post("/lists").then().statusCode(400);
    }*/

    //invalid method
    @Test(priority = 4 ,description = "check creating list with invalid method")
    public void Check_Create_ListWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).queryParam("idBoard",BoardID)
                //.header("Host","").when()
                .patch("/lists").then().statusCode(404); //method not allowed  405


    }
    //Auth
    /*@Test(priority = 5 ,description = "check creating list with auth")
    public void CheckUpdateListWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).queryParam("idBoard",BoardID).body("")
                // .header("Host","").when()
                .post("/lists").then().statusCode(400); // or 405?
    }*/

    @Test(priority = 6, description = "Check list name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(list_create.jsonPath().getString("name"),List_Name);
    }


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(list_create.jsonPath().getString("name").isEmpty());
    }
}
