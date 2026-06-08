package br.edu.ufrgs.model;

public class ClienteNormal extends Cliente {

    public ClienteNormal(int idCliente, String nome) {
        super(idCliente, nome);
    }

    @Override
    public String getNomeTier() {
        return "NORMAL";
    }

}
