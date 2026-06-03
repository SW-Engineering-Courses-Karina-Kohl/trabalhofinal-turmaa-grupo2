package br.edu.ufrgs.persistence;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

import java.util.ArrayList;
import java.util.List;
import  br.edu.ufrgs.persistence.LeitorCSV;


public class GerenciadorDeArquivos {
    
    private String separador;


    public GerenciadorDeArquivos(String separador) {
        this.separador = separador;
    }

    public List<Venda> lerVendas(String caminhoArquivo) {
        // Lógica para ler o CSV de entrada
        LeitorCSV leitor = new LeitorCSV();
        List<String> listaStr = new ArrayList<>();
        listaStr = leitor.lerArquivo(caminhoArquivo);

        //Todo:
        //implementação da manutenção das strings

        return null;
    }

    public void exportarRelatorio(List<Cliente> clientes, String caminhoSaida) {
        // Lógica para gravar o CSV de saída consolidado
        ExportadorDadosCSV exportador = new ExportadorDadosCSV();
        exportador.ExportaListaCliente(clientes,caminhoSaida);
    }
}