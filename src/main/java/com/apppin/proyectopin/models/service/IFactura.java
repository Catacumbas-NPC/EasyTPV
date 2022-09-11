package com.apppin.proyectopin.models.service;


import java.util.Date;
import java.util.List;

import com.apppin.proyectopin.models.entity.Factura;

public interface IFactura {
    public List<Factura> listarTodos();
	public void guardar(Factura factura);
	public Factura buscarPorId(Long id);
	public void eliminar(Long id);
	public List<Factura> listarFacturasEmpresa(Long idEmpresa);
	public List<Factura> facturasOrdenadas(List<Factura> listaFacturas);
	public List<Factura> facturasDesdeHasta(Date desde, Date hasta);
}
