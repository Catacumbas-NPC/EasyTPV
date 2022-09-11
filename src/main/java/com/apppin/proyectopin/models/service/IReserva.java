package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Reserva;

public interface IReserva {
    public List<Reserva> listarTodos();
	public void guardar(Reserva reserva);
	public Reserva buscarPorId(Long id);
	public void eliminar(Long id);
	public List<Reserva> listarReservasEmpresa(Long idEmpresa);
	public List<Reserva> reservasOrdenadas(List<Reserva> listaReservas);
	public List<Reserva> reservasHoy();
	public Long obtenerIdUltimaReservaHecha();
}
