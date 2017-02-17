package AP;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class RunProgram extends Helper{
  
	@Parameters("username")
  @BeforeTest
  public void beforeTest(String username) throws Exception {
	  Login(username);
	 
  }
  @Parameters({"column","username"})
  @Test
  public void f(String column,String username) throws Exception {
  
	  Autopost(username,"y",column);
  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
