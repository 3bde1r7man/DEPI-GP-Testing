package Attendance;

import BaseTest.BaseTest;
import Pages.Attendance.MyRecordsPage;
import Pages.Attendance.PunchInOutPage;
import Pages.NavBar.Navbar;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AttendanceTest extends BaseTest {
    PunchInOutPage punchInOutPage;
    MyRecordsPage myRecordsPage;
    Navbar navbar;

    // messages
    String successMessage = "Success\nSuccessfully Saved";


    @BeforeTest
    public void setupPunchInOut(){
        super.setUp();
    }

    @Owner("Abdelrhman")
    @Test(  testName = "TC-1",
            description = "This test will navigate to the punch in/out page",
            dependsOnMethods = {"Login.LoginPageTest.validLoginPageTest"}
    )
    public void navigatePunchInOut(){
        punchInOutPage = new PunchInOutPage(driver);
        navbar = new Navbar(driver);
        navbar.navigateTime();
        punchInOutPage.navigateToPunchInOut();
    }

    @Owner("Abdelrhman")
    @Test(  testName = "TC-2",
            dependsOnMethods = "navigatePunchInOut",
            description = "This test will punch in"
    )
    public void punchIn(){
        punchInOutPage.punchInOut(true);
        Assert.assertEquals(punchInOutPage.getMessage(), successMessage, "Punch in/out failed");
        punchInOutPage.closeMessage();
        punchInOutPage.waitForLoaderToDisappear(10);
    }

    @Owner("Abdelrhman")
    @Test(  testName = "TC-3",
            dependsOnMethods = "punchIn",
            description = "This test will punch out"
    )
    public void punchOut(){
        punchInOutPage.punchInOut(false);
        Assert.assertEquals(punchInOutPage.getMessage(), successMessage, "Punch in/out failed");
        punchInOutPage.closeMessage();
    }

    @Owner("Abdelrhman")
    @Test(  testName = "TC-4",
            dependsOnMethods = "punchOut",
            description = "This test will navigate to my records page"
    )
    public void navigateToMyRecords(){
        myRecordsPage = new MyRecordsPage(driver);
        myRecordsPage.navigateToMyRecords();
    }

    @Owner("Abdelrhman")
    @Test(  testName = "TC-5",
            dependsOnMethods = "navigateToMyRecords",
            description = "This test will check the records"
    )
    public void checkRecords(){
        int tableSize = myRecordsPage.getTableDataSize();
        Assert.assertTrue(tableSize >= 2, "No records found");
    }












}