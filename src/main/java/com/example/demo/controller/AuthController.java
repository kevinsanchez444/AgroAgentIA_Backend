package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginRequest;
import com.example.demo.modelo.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {

        Optional<Usuario> usuario = usuarioService.login(
                request.getCorreo(),
                request.getContrasena());

        if (usuario.isPresent()) {
            return usuario.get();
        }

        return "Correo o contraseña incorrectos";
    }
}
