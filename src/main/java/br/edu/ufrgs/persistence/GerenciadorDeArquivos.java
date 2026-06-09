package br.edu.ufrgs.persistence;

import br.edu.ufrgs.model.Cliente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.edu.ufrgs.service.ConsolidadorClientes;

public class GerenciadorDeArquivos {

    private LeitorCSV leitor  = new LeitorCSV();
    private ExportadorDadosCSV exportador = new ExportadorDadosCSV();
    private ConsolidadorClientes consolidador = new ConsolidadorClientes();

    /**
     * Metodo responsavel por ler o arquivo csv de vendas.
     * @param caminhoArquivo é um caminho de onde leremos o arquivo csv de vendas
     * @return retorna um Collection<Cliente>
     *
     * @author Luis Antonio
     */
    public Collection<Cliente> lerVendas(String caminhoArquivo) {

        List<String> listaStrings = new ArrayList<>();
        listaStrings = leitor.lerArquivo(caminhoArquivo);

        return consolidador.consolidar(listaStrings);
    }

    /**
     *Metodo responsavel por criar o relatorio csv e exporta-lo.
     * @param clientes é uma lista de clientes a serem exportados
     * @param caminhoSaida é uma string que diz o caminho para onde sera criado o arquivo que sera exportado
     *
     * @author Luis Antonio
     */
    public void exportarRelatorio(List<Cliente> clientes, String caminhoSaida) {
        exportador.exportaRelatorio(clientes, caminhoSaida);
    }
}