package com.TechLab.spring.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.TechLab.spring.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration // Como marco la clase como @Configuration ahora puedo utilizar @Bean
public class SecurityConfig {

    //Para la inyeccion de dependencias
    @Bean //El resultado de este metodo es un objeto que spring va a tener que guardar y administrar.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/producto/list", "/producto/find/**", "/producto/buscar").permitAll()
                        //cualquier usuario podría utilizar cualquier de los path definidos
                        .requestMatchers("/producto/**").hasRole("ADMIN")
                        //Solo los que usuarios que tendan ROl ADMIN pueden utilizar este path
                        .requestMatchers("/auth/**").permitAll()
                        //
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // configuración Basica de Http
        return http.build();
    }

    @Bean //encripta el password
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Servicio de autenticación
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> repo.findByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        user.getRol().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .toList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}