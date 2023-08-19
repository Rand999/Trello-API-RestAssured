package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC01_Check_Create_Board_POST extends Testbase{


    public static Response response_create;

    // check creating board with valid data
    @Test(priority = 1, description = "Check creating board with valid data")
    public void CheckCreatingBoardWithValidData_P() {


        //given (parameters ,authentication , header , body) ---------> request data
        //when  (method with path)
        //then  (assert results)

        response_create =  given().log().all().queryParam("name", "board1").queryParam("key", key)
                .queryParam("token", token).body("")
                //.header("Host", "")
                .when().post("/boards").then().log().all().statusCode(200).extract().response();


        BoardID = response_create.jsonPath().getString("id");
        Board_Name = response_create.jsonPath().getString("name");
    }

    //invalid request path
    @Test(priority = 2 ,description = "check creating board with invalid request path")
    public  void CheckCreatingBoardWithInvalidPath(){
        given().queryParam("name","board3").queryParam("key",key)
                .queryParam("token",token).body("")
                //.header("Host","")
                .when().post("/boards/test").then().statusCode(404);
    }

    //invalid header
 /*  @Test(priority = 3 , description = "Check creating board with invalid header")
 public void CheckCreatingBoardWithInvalidHeader(){
 given().queryParam("name","board1").queryParam("key",key)
 .queryParam("token",token).body("")
 .header("Content-Type","application/xml").when()
 .post("/boards").then().statusCode(400);
 }
*/
    //invalid method
    @Test(priority = 4 ,description = "check creating board with invalid method")
    public void CheckCreatingBoardWithInvalidMethod(){
        given().queryParam("name","board1").queryParam("key",key)
                .queryParam("token",token)
                //.header("Host","").when()
                .patch("/boards").then().statusCode(404); //method not allowed  405


    }
    //Auth
/*@Test(priority = 5 ,description = "check creating board with auth")
 public void CheckCreatingBoardWithAuth(){
 given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
 .queryParam("name","board1").queryParam("key",key)
 .queryParam("token",token).body("")
 // .header("Host","").when()
 .post("/boards").then().statusCode(400); // or 405?
}
*/
    @Test(priority = 6, description = "Check name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(response_create.jsonPath().getString("name"),Board_Name);
    }


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(response_create.jsonPath().getString("name").isEmpty());
    }
}
