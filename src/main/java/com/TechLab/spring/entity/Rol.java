package com.TechLab.spring.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity //Marca la clase como una entidad JPA
@AllArgsConstructor //genera el constructor
@NoArgsConstructor
@Getter
@Setter

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Indica que idRol es la clave primaria, y se genera incrementalmente de manera automatica.
    private Long idRol;

    @Column(unique = true, nullable = false) //el campo tiene que ser único y adémas obligatorio
    //@NotBlank(message = "El nombre no puede estar vacío") revisar la dependencia
    private String name;
}
