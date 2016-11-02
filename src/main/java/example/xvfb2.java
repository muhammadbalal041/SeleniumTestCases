package example;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class xvfb2 {
  @Test
  public void f() {
	  //WebDriver driver;
	 WebDriver driver;
	boolean outOfStock = false;
	  System.setProperty("webdriver.gecko.driver", "geckodriver");
	  String Xport = System.getProperty(
              "lmportal.xvfb.id", ":10");
      final File firefoxPath = new File(System.getProperty(
              "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
      FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
      firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

      // Start Firefox driver
       driver = new FirefoxDriver(firefoxBinary, null);
// 	 driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      //driver.get("http://google.com/");
      //System.out.println("Page title is: " + driver.getTitle());
      //Take snapshot of browser
      //File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      //FileUtils.copyFile(srcFile, new File("ffsnapshot.png"));
	 
	  Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		//OPEN BEACHTREE WEBSITE
		driver.get("http://www.beechtree.pk");
		//driver.get("http://www.beechtree.pk");

		System.out.println("Page title is: " + driver.getTitle());

		//RANDOMLY SELECT CATEGORY
		List<WebElement> allCategories = driver.findElements(By.cssSelector("a.level0"));
		Random random1 = new Random();
		//WebElement randomCategory = allCategories.get(random1.nextInt(allCategories.size()));

		WebElement randomCategory = allCategories.get(1);

		//prints on console which category is chosen
		String temp = randomCategory.getText(); //driver.findElement(By.xpath("//*[@id='nav']/ol/li[2]/a")).getText();
		System.out.println("print the selected tab "+temp);

		if(!(temp.equals("LOOKBOOK")))
		{
			//CLICK ON RANDOMLY SELECTED CATEGORY
			randomCategory.click();


			//SELECT A RANDOM PRODUCT
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			Random random2 = new Random();
			WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
			System.out.println("print the selected product "+randomProduct);
			randomProduct.click();


			if(!(temp.equals("ACCESSORIES")||temp.equals("BT LAWN '16")))
			{
				//RANDOMLY SELECT THE SIZE
				//this will only make a list of available sizes/clickable size buttons on the website so the issue of availabe sizes will be handled
				List<WebElement> allsizes = driver.findElements(By.cssSelector("span[class='swatch']"));
				Random random3 = new Random();
				WebElement randomSize = allsizes.get(random3.nextInt(allsizes.size()));
				if(!allsizes.isEmpty())//if the size is availabe,click/select it
				{
					randomSize.click();
				}
				else//if the sizes are not available, print on console that the product is out of stock
				{
					System.out.println("the item selected is out of stock");
					outOfStock = true;
				}
			}
			if(outOfStock == false)//continue testing only if product is in stock
			{

				//SELECT QUANTITY = 1
				Select oSelect = new Select(driver.findElement(By.xpath("//*[@id='qty']")));
				oSelect.selectByVisibleText("1");

				//ADD TO CART
				WebElement addCart;
				if(temp.equals("ACCESSORIES"))
				{
					addCart = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[6]/div/div/div[2]/button/span/span"));
				}
				else if(temp.equals("BT LAWN '16"))
				{
					addCart = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[6]/div/div/div[2]/button/span/span"));
				}
				else if(temp.equals("SALE"))
				{
					addCart = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[6]/div[2]/div[2]/button/span/span"));
				}
				else //temp = summer sale/pret
				{
					addCart = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[6]/div[2]/div[2]/button"));
				}



				//CHECK IF THE ADD TO CART BUTTON IS ENABLED + DISPLAYED ON WEBPAGE
				if(!(addCart.isDisplayed()&& addCart.isEnabled()))
				{
					if(!addCart.isDisplayed())
					{
						System.out.println("Add to Cart button is not displayed on the webpage");
					}
					if(!addCart.isEnabled())
					{
						System.out.println("Add to Cart button is disabled on webpage");
					}
				}
				else
				{
					addCart.click();

					System.out.println("Add to Cart button is clicked");
					//CHECKOUT

					//checks if checkout button is enabled + displayed on webpage

					WebDriverWait wait = new WebDriverWait(driver, 100);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-cart\"]/div[3]/div[3]/div/a/span")));
					WebElement CheckOut = driver.findElement(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a/span"));
					if(!(CheckOut.isDisplayed()&& CheckOut.isEnabled()))
					{
						if(!CheckOut.isDisplayed())
						{
							System.out.println("CHECKOUT button is not displayed on the webpage");
						}
						if(!CheckOut.isEnabled())
						{
							System.out.println("CHECKOUT button is disabled on webpage");
						}
					}
					else
					{
						CheckOut.click();
						System.out.println("CheckOut button is clicked");
						driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						//FILL IN THE BILLING INFORMATION
						WebDriverWait waitt = new WebDriverWait(driver, 100);
						waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:firstname']"))).sendKeys("test");

						//driver.findElement(By.xpath("//*[@id='billing:firstname']")).sendKeys("test");
						System.out.println("First Name is enterd");
						//WebDriverWait waitt2 = new WebDriverWait(driver, 10);
						//waitt2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:lastname']"))).sendKeys("test");

						driver.findElement(By.xpath("//*[@id='billing:lastname']")).sendKeys("test");
						System.out.println("Last Name is Enterd");
						//WebDriverWait waitt3 = new WebDriverWait(driver, 10);
						//waitt3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bill_form']/div[2]/div[1]/input"))).sendKeys("sara.iftikharsi@gmail.com");

						driver.findElement(By.xpath("//*[@id='bill_form']/div[2]/div[1]/input")).sendKeys("sara.iftikharsi@gmail.com");
						System.out.println("Email is Enterd");
						//WebDriverWait waitt4 = new WebDriverWait(driver, 10);
						//waitt4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:confirm_email']"))).sendKeys("sara.iftikharsi@gmail.com");

						driver.findElement(By.xpath("//*[@id='billing:confirm_email']")).sendKeys("sara.iftikharsi@gmail.com");
						System.out.println("Email is Confirmed");
						//WebDriverWait waitt5 = new WebDriverWait(driver, 10);
						//waitt5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:street1']"))).sendKeys("test");

						driver.findElement(By.xpath("//*[@id='billing:street1']")).sendKeys("test");
						System.out.println("Street is Enterd");
						//WebDriverWait waitt6 = new WebDriverWait(driver, 10);
						//waitt6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:region']"))).sendKeys("test");

						driver.findElement(By.xpath("//*[@id='billing:region']")).sendKeys("test");
						System.out.println("Region is Enterd");
						//WebDriverWait waitt7 = new WebDriverWait(driver, 10);
						//waitt7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:postcode']"))).sendKeys("test");

						driver.findElement(By.xpath("//*[@id='billing:postcode']")).sendKeys("test");
						System.out.println("Post code is Enterd");
						//Select oSelect2 = new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']")));
						//oSelect2.selectByVisibleText("PAKISTAN");

						Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:city']")));
						oSelect3.selectByIndex(3);
						System.out.println("City is Enterd");
						//WebDriverWait waitt8 = new WebDriverWait(driver, 10);
						//waitt8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:telephone']"))).sendKeys("03364054186");

						driver.findElement(By.xpath("//*[@id='billing:telephone']")).sendKeys("03364054186");
						System.out.println("Telephone number is Enterd");
						//WebDriverWait waitt9 = new WebDriverWait(driver, 10);
						//waitt9.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tel2']"))).sendKeys("03364054186");

						driver.findElement(By.xpath("//*[@id='tel2']")).sendKeys("03364054186");
						System.out.println("Telephone number is confirmed");

						//File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						//FileUtils.copyFile(scrFile2, new File("beechtree2.png"), true);

						//SELECT CASH ON DELEIVERY
						//WebDriverWait waitt10 = new WebDriverWait(driver, 10);
						//waitt10.until(ExpectedConditions.visibilityOfElementLocated(By.id("p_method_cashondelivery"))).click();

						driver.findElement(By.id("p_method_cashondelivery")).click();

						System.out.println("Method cash delivery is clicked");

						//PLACE ORDER
						WebDriverWait wait3 = new WebDriverWait(driver, 200);
						wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='review-buttons-container']/button")));
						driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();
						System.out.println("Review Button is clicked");
					}
				}
			}
		}
		System.out.println("after the Review Button is clicked");

		//CLOSE THE BROWSER
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement VerifyCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='test']")));

		System.out.println("Order is Successfully placed "+VerifyCode);
		//File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(scrFile3, new File("beechtree3.png"), true);
		//driver.quit();



	 
      driver.quit();
  }
}
