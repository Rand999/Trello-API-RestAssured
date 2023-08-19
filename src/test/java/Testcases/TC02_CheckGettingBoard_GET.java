package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC02_CheckGettingBoard_GET  extends  Testbase{


    Response response_get;

    @Test(priority = 1, description = "check getting board by ID")
    public void CheckGetBoardWithValidData() {
        response_get = given().param("key", key).param("token", token)
                //.pathParam("id", "64d33f6c952926a7e8e72325")
                //.header("Host", "")
                .when().get("/boards/"+BoardID).then().statusCode(200).extract().response();

    }

    @Test(priority = 2, description = "check the name feild isn't empty")
    public void CheckNameIsnottEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("name").isEmpty());

    }

    @Test(priority = 3, description = "check name value as expected")
    public void CheckNameAsExpected() {

        Assert.assertEquals(Board_Name, response_get.jsonPath().getString("name"));
    }

//name does not contain spichal character.


 /*@Test(priority = 4 , description = "check name doesn't contains a spechal char")
 public void CheckNameNotHaveSpechalChar(){
 assertThat(response_get.jsonPath().getString("name"), Matchers.not(Matchers.containsString("[\\W]")));
 }

 private void assertThat(String responseGet, Matcher<String> not) {
 }*/


    // check values not null and as expected

 /* @Test(priority = 4 , description = "check name doesn't contains a spechal char")
 public void CheckPinnedValue(){
 assertThat(String.valueOf(response_get.jsonPath().getBoolean("false")), "false");
 }*/

    @Test(priority = 4, description = "check permissionLevel as expected")
    public void CheckPermissionLevelValue() {
        Assert.assertEquals(response_get.jsonPath().getString("prefs.permissionLevel"), "private");
    }

    @Test(priority = 4, description = "check voting not empty")
    public void CheckpermissionLevelnotEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("prefs.permissionLevel").isEmpty());
    }

    ////////////////////////////////////////////////////////
    @Test(priority = 5, description = "check voting value as expected")
    public void CheckVotingValue() {
        Assert.assertEquals(response_get.jsonPath().getString("prefs.voting"), "disabled");
    }

    @Test(priority = 4, description = "check voting not empty")
    public void CheckvotingsnotEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("prefs.voting").isEmpty());
    }


    ////////////////////////////////////////////////////////////////////////////////////
    @Test(priority = 6, description = "check comments value as expected")
    public void CheckcommentsValue() {
        Assert.assertEquals(response_get.jsonPath().getString("prefs.comments"), "members");
    }

    @Test(priority = 4, description = "check comments not empty")
    public void CheckcommentsnotEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("prefs.comments").isEmpty());
    }


    ////////////////////////////////////////////////////
    @Test(priority = 7, description = "check invitations value as expected")
    public void CheckinvitationsValue() {
        Assert.assertEquals(response_get.jsonPath().getString("prefs.invitations"), "members");
    }

    @Test(priority = 4, description = "check invitations not empty")
    public void CheckprefsinvitationsnotEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("prefs.invitations").isEmpty());
    }


    /////////////////////////////////////////
    @Test(priority = 8, description = "check hideVotes value as expected")
    public void CheckhideVotesValue() {
        Assert.assertEquals(response_get.jsonPath().getBoolean("prefs.hideVotes"), false);
    }

    @Test(priority = 4, description = "check hideVotes not empty")
    public void CheckinvitationsnotEmpty() {
        Assert.assertFalse(response_get.jsonPath().getString("prefs.hideVotes").isEmpty());
    }


 /*  @Test(priority = 4, description = "check hideVotes not empty")
 public void Checkswitch() {

 // Check that the switcherViews array is not empty
 // JsonPath jsonPath = response_get.jsonPath();
 /* int switcherViewsLength = response_get.jsonPath().getInt("prefs.switcherViews.length");
 if (switcherViewsLength == 0) {
 throw new AssertionError("The switcherViews array is empty.");
 }

 // Check that the values in the switcherViews array are not null
 for (int i = 0; i < switcherViewsLength; i++) {
 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[\" + i + \"]").isEmpty());

 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[\" + i + \"].viewType").isEmpty());

 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[\" + i + \"].enabled").isEmpty());


 // Check if the switcherViews array exists
 // Check if the switcherViews array is not empty
 //  if (response_get.jsonPath().valueExists("prefs.switcherViews")) {
 int switcherViewsLength = response_get.jsonPath().getInt("prefs.switcherViews.length");
 if (switcherViewsLength == 0) {
 throw new AssertionError("The switcherViews array is empty.");
 }

 // Check that the values in the switcherViews array are not null
 for (int i = 0; i < switcherViewsLength; i++) {
 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[" + i + "]").isEmpty());

 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[" + i + "].viewType").isEmpty());

 Assert.assertFalse(response_get.jsonPath().getString("prefs.switcherViews[" + i + "].enabled").isEmpty());

 }
 } else {
 // The switcherViews array does not exist
 }


 }*/
}
