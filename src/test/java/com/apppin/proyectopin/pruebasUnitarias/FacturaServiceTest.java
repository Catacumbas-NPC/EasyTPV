package com.apppin.proyectopin.pruebasUnitarias;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Factura;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IFactura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class FacturaServiceTest {
    
    @SpyBean
    private IFactura facturaService;

    @MockBean
    private IEmpresa empresaService;

    @Test
    public void listarFacturasEmpresaTest(){
        //Empresa y factura que me interesa
        Empresa empresa = new Empresa();
        empresa.setId(21L);
        empresa.setNombre("Empresa Pruebas");
        empresa.setHoraApertura(LocalTime.of(8, 0, 0));
        empresa.setHoraCierre(LocalTime.of(20, 0, 0));
        empresa.setPropietario("Lourdes Peña");
        empresa.setNif("A08001851");
        Reserva reserva = new Reserva();
        reserva.setFecha("2021-11-10");
        reserva.setHora("12:00");
        reserva.setNombreCliente("Paco Murillo");
        reserva.setNumeroCliente("652354125");
        reserva.setEmpresa(empresa);
        Reserva reserva2 = new Reserva();
        reserva2.setFecha("2021-11-09");
        reserva2.setHora("12:00");
        reserva2.setNombreCliente("Ramona Jimenez");
        reserva2.setNumeroCliente("652355475");
        reserva2.setEmpresa(empresa);
        Factura factura1 = new Factura();
        factura1.setEmpresa(empresa);
        try {
            factura1.setFechaDeExpedicion( new SimpleDateFormat("yyyy-mm-dd").parse("2021-11-10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        factura1.setImpuestoAplicado("21%");
        factura1.setMetodoPago(true);
        factura1.setServicios("Corte Caballero");
        factura1.setTotal(12L);
        factura1.setReserva(reserva);
        Factura factura2 = new Factura();
        factura2.setEmpresa(empresa);
        try {
            factura2.setFechaDeExpedicion(new SimpleDateFormat("yyyy-mm-dd").parse("2021-11-09"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        factura2.setImpuestoAplicado("21%");
        factura2.setMetodoPago(false);
        factura2.setServicios("Corte Mujer");
        factura2.setTotal(40L);
        factura2.setReserva(reserva2);

        //Otra empresa con sus reservas
        Empresa empresa2 = new Empresa();
        empresa2.setId(23L);
        empresa2.setNombre("Otra Empresa");
        empresa2.setHoraApertura(LocalTime.of(8, 0, 0));
        empresa2.setHoraCierre(LocalTime.of(20, 0, 0));
        empresa2.setPropietario("Paco Soriano");
        empresa2.setNif("A08001521");
        Reserva reserva3 = new Reserva();
        reserva3.setFecha("2021-11-20");
        reserva3.setHora("10:00");
        reserva3.setNombreCliente("Kiko Gallego");
        reserva3.setNumeroCliente("674251415");
        reserva3.setEmpresa(empresa2);
        Factura factura3 = new Factura();
        factura3.setEmpresa(empresa2);
        try {
            factura3.setFechaDeExpedicion(new SimpleDateFormat("yyyy-mm-dd").parse("2021-11-20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        factura3.setImpuestoAplicado("21%");
        factura3.setMetodoPago(true);
        factura3.setServicios("Corte Caballero con lavado");
        factura3.setTotal(13L);
        factura3.setReserva(reserva3);

        List<Factura> listaInteresa = new ArrayList<>();
        listaInteresa.add(factura1);
        listaInteresa.add(factura2);

        List<Factura> listaEmpresas = new ArrayList<>();
        listaEmpresas.add(factura1);
        listaEmpresas.add(factura2);
        listaEmpresas.add(factura3);

        doReturn(empresa).when(empresaService).buscarPorId(21L);
        doReturn(listaEmpresas).when(facturaService).listarTodos();

        assertEquals(listaInteresa, facturaService.listarFacturasEmpresa(empresa.getId()));
    }
}
