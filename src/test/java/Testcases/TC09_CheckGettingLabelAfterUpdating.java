package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC09_CheckGettingLabelAfterUpdating extends Testbase{

    Response get_label_after_update;

    @Test(priority = 1, description = "Check get label after update with valid data")
    public void CheckGetLabelWithValidData_P() {



        get_label_after_update = given().log().all().queryParam("key", key)
                .queryParam("token", token)
                //.header("Host", "")
                .when().get("/labels/"+Label_ID).then().log().all().statusCode(200).extract().response();

       // Label_Name=get_label_after_update.jsonPath().getString("name");


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //invalid request path
    @Test(priority = 2 ,description = "check getting label with invalid request path")
    public  void CheckGetLabelWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).body("")
                //.header("Host","")
                .when().get("/boards/label/test").then().statusCode(404);
    }

    //invalid header
    @Test(priority = 3 , description = "Check Getting label with invalid header")
    public void CheckGetLabelWithInvalidHeader(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")
                .header("Content-Type","application/xml").when()
                .get("/labels/"+Label_ID).then().statusCode(400);
    }

    //invalid method
    @Test(priority = 4 ,description = "check get Label with invalid method")
    public void CheckGetLabelWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token)
                //.header("Host","").when()
                .patch("/labels/"+Label_ID).then().statusCode(404); //method not allowed  405


    }
    //Auth
    @Test(priority = 5 ,description = "check get label with auth")
    public void CheckGetLabelWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")

                .get("/labels/"+Label_ID).then().statusCode(400); // or 405?
    }

    @Test(priority = 6, description = "Check name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(get_label_after_update.jsonPath().getString("name"),updated_Label_name);
    }


    @Test(priority = 6, description = "Check color value")
    public void checkColorAsExpected_P(){

        Assert.assertEquals(get_label_after_update.jsonPath().getString("color"),updated_Label_color);
    }


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(get_label_after_update.jsonPath().getString("name").isEmpty());
    }

}
