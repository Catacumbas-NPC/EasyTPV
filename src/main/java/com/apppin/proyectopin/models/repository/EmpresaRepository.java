package com.apppin.proyectopin.models.repository;

import com.apppin.proyectopin.models.entity.Empresa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
    
}
