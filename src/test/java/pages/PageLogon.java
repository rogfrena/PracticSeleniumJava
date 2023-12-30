package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageLogon {
	private WebDriver driver;
	private By titleText;
	public PageLogon(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b");
	}
	
	public void assertLogonPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Thank you"));
	}

}
