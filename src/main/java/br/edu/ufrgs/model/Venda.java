package br.edu.ufrgs.model;

public class Venda {
    private String idVenda;
    private double valor;
    private CategoriaProduto categoria;

    public Venda(String idVenda, double valor, String categoria) {
        this.idVenda = idVenda;
        this.valor = valor;
        this.categoria = new CategoriaProduto(categoria);
    }

    public String getIdVenda() {
        return idVenda;
    }

    public double getValor() {
        return valor;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}