package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Helper {
	WebDriver driver;
	FileOutputStream fo;
	WritableWorkbook wb;
	WritableSheet s;

	public void openfb() throws Exception {
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.chrome.driver",
				"C://Users//sandeepkumarg//Desktop//Softwares//Sundar_Tools//Jars & Drivers//chromedriver_win32//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://www.facebook.com");
		driver.manage().window().maximize();
/*		 System.setProperty("webdriver.firefox.marionette","C:\\Users\\sandeepkumarg\\Pratice_Workspace\\AP\\drivers\\geckodriver.exe");
         
         // if above property is not working or not opening the application in browser then try below property

        //System.setProperty("webdriver.firefox.marionette","G:\\Selenium\\Firefox driver\\geckodriver.exe");

       driver = new FirefoxDriver();
       driver.manage().window().maximize();*/
		
	}

	public void hiddenbutton() {
		WebElement we = driver
				.findElement(By.xpath(".//*[@class='_42ft _5upp _50zy _3e-u _50-0 _50z_ _3e-t' and @title='Remove']"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", we);
	}

	public void login(String id, String pwd) throws Exception {
		driver.findElement(By.id("email")).sendKeys(id);
		driver.findElement(By.id("pass")).sendKeys(pwd);
		Thread.sleep(3000);
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(8000);
		System.out.println("login succeed ");

	}

	public void printgroups(String groupsUrl) throws Exception {
		driver.navigate().to(groupsUrl);

		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(40000);
		System.out.println("started colleting href data");
		List<WebElement> str = driver.findElements(By.xpath(".//*[contains(@href,'/groups')]"));
		for (int i = 0; i < str.size(); i++) {
			System.out.println(str.get(i).getAttribute("href"));
			Label l = new Label(0, i, str.get(i).getAttribute("href"));
			s.addCell(l);
		}
		wb.write();
		wb.close();
	}

	public void GroupPost(String inputexcelpath) throws Exception {

		FileInputStream fi = new FileInputStream(inputexcelpath);
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet s = wb.getSheet(0);

		for (int i = 0; i < 46; i++) {
			try {
				driver.navigate().to(s.getCell(0, i).getContents());
				Thread.sleep(4000);
				try {
					driver.findElement(By.name("xhpc_message_text")).click();
					Thread.sleep(4000);
					Actions a = new Actions(driver);
					a.sendKeys(Keys.chord(Keys.CONTROL, "v")).build().perform();
					Thread.sleep(10000);

					if (driver
							.findElement(By
									.xpath(".//*[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft' and @type='submit']"))
							.isDisplayed()) {
						driver.findElement(By
								.xpath(".//*[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft' and @type='submit']"))
								.click();
					} else {

						Actions b = new Actions(driver);

						b.keyDown(Keys.DOWN).build().perform();
						Thread.sleep(1000);
						driver.findElement(By
								.xpath(".//*[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft' and @type='submit']"))
								.click();
					}
					System.out.println("posted on group " + i);
				} catch (Exception e) {
					System.out.println("failed to post on group " + i);
				}
				Thread.sleep(8000);

			} catch (Exception e) {
				System.out.println("Error or Alert opened or No url found in file");
				break;
			}
		}
		}

	@BeforeTest
	public void beforeTest() throws Exception {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		openfb();

	}

	@AfterTest
	public void afterTest() {

	}
}
