package com.apppin.proyectopin.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    @OneToOne
    @JoinColumn(name = "idReserva")
    private Reserva reserva;

    private Boolean metodoPago;
    private Long total;
    

    private Date fechaDeExpedicion;
    private String impuestoAplicado;
    private String servicios;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public Date getFechaDeExpedicion() {
        return fechaDeExpedicion;
    }
    public void setFechaDeExpedicion(Date fechaDeExpedicion) {
        this.fechaDeExpedicion = fechaDeExpedicion;
    }
    public String getImpuestoAplicado() {
        return impuestoAplicado;
    }
    public void setImpuestoAplicado(String impuestoAplicado) {
        this.impuestoAplicado = impuestoAplicado;
    }
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
  
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public Boolean getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(Boolean metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getServicios() {
        return servicios;
    }
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
    @Override
    public String toString() {
        return "Factura [empresa=" + empresa + ", fechaDeExpedicion=" + fechaDeExpedicion + ", id=" + id
                + ", impuestoAplicado=" + impuestoAplicado + ", metodoPago=" + metodoPago + ", reserva=" + reserva
                + ", serviciosFacturados=" + servicios + ", total=" + total + "]";
    }

    
    

}
