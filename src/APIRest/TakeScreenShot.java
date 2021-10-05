package APIRest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.internal.annotations.ITest;

import com.sun.jna.platform.FileUtils;

public class TakeScreenShot {
	
	WebDriver driver;
	
	public void captureScreenshot(ITestResult result) throws Exception
	{
		if(ITestResult.FAILURE== result.getStatus())
		{
			//Create reference of takescreenshot interface and type casting
			TakesScreenshot ts=(TakesScreenshot) driver;//type casting of 2 interface
			
			//use getscreenshot as method  to capture the screenshot in file format
			//Get screenshot method return type is file
			File src=ts.getScreenshotAs(OutputType.FILE);
			
			//Copy file to specific location
			org.apache.commons.io.FileUtils.copyFile(src, new File("./Screenshots/" +result.getName()+".png"));
			
		}
	}

}
