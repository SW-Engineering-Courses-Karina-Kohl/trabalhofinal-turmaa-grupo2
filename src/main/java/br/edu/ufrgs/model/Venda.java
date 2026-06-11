package br.edu.ufrgs.model;


/**
 * Representa uma venda individual realizada por um cliente,
 * contendo o identificador, o valor e a categoria do produto.
 */

public class Venda {
    private String idVenda;
    private double valor;
    private CategoriaProduto categoria;

    /**
     * Cria uma venda com os dados informados.
     *
     * @param idVenda o identificador da venda
     * @param valor o valor monetario da venda
     * @param categoria o nome da categoria do produto vendido
     */

    public Venda(String idVenda, double valor, String categoria) {
        this.idVenda = idVenda;
        this.valor = valor;
        this.categoria = new CategoriaProduto(categoria);
    }

    /**
     * @return o identificador da venda
     */

    public String getIdVenda() {
        return idVenda;
    }

    /**
     * @return o valor monetario da venda
     */

    public double getValor() {
        return valor;
    }

    /**
     * @return a categoria do produto vendido
     */

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}