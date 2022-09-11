package com.apppin.proyectopin.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.service.IServicio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class ServicioServiceTest {
    
    @SpyBean
    private IServicio servicioService;

    @Test
    public void buscarPorNombreTest(){

        Servicio servicio1 = new Servicio();
        servicio1.setNombre("Corte Caballero");
        Servicio servicio2 = new Servicio();
        servicio2.setNombre("Corte Mujer");
        Servicio servicio3 = new Servicio();
        servicio3.setNombre("Corte Caballero con lavado");
        Servicio servicio4 = new Servicio();
        servicio4.setNombre("Corte Mujer con lavado");
        Servicio servicio5 = new Servicio();
        servicio5.setNombre("Afeitado de barba");

        List<Servicio> listadoServicios = new ArrayList<>();
        listadoServicios.add(servicio1);
        listadoServicios.add(servicio2);
        listadoServicios.add(servicio3);
        listadoServicios.add(servicio4);
        listadoServicios.add(servicio5);

        doReturn(listadoServicios).when(servicioService).listarTodos();

        assertEquals(listadoServicios.get(1), servicioService.buscarPorNombre("Corte Mujer"));
    }

    @Test
    public void buscarPorNombreTestFallo(){

        Servicio servicio1 = new Servicio();
        servicio1.setNombre("Corte Caballero");
        Servicio servicio2 = new Servicio();
        servicio2.setNombre("Corte Mujer");
        Servicio servicio3 = new Servicio();
        servicio3.setNombre("Corte Caballero con lavado");
        Servicio servicio4 = new Servicio();
        servicio4.setNombre("Corte Mujer con lavado");
        Servicio servicio5 = new Servicio();
        servicio5.setNombre("Afeitado de barba");

        List<Servicio> listadoServicios = new ArrayList<>();
        listadoServicios.add(servicio1);
        listadoServicios.add(servicio2);
        listadoServicios.add(servicio3);
        listadoServicios.add(servicio4);
        listadoServicios.add(servicio5);

        doReturn(listadoServicios).when(servicioService).listarTodos();

        assertEquals(null, servicioService.buscarPorNombre("Corte Niño"));
    }

}
