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
                //lida com o cliente
                if (cliente instanceof ClienteNormal) {
                    //faz algo sobre o cliente normal
                    formato = cliente.getIdCliente() + "," + cliente.getNome() + ","
                            + cliente.getValorTotalVendas() + ","  + "cashbackAcumulado" + ","  + "NORMAL";

                    writer.write(formato);

                } else if (cliente instanceof ClienteGold) {
                    //faz algo sobre cliente gold
                    formato = cliente.getIdCliente() + "," + cliente.getNome() + ","
                            + cliente.getValorTotalVendas() + ","  + "cashbackAcumulado" + ","  + "GOLD";

                    writer.write(formato);

                } else if (cliente instanceof ClientePlatinum) {
                    //faz algo sobre cliente platinum
                    formato = cliente.getIdCliente() + "," + cliente.getNome() + ","
                            + cliente.getValorTotalVendas() + ","  + "cashbackAcumulado" + ","  + "PLATINUM";

                    writer.write(formato);
                }

            }
        } catch (IOException e) {
            System.out.println("Erro na exportação do arquivo csv");
        }

    }



}
