package br.edu.ufrgs.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela leitura de arquivos CSV.
 *
 * @author Luis Antonio
 */
public class LeitorCSV {
    /**
     * Método responsável por ler o arquivo CSV
     *
     * @param caminho caminho do arquivo CSV a ser lido
     * @return lista é uma lista com todas as strings sem formatacao do arquivo retorna
     * @return lista vazia quando o caminho nao foi encontrado
     *
     * @author Luis Antonio
     */
    public List<String> lerArquivo(String caminho){
        BufferedReader leitor = null;
        List<String> lista = new ArrayList<>(); //cria uma lista de strings

        try{
            FileReader leitorarquivo = new FileReader(caminho);
            leitor = new BufferedReader(leitorarquivo);

            String linha = leitor.readLine();
            linha = leitor.readLine();

            while(linha != null){
                lista.add(linha);
                linha = leitor.readLine();

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
