package com.apppin.proyectopin.controller;

import java.util.List;

import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.service.IServicio;
import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.service.IEmpresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ServiciosController {

    @Autowired
	private IServicio servicioService;

    @Autowired
	private IEmpresa empresaService;
    
    
    public String crearServicio(Model model){
        model.addAttribute("servicio", new Servicio());
        return "views/servicios";
    }

    
}
