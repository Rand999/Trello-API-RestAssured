import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC03_CheckUpdatingBoard extends Testbase{


    public static Response response_update;

    //valid data
    @Test(priority = 1,description = "check update board with valid data")
    public void CheckUpdateBoardWithValidData_P(){

        response_update= given().log().all().pathParam("id", "64d33f6c952926a7e8e72325").queryParam("key",key).queryParam("token",token)
                .queryParam("name","updated board")//.header("Host", "")
                .when().put("/boards/{id}").then().log().all().statusCode(200).extract().response();

    }
//check name not null

    @Test(priority = 2,description = "check name is not empty")
    public void CheckNameNotEmpty(){

        Assert.assertFalse(response_update.jsonPath().getString("name").isEmpty());
    }


    // public static name_update = response_update.jsonPath().getString("name");
}

















