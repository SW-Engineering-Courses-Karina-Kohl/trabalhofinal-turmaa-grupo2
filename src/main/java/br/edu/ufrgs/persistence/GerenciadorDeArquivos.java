package br.edu.ufrgs.persistence;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;
import java.util.List;

public class GerenciadorDeArquivos {
    
    private String separador;


    public GerenciadorDeArquivos(String separador) {
        this.separador = separador;
    }

    public List<Venda> lerVendas(String caminhoArquivo) {
        // Lógica para ler o CSV de entrada
        return null; 
    }

    public void exportarRelatorio(List<Cliente> clientes, String caminhoSaida) {
        // Lógica para gravar o CSV de saída consolidado 
    }
}