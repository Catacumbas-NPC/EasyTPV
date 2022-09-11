package com.apppin.proyectopin.models.repository;

import org.springframework.stereotype.Repository;

import com.apppin.proyectopin.models.entity.Reserva;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    
}
