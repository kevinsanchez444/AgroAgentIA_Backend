package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(loginDTO.getCorreo());

        if (usuarioOpt.isEmpty()) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setMensaje("Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getContrasena().equals(loginDTO.getContrasena())) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setMensaje("Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        LoginResponseDTO response = new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol(),
                "Login exitoso");

        return ResponseEntity.ok(response);
    }
}
