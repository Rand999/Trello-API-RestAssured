package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC05_CheckCreatingAlable extends Testbase{
  public static String label_name = utility.utileties.generateRandomLabelName();
    Response create_lable_response;

    @Test(priority = 1, description = "Check creating label with valid data")
    public void CheckCreatingLabelWithValidData_P() {



        create_lable_response = given().log().all().queryParam("name", label_name).queryParam("color","sky").queryParam("key", key)
                .queryParam("token", token)
                //.queryParam("color", "green")
                 .body("")
                //.header("Host", "")
                .when().post("/boards/"+BoardID+"/labels").then().log().all().statusCode(200).extract().response();

        Label_ID=create_lable_response.jsonPath().getString("id");
        Label_Name=create_lable_response.jsonPath().getString("name");
        Label_color=create_lable_response.jsonPath().getString("color");

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //invalid request path
    @Test(priority = 2 ,description = "check creating label with invalid request path")
    public  void CheckCreatingLabelWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).body("")
                //.header("Host","")
                .when().post("/boards/label/test").then().statusCode(404);
    }

    //invalid header
   /* @Test(priority = 3 , description = "Check creating label with invalid header")
    public void CheckCreatingLabelWithInvalidHeader(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")
                .header("Content-Type","application/xml").when()
                .post("/boards/"+BoardID+"/labels").then().statusCode(400);
    }
*/
    //invalid method
    @Test(priority = 4 ,description = "check creating Label with invalid method")
    public void CheckCreatingLabelWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token)
                //.header("Host","").when()
                .patch("/boards/"+BoardID+"/labels").then().statusCode(404); //method not allowed  405


    }
    //Auth
    @Test(priority = 5 ,description = "check creating label with auth")
    public void CheckCreatingLabelWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")
                // .header("Host","").when()
                .post("/boards/"+BoardID+"/labels").then().statusCode(200); // or 405?
    }

    /*@Test(priority = 6, description = "Check name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(create_lable_response.jsonPath().getString("name"),label_name);
    }*/

    @Test(priority = 6, description = "Check color value")
    public void checkColorAsExpected_P(){

        Assert.assertEquals(create_lable_response.jsonPath().getString("color"),Label_color);
    }

    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(create_lable_response.jsonPath().getString("name").isEmpty());
    }
    @Test(priority = 6, description = "Check color not empty")
    public void checkColorNotEmpty_P(){

        Assert.assertFalse(create_lable_response.jsonPath().getString("color").isEmpty());
    }

}
