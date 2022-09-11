package com.apppin.proyectopin.pruebasAutomatizadas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.service.IServicio;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.Keys;


public class CrearServicioTest {

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
	public void testCrearServicio() {
		File file = new File("src\\main\\resources\\static\\images\\maldini.jpeg");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("usuario")).sendKeys("alavso");
        driver.findElement(By.id("contrasena")).sendKeys("123", Keys.ENTER);
		driver.findElement(By.id("AutoService")).click();
		driver.findElement(By.id("newService")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("exampleModalCenter")).findElement(By.id("nombre")).sendKeys("Transplante Capilar");
		driver.findElement(By.id("exampleModalCenter")).findElement(By.id("precio")).sendKeys("3000");
		driver.findElement(By.id("exampleModalCenter")).findElement(By.id("formFileMultiple")).sendKeys(file.getAbsolutePath());
		driver.findElement(By.id("exampleModalCenter")).findElement(By.id("crearServicio")).click();
		List<Servicio> servicios = servicioService.listarTodos();
		Servicio servicio = servicios.get(servicios.size()-1);
		assertEquals("Transplante Capilar", servicio.getNombre());
		assertEquals(3000, servicio.getPrecio());
		assertEquals("/images/maldini.jpeg", servicio.getImagen());
	}

	@AfterEach
	public void tearDown() {
		/* driver.quit(); */
	}

}
