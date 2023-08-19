package Testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class Testbase {
/************************  Extent Report ********************/

protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private static String PROJECT_NAME = "Attalsian Board";

    /************************  Extent Report ********************/


    public static String key = "2ad662f7bb7b15d002a47937f855f462";
    public static String token = "12b294f47feb8ef6dc3092fbc4f76fe03a22edb5447a7ca26b2218daa1166bf6";

    public static String BoardID ="";
    public static String Board_Name ="";
    public  static String Board_Updated_Name ="";
    public  static String Label_ID ="";
    public  static String Label_Name ="";
    public static String Label_color="";

    public static String updated_Label_color="";

    public static String updated_Label_name="";

    public static String List_ID="";

    public static String List_Name="";

    public static String List_closed="";

    // add before siute before (before method)

    @BeforeSuite
    public void defineSuiteElements() throws IOException {
        // initialize the HtmlReporter
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //setProjectDetails();

        // initialize test
        test = extent.createTest(PROJECT_NAME + " Test Automation Project");

        //configuration items to change the look and fee add content, manage tests etc
        htmlReporter.config().setDocumentTitle(PROJECT_NAME + " Test Automation Report");
        htmlReporter.config().setReportName(PROJECT_NAME + " Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    /***************************************************************************/
    @BeforeMethod
    public void SetUrl(){

        RestAssured.baseURI = "https://api.trello.com/1";
    }


    @AfterSuite
    public void tearDown() throws IOException {
        extent.flush();
        //start html report after test end
       // Utilities.startHtmlReport(System.getProperty("user.dir"), "testReport.html");
    }


    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getName() + " failed with the following error: " + result.getThrowable());
            Reporter.log("Failed to perform "+result.getName(), 10, true);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName());
            Reporter.log("Successfully perform "+result.getName(), 10, true);
        } else {
            test.log(Status.SKIP, result.getName());
            Reporter.log("Skip "+result.getName(), 10, true);
        }
    }

}
