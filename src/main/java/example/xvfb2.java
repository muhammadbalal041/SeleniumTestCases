package example;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class xvfb2 {
  @Test
  public void f() {
	  //WebDriver driver;
	  System.setProperty("webdriver.gecko.driver", "geckodriver");
	  String Xport = System.getProperty(
              "lmportal.xvfb.id", ":10");
      final File firefoxPath = new File(System.getProperty(
              "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
      FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
      firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

      // Start Firefox driver
      WebDriver driver = new FirefoxDriver(firefoxBinary, null);
// 	 driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.get("http://google.com/");
      System.out.println("Page title is: " + driver.getTitle());
      //Take snapshot of browser
      //File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      //FileUtils.copyFile(srcFile, new File("ffsnapshot.png"));
      driver.quit();
  }
}
