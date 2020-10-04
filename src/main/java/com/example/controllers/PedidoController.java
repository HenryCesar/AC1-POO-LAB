package com.example.controllers;

import java.net.URI;
import java.util.List;

import com.example.models.entities.Pedido;
import com.example.models.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> receberPedidos() {
        //System.out.println("a");
        List<Pedido> pedidos = pedidoRepository.receberPedidos();
        //System.out.println("b");
        
        return (pedidos != null && pedidos.size() > 0) ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> receberPorCodigo(@PathVariable int codigo) {
        Pedido pedido = pedidoRepository.receberPorCodigo(codigo);
        return (pedido != null) ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido) {
        Pedido aux = pedidoRepository.cadastrar(pedido);
        URI uri = URI.create("http://localhost:3000/pedidos/" + aux.getCodigo());
        return (pedido != null) ? ResponseEntity.created(uri).body(aux) : ResponseEntity.badRequest().build();

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> atualizarPedido(@RequestBody Pedido pedido, @PathVariable int codigo) {
        if (pedidoRepository.receberPorCodigo(codigo) != null) {
            pedido.setCodigo(codigo);
            Pedido auxPedido = pedidoRepository.atualizarPedido(pedido);
            return ResponseEntity.ok(auxPedido);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Pedido> deletarPedido(@PathVariable int codigo) {
        return (pedidoRepository.deletarPedido(codigo) != null) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();

    }
}
