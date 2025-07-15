package com.TechLab.spring.service;
/*
* Esta clase brindará los servicios correspondientes para la autentificacion de un usuario a nuestra API
*
*
* */
import com.TechLab.spring.entity.Rol;
import com.TechLab.spring.entity.Usuario;
import com.TechLab.spring.repository.RolRepository;
import com.TechLab.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    //declaramos un métod0 que utiliza el repositorio de usuario (que es para conectarse a la db)
    private UserRepository userRepo;

    @Autowired
    //declaramos el metodo que utilizara el repositorio de rol
    private RolRepository rolRepo;

    @Autowired
    //declaramos el metodo que viene de SecurityConfig para hacer un encoder del password
    private PasswordEncoder passwordEncoder;

    //Creamos el metodo para que un usuario se acredite
    public Usuario registerUser(String userName, String rawPassword){
        Rol userRol = rolRepo.findByName("ROL_USER").orElseGet(()->rolRepo.save(new Rol(null, "ROL_USER")));

        //creamos el usuario y lo llenamos con los datos
        Usuario user = new Usuario();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRol(List.of(userRol));

        return userRepo.save(user);
    }

    public Optional<Usuario> authenticate(String userName, String password){
        Optional<Usuario> userOpt = userRepo.findByUserName(userName);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())){
            return userOpt;
        }
        return Optional.empty();
    }

}
