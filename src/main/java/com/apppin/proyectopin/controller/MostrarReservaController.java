package com.apppin.proyectopin.controller;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IReserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reserva")
public class MostrarReservaController {

    @Autowired
    private IReserva reservaService;

    @Autowired
    private IEmpresa empresaService;

    @GetMapping("/mostrarReservas")
    public String mostrarReservas(Model model) {

        Empresa empresa = empresaService.buscarPorId(1L);
        // List<Reserva> listaReservas =
        // reservaService.listarReservasEmpresa(empresa.getId());
        List<Reserva> listaDefinitiva = reservaService.reservasHoy();

        /*
         * for (int i = 0; i < listaReservas.size(); i++) {
         * LocalDate fecha = LocalDate.parse(listaReservas.get(i).getFecha());
         * if(fecha.isAfter(LocalDate.now())){
         * listaDefinitiva.add(listaReservas.get(i));
         * }
         * }
         */

        List<Reserva> prueba = reservaService.reservasOrdenadas(listaDefinitiva);
        model.addAttribute("reservas", prueba);

        return "reserva/mostrarReservas";
    }

    @GetMapping("/mostrarReservas/{id}")
    public String eliminarReservaById(@PathVariable("id") Long idReserva, RedirectAttributes attribute) {
        reservaService.eliminar(idReserva);
        attribute.addFlashAttribute("success", "Reserva eliminada con éxito.");

        return "redirect:/reserva/mostrarReservas";

    }
}
