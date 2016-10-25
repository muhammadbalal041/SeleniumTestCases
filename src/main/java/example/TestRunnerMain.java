package example;

import org.testng.TestListenerAdapter;

import com.beust.testng.TestNG;

@SuppressWarnings("deprecation")
public class TestRunnerMain {

	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { PlaceAnOrder.class });
		testng.addListener(tla);
		testng.run();
		}


}
