package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PageReservation {
	
	private WebDriver driver;
	private By titleText;
	private By passengersDrop;
	private By fromDrop;
	private By toDrop;
	
	public PageReservation(WebDriver driver) {
		
		this.driver = driver;
		titleText = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img");
		passengersDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		toDrop = By.name("toPort");
	}
	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("FLIHGT"));
	}
	
	public void selectPassengers(int cant) {
		
		Select selectPasajeros = new Select(driver.findElement(passengersDrop));
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	
	public void selectFromPort(int index) {
		Select selectFrom = new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	
	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}

}
