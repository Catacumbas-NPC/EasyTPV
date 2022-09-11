package com.apppin.proyectopin.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombre;

    private String usuario;

    private String contrasenya;
    @Transient
	private String confirmContrasenya;

    @ManyToOne
	@JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }
    
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmContrasenya() {
        return confirmContrasenya;
    }

    public void setConfirmContrasenya(String confirmContrasenya) {
        this.confirmContrasenya = confirmContrasenya;
    }

    @Override
    public String toString() {
        return "Empleado [confirmContrasenya=" + confirmContrasenya + ", contrasenya=" + contrasenya + ", dni=" + dni
                + ", empresa=" + empresa + ", id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + "]";
    }

    


}