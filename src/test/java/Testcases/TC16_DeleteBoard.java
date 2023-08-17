package Testcases;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC16_DeleteBoard extends Testbase{

    @Test(priority = 1,description = "check delete board with valid data")
    public void CheckDeleteBoardWithValidData_P(){

        given().log().all()
                //.pathParam("id", "64d33f6c952926a7e8e72325")
                .queryParam("key",key).queryParam("token",token)
                //.header("Host", "")
                .when().delete("/boards/"+BoardID).then().log().all().statusCode(200);

    }

}
