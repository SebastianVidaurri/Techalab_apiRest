package com.TechLab.spring.controller;

import com.TechLab.spring.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoControler {
    private final PedidoService service;

    public PedidoControler(PedidoService service){
        this.service = service;
    }


}
