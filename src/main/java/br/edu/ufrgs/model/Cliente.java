package br.edu.ufrgs.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente do sistema de fidelidade, agregando suas
 * vendas e o cashback acumulado. Serve como classe base para os
 * diferentes niveis de fidelidade (Normal, Gold e Platinum).
 */

public class Cliente {
    private int idCliente;
    private String nome;
    private List<Venda> vendas = new ArrayList<>();
    private double cashBackAcumulado;

     /**
     * Cria um cliente com o id e o nome informados.
     *
     * @param idCliente o identificador unico do cliente
     * @param nome o nome do cliente
     */

    public Cliente(int idCliente, String nome) {
        this.idCliente = idCliente;
        this.nome = nome;
    }

    /**
     * Adiciona uma venda ao historico de compras do cliente.
     *
     * @param v a venda a ser adicionada
     */

    public void adicionarVenda(Venda v) {
        vendas.add(v);
    }

    /**
     * @return o identificador unico do cliente
     */

    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @return o nome do cliente
     */

    public String getNome() {
        return nome;
    }

    /**
     * @return a lista de vendas realizadas pelo cliente
     */

    public List<Venda> getVendas() {
        return vendas;
    }

    /**
     * Soma o valor de todas as vendas do cliente.
     *
     * @return o valor total comprado pelo cliente
     */

    public double getValorTotalVendas() {
        double total = 0;
        for (Venda v : vendas) {
            total += v.getValor();
        }
        return total;
    }

    /**
     * @return o valor total comprado pelo cliente
     */

    public double getTotalComprado() {
        return getValorTotalVendas();
    }

    /**
     * @return o nome do Tier (nivel de fidelidade) do cliente
     */

    public String getTier() {
        return getNomeTier();
    }

    /**
     * @return o cashback acumulado pelo cliente
     */

    public double getCashBackAcumulado(){return cashBackAcumulado;}

    /**
     * Define o cashback acumulado do cliente.
     *
     * @param cashBackAcumulado o valor de cashback a ser armazenado
     */

    public void setCashBackAcumulado(double cashBackAcumulado){this.cashBackAcumulado = cashBackAcumulado;}

    /**
     * Retorna o nome do Tier do cliente. Na classe base retorna
     * "NORMAL"; as subclasses sobrescrevem com seu proprio nivel.
     *
     * @return o nome do Tier do cliente
     */


    public String getNomeTier() {
        return "NORMAL";
    }
}
