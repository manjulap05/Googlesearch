package google.qa.Testcases;

import java.io.IOException;

import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
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
	ExtentReports extent= new ExtentReports();
	ExtentTest logger;
	

	
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
		
        logger=extent.createTest("TitleTest");
	   /* logger.log(Status.INFO, "Google ");
	    logger.log(Status.PASS, "Google title verified");
	    extent.flush();*/
		
	}

	@Test(priority = 2)
	public void GoogleLogoImageTest() throws Exception {
		boolean flag = searchpage.validategoolgleImage();
		Assert.assertTrue(flag);
		logger=extent.createTest("GoogleLogoImageTest");
/*	  	logger.log(Status.FAIL, "Google image verified");
	    logger.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("/screenshots/screenshot.png").build());
	    extent.flush();*/
	}

	@Test(priority = 3)
	public void GooglesearchtextTest() {
		boolean flag = searchpage.validategoolgeSearchEdit();
		Assert.assertTrue(flag);
		 logger=extent.createTest("GooglesearchtextTest");
	 /*   logger.log(Status.INFO, "Search text box ");
	    logger.log(Status.PASS, "Google Search textbox verified");
	    extent.flush();*/
	}

	@Test(priority = 4)
	public void Googlesearchwithnovalue() {
		WebElement inputBox = searchpage.validategoolgeSearchwithnodata();
		String textInsideInputBox = inputBox.getAttribute("value");
		// Check whether input field is blank
		if (textInsideInputBox.isEmpty()) {
			System.out.println("Input field is empty");
		}
	}

	@Test(priority = 5)
	public void Googlesearchwithspecialchar() {
		searchpage.Googlesearchwithsplchar("!@#$% - did not match any documents");
		logger=extent.createTest("Googlesearchwithspecialchar");
	 /*   logger.log(Status.INFO, "Search with special char ");
	    logger.log(Status.PASS, "Google Searchwith special char verified");
	    extent.flush();*/
	}

	@Test(priority = 6)
	public void Googlebtn() {
		searchpage.validategooglesearchbtn();
		searchpage.validateFeelluckybtn();
		logger=extent.createTest("validategooglesearchbtn");
	   /* logger.log(Status.INFO, "validate google search btn ");
	    logger.log(Status.PASS, "Google Searchbutton verified");
	    extent.flush();*/
	}

	@Test(priority = 7)
	public void GoogleLanguagelnk() {
		searchpage.validdategooglelnglink();
		logger=extent.createTest("validdategooglelnglink");
	   /* logger.log(Status.INFO, "google language link ");
	    logger.log(Status.PASS, "Google language link verified");
	    extent.flush();*/

	}

	@Test(priority = 8)
	public void Googleautosearch() {
		searchpage.autosearch();
		 logger=extent.createTest("Googleautosearch");
	  /*  logger.log(Status.INFO, "google Auto sugeestion verification ");
	    logger.log(Status.PASS, "Google  Auto sugeestion verification verified");
	    extent.flush();*/
	}
	
	/*@Test(priority = 0)
	public void Googleaapps() {
	searchpage.Validategoogleapps();
	}*/
	


		
		
		@AfterMethod
	    public void getResult(ITestResult result) throws IOException
	    {
			
		
	        if (result.getStatus() == ITestResult.FAILURE)
	        {
	    		
	    	     	    
	            String screenShotPath = TestUtil.takeScreenshotAtEndOfTest().getAbsolutePath();
	            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	            logger.fail(result.getThrowable());
	            logger.fail("Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
	            
	           
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	        }
	        else
	        {
	        	logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	        	logger.skip(result.getThrowable());
	        }
	        extent.flush();
	        driver.quit();
	    }
	 
	

}
