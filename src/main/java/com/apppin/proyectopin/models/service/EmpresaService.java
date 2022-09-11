package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService implements IEmpresa {

    @Autowired
	private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> listarTodos() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    @Override
    public void guardar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        empresaRepository.deleteById(id);
    }
    
}
