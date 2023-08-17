package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC13_CheckUpdateAlist extends Testbase{

    Response update_list;


    @Test(priority = 1, description = "Check get list with valid data")
    public void Check_Get_ListWithValidData_P() {



        update_list = (Response) given().log().all().queryParam("key", key)
                .queryParam("token", token).queryParam("name","updated list")
                .queryParam("closed","true")

                .when().put("/lists/"+List_ID).then().log().all().statusCode(200).extract().response();



        List_Name = update_list.jsonPath().getString("name");
        List_closed=update_list.jsonPath().getString("closed");
    }

    //invalid request path
    @Test(priority = 2 ,description = "check get list with invalid request path")
    public  void CheckGetListWithInvalidPath(){
        given().queryParam("key",key)
                .queryParam("token",token).queryParam("name","updated list")
                .queryParam("closed","true")

                .when().put("/lists/"+List_ID+"/test").then().statusCode(404);
    }

    //invalid header
  /*  @Test(priority = 3 , description = "Check get list with invalid header")
    public void CheckGetListWithInvalidHeader(){
        given().queryParam("key",key)
                .queryParam("token",token)
                . queryParam("name","updated list")
                .queryParam("closed","true")
                .header("Content-Type","application/xml").when()
                .put("/lists/"+List_ID).then().statusCode(400);
    }*/

    //invalid method
    @Test(priority = 4 ,description = "check get list with invalid method")
    public void CheckCreatingListWithInvalidMethod(){
        given().queryParam("key",key)
                .queryParam("token",token)
                .queryParam("name","updated list")
                .queryParam("closed","true")
                //.header("Host","").when()
                .patch("/lists/"+List_ID).then().statusCode(404); //method not allowed  405


    }
   /* //Auth
    @Test(priority = 5 ,description = "check get list with auth")
    public void CheckGetListWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("key",key)
                .queryParam("token",token)
                .queryParam("name","updated list")
                .queryParam("closed","true")

                .put("/lists/"+List_ID).then().statusCode(400); // or 405?
    }*/

    @Test(priority = 6, description = "Check list name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(update_list.jsonPath().getString("name"),List_Name);
    }
    @Test(priority = 6, description = "Check list name value")
    public void checkClosedAsExpected_P(){

        Assert.assertEquals(update_list.jsonPath().getString("name"),List_closed);
    }

    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(update_list.jsonPath().getString("name").isEmpty());
    }
}
