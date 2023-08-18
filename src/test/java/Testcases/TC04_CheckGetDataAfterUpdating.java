package Testcases;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static Testcases.TC03_CheckUpdatingBoard.response_update;
import static io.restassured.RestAssured.given;




public class TC04_CheckGetDataAfterUpdating extends Testbase {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Response response_G_update ;

    //check the name backs with the value we updated
@Test(priority = 1 , description="check value backs in Get the same of updated value")
    public void CheckGeyDataAfterUpdate_P(){
     response_G_update = given().log().all()

                .queryParam("key",key)
                .queryParam("token",token)
                .when().get("/boards/"+BoardID).then().log().all().statusCode(200).extract().response();

    }

    //Assert the name feild value some of updated value
    @Test(priority = 2,description = "check the value retrived in get the same of the updated value")
    public void CheckUpdatedValuename(){

    Assert.assertEquals(Board_Updated_Name,response_G_update.jsonPath().getString("name"));
    }
}
