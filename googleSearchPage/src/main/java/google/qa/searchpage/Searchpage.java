/**
 * @author Manjula
 *
 */
package google.qa.searchpage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import google.qa.base.TestBase;
import google.qa.util.TestUtil;

public class Searchpage extends TestBase {

	@FindBy(xpath = "//img[@alt='Google']")
	WebElement googleLogoImageTest;

	@FindBy(xpath = "//input[@name='q']")
	WebElement validategoolgeSearchEdit;

	@FindBy(xpath = "//div[@id='fakebox-search-icon']")
	WebElement validategoolgeSearchicon;

	@FindBy(xpath = "//div[@class='FPdoLc VlcLAe']//input[@value='Google Search']")
	WebElement validategoolgeSearchbtn;

	@FindBy(xpath = "//div[@class='FPdoLc VlcLAe']//input[@name='btnI']")
	WebElement validatefeelluckybtn;

	@FindBy(xpath = "//a[@title='Google apps']")
	WebElement validategoogleapslnk;

	@FindBy(xpath = "//div[@class=qWuU9c']//a/span")
	WebElement validategoogleaps;

	@FindBy(xpath = "//a[contains(text(), 'Sign in')]")
	WebElement validategooglesignbtn;

	@FindBy(xpath = "//a[contains(text(), 'Gmail')]")
	WebElement validategooglegmaillnk;

	@FindBy(xpath = "//a[contains(text(), 'Images')]")
	WebElement validategoogleimg;

	TestUtil testUtil;

	// Initializing the Page Objects:
	public Searchpage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	// method to verify google Title
	public String validategooglePageTitle() {
		return driver.getTitle();

	}

	// method to verify google image in search page
	public boolean validategoolgleImage() {
		return googleLogoImageTest.isDisplayed();
	}

	// method to verify google search textbox
	public boolean validategoolgeSearchEdit() {
		return validategoolgeSearchEdit.isDisplayed();

	}

	// method to verify google search text with no data
	public WebElement validategoolgeSearchwithnodata() {
		validategoolgeSearchEdit.click();
		return validategoolgeSearchEdit;
	}

	public void validategooglesearchbtn() {
		try {
			if (validategoolgeSearchbtn.isDisplayed()) {
				validategoolgeSearchbtn.click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("search button avaliable");
		}
	}

	// method to verify google buttons
	public void validateFeelluckybtn() {
		try {
			if (validatefeelluckybtn.isDisplayed()) {
				validatefeelluckybtn.getAttribute("value");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Feel lucky button avaliable");
		}
	}

	// method to verify google language links
	public void validdategooglelnglink() {

		List<String> hrefs = new ArrayList<String>();
		WebElement LangSection = driver.findElement(By.xpath("//*[@id='SIvCob']"));
		List<WebElement> anchors = LangSection.findElements(By.tagName("a"));
		for (WebElement anchor : anchors) {
			hrefs.add(anchor.getAttribute("href"));
		}
		for (String href : hrefs) {
			driver.get(href);

		}
	}

	// method to verify google auto suggestions when user enter search keyword in
	// search edit
	public void autosearch(String Search) {

		WebElement element = driver.switchTo().activeElement();
		element.sendKeys(Search);
		List<WebElement> lstGoogle = driver.findElement(By.xpath("//ul[@role='listbox']"))
				.findElements(By.xpath("//li[@role='presentation']"));
		for (int i = 0; i < lstGoogle.size(); i++) {
			System.out.println(lstGoogle.get(i).getText());
		}
		element.submit();
		System.out.println("Page title is: " + driver.getTitle());

	}

	// method to verify google application ling on leftside google page
	public void Validategoogleapps() {
		testUtil = new TestUtil();
		validategoogleapslnk.isDisplayed();
		validategoogleapslnk.click();
		testUtil.switchToFrame();
		WebElement aps = (WebElement) driver.findElements(By.xpath("//*[@target='_parent']/.."));
		WebElement parentElement = driver.findElement(By.xpath("//div/div/ul[1]/li/a"));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("//ul[1]/li/a"));
		int count = 0;
		System.out.println("size : " + childrenElements.size());
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getAttribute("href");
			System.out.println(childrenElementTag);
			count++;
		}
	}

	// method to verify special char inside google search edit box
	public void Googlesearchwithsplchar(String strToCheck) {

		validategoolgeSearchEdit.sendKeys("#@!$");
		validategoolgeSearchEdit.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		if (driver.getPageSource().contains(strToCheck)) {
			System.out.println("Text is Present");
		} else {
			System.out.println("Text is not Present");
		}

	}

	// method to verify google signIn links
	public String validategooglesignin() {
		try {
			if (validategooglesignbtn.isDisplayed()) {
				validategooglesignbtn.click();
			}
		} catch (NoSuchElementException e) {

			System.out.println(" signin button is Present");
		}
		return driver.getTitle();
	}

	// method to verify google Gmail link in search page
	public String validategooglegmaillnk() {
		try {
			if (validategooglegmaillnk.isDisplayed()) {
				validategooglegmaillnk.click();
			}
		} catch (NoSuchElementException e) {

			System.out.println("Gmail link  is Present");
		}
		return driver.getTitle();
	}

	// method to verify google image link in seach page
	public String validategoogleimg() {
		try {
			if (validategoogleimg.isDisplayed()) {
				validategoogleimg.click();
			}
		} catch (NoSuchElementException e) {

			System.out.println("Image link is Present");
		}
		return driver.getTitle();
	}

}
