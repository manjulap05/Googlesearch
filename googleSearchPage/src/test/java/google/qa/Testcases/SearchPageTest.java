package google.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import google.qa.base.TestBase;
import google.qa.searchpage.Searchpage;

public class SearchPageTest extends TestBase {
	Searchpage searchpage;

	public SearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		searchpage = new Searchpage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = searchpage.validategooglePageTitle();
		Assert.assertEquals(title, "Google");
	}

	@Test(priority = 2)
	public void GoogleLogoImageTest() {
		boolean flag = searchpage.validategoolgleImage();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void GooglesearchtextTest() {
		boolean flag = searchpage.validategoolgeSearchEdit();
		Assert.assertTrue(flag);
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

	}

	@Test(priority = 6)
	public void Googlebtn() {
		searchpage.validategooglesearchbtn();
		searchpage.validateFeelluckybtn();
	}

	@Test(priority = 7)
	public void GoogleLanguagelnk() {
		searchpage.validdategooglelnglink();

	}

	@Test(priority = 8)
	public void Googleautosearch() {
		searchpage.autosearch();

	}
	
	@Test(priority = 0)
	public void Googleaapps() {
	searchpage.Validategoogleapps();
	}
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
