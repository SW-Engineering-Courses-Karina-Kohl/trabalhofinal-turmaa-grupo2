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
     * pertencentes ao mesmo cliente. Linhas mal formatadas
     * (colunas faltando, valores invalidos ou vazias) sao ignoradas.
     */
    public Collection<Cliente> consolidar (List<String> linhasCSV){
        Map<Integer, Cliente> clientesMap = new HashMap<>();

        for (String linha : linhasCSV){
            if (linha == null || linha.trim().isEmpty()){
                continue;
            }

            String[] dados = linha.split(",");

            if (dados.length < 5){
                continue;
            }

            try {
                String idVenda = dados[0];
                int clienteId = Integer.parseInt(dados[1].trim());
                String nomeCliente = dados[2];
                double valor = Double.parseDouble(dados[3].trim());
                String categoria = dados[4];

                if (!clientesMap.containsKey(clienteId)){
                    Cliente cliente = new Cliente(clienteId, nomeCliente);
                    clientesMap.put(clienteId, cliente);
                }

                Venda venda = new Venda(idVenda, valor, categoria);
                clientesMap.get(clienteId).adicionarVenda(venda);

            } catch (NumberFormatException e) {
                continue;
            }
        }

        return clientesMap.values();
    }
}