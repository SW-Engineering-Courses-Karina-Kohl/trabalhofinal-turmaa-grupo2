package br.edu.ufrgs.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorCSV {

    public String lerArquivo(String caminho, String separador){
        BufferedReader leitor = null;
        String resultado = "";

        try{
            FileReader leitorarquivo = new FileReader(caminho);
            leitor = new BufferedReader(leitorarquivo);

            String linha = leitor.readLine();

            while(linha != null){
                System.out.println(linha);
                resultado = resultado + linha + "\n";
                linha = leitor.readLine();
                System.out.println(resultado);
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
        return resultado;
    }




}
