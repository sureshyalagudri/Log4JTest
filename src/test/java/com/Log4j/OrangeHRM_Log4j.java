package com.Log4j;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.Log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Log4j {
	WebDriver driver;

	// ChromeDriver driver;
	@BeforeTest
	public void LaunchBrowser() {
		Log.startLog("Chrome Browser Launched");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@AfterTest
	public void CloseBrowser() {
		// driver.close();//Close will close only current chrome browser
		driver.quit();
		Log.endLog("Chrome Browser closed successfully");
	}

	@Test
	public void LoginToOrangeHRM() throws InterruptedException {
		Log.debug("OpenHRM URL Lauched");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		Utilities.Common.explicitWaitByID(driver, "btnLogin", 180);
	
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		// Verify that Dashboard page displayed
		String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
		String ExpElement = "Dashboard";
		AssertJUnit.assertEquals(ActElement, ExpElement);
		System.out.println(ActElement);
		// ----------------To Verify Logout Function without using Assert----------------
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(3000);
		Log.debug("User Clicked on Logout button");
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();

	}

}
