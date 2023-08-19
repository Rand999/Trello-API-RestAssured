package Testcases;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC10_DeleteAlabel extends Testbase{

    @Test(priority = 1,description = "check delete board with valid data")
    public void CheckDeleteBoardWithValidData_P(){

        given().log().all()

                .queryParam("key",key).queryParam("token",token)
                //.header("Host", "")
                .when().delete("/labels/"+Label_ID).then().log().all().statusCode(200);

    }
}
