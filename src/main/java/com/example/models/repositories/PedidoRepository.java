package com.example.models.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.models.entities.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    List<Pedido> pedidos = new ArrayList<>();

    public Pedido cadastrar(Pedido pedido) {
        int novoCod = 1;
        if (pedidos.size() > 0) {
            novoCod = pedidos.get(pedidos.size() - 1).getCodigo() + 1;
        }
        if (pedido != null) {
            pedido.setCodigo(novoCod);
            pedido.setData(new Date());
            pedidos.add(pedido);
            return pedido;
        }
        return null;
    }

    public List<Pedido> receberPedidos() {
        if (pedidos.size() > 0) {
            return pedidos;
        }
        return null;
    }

    public Pedido receberPorCodigo(int cod) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (pedido.getCodigo() == cod) {
                return pedido;
            }
        }
        return null;
    }

    public Pedido atualizarPedido(Pedido pedido) {
        Pedido ped = this.receberPorCodigo(pedido.getCodigo());
        if (ped != null) {
            ped.setValor(pedido.getValor());
            ped.setDescricao(pedido.getDescricao());
            ped.setCliente(pedido.getCliente());

            for (int i = 0; i < pedidos.size(); i++) {
                Pedido pedidoaux = pedidos.get(i);
                if (pedidoaux.getCodigo() == ped.getCodigo()) {
                    pedidos.set(i, ped);
                }
            }
        }
        return ped;
    }

    public Pedido deletarPedido(int codigo) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoaux = pedidos.get(i);
            if (pedidoaux.getCodigo() == codigo) {
                pedidos.remove(i);
                return pedidoaux;
            }
        }
        return null;
    }
}
