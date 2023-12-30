package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Screenshooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageNoLogon;
import pages.PageReservation;


public class Tests {
	
	private WebDriver driver;
	ArrayList<String> tabs;
	
	@BeforeMethod
	public void setUp( ) {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().window().fullscreen(); // pantalla completa
		//driver.manage().window().setSize(new Dimension(800 , 600)); // definir el tamaño de pantalla al abrir 
		//driver.manage().window().setPosition(new Point(400 , 300)); // para mover la ventana del browser en cualquier parte del windows
		driver.navigate().to("https://demo.guru99.com/test/newtours/");
		
		// ejecutar codigo JavaScript
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		
		tabs = new ArrayList<String> (driver.getWindowHandles()); // obetener todos los tabs del navegador y guardarlos en el arraylist
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pruebaUno() {
		
		WebDriverManager.setWindowSize(driver, "maximized");
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "user");
		pageLogon.assertLogonPage();
	}
	
	@Test
	public void pruebaDos() {
		
		WebDriverManager.setWindowSize(driver, "fullscreen");
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com"); // ir a youtube en el otro tab
		driver.switchTo().window(tabs.get(0)); // regresar al tab principal par aqu eno ede error
		
		PageLogin pageLogin = new PageLogin(driver);
		PageNoLogon pageNoLogon = new PageNoLogon(driver);
		pageLogin.login("rocco", "rocco");
		pageNoLogon.assertPage();
		/*Helpers helper = new Helpers();
		helper.sleepSeconds(5);*/
		//Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/span")).getText().contains("Enter your userName and password correct"));
	}
	
	/*@Test se comenta para probar la ventana de youtube a que aqui no bre con este test y da error
	public void pruebaTres() {
		//WebDriverManager.setWindowSize(driver,400,400);
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.selectPassengers(2);
		pageReservation.selectFromPort(3);
		pageReservation.selectToPort("London");
	}*/
	
	@Test
	public void testCantFields() {
		
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		// hacer capture cuando un Test falle
		if (!result.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		
		 driver.switchTo().window(tabs.get(1)).close();
		 driver.switchTo().window(tabs.get(0)).close();
		 
}
}