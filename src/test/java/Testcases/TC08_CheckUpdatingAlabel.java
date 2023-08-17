package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC08_CheckUpdatingAlabel extends Testbase{

    Response update_label_response;

    @Test(priority = 1, description = "Check update label with valid data")
    public void CheckUpdateLabelWithValidData_P() {



        update_label_response = given().log().all().queryParam("key", key)
                .queryParam("token", token).queryParam("name","updated label name")
                .queryParam("color","sky")
                .when().put("/labels/"+Label_ID).then().log().all().statusCode(200).extract().response();

        updated_Label_name =update_label_response.jsonPath().getString("name");
        updated_Label_color =update_label_response.jsonPath().getString("color");


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //invalid request path
    @Test(priority = 2 ,description = "check update with invalid request path")
    public  void CheckUpdateLabelWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).queryParam("name","updated label name")
                .queryParam("color","sky")
                //.header("Host","")
                .when().put("/labels"+Label_ID+"test").then().statusCode(404);
    }

    //invalid header
  /*  @Test(priority = 3 , description = "Check update label with invalid header")
    public void CheckUpdateLabelWithInvalidHeader(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("").queryParam("name","updated label name")
                .queryParam("color","sky")
                .header("Content-Type","application/xml").when()
                .put("/labels"+Label_ID).then().statusCode(400);
    }/*

    //invalid method
    @Test(priority = 4 ,description = "check update Label with invalid method")
    public void CheckUpdateLabelWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).queryParam("name","updated label name")
                .queryParam("color","sky")
                //.header("Host","").when()
                .patch("/labels"+Label_ID).then().statusCode(404); //method not allowed  405


    }
    //Auth
    @Test(priority = 5 ,description = "check update label with auth")
    public void CheckUpdateLabelWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).queryParam("name","updated label name")
                .queryParam("color","sky")
                // .header("Host","").when()
                .put("/labels"+Label_ID).then().statusCode(400); // or 405?
    }

   /* @Test(priority = 6, description = "Check name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(update_label_response.jsonPath().getString("name"),updated_Label_name);
    }

    @Test(priority = 6, description = "Check color value")
    public void checkColorAsExpected_P(){

        Assert.assertEquals(update_label_response.jsonPath().getString("color"),updated_Label_color);
    }
*/


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(update_label_response.jsonPath().getString("name").isEmpty());
    }




}
