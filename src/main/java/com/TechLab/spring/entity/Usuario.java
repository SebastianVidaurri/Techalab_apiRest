package com.TechLab.spring.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity //Indica que la clase se mapeará en una tabla en la base de datos
@Table(name = "usuario") //nombre de la tabla
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "rol")

public class Usuario {
    @Id //Es primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera de manera incremental del id
    private Long idUsuario;

    private String userName;
    private String apellido;
    private String password;

    @ManyToMany (fetch = FetchType.EAGER)
    //Cuando cargás un Usuario desde la base de datos, también se carga automáticamente y en la misma consulta el objeto Rol asociado.
    private List<Rol> rol;
}
