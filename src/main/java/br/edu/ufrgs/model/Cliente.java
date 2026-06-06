package br.edu.ufrgs.model;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nome;
    private List<Venda> vendas = new ArrayList<>();

    public Cliente(int idCliente, String nome) {
        this.idCliente = idCliente;
        this.nome = nome;
    }

    public void adicionarVenda(Venda v) {
        vendas.add(v);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public double getValorTotalVendas() {
        double total = 0;
        for (Venda v : vendas) {
            total += v.getValor();
        }
        return total;
    }
<<<<<<< HEAD
=======
    public double getCashBackAcumulado(){return cashBackAcumulado;}

    public String getNomeTier() {
        return "NORMAL";
    }
>>>>>>> parent of 683406f (Revert "Adiciona tipos de cliente por herança")
}