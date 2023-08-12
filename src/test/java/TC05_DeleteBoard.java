import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC05_DeleteBoard extends  Testbase{

    @Test(priority = 1,description = "check update board with valid data")
    public void CheckUpdateBoardWithValidData_P(){

        given().log().all().pathParam("id", "64d33f6c952926a7e8e72325").queryParam("key",key).queryParam("token",token)
                //.header("Host", "")
                .when().delete("/boards/{id}").then().log().all().statusCode(200);

    }
}
