package com.apppin.proyectopin.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apppin.proyectopin.models.entity.Servicio;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Long> {
    
}
