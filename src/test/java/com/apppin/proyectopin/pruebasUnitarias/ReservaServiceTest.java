package com.apppin.proyectopin.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IReserva;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;



@SpringBootTest
public class ReservaServiceTest {
    
    @SpyBean
    private IReserva reservaService;

    @MockBean
    private IEmpresa empresaService;
    
    @Test
    public void listarReservasEmpresaTest(){
        //Empresa y reserva que me interesa
        Empresa empresa = new Empresa();
        empresa.setId(21L);
        empresa.setNombre("Empresa Pruebas");
        empresa.setHoraApertura(LocalTime.of(8, 0, 0));
        empresa.setHoraCierre(LocalTime.of(20, 0, 0));
        empresa.setPropietario("Lourdes Peña");
        empresa.setNif("A08001851");
        Reserva reserva = new Reserva();
        reserva.setFecha("2021-12-25");
        reserva.setHora("12:00");
        reserva.setNombreCliente("Paco Murillo");
        reserva.setNumeroCliente("652354125");
        reserva.setEmpresa(empresa);

        //Otra empresa con sus reservas
        Empresa empresa2 = new Empresa();
        empresa2.setId(23L);
        empresa2.setNombre("Otra Empresa");
        empresa2.setHoraApertura(LocalTime.of(8, 0, 0));
        empresa2.setHoraCierre(LocalTime.of(20, 0, 0));
        empresa2.setPropietario("Paco Soriano");
        empresa2.setNif("A08001521");
        Reserva reserva2 = new Reserva();
        reserva2.setFecha("2021-12-27");
        reserva2.setHora("10:00");
        reserva2.setNombreCliente("Kiko Gallego");
        reserva2.setNumeroCliente("674251415");
        reserva2.setEmpresa(empresa2);


        List<Reserva> listaReservas = new ArrayList<>();
        listaReservas.add(reserva);
        listaReservas.add(reserva2);


        List<Reserva> listaEmpresaInteresada = new ArrayList<>();
        listaEmpresaInteresada.add(reserva);

        doReturn(empresa).when(empresaService).buscarPorId(21L);
        doReturn(listaReservas).when(reservaService).listarTodos();

        assertEquals(listaEmpresaInteresada, reservaService.listarReservasEmpresa(empresa.getId()));
    }

    @Test
    public void reservasOrdenadasTest(){
        Reserva reserva1 = new Reserva();
        reserva1.setFecha("2021-12-03");
        reserva1.setHora("08:00:00");
        Reserva reserva2 = new Reserva();
        reserva2.setFecha("2021-12-03");
        reserva2.setHora("09:00:00");
        Reserva reserva3 = new Reserva();
        reserva3.setFecha("2021-12-03");
        reserva3.setHora("10:00:00");
        Reserva reserva4 = new Reserva();
        reserva4.setFecha("2021-12-03");
        reserva4.setHora("10:30:00");
        List<Reserva> listaDesordenada = new ArrayList<>();
        listaDesordenada.add(reserva4);
        listaDesordenada.add(reserva3);
        listaDesordenada.add(reserva1);
        listaDesordenada.add(reserva2);

        List<Reserva> listaOrdenada = new ArrayList<>();
        listaOrdenada.add(reserva1);
        listaOrdenada.add(reserva2);
        listaOrdenada.add(reserva3);
        listaOrdenada.add(reserva4);

        assertEquals(listaOrdenada, reservaService.reservasOrdenadas(listaDesordenada));
    }

    @Test
    public void reservasOrdenadasListaVaciaTest(){
        List<Reserva> listaVacia = new ArrayList<>();

        assertEquals(listaVacia, reservaService.reservasOrdenadas(listaVacia));

    }

    @Test
    public void reservaHoyTest(){
        Reserva reserva1 = new Reserva();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);
        reserva1.setFecha(formattedString);
        reserva1.setHora("08:00:00");
        Reserva reserva2 = new Reserva();
        reserva2.setFecha("2021-12-03");
        reserva2.setHora("09:00:00");
        Reserva reserva3 = new Reserva();
        reserva3.setFecha(formattedString);
        reserva3.setHora("10:00:00");

        List<Reserva> listaEntera = new ArrayList<>();
        listaEntera.add(reserva1);
        listaEntera.add(reserva2);
        listaEntera.add(reserva3);

        List<Reserva> listaHoy = new ArrayList<>();
        listaHoy.add(reserva1);
        listaHoy.add(reserva3);

        doReturn(listaEntera).when(reservaService).listarTodos();

        assertEquals(listaHoy, reservaService.reservasHoy());
    }

}
