package helpers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshooter {
	
	public static void takeScreenshot(String screenName, WebDriver driver) {
		
		File myScreeshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try { 
			FileUtils.copyFile(myScreeshot, new File(screenName+ "_" +System.currentTimeMillis()+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
