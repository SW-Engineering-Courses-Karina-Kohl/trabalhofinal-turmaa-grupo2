package br.edu.ufrgs.service;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

/**
 * Motor de calculo de cashback do sistema de fidelidade.
 * Responsavel por aplicar as regras de negocio: cashback por
 * categoria de produto e bonus progressivo por nivel de fidelidade (Tier).
 */

public class CalculadoraCashback {
    /**
     * Calcula o cashback de uma venda com base na categoria do produto.
     * Eletronicos retornam 5%, Vestuario 3% e demais categorias 1%.
     *
     * @param v a venda a ser avaliada
     * @return o valor de cashback correspondente a categoria da venda
     */

    public double calcularCashbackCategoria(Venda v) {
        double valor = v.getValor();
        String cat = v.getCategoria().getCategoria().toLowerCase();  //

        if (cat.contains("eletronicos")) {
            return valor * 0.05;
        } else if (cat.contains("vestuario")) {
            return valor * 0.03;
        } else {
            return valor * 0.01;
        }
    }

     /**
     * Calcula o bonus adicional de acordo com o Tier do cliente,
     * definido pelo valor total de suas compras.
     * Platinum (acima de R$ 5.000): R$ 50 fixo mais 2% do total.
     * Gold (acima de R$ 1.000): R$ 50 fixo.
     * Normal (ate R$ 1.000): sem bonus.
     *
     * @param c o cliente cujo bonus sera calculado
     * @return o valor do bonus correspondente ao Tier do cliente
     */

    public double calcularBonusTier(Cliente c) {
        double gastoTotal = c.getValorTotalVendas();
        
        if (gastoTotal > 5000.00) {
            return 50.00 + (gastoTotal * 0.02);
        } else if (gastoTotal > 1000.00) {
            return 50.00;
        }
        return 0.00;
    }

    /**
     * Calcula o cashback final do cliente, somando o cashback de
     * todas as suas vendas (por categoria) com o bonus do Tier.
     *
     * @param c o cliente cujo cashback total sera calculado
     * @return o valor total de cashback acumulado pelo cliente
     */

    public double calcularCashbackFinal(Cliente c) {
        double totalCategorias = 0;
        for (Venda v : c.getVendas()) {
            totalCategorias += calcularCashbackCategoria(v);
        }
        return totalCategorias + calcularBonusTier(c);
    }
}