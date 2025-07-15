package com.TechLab.spring.controller;
/*
* Esta clase será el controlador de la autentificacion del los usuarios
* */
import com.TechLab.spring.entity.Usuario;
import com.TechLab.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController //así se manejan los controller
@RequestMapping("/auth") //En este endpoint el usuario se logeara
public class AuthController {

    @Autowired
    //llamamos al Servicio de autentificacion de usuarios de AuthService
    private AuthService autohService;

    @PostMapping("/register")
    public String register(@RequestBody Usuario user) {
        autohService.registerUser(user.getUserName(), user.getPassword());
        return "usuario creado con éxito";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user){
        Optional<Usuario> usuario = autohService.authenticate(user.getUserName(), user.getPassword());
        if (usuario.isPresent()){
            boolean isAdmin = usuario.get().getRol().stream().anyMatch(rol-> rol.getName().equals("ROLE_ADMIN"));
            return ResponseEntity.ok(Map.of(
               "userName", usuario.get().getUserName(),
               "isAdmin", isAdmin
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales invalidas");
        }
    }
}