package br.edu.ufrgs.model;
/**
 * Representa um cliente do nivel de fidelidade Platinum,
 * alcancado quando o total de compras ultrapassa R$ 5.000.
 */
public class ClientePlatinum extends Cliente {

    /**
     * Cria um cliente Platinum com o id e o nome informados.
     *
     * @param idCliente o identificador unico do cliente
     * @param nome o nome do cliente
     */

    public ClientePlatinum(int idCliente, String nome) {
        super(idCliente, nome);
    }

    /**
     * Retorna o nome do Tier deste cliente.
     *
     * @return a string "PLATINUM"
     */

    @Override
    public String getNomeTier() {
        return "PLATINUM";
    }

}
