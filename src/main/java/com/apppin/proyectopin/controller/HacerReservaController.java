package com.apppin.proyectopin.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.service.IEmpleado;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IReserva;
import com.apppin.proyectopin.models.service.IServicio;
import com.apppin.proyectopin.models.entity.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservar")
public class HacerReservaController {

    @Autowired
    private IEmpresa empresaService;

    @Autowired
    private IReserva reservaService;

    @Autowired
    private IServicio servicioService;

    @Autowired
    private IEmpleado empleadoService;

    @GetMapping("/hacerReserva")
    public String hacerReserva(Model model, RedirectAttributes attribute, Authentication auth, HttpSession session) {
        Reserva reserva = new Reserva();

        Empresa empresa = empresaService.buscarPorId(1L);
        List<Reserva> listaReservas = reservaService.listarReservasEmpresa(empresa.getId());

        List<LocalTime> horasDia = new ArrayList<>();
        LocalTime horaPivote = empresa.getHoraApertura();

        // horas en un dia
        while (horaPivote.isBefore(empresa.getHoraCierre()) || horaPivote.equals(empresa.getHoraCierre())) {
            horasDia.add(horaPivote);
            horaPivote = horaPivote.plusMinutes(30);
        }

        List<Servicio> servicios = servicioService.listarTodos();

        try {
            String username = auth.getName();

            if (session.getAttribute("usuario") == null) {
                Empleado usuario = empleadoService.findByUsuario(username);
                usuario.setContrasenya(null);
                session.setAttribute("usuario", usuario);
            }
        } catch (Exception e) {
            
        }

        model.addAttribute("horasDia", horasDia);
        model.addAttribute("listaReservas", listaReservas);
        model.addAttribute("titulo", "Reservar");
        model.addAttribute("reserva", reserva);
        model.addAttribute("horas", "");
        model.addAttribute("servicios", servicios);

        return "reserva/hacerReserva";
    }

    @PostMapping("/hacerReserva")
    public String reservar(Model model, RedirectAttributes attribute, @Valid @ModelAttribute Reserva reserva) {
        try {
            reserva.setEmpresa(empresaService.buscarPorId(1L));
            reserva.setFecha(reserva.getFecha());
            reserva.setNombreCliente(reserva.getNombreCliente());
            reserva.setNumeroCliente(reserva.getNumeroCliente());
            reserva.setHora(reserva.getHora());

            reservaService.guardar(reserva);
        } catch (Exception e) {
            attribute.addFlashAttribute("warning", "Fallos en los campos");
            return "redirect:/";
        }

        attribute.addFlashAttribute("success", "Reserva realizada con éxito.");

        return "redirect:/reservar/hacerReserva";
    }

    @GetMapping("/terminos")
    public String terminos() {
        return "reserva/terminos";
    }
}
