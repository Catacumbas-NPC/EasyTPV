package com.apppin.proyectopin.models.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nif;

    private String nombre;
    private String propietario;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPropietario() {
        return propietario;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    public LocalTime getHoraApertura() {
        return horaApertura;
    }
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    @Override
    public String toString() {
        return "Empresa [id=" + id + ", nif=" + nif
                + ", nombre=" + nombre + ", propietario= " + propietario + ", horaApertura= " + horaApertura + ", horaCierre= " + horaCierre + "]";
    }

}
