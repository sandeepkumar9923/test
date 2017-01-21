package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

public class Finallist extends Helper {

	String mou="mounikareddy.bantu@gmail.com",moup="feedburner1";
	String man="manohar.gampa23@gmail.com",manp="feedburner1";
	String sru="srujasakshi@gmail.com",srup="srujasakshi143";
	String sri="srinivasranjola@zoho.com",srip="secret09";
	String ram="ramya.ranjola@gmail.com",ramp="feedburner1";
	String sha="shravani.sravan23@gmail.com",shap="feedburner1";
	String swe="sandeepkumar.gampa24@gmail.com",swep="feedburner1";
	
	
	@Test(description="This method contains login and printgroups",priority =0)
	public void Login() throws Exception {
	login(swe,swep);

	}
	
@Test
public void groupscheck() throws Exception {
//VerifyGroups("C:\\test\\man.xls","0","C:\\test\\Fman.xls");

	fo = new FileOutputStream("C:\\test\\Fswe.xls");
	wb = Workbook.createWorkbook(fo);
	s = wb.createSheet("sru", 0);
	
	FileInputStream fi= new FileInputStream("C:\\test\\Unqiue.xls");
	Workbook objWorkbook = Workbook.getWorkbook(fi);
	Sheet objSheet = objWorkbook.getSheet(0);
	int size = objSheet.getRows();


System.out.println("Reading input Excel file ");
		

		for (int i = 1; i < 198; i++) {
			String CurrentGroupURL = objSheet.getCell(5, i).getContents();
			driver.navigate().to(CurrentGroupURL);
			Thread.sleep(5000);
			try {
				
				//if (driver.findElement(By.name("xc_message")).isDisplayed()) 
				if(driver.findElement(By.xpath("//button[text()='Post']")).isDisplayed())
				{
					System.out.println(i + " Text area available for "+ CurrentGroupURL);
					Label l = new Label(0, i, driver.getCurrentUrl());
					s.addCell(l);
	//				System.out.println(CurrentGroupURL+"   URL addeded in excel " );
				}else
				{
					System.out.println("Text area not available for "+ CurrentGroupURL +" and URL index number is  "+i);
					
				}
				/*if (driver.findElement(By.xpath(".//*[@class='_585r _2i-a _50f4']/../..//span/a")).isDisplayed())
				{
					
					System.out.println(driver.getCurrentUrl()+" have pending posts ");
					
					System.out.println(driver.findElement(By.xpath(".//*[@class='_585r _2i-a _50f4']/../..//span/a")).getText());
					
				
				}*/

			} catch (Exception e) {
				System.out.println("failed at "  + CurrentGroupURL );
			}
		}wb.write();
		wb.close();
	}


}