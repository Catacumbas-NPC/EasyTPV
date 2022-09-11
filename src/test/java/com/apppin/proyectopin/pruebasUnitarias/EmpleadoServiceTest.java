package com.apppin.proyectopin.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import com.apppin.proyectopin.models.entity.Empleado;
import com.apppin.proyectopin.models.repository.EmpleadoRepository;
import com.apppin.proyectopin.models.service.IEmpleado;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class EmpleadoServiceTest {
    
    @SpyBean
    private IEmpleado empleadoService;

    @MockBean
    private EmpleadoRepository empleadoRepository;

    @Test
    public void crearPersonaTest(){
        Empleado empleado = new Empleado();
        empleado.setNombre("Paco Linares");
        empleado.setUsuario("pacolina");
        empleado.setContrasenya("123456pacolinares");
        empleado.setConfirmContrasenya("123456pacolinares");

        doReturn(null).when(empleadoRepository).findByUsuario(empleado.getUsuario());
        doReturn(empleado).when(empleadoRepository).save(empleado);

        try {
            assertEquals(empleado,empleadoService.crearPersona(empleado));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void crearPersonaFalloUsuarioDisponibleEnBD(){
        Empleado empleado = new Empleado();
        empleado.setNombre("Paco Linares");
        empleado.setUsuario("pacolina");
        empleado.setContrasenya("123456pacolinares");
        empleado.setConfirmContrasenya("123456pacolinares");

        doReturn(empleado).when(empleadoRepository).findByUsuario(empleado.getUsuario());

        assertThrows(Exception.class, ()->{empleadoService.crearPersona(empleado);});
    }

    @Test
    public void crearPersonaFalloConfirmPasswordNulo(){
        Empleado empleado = new Empleado();
        empleado.setNombre("Paco Linares");
        empleado.setUsuario("pacolina");
        empleado.setContrasenya("123456pacolinares");

        doReturn(null).when(empleadoRepository).findByUsuario(empleado.getUsuario());
        assertThrows(Exception.class, ()->{empleadoService.crearPersona(empleado);});
    }

    @Test
    public void crearPersonaFalloConfirmPassword(){
        Empleado empleado = new Empleado();
        empleado.setNombre("Paco Linares");
        empleado.setUsuario("pacolina");
        empleado.setContrasenya("123456pacolinares");
        empleado.setConfirmContrasenya("123");

        doReturn(null).when(empleadoRepository).findByUsuario(empleado.getUsuario());
        assertThrows(Exception.class, ()->{empleadoService.crearPersona(empleado);});
    }
}
