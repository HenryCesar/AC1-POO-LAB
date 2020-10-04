package com.example.models.entities;

import java.util.Date;

public class Pedido {
    private int codigo;
    private double valor;
    private String descricao;
    private String cliente;
    private Date data;

    public String getCliente() {
        return cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "codigo: " + this.codigo + "\nvalor: " + this.valor + "\ndescricao: " + this.descricao + "\ncliente: "
                + this.cliente + "\ndata: " + this.data;
    }
}
