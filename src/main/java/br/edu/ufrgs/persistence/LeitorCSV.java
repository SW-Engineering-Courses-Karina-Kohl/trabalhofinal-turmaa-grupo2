package br.edu.ufrgs.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {

    public List<String> lerArquivo(String caminho){
        BufferedReader leitor = null;
        List<String> lista = new ArrayList<>(); //cria uma lista de strings

        try{
            FileReader leitorarquivo = new FileReader(caminho);
            leitor = new BufferedReader(leitorarquivo);

            String linha = leitor.readLine();
            linha = leitor.readLine();

            while(linha != null){
                linha = leitor.readLine();
                lista.add(linha);
            }
            
        }catch(Exception erro) {
            System.out.println("Erro na abertura do arquivo");
            System.out.println(erro.getMessage());
        }finally {

            try{
                if(leitor != null){
                    leitor.close();
                }
            }catch (Exception e){
                System.out.println("Erro no fechamento do arquivo");
            }

        }
        return lista;
    }

}
