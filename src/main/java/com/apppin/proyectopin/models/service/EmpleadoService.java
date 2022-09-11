package com.apppin.proyectopin.models.service;

import java.util.List;

import com.apppin.proyectopin.models.entity.Empleado;
import com.apppin.proyectopin.models.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class EmpleadoService implements IEmpleado {

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
	private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarTodos() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public void guardar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado findByUsuario(String usuario) {
        return empleadoRepository.findByUsuario(usuario);
    }

    @Override
    public Empleado crearPersona(Empleado persona) throws Exception {
        if (usuarioDisponibleEnBD(persona) && contraseñaIgual(persona)) {
			persona = empleadoRepository.save(persona);
		}
		return persona;
    }

    private boolean usuarioDisponibleEnBD(Empleado persona) throws Exception {
		String usuario = persona.getUsuario();
		Empleado p = empleadoRepository.findByUsuario(usuario);
		if(p != null) {
			throw new Exception("Nombre de usuario en uso!!");
		}
		return true;
	}

    private boolean contraseñaIgual(Empleado persona) throws Exception {
		
		if (persona.getConfirmContrasenya() == null || persona.getConfirmContrasenya().isEmpty()) {
			throw new Exception("Confirm Password es obligatorio");
		}
		if(!persona.getContrasenya().equals(persona.getConfirmContrasenya())) {
			throw new Exception("Contraseña y Confirmar Contraseña no son iguales");
		}
		return true;
	}

    @Override
    public Empleado registrar(Empleado empleado) {
        empleado.setContrasenya(passwordEncoder.encode(empleado.getContrasenya()));
        return empleadoRepository.save(empleado);
    }
    
}
