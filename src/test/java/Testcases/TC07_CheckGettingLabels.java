package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC07_CheckGettingLabels extends Testbase{
Response get_all_label_response;
    @Test(priority = 1, description = "Check get labels with valid data")
    public void CheckGetLabelesWithValidData_P() {



        get_all_label_response = given().log().all().queryParam("key", key)
                .queryParam("token", token)
                //.header("Host", "")
                .when().get("/boards/"+BoardID+"/labels").then().log().all().statusCode(200).extract().response();

                Label_Name=get_all_label_response.jsonPath().getString("name");


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //invalid request path
    @Test(priority = 2 ,description = "check getting labeles with invalid request path")
    public  void CheckGetLabelesWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).body("")

                .when().get("/boards/label/test").then().statusCode(404);
    }

    //invalid header
    /*@Test(priority = 3 , description = "Check Getting labeles with invalid header")
    public void CheckGetLabelesWithInvalidHeader(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")
                .header("Content-Type","application/xml").when()
                .get("/boards/"+BoardID+"/labels").then().statusCode(400);
    }*/

    //invalid method
    @Test(priority = 4 ,description = "check get Labeles with invalid method")
    public void CheckGetLabelesWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token)
                //.header("Host","").when()
                .patch("/boards/"+BoardID+"/labels").then().statusCode(404); //method not allowed  405


    }
    //Auth
   /* @Test(priority = 5 ,description = "check get labeles with auth")
    public void CheckGetLabelesWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token).body("")
                // .header("Host","").when()
                .get("/boards/"+BoardID+"/labels").then().statusCode(400); // or 405?
    }
*/
    @Test(priority = 6, description = "Check name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(get_all_label_response.jsonPath().getString("name"),Label_Name);
    }


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(get_all_label_response.jsonPath().getString("name").isEmpty());
    }







}
