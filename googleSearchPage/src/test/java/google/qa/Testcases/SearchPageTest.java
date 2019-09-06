package google.qa.Testcases;
/**
 * @author Manjula
 *
 */

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import google.qa.base.TestBase;
import google.qa.searchpage.Searchpage;
import google.qa.util.TestUtil;

public class SearchPageTest extends TestBase {

	Searchpage searchpage;
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reporter/Googlereuslt.html");
	ExtentReports extent = new ExtentReports();
	ExtentTest logger;
	String sheetName = "Sheet1";

	public SearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		searchpage = new Searchpage();
	}

	@Test(priority = 1)

	public void GooglepageTitleTest() {
		extent.attachReporter(reporter);
		String title = searchpage.validategooglePageTitle();
		Assert.assertEquals(title, "Google");
		logger = extent.createTest("TitleTest");

	}

	@Test(priority = 2)
	public void GoogleLogoImageTest() throws Exception {
		boolean flag = searchpage.validategoolgleImage();
		Assert.assertTrue(flag);
		logger = extent.createTest("GoogleLogoImageTest");

	}

	@Test(priority = 3)
	public void GooglesearchtextTest() {
		boolean flag = searchpage.validategoolgeSearchEdit();
		Assert.assertTrue(flag);
		logger = extent.createTest("GooglesearchtextTest");

	}

	@Test(priority = 4)
	public void Googlesearchwithnovalue() {
		WebElement inputBox = searchpage.validategoolgeSearchwithnodata();
		String textInsideInputBox = inputBox.getAttribute("value");
		// Check whether input field is blank
		if (textInsideInputBox.isEmpty()) {
			System.out.println("Input field is empty");
		}
		logger = extent.createTest("Googlesearchwithspecialchar");
	}

	@Test(priority = 5)
	public void Googlesearchwithspecialchar() {
		searchpage.Googlesearchwithsplchar("!@#$% - did not match any documents");
		logger = extent.createTest("Googlesearchwithspecialchar");

	}

	@Test(priority = 6)
	public void Googlebtn() {
		searchpage.validategooglesearchbtn();
		searchpage.validateFeelluckybtn();
		logger = extent.createTest("validategooglesearchbtn");

	}

	@Test(priority = 7)
	public void GoogleLanguagelnk() {
		searchpage.validdategooglelnglink();
		logger = extent.createTest("validdategooglelnglink");

	}

	@Test(priority = 8, dataProvider = "getgooglesearchTestData")
	public void Googleautosearch(String Search) {
		searchpage.autosearch(Search);
		logger = extent.createTest("Googleautosearch");

	}

	@Test(priority = 9)

	public void validategooglesigninTest() {
		extent.attachReporter(reporter);
		String title = searchpage.validategooglesignin();
		Assert.assertEquals(title, "Sign in - Google Accounts");
		logger = extent.createTest(" Google  singn TitleTest");
	}

	@Test(priority = 10)

	public void validategooglegmaillnkTest() {
		String title = searchpage.validategooglegmaillnk();
		Assert.assertEquals(title, "Gmail - Free Storage and Email from Google");
		logger = extent.createTest(" Gmail TitleTest");

	}

	@Test(priority = 11)
	public void validategoogleimgTest() {
		String title = searchpage.validategoogleimg();
		Assert.assertEquals(title, "Google Images");
		logger = extent.createTest("validategoogleimg");

	}

	@Test(priority = 12)
	public void Googleaapps() {
		searchpage.Validategoogleapps();
		logger = extent.createTest("Validategoogleapps");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenShotPath = TestUtil.takeScreenshotAtEndOfTest().getAbsolutePath();
			logger.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(result.getThrowable());
			logger.fail("Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
		}
		extent.flush();
		driver.quit();
	}

	@DataProvider
	public Object[][] getgooglesearchTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

}
