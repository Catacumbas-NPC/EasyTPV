package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Servicio;
import com.apppin.proyectopin.models.repository.ServicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService implements IServicio {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> listarTodos() {
        return (List<Servicio>) servicioRepository.findAll();
    }

    @Override
    public void guardar(Servicio reserva) {
        servicioRepository.save(reserva);
    }

    @Override
    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio buscarPorNombre(String nombre) {
        List<Servicio> servicios = listarTodos();
        for (int i = 0; i < servicios.size(); i++) {
            if (servicios.get(i).getNombre().trim().compareTo(nombre.trim()) == 0) {
                return servicios.get(i);
            }
        }
        return null;
    }

    // public String subirImagen(File imagen) throws IOException {
    // Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
    // "cloud_name", "lopeez10",
    // "api_key", "138258334466193",
    // "api_secret", "lyqyXD1mwL2cVLTmtFB-2pGWNFs"));

    // JSONObject img = new JSONObject(cloudinary.uploader().upload(imagen,
    // ObjectUtils.emptyMap()));

    // String imagenUrl = img.getString("url");

    // System.out.println(imagenUrl);

    // return imagenUrl;

    // }

}
