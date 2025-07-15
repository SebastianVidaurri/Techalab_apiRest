package com.TechLab.spring.service;

import com.TechLab.spring.entity.Pedido;
import com.TechLab.spring.repository.PedidoRepository;
import com.TechLab.spring.service.interfaces.IPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// hay que utilizar implements para utilizar la interface IPedidoService
public class PedidoService implements IPedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        pedido.setEstado("EN_PROCESO");
        return repo.save(pedido);
    }
    ///
    @Override
    public List<Pedido> listarPedidos() {
        return repo.findAll();
    }

    @Override
    public Pedido buscarPedido(Long id) {
        Optional<Pedido> pedidoOpt = repo.findById(id);
        return pedidoOpt.orElse(null); // o lanzar excepción si querés
    }
}
