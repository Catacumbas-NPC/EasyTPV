package com.apppin.proyectopin.pruebasAutomatizadas;

import java.util.concurrent.TimeUnit;
import com.apppin.proyectopin.models.service.IReserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.openqa.selenium.Keys;

public class CrearReservaTest {
    
    @Autowired
    private IReserva reservaService;

	private WebDriver driver;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
	}

	@Test
	public void testCrearReserva() {
		// Logearse
		driver.findElement(By.id("usuario")).sendKeys("alavso");
        driver.findElement(By.id("contrasena")).sendKeys("123", Keys.ENTER);

		// Crear reserva
		driver.findElement(By.id("crearReserva")).click();
		driver.findElement(By.id("telefono")).sendKeys("685678567");
		driver.findElement(By.id("nombre")).sendKeys("Daniel");
		driver.findElement(By.id("inputMDEx")).sendKeys("25/11/2022");
		driver.findElement(By.id("prueba")).sendKeys("08:30:00");
		driver.findElement(By.id("check")).click();
		driver.findElement(By.id("reservar")).submit();
		reservaService.eliminar(reservaService.obtenerIdUltimaReservaHecha());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterEach
	public void tearDown() {
		/* driver.quit(); */
	}

}
