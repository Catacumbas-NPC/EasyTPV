package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.ServicioFacturado;
import com.apppin.proyectopin.models.repository.ServicioFacturadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioFacturadoService implements IServicioFacturado {

    @Autowired
    private ServicioFacturadoRepository servicioFacturadoRepository;

    @Override
    public List<ServicioFacturado> listarTodos() {
        return (List<ServicioFacturado>) servicioFacturadoRepository.findAll();
    }

    @Override
    public void guardar(ServicioFacturado reserva) {
        servicioFacturadoRepository.save(reserva);
    }

    @Override
    public ServicioFacturado buscarPorId(Long id) {
        return servicioFacturadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        servicioFacturadoRepository.deleteById(id);
    }
    
}
