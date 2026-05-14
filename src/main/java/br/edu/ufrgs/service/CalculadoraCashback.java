package br.edu.ufrgs.service;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

public class CalculadoraCashback {

    public double calcularCashbackCategoria(Venda v) {
        double valor = v.getValor();
        String cat = v.getCategoria().toLowerCase();

        if (cat.contains("eletronicos")) {
            return valor * 0.05;
        } else if (cat.contains("vestuario")) {
            return valor * 0.03;
        } else {
            return valor * 0.01;
        }
    }

    public double calcularBonusTier(Cliente c) {
        double gastoTotal = c.getValorTotalVendas();
        
        if (gastoTotal > 5000.00) {
            return 50.00 + (gastoTotal * 0.02);
        } else if (gastoTotal > 1000.00) {
            return 50.00;
        }
        return 0.00;
    }

    public double calcularCashbackFinal(Cliente c) {
        double totalCategorias = 0;
        for (Venda v : c.getVendas()) {
            totalCategorias += calcularCashbackCategoria(v);
        }
        return totalCategorias + calcularBonusTier(c);
    }
}