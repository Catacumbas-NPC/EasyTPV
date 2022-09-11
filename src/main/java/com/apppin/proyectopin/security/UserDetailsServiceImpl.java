package com.apppin.proyectopin.security;

import com.apppin.proyectopin.models.entity.Empleado;
import com.apppin.proyectopin.models.repository.EmpleadoRepository;
import com.apppin.proyectopin.models.service.IEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado usuario = empleadoRepository.findByUsuario(username);
        UserBuilder builder = null;

        if(usuario != null) {
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(usuario.getContrasenya());
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return builder.build();
    }
    
}
