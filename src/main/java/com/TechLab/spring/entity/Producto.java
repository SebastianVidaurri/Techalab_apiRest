package com.TechLab.spring.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity //esta clase producto va tener persistencia de datos, le decimos que va a ser una entidad
@AllArgsConstructor //Genera el constructor
@NoArgsConstructor //si quiero generar un constructor sin argumentos
@Getter
@Setter
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String nombre;
    private int precio;
    private String categoria;

}
