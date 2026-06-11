package br.edu.ufrgs.model;
/**
 * Representa um cliente do nivel de fidelidade Normal,
 * atribuido quando o total de compras nao ultrapassa R$ 1.000.
 */
public class ClienteNormal extends Cliente {

    /**
     * Cria um cliente Normal com o id e o nome informados.
     *
     * @param idCliente o identificador unico do cliente
     * @param nome o nome do cliente
     */

    public ClienteNormal(int idCliente, String nome) {
        super(idCliente, nome);
    }

    /**
     * Retorna o nome do Tier deste cliente.
     *
     * @return a string "NORMAL"
     */

    @Override
    public String getNomeTier() {
        return "NORMAL";
    }

}
