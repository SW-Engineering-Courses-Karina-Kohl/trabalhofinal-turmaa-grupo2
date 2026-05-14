package br.edu.ufrgs.model;

public class Venda {
    private String idVenda;
    private double valor;
    private String categoria;

    public Venda(String idVenda, double valor, String categoria) {
        this.idVenda = idVenda;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getIdVenda() {
        return idVenda;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}