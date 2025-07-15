package com.TechLab.spring.service;

import com.TechLab.spring.entity.Pedido;
import com.TechLab.spring.entity.Producto;
import com.TechLab.spring.entity.Usuario;
import com.TechLab.spring.repository.PedidoRepository;
import com.TechLab.spring.repository.ProductoRepository;
import com.TechLab.spring.repository.UserRepository;
import com.TechLab.spring.service.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository repo;
    private final UserRepository usuarioRepo;
    private final ProductoRepository productoRepo;

    @Autowired
    public PedidoService(PedidoRepository repo, UserRepository usuarioRepo, ProductoRepository productoRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        // Busco el usuario en base a idUsuario que viene en el pedido
        Long usuarioId = pedido.getUsuario().getIdUsuario();
        Usuario usuarioPersistido = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + usuarioId));

        // Seteo el usuario persistido
        pedido.setUsuario(usuarioPersistido);

        // Para cada producto del pedido busco el producto persistido y lo agrego a una lista nueva
        List<Producto> productosPersistidos = new ArrayList<>();
        for (Producto p : pedido.getProductos()) {
            Producto productoPersistido = productoRepo.findById(p.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + p.getIdProducto()));
            productosPersistidos.add(productoPersistido);
        }

        // Seteo la lista con productos persistidos
        pedido.setProductos(productosPersistidos);

        // Estado por defecto
        pedido.setEstado("EN_PROCESO");

        // Finalmente guardo el pedido
        return repo.save(pedido);
    }

    // Implementá listarPedidos y buscarPedido según tu interfaz
    @Override
    public List<Pedido> listarPedidos() {
        return repo.findAll();
    }

    @Override
    public Pedido buscarPedido(Long id) {
        return repo.findById(id).orElse(null);
    }
}