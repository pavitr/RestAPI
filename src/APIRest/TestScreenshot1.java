package APIRest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestScreenshot1 {
	
	TakeScreenShot t1=new TakeScreenShot();
	
	@Test(priority=1)
	public void doLogin()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pavthrc\\Desktop\\Software\\chromedriver.exe");
		t1.driver=new ChromeDriver();
		t1.driver.manage().window().maximize();
		t1.driver.get("https://www.facebook.com/");
		t1.driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pavithrac582@gmail.com");
		t1.driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("7353292671@Pc");
		t1.driver.findElement(By.xpath("//button[@name='login12']")).click();
	}
	
	@Test(priority=2)
	public void assertionCheck()
	{
		System.out.println("Assertion check");
	}
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws Exception
	{
		t1.captureScreenshot(result);
	}

}
