package com.apppin.proyectopin.models.repository;

import org.springframework.stereotype.Repository;

import com.apppin.proyectopin.models.entity.Empleado;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
    public Empleado findByUsuario(String usuario);
}