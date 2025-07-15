package com.TechLab.spring.repository;

import com.TechLab.spring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName (String name); //que exista el metodo Buscar por nombre

}
