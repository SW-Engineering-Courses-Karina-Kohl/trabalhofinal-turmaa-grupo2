package br.edu.ufrgs.model;

/**
 * Representa um cliente do nivel de fidelidade Gold,
 * alcancado quando o total de compras ultrapassa R$ 1.000.
 */

public class ClienteGold extends Cliente {

    /**
     * Cria um cliente Gold com o id e o nome informados.
     *
     * @param idCliente o identificador unico do cliente
     * @param nome o nome do cliente
     */

    public ClienteGold(int idCliente, String nome) {
        super(idCliente, nome);
    }

    /**
     * Retorna o nome do Tier deste cliente.
     *
     * @return a string "GOLD"
     */

    @Override
    public String getNomeTier() {
        return "GOLD";
    }

}
