package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC12_CheckGitLists extends Testbase{

    Response get_list;


    @Test(priority = 1, description = "Check get list with valid data")
    public void Check_Get_ListWithValidData_P() {



        get_list = (Response) given().log().all().queryParam("key", key)
                .queryParam("token", token)

                .when().get("/lists/"+List_ID).then().log().all().statusCode(200).extract().response();



        List_Name = get_list.jsonPath().getString("name");
    }

    //invalid request path
    @Test(priority = 2 ,description = "check get list with invalid request path")
    public  void CheckGetListWithInvalidPath(){
        given().queryParam("key",key)
                .queryParam("token",token)

                .when().get("/lists/"+List_ID+"/test").then().statusCode(404);
    }

    //invalid header
  /*  @Test(priority = 3 , description = "Check get list with invalid header")
    public void CheckGetListWithInvalidHeader(){
        given().queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type","application/xml").when()
                .get("/lists/"+List_ID).then().statusCode(400);
    }*/

    //invalid method
    @Test(priority = 4 ,description = "check get list with invalid method")
    public void CheckCreatingListWithInvalidMethod(){
        given().queryParam("key",key)
                .queryParam("token",token)
                //.header("Host","").when()
                .patch("/lists/"+List_ID).then().statusCode(404); //method not allowed  405


    }
    //Auth
    /*@Test(priority = 5 ,description = "check get list with auth")
    public void CheckGetListWithAuth(){
        given().auth().basic("user","test test").auth().basic("usertest","test test").queryParam("tset","475")
                .queryParam("key",key)
                .queryParam("token",token)

                .get("/lists/"+List_ID).then().statusCode(400); // or 405?
    }*/

    @Test(priority = 6, description = "Check list name value")
    public void checkNameAsExpected_P(){

        Assert.assertEquals(get_list.jsonPath().getString("name"),List_Name);
    }


    @Test(priority = 6, description = "Check name not empty")
    public void checkNameNotEmpty_P(){

        Assert.assertFalse(get_list.jsonPath().getString("name").isEmpty());
    }
}
