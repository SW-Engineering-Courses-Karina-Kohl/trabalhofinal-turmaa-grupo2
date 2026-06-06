package br.edu.ufrgs.model;

public class ClientePlatinum extends Cliente {

    public ClientePlatinum(int idCliente, String nome) {
        super(idCliente, nome);
    }

    @Override
    public String getNomeTier() {
        return "PLATINUM";
    }

}
