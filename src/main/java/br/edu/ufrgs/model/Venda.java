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
<<<<<<< HEAD
    public CategoriaProduto getCategoria() { // Ou String, dependendo de como tipou
    return this.categoria;
=======

    public CategoriaProduto getCategoria() {
        return categoria;
>>>>>>> origin/main
    }
}