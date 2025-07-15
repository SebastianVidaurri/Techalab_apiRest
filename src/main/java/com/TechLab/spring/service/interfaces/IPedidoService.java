package com.TechLab.spring.service.interfaces;
import com.TechLab.spring.entity.Pedido;
import java.util.List;

public interface IPedidoService {
    Pedido crearPedido(Pedido pedido);
    List<Pedido> listarPedidos();
    Pedido buscarPedido(Long id);
}
