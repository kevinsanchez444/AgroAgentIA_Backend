package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> login(String correo, String contrasena) {

        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        if (usuario.isPresent()) {

            if (usuario.get().getContrasena().equals(contrasena)) {
                return usuario;
            }

        }

        return Optional.empty();

    }
    public String registrar(Usuario usuario){

    if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
        return "El correo ya está registrado";
    }

    usuario.setRol("ADMIN");

    usuarioRepository.save(usuario);

    return "Usuario registrado correctamente";

}

}