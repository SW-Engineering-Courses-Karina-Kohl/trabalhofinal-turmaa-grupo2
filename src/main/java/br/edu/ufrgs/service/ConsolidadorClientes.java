package br.edu.ufrgs.service;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsolidadorClientes{

    /**
     * Recebe as linhas lidas do CSV e agrupa as vendas
     * pertencentes ao mesmo cliente.
     */
    public Collection<Cliente> consolidar (List<String> linhasCSV){
        //Estrutura utilizada para armazenar os clientes
        //A chave é o ID do cliente e o valor é o objeto Cliente correspondente
        Map<Integer, Cliente> clientesMap = new HashMap<>();

        //Percorre todas as linhas do CSV
        for (String linha : linhasCSV){
            String[] dados = linha.split(",");

            //Extrai os dados de cada coluna
            String idVenda = dados[0];
            int clienteId = Integer.parseInt(dados[1]);
            String nomeCliente = dados[2];
            double valor = Double.parseDouble(dados[3]);
            String categoria = dados[4];

            //Verifica se o cliente já existe no mapa
            if (!clientesMap.containsKey(clienteId)){

                //Se não existir, cria um novo cliente
                Cliente cliente = new Cliente (
                    clienteId,
                    nomeCliente
                );
                clientesMap.put(clienteId, cliente);
            }

            //Cria o objeto Venda com dados da linha
            Venda venda = new Venda (
                idVenda,
                valor,
                categoria
            );

            //Recupera o cliente correspondente do mapa e adiciona a venda 
            clientesMap.get(clienteId).adicionarVenda(venda);
        }

        //Retorna apenas os clientes consolidados
        return clientesMap.values();

    }
}