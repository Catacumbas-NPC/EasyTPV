package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Empresa;

public interface IEmpresa {
    public List<Empresa> listarTodos();
	public void guardar(Empresa empresa);
	public Empresa buscarPorId(Long id);
	public void eliminar(Long id);
}
