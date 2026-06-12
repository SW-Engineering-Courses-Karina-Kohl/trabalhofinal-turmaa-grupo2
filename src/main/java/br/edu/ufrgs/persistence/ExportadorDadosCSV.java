package br.edu.ufrgs.persistence;
import br.edu.ufrgs.model.Cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * Classe responsavel por exportar a lista de clientes processados
 * para um arquivo CSV de saida, no formato do relatorio de fidelidade.
 */
public class ExportadorDadosCSV {
   

    private static final Logger LOGGER = Logger.getLogger(ExportadorDadosCSV.class.getName());

     /**
     * Metodo reponsavel por exportar uma lista de clientes a um arquivo csv.
     * @param clientes lista de clientes
     * @param caminho e o caminho para onde sera exportado o arquivo
     *
     * @author Luis Antonio
     */
    public void exportaRelatorio(List<Cliente> clientes, String caminho){
        String formato;

        LOGGER.info("Iniciando exportação CSV para: " + caminho);

        if (clientes == null || clientes.isEmpty()) {
            LOGGER.warning("Lista de clientes vazia ou nula. Nada será exportado.");
            return;
        }

        try(FileWriter writer = new FileWriter(caminho) ) {

            for (Cliente cliente : clientes) {
                formato = formatador(cliente);
                writer.write(formato + System.lineSeparator());
            }
            LOGGER.info("Exportação concluída com sucesso.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro na exportação do arquivo CSV: " + caminho, e);
        }

    }

    /**
     * Metodo responsavel por formatar a string a ser exportada.
     * @param cliente um cliente que sera exportado
     * @return formato uma string no formato correto para a exportação
     *
     * @author Luis Antonio
     */
        private String formatador(Cliente cliente){
        String valorVendasFormatado = String.format(java.util.Locale.US, "%.2f", cliente.getValorTotalVendas());
        String cashbackFormatado = String.format(java.util.Locale.US, "%.2f", cliente.getCashBackAcumulado());
        String formato = cliente.getIdCliente() + "," + cliente.getNome() + ","
            + valorVendasFormatado + ","  + cashbackFormatado + "," + cliente.getNomeTier();

        return formato;
     }


}
