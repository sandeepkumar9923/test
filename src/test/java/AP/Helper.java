package AP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xml.serializer.utils.Messages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


import com.planit.framework.lib.Utilities;


public class Helper {

	WebDriver driver;

	String CurrentURL;
public String Texttobeposted ;
	public void Login(String Testcasename) throws Exception {
		Map<String, String> ExcelDataDetails = Utilities.readTestData("TestData//AllUn.xls", "logins", Testcasename);
		// driver = new FirefoxDriver();
		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "drivers//geckodriver.exe"); System.out.println("hi"); driver = new
		 * FirefoxDriver();
		 */
		Texttobeposted = ExcelDataDetails.get("text");
		File pathToBinary = new File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		 driver = new FirefoxDriver(ffBinary, firefoxProfile);

		driver.get("http://m.facebook.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("email")).sendKeys(ExcelDataDetails.get("username"));
		driver.findElement(By.name("pass")).sendKeys(ExcelDataDetails.get("password"));
		driver.findElement(By.name("login")).click();
		System.out.println("...........Logged In successfully ......");
	}

	public void Autopost(String sheetName, String testcaseName, String key) throws Exception {
		Map<String, Map<String, String>> ExcelDataDetails = Utilities.readMultipleTestData("TestData//AllUn.xls", sheetName,testcaseName);
System.out.println("the text is " +Texttobeposted);
		for (int i = 0; i < ExcelDataDetails.size(); i++) {
try{
			Map<String, String> Exceldata = ExcelDataDetails.get("Row" + (i + 1));
			CurrentURL = Exceldata.get(key);
			driver.navigate().to("https://m.facebook.com/groups"+CurrentURL);
			if (driver.findElement(By.name("xc_message")).isDisplayed()) {
				driver.findElement(By.name("xc_message")).click();
				driver.findElement(By.name("xc_message")).sendKeys(Texttobeposted);
				driver.findElement(By.name("view_post")).click();
				System.out.println("posted on " + i + CurrentURL);
				Thread.sleep(5000);
			} 
			}
		catch (Exception e){
				System.out.println("text area not found on " + CurrentURL);
			}
		
		}}
	}


