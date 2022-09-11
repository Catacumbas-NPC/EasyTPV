package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.ServicioFacturado;

public interface IServicioFacturado {
    public List<ServicioFacturado> listarTodos();
	public void guardar(ServicioFacturado reserva);
	public ServicioFacturado buscarPorId(Long id);
	public void eliminar(Long id);
}
