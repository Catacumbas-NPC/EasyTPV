package com.apppin.proyectopin.controller;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Factura;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/factura")
public class MostrarFacturasController {

    @Autowired
    private IFactura facturaService;

    @Autowired
    private IEmpresa empresaService;

    @GetMapping("/mostrarFacturas")
    public String mostrarFacturas(Model model){
        Empresa empresa = empresaService.buscarPorId(1L);
        List<Factura> listaFacturas = facturaService.facturasOrdenadas(facturaService.listarFacturasEmpresa(empresa.getId()));

        // No usar servicios
        model.addAttribute("facturas", listaFacturas);

        return "factura/mostrarFacturas";
    }

    @GetMapping("/eliminar_factura/{id}")
    public String eliminarServicio(@PathVariable("id") Long idFactura, RedirectAttributes attribute){
        facturaService.eliminar(idFactura);
        attribute.addFlashAttribute("success", "Factura eliminada con éxito.");

        return "redirect:/factura/mostrarFacturas";

    }

    @GetMapping("/mostrarFacturasFechas")
    public String facturasEntreFechas(Model model, String desde, String hasta) throws ParseException{
        Date desdeFecha = new SimpleDateFormat("yyyy-MM-dd").parse(desde);  
        Date hastaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(hasta);  
        List<Factura> listaFacturasOrdenar = facturaService.facturasDesdeHasta(desdeFecha, hastaFecha);
        List<Factura> listaFacturas = facturaService.facturasOrdenadas(listaFacturasOrdenar);
        model.addAttribute("facturas", listaFacturas);
        return "factura/mostrarFacturas";
    }

}
