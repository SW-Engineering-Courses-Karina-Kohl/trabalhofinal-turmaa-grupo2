package br.edu.ufrgs.service;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;
import br.edu.ufrgs.model.ClientePlatinum;
import br.edu.ufrgs.model.ClienteGold; 
import br.edu.ufrgs.model.ClienteNormal;
/**
 * Fabrica responsavel por criar o tipo correto de cliente
 * (Normal, Gold ou Platinum) com base no valor total de compras,
 * aplicando o padrao de projeto Factory.
 */
public class FabricaCliente {


    /**
     * Recebe um cliente original e cria um novo cliente do tipo adequado
     * com base no valor total das vendas do cliente original.
     *
     * @param clienteOriginal o cliente generico com suas vendas
     * @return um cliente do tipo correto (Normal, Gold ou Platinum) com as vendas copiadas
     */
    public Cliente fabricarCliente (Cliente clienteOriginal){
        double valorTotalVendas = clienteOriginal.getValorTotalVendas();
        Cliente cliente;

        if (valorTotalVendas > 5000){
            cliente = new ClientePlatinum(
                clienteOriginal.getIdCliente(), 
                clienteOriginal.getNome());
        } 
        else if (valorTotalVendas > 1000){
            cliente = new ClienteGold(
                clienteOriginal.getIdCliente(), 
                clienteOriginal.getNome());
        }
        else {
            cliente = new ClienteNormal(
                clienteOriginal.getIdCliente(), 
                clienteOriginal.getNome());
        }

        // Copia as vendas do cliente original para o novo cliente criado
        for (Venda v : clienteOriginal.getVendas()){
            cliente.adicionarVenda(v);
        }
        return cliente;
    }
}