package com.apppin.proyectopin.pruebasAutomatizadas;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private WebDriver driver;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
	}

    @Test
	public void testLogin() {
		// Logearse
		driver.findElement(By.id("usuario")).sendKeys("alavso");
        driver.findElement(By.id("contrasena")).sendKeys("123", Keys.ENTER);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

    @AfterEach
	public void tearDown() {
		/* driver.quit(); */
	}

}
