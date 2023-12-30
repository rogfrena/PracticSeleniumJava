package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import helpers.Helpers;

public class PageLogin {
	
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;
	
	
	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("submit");
		fields = By.tagName("input");
	
		}
	
	public void login(String user, String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
		
		//captura y guardado de screen 		
		File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(myScreenshot, new File("LOGIN" + System.currentTimeMillis()+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Helpers helper = new Helpers();
		//helper.sleepSeconds(5);
	}
	
	public void fields_login(String user, String pass) {
		
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(2).sendKeys(user);
		loginFields.get(3).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void verifyFields() {
		
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size() != 9);
	}

}
