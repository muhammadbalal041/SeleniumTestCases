package example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class xvfb2 {
	WebDriver driver;
	boolean outOfStock = false;
  @Test
  public void f() {
	String Xport = System.getProperty("lmportal.xvfb.id", ":1");
		System.setProperty("firefox.gecko.driver", "geckodriver");
		// /usr/bin/firefox
		final File firefoxPath = new File(System.getProperty("lmportal.deploy.firefox.path", "/usr/bin/firefox"));
		FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
		firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
		driver = new FirefoxDriver(firefoxBinary, null);
	//driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	  Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		//OPEN BEACHTREE WEBSITE
		driver.get("http://www.leisureclub.pk/");
		//driver.get("http://www.beechtree.pk");

		System.out.println("Page title is: " + driver.getTitle());

		driver.findElement(By.cssSelector("span.close")).click();

		//WebDriverWait waitSpan = new WebDriverWait(driver, 100);
		//waitSpan.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='bg-popup']/span")));
		//driver.findElement(By.xpath(".//*[@id='bg-popup']/span")).click();
	  	
		System.out.println("Successfully closed the start Popup");


		List<WebElement> allCategories = driver.findElements(By.cssSelector("div.landing-content"));
		//driver.findElement(By.cssSelector("category-landing")).click();

		WebElement randomCategory =allCategories.get(1);
		randomCategory.click();
		System.out.println("print the selected Category "+ randomCategory);

		WebElement p=driver.findElement(By.xpath("//*[@id='product-collection-image-11203']"));
		String temp = p.getText(); 
		System.out.println("p.getText "+ temp);
		System.out.println("print the selected product "+ p);
		p.click();	    

		List<WebElement> allsizes = driver.findElements(By.cssSelector("span[class='swatch']"));
		System.out.println("allsizes is "+allsizes);
		Random random3 = new Random();
		WebElement randomSize = allsizes.get(random3.nextInt(allsizes.size()));
		randomSize.click();

		WebElement addCart=driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[5]/div[3]/button/span/span"));
		addCart.click();

		WebDriverWait waitt2 = new WebDriverWait(driver, 100);
		waitt2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a/span")));
		WebElement CheckOut = driver.findElement(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a/span"));
		CheckOut.click();

		//FILL IN THE BILLING INFORMATION
		WebDriverWait waitt = new WebDriverWait(driver, 100);
		//*[@id="billing:firstname"]
		waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:firstname']"))).sendKeys("test");

		System.out.println("FirstName is Enterd");

		//driver.findElement(By.xpath("//*[@id='billing:firstname']")).sendKeys("test");

		//WebDriverWait waitt2 = new WebDriverWait(driver, 10);
		//waitt2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:lastname']"))).sendKeys("test");

		//*[@id="billing:lastname"]
		driver.findElement(By.xpath("//*[@id='billing:lastname']")).sendKeys("test");
		System.out.println("LastName is Enterd");

		//WebDriverWait waitt3 = new WebDriverWait(driver, 10);
		//waitt3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bill_form']/div[2]/div[1]/input"))).sendKeys("sara.iftikharsi@gmail.com");

		//*[@id="billing:email"]
		driver.findElement(By.xpath("//*[@id='billing:email']")).sendKeys("sara.iftikharsi@gmail.com");
		System.out.println("Email is Enterd");
		//WebDriverWait waitt4 = new WebDriverWait(driver, 10);
		//waitt4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:confirm_email']"))).sendKeys("sara.iftikharsi@gmail.com");

		//*[@id="billing:confirm_email"]
		driver.findElement(By.xpath("//*[@id='billing:confirm_email']")).sendKeys("sara.iftikharsi@gmail.com");
		System.out.println("Email is Confirmed");
		//WebDriverWait waitt5 = new WebDriverWait(driver, 10);
		//waitt5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:street1']"))).sendKeys("test");

		//*[@id="billing:street1"]
		driver.findElement(By.xpath("//*[@id='billing:street1']")).sendKeys("test");
		System.out.println("Street is Enterd");
		//WebDriverWait waitt6 = new WebDriverWait(driver, 10);
		//waitt6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:region']"))).sendKeys("test");

		//*[@id="billing:street2"]
		driver.findElement(By.xpath("//*[@id='billing:street2']")).sendKeys("test");
		System.out.println("Street2 is Enterd");
		//WebDriverWait waitt7 = new WebDriverWait(driver, 10);
		//waitt7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:postcode']"))).sendKeys("test");

		//driver.findElement(By.xpath("//*[@id='billing:postcode']")).sendKeys("test");
		//System.out.println("PostCode is Enterd");

		//*[@id="billing:country_id"]
		Select oSelect2 = new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']")));
		oSelect2.selectByVisibleText("Pakistan");
		//oSelect2.selectByIndex(0);
		System.out.println("Pakistan is Enterd");

		//*[@id="billing:city"]
		Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:city']")));
		oSelect3.selectByIndex(3);
		System.out.println("City is Enterd");

		//WebDriverWait waitt8 = new WebDriverWait(driver, 10);
		//waitt8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:telephone']"))).sendKeys("03364054186");

		//*[@id="billing:telephone"]
		driver.findElement(By.xpath("//*[@id='billing:telephone']")).sendKeys("03364054186");
		System.out.println("Phone number is Enterd");

		//*[@id="tel2"]
		WebDriverWait waitt9 = new WebDriverWait(driver, 100);
		waitt9.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tel2']")));
		//.sendKeys("03364054186");

		//driver.findElement(By.xpath("//*[@id='tel2']")).sendKeys("03364054186");

		WebElement ConfirmMobileNumber = driver.findElement(By.id("tel2"));
		if(!(ConfirmMobileNumber.isDisplayed()&& ConfirmMobileNumber.isEnabled()))
		{
			if(!ConfirmMobileNumber.isDisplayed())
			{
				System.out.println("ConfirmMobileNumber button is not displayed on the webpage");
			}
			if(!ConfirmMobileNumber.isEnabled())
			{
				System.out.println("ConfirmMobileNumber button is disabled on webpage");
			}
		}
		else
		{
			ConfirmMobileNumber.sendKeys("03364054186");
			System.out.println("Phone number is confirmed");

		}

		//SELECT CASH ON DELEIVERY
		//WebDriverWait waitt10 = new WebDriverWait(driver, 10);
		//waitt10.until(ExpectedConditions.visibilityOfElementLocated(By.id("p_method_cashondelivery"))).click();

		//*[@id="p_method_cashondelivery"]
		WebDriverWait waittt3 = new WebDriverWait(driver, 300);
		waittt3.until(ExpectedConditions.elementToBeClickable(By.id("p_method_cashondelivery")));

		driver.findElement(By.id("p_method_cashondelivery")).click();
		System.out.println("Cash delivery method is Enterd");

		System.out.println("Before Review Button is reached");

		//PLACE ORDER

		//*[@id="review-buttons-container"]/button/span/span
		//*[@id="review-buttons-container"]/button/span/span
		//*[@id="review-buttons-container"]
		//*[@id="checkout-review-submit"]

		WebDriverWait waitttt3 = new WebDriverWait(driver, 300);
		waitttt3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='checkout-review-submit']")));
		driver.findElement(By.xpath("//*[@id='checkout-review-submit']")).click();

		System.out.println("Review Button is clicked");

		//*[@id="opc-review-block"]/ul/li/label
		//*[@id="opc-review-block"]/ul/li/div
		//*[@id="review-buttons-container"]/button/span/span
		//*[@id="is_subscribed"]

		//WebDriverWait waittt33 = new WebDriverWait(driver, 300);
		//waittt33.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='is_subscribed']")));
		//driver.findElement(By.xpath("//*[@id='is_subscribed']")).click();
		//System.out.println("unsubscribed is clicked");

		//driver.findElement(By.xpath("//*[@id='checkout-review-submit']")).click();

		//*[@id="btn1"]
		//*[@id="resend_btn"]

//		WebDriverWait waitt4 = new WebDriverWait(driver, 500);
//		WebElement VerifyCode = waitt4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resend_btn']")));
//		System.out.println("Order is Successfully placed "+VerifyCode);
//		System.out.println("Title of the page is" + driver.getTitle());




	 
      driver.quit();
  }
}
