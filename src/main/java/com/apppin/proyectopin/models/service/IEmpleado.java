package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Empleado;

public interface IEmpleado {
    public List<Empleado> listarTodos();
	public void guardar(Empleado empleado);
	public Empleado buscarPorId(Long id);
	public void eliminar(Long id);

	public Empleado findByUsuario(String usuario);
	public Empleado crearPersona(Empleado empleado) throws Exception;
	public Empleado registrar(Empleado empleado);
}
