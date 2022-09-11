package com.apppin.proyectopin.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.apppin.proyectopin.models.entity.Empleado;
import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.service.IEmpleado;
import com.apppin.proyectopin.models.service.IEmpresa;
import com.apppin.proyectopin.models.service.IServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class HomeController {

    @Autowired
    private IServicio servicioService;

    @Autowired
    private IEmpresa empresaService;

    @Autowired
    private IEmpleado empleadoService;

    @GetMapping("/")
    public String mostrarInicio(Model model, Authentication auth, HttpSession session) {
        model.addAttribute("usuario", new Empleado());

        try {
            String username = auth.getName();

            if (session.getAttribute("usuario") == null) {
                Empleado usuario = empleadoService.findByUsuario(username);
                usuario.setContrasenya(null);
                session.setAttribute("usuario", usuario);
            }
        } catch (Exception e) {
            
        }

        return "views/login";
    }

    @GetMapping("/home")
    private String mostrarHome(Model model, Authentication auth, HttpSession session) {

        String username = auth.getName();

        if (session.getAttribute("usuario") == null) {
            Empleado usuario = empleadoService.findByUsuario(username);
            usuario.setContrasenya(null);
            session.setAttribute("usuario", usuario);
        }

        return "home";
    }

    @GetMapping("/registro")
    private String verFormularioRegistro(Model model, Authentication auth, HttpSession session) {
        Empleado empleado = new Empleado();
        List<Empresa> empresa = empresaService.listarTodos();
        Empresa emp = empresa.get(0);
        empleado.setEmpresa(emp);
        model.addAttribute("titulo", "Formulario: Nuevo Empleado");
        /* model.addAttribute("nombreEmpresa", id_empresa); */
        model.addAttribute("persona", empleado);

        try {
            String username = auth.getName();

            if (session.getAttribute("usuario") == null) {
                Empleado usuario = empleadoService.findByUsuario(username);
                usuario.setContrasenya(null);
                session.setAttribute("usuario", usuario);
            }
        } catch (Exception e) {
            
        }

        return "views/registro";
    }

    @PostMapping("/registro")
    public String registrarEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult result, Model model,
            RedirectAttributes attribute) {

        if (result.hasErrors()) {
            System.out.println("Errores en los campos.");
            return "views/registro";
        } else {
            try {
                empleadoService.crearPersona(empleado);
            } catch (Exception e) {
                model.addAttribute("registroErrorMessage", e.getMessage());
                model.addAttribute("persona", empleado);
                System.out.println("Errores en los campos.");
                return "views/registro";
            }
        }

        model.addAttribute("persona", empleadoService.registrar(empleado));
        System.out.println("Empleado guardado con exito!");
        attribute.addFlashAttribute("success", "Empleado guardado con exito!");
        return "redirect:/";

    }

    @GetMapping("/servicios")
    public String verServicios(Model model) {
        List<Servicio> listadoServicios = servicioService.listarTodos();
        model.addAttribute("servicios", listadoServicios);
        model.addAttribute("servicio", new Servicio());

        return "views/servicios";
    }

    @PostMapping("/editar_servicio")
    public String editar(Model model, @ModelAttribute Servicio servicio, 
            @RequestParam("formFileMultiple") MultipartFile multipartFile) throws FileNotFoundException, IOException {
                
        System.out.println("atributo servicio enviado");
        Servicio serv = servicioService.buscarPorId(servicio.getId());
        serv.setNombre(servicio.getNombre());
        serv.setPrecio(servicio.getPrecio());

        if (multipartFile != null && !multipartFile.isEmpty()) {
            //serv.setImagen("/images/" + servicio.getImagen());
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "lopeez10",
                "api_key", "138258334466193",
                "api_secret", "lyqyXD1mwL2cVLTmtFB-2pGWNFs"));

            File file = new File("imagen");
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(multipartFile.getBytes());
            }

            JSONObject img = new JSONObject(cloudinary.uploader().upload(file, ObjectUtils.emptyMap()));

            String imagenUrl = img.getString("url");

            serv.setImagen(imagenUrl);
        }
        servicioService.guardar(serv);
        return "redirect:/servicios";
    }

    @GetMapping("/eliminar_servicio/{id}")
    public String eliminarServicio(@PathVariable("id") Long idServicio, RedirectAttributes attribute) {
        servicioService.eliminar(idServicio);
        attribute.addFlashAttribute("success", "Servicio eliminado con éxito.");

        return "redirect:/servicios";

    }

    // Entiendo que el boton facturas sera para ver todas las facturas de un mismo
    // negocio, pero de momento por conveniencia lo voy as usar para probar mi ut
    /*
     * @GetMapping("/factura")
     * public String verfacturas(Model model) {
     * //List<Servicio> listadoServicios = servicioService.listarTodos();
     * // model.addAttribute("servicios", listadoServicios);
     * 
     * return "views/factura";
     * }
     */

    @PostMapping("/añadir_servicio")
    public String añadirServicio(Model model, @ModelAttribute Servicio servicio,
            @RequestParam("formFileMultiple") MultipartFile multipartFile) throws IOException {
        servicio.setEmpresa(empresaService.listarTodos().get(0));

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "lopeez10",
                "api_key", "138258334466193",
                "api_secret", "lyqyXD1mwL2cVLTmtFB-2pGWNFs"));

        File file = new File("imagen");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }

        JSONObject img = new JSONObject(cloudinary.uploader().upload(file, ObjectUtils.emptyMap()));

        String imagenUrl = img.getString("url");

        servicio.setImagen(imagenUrl);
        servicioService.guardar(servicio);

        return "redirect:/servicios";
    }

}
