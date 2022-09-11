package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Servicio;

public interface IServicio {
	public List<Servicio> listarTodos();

	public void guardar(Servicio reserva);

	public Servicio buscarPorId(Long id);

	public void eliminar(Long id);

	public Servicio buscarPorNombre(String nombre);
}
