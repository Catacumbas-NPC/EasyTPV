package com.apppin.proyectopin.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.apppin.proyectopin.models.entity.Factura;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.entity.ServicioFacturado;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IFactura;
import com.apppin.proyectopin.models.service.IReserva;
import com.apppin.proyectopin.models.service.IServicio;
import com.apppin.proyectopin.models.service.IServicioFacturado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VentanaPagoController {

    @Autowired
	private IServicio servicioService;

    @Autowired
	private IServicioFacturado servicioFacturadoService;

    @Autowired
    private IReserva reservaService;

    @Autowired
    private IEmpresa empresaService;

    @Autowired
    private IFactura facturaService;
    
    @GetMapping("/ventanaPago")
    public String mostrarVentanaPago(Model model) {
        List<Servicio> listadoServicios = servicioService.listarTodos();
        List<Servicio> serviciosFacturados = new ArrayList<>();
        List<Reserva> reservas = reservaService.reservasHoy();
        List<Factura> facturasTodas = facturaService.listarTodos();

        model.addAttribute("factura", new Factura());
        model.addAttribute("servicios", listadoServicios);
        model.addAttribute("serviciosFacturados", serviciosFacturados);
        model.addAttribute("reservasHoy", reservas);
        model.addAttribute("facturasTodas", facturasTodas);
        
        return "views/ventanaDePago";
    }

    @PostMapping("/ventanaPago")
    private String pagarFactura(Model model, @Valid @ModelAttribute Factura factura, RedirectAttributes attribute) {

        if(factura.getServicios().compareTo("") == 0){
            attribute.addFlashAttribute("warning", "No ha seleccionado ningún servicio");
            return "redirect:/ventanaPago";
        }
        if(factura.getReserva() == null){
            attribute.addFlashAttribute("warning", "No hay reserva para hacer la factura");
            return "redirect:/ventanaPago";
        }

        factura.setEmpresa(empresaService.buscarPorId(1L));
        factura.setFechaDeExpedicion(Date.from(Instant.now()));
        factura.setImpuestoAplicado("0,21");
        factura.setReserva(factura.getReserva());
        
        String[] lista = factura.getServicios().split(",");
        factura.setServicios(factura.getServicios().substring(0, factura.getServicios().length()-1) + ".");
        facturaService.guardar(factura);


        for(int i=0; i<lista.length; i++){
            ServicioFacturado servicioFact = new ServicioFacturado();
            servicioFact.setFactura(factura);
            servicioFact.setServicio(servicioService.buscarPorNombre(lista[i]));
            servicioFacturadoService.guardar(servicioFact);
        }
        
        

        return "redirect:/ventanaPago/factura/"+factura.getId();
    }

    @GetMapping("/ventanaPago/factura/{id}")
    private String mostrarFactura(Model model, RedirectAttributes attribute, @PathVariable("id") Long idFactura) {
        Factura factura = facturaService.buscarPorId(idFactura);
        model.addAttribute("factura", factura);
        return "views/factura";
    }

    void mensajeAlerta(String a, String b, RedirectAttributes attribute) {
		attribute.addFlashAttribute(a, b);
	}

}
