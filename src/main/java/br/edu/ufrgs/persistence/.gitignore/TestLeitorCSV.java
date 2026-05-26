package br.edu.ufrgs.persistence;

import br.edu.ufrgs.persistence.LeitorCSV;
import java.util.List;
import java.util.ArrayList;


public class TestLeitorCSV {

    public static void main(String[] args) {
        LeitorCSV li = new LeitorCSV();
        List<String> listaComprastotais = new ArrayList<>();

       listaComprastotais = li.lerArquivo("/home/luis-ant-nio/projetosProg/trabalhofinal-turmaa-grupo2/src/main/java/br/edu/ufrgs/persistence/CSVTEST.csv");
        System.out.println(listaComprastotais);
    }
}
