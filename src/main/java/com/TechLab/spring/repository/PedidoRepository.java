package com.TechLab.spring.repository;

import com.TechLab.spring.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { //Pedido es la entidad y Long será la clave primaria
}
