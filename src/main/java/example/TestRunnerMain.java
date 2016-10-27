package example;

import org.testng.TestListenerAdapter;

import com.beust.testng.TestNG;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestListenerAdapter;

//import com.beust.testng.TestNG;
import com.sun.jna.platform.FileUtils;

@SuppressWarnings("deprecation")
public class TestRunnerMain {

 	public static void main(String[] args) {
		
// 		TestListenerAdapter tla = new TestListenerAdapter();
// 		TestNG testng = new TestNG();
// 		testng.setTestClasses(new Class[] { PlaceAnOrder.class });
// 		testng.addListener(tla);
// 		testng.run();
		
		String Xport = System.getProperty(
                "lmportal.xvfb.id", ":1");
        final File firefoxPath = new File(System.getProperty(
                "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

//         // Start Firefox driver
        WebDriver driver = new FirefoxDriver(firefoxBinary, null);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://google.com/");
        System.out.println("Page title is: " + driver.getTitle());
        // Take snapshot of browser
        //File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(srcFile, new File("ffsnapshot.png"));
        driver.quit();
 	}

		
	}
		


}
