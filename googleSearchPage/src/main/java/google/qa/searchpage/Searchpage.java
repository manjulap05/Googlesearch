/**
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

/**
 * @author Manjula
 *
 */


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
	

	
	// Initializing the Page Objects:
	public Searchpage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validategooglePageTitle() {
		 return driver.getTitle();
		
	}

	public boolean validategoolgleImage() {
		return googleLogoImageTest.isDisplayed();
	}

	public boolean validategoolgeSearchEdit() {
		return validategoolgeSearchEdit.isDisplayed();
		
	}
	
	
	public WebElement validategoolgeSearchwithnodata() {
		validategoolgeSearchEdit.click();
		return validategoolgeSearchEdit;
	}

	
	
	public void validategooglesearchbtn() {
		try 
		{
		     if(validategoolgeSearchbtn.isDisplayed()) 
		     {
		    	 validategoolgeSearchbtn.click();
		     }
		}
		catch (NoSuchElementException e)
		{
		    System.out.println("search button avaliable");
		}
	}
	
	
	
	public void validateFeelluckybtn() {
		try 
		{
		     if(validatefeelluckybtn.isDisplayed()) 
		     {
		    	 validatefeelluckybtn.getAttribute("value");
		     }
		}
		catch (NoSuchElementException e)
		{
		    System.out.println("Feel lucky button avaliable");
		}
	}
	
	
	public void validdategooglelnglink() {
		
				
		List<String> hrefs = new ArrayList<String>();
		WebElement LangSection=driver.findElement(By.xpath("//*[@id='SIvCob']"));
		List<WebElement> anchors = LangSection.findElements(By.tagName("a"));
		for ( WebElement anchor : anchors ) {
		    hrefs.add(anchor.getAttribute("href"));
		}
		for ( String href : hrefs ) {
		    driver.get(href); 
		    
		}
	}
	
	
	public void autosearch() {
	
		WebElement element = driver.switchTo().activeElement();
		element.sendKeys("selenium");
		List<WebElement> lstGoogle = driver.findElement(By.xpath("//ul[@role='listbox']"))
	.findElements(By.xpath("//li[@role='presentation']"));
		for (int i = 0; i < lstGoogle.size(); i++) {
			System.out.println(lstGoogle.get(i).getText());
		}
		element.submit();
		System.out.println("Page title is: " + driver.getTitle());
		//getScreenShot(driver);	
		
	}
	
	
	public void Validategoogleapps() {
		
		validategoogleapslnk.isDisplayed();
		validategoogleapslnk.click();
		WebElement aps = (WebElement) driver.findElements(By.xpath("//*[@id='gbwa']//div/ul/li"));
		     /* int links=aps.size();
		//Traversing through the list and printing its text along with link address
				for(WebElement link:aps){
					System.out.println(link.getText() + " - " + link.getAttribute("href"));
				}
				
		      System.out.println("List of links Available: " + links);
		      for(int i=1;i<links;i++)
		      {
		      if(aps.get(i).getAttribute("href").contains("google"))
		      {
		      String link = aps.get(i).getAttribute("href");
		      System.out.println(link);
		      }   
		      }*/
		
		List<WebElement> links =aps.findElements(By.tagName("a"));
	     System.out.println("Total links are : "+links.size());

	     for(int i=0;i<links.size();i++)
	     {
	        System.out.println(links.get(i).getText());
	     }
	}
	
	
	
	
	
	
	
	public void Googlesearchwithsplchar(String strToCheck) {
		
		validategoolgeSearchEdit.sendKeys("#@!$");
		validategoolgeSearchEdit.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		if(driver.getPageSource().contains(strToCheck))
			{
				System.out.println("Text is Present");
				}
				else
				{
				System.out.println("Text is not Present");
				}
		
		
		
	}

	
	
	
	
	}


