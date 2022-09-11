package com.apppin.proyectopin.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private String nombreCliente;
    
    @Pattern(regexp="[0-9]{9}")
    private String numeroCliente;
    private String hora;

    @ManyToOne
	@JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    @ManyToOne
	@JoinColumn(name = "idServicio")
    private Servicio servicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }


    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Reserva [empresa=" + empresa + ", fecha=" + fecha + ", hora=" + hora + ", id=" + id + ", nombreCliente="
                + nombreCliente + ", numeroCliente=" + numeroCliente + ", servicio=" + servicio + "]";
    }


}
