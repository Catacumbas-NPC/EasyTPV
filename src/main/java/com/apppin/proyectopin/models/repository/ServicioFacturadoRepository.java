package com.apppin.proyectopin.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apppin.proyectopin.models.entity.ServicioFacturado;

@Repository
public interface ServicioFacturadoRepository extends CrudRepository<ServicioFacturado, Long> {
    
}
