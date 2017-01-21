package test;


import java.io.FileOutputStream;
import org.testng.annotations.Test;

import jxl.Workbook;

public class Fb_printlinks extends Helper {
	String mou="mounikareddy.bantu@gmail.com",moup="feedburner1";
	String man="manohar.gampa23@gmail.com",manp="feedburner1";
	String sru="srujasakshi@gmail.com",srup="srujasakshi143";
	String sri="srinivasranjola@zoho.com",srip="secret09";
	String ram="ramya.ranjola@gmail.com",ramp="feedburner1";
	String sha="shravani.sravan23@gmail.com",shap="feedburner1";
	String swe="sandeepkumar.gampa24@gmail.com",swep="feedburner1";
	
	String gmou,gman,gsru,gsri,gram,gsha,gswe;
	
	@Test(description="This method contains login and printgroups")
	public void grouplinks() throws Exception {
		
	gmou="https://www.facebook.com/jobshuntingg/groups?lst=100008917032079%3A100008917032079%3A1481556832";
	gsri="https://www.facebook.com/srinivas.raj.7587/groups?lst=100010970196401%3A100010970196401%3A1483164061";
	gman="https://www.facebook.com/profile.php?id=100010246505449&lst=100010246505449%3A100010246505449%3A1483164643&sk=groups";
	gsru="https://www.facebook.com/profile.php?id=100011565820911&lst=100011565820911%3A100011565820911%3A1483854694&sk=groups";
    gram="https://www.facebook.com/profile.php?id=100010789210426&lst=100010789210426%3A100010789210426%3A1483854856&sk=groups";
    gsha="https://www.facebook.com/shravani.sravs.165/groups?lst=100009999757467%3A100009999757467%3A1483855116";
    gswe="https://www.facebook.com/swetha.valaba/groups?lst=100009606672014%3A100009606672014%3A1483855373";
	login(swe,swep);
	printgroups(gswe);
	}

	@Test
	public void Exceloutput() throws Exception {
		fo = new FileOutputStream("C:\\test\\swe.xls");
		wb = Workbook.createWorkbook(fo);
		s = wb.createSheet("sha", 1);
	}

}
