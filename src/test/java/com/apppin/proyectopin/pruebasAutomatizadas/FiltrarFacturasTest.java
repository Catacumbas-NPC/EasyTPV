package com.apppin.proyectopin.pruebasAutomatizadas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.apppin.proyectopin.models.service.IServicio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.openqa.selenium.Keys;

public class FiltrarFacturasTest {
    
    /* @Test
	void contextLoads() {
	} */

	@Autowired
	private IServicio servicioService;

	private WebDriver driver;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
	}

	@Test
	public void testFiltrarFacturas() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("usuario")).sendKeys("alavso");
        driver.findElement(By.id("contrasena")).sendKeys("123", Keys.ENTER);
		driver.findElement(By.id("facturas")).click();
		//driver.findElement(By.id("inputMDEx")).sendKeys(LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear());
		driver.findElement(By.id("inputMDEx")).sendKeys("05/10/2021");
		driver.findElement(By.id("inputMDEx2")).sendKeys("10/10/2021");
		driver.findElement(By.id("buscar")).click();
		List<WebElement> reservas = driver.findElements(By.className("card-title"));
		assertEquals("María Dolores Rivera Milagros", reservas.get(0).getText());
		assertEquals("Kiko Hernández López", reservas.get(1).getText());
		assertEquals("Francisco Pérez", reservas.get(2).getText());
		assertEquals("Kiko Hernández López", reservas.get(3).getText());
	}

	@AfterEach
	public void tearDown() {
		/* driver.quit(); */
	}

}
