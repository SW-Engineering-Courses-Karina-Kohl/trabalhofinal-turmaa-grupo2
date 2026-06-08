package br.edu.ufrgs.persistence;
import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.ClienteGold;
import br.edu.ufrgs.model.ClienteNormal;
import br.edu.ufrgs.model.ClientePlatinum;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorDadosCSV {

    public void ExportaListaCliente(List<Cliente> clientes, String caminho){
        String formato;

        try(FileWriter writer = new FileWriter(caminho) ) {

            for (Cliente cliente : clientes) {
                formato = formatador(cliente);
                writer.write(formato);

            }
        } catch (IOException e) {
            System.out.println("Erro na exportação do arquivo csv");
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
        String formato = cliente.getIdCliente() + "," + cliente.getNome() + ","
                + cliente.getValorTotalVendas() + ","  + cliente.getCashBackAcumulado() + ",";

        if (cliente instanceof ClienteNormal) {
             formato = formato + "NORMAL";
        } else if (cliente instanceof ClienteGold) {
             formato = formato + "GOLD";
        } else if (cliente instanceof ClientePlatinum) {
             formato = formato + "PLATINUM";
        }
        return formato;
     }


}
