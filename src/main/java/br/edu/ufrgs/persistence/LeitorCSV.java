package br.edu.ufrgs.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe responsavel por ler um arquivo CSV e devolver
 * suas linhas como uma lista de strings, ignorando o cabecalho.
 */
public class LeitorCSV {

  

    private static final Logger LOGGER = Logger.getLogger(LeitorCSV.class.getName());


  /**
     * Método responsável por ler o arquivo CSV.
     *
     * @param caminho caminho do arquivo CSV a ser lido
     * @return lista é uma lista com todas as strings sem formatacao do arquivo retorna
     * lista vazia quando o caminho nao foi encontrado
     *
     * @author Luis Antonio
     */
    public List<String> lerArquivo(String caminho){
        BufferedReader leitor = null;
        List<String> lista = new ArrayList<>(); //cria uma lista de strings

        try{
            LOGGER.info("Tentando abrir arquivo: " + caminho);
            FileReader leitorarquivo = new FileReader(caminho);
            leitor = new BufferedReader(leitorarquivo);

            String linha = leitor.readLine(); //cabeçalho

            linha = leitor.readLine();//primeira linha

            while(linha != null){
                lista.add(linha);
                linha = leitor.readLine();
            }
            LOGGER.info("Arquivo lido com sucesso. Total de linhas: " + lista.size());
        }catch(Exception erro) {
            LOGGER.log(Level.SEVERE, "Erro na abertura/leitura do arquivo: " + caminho, erro);
            System.out.println(erro.getMessage());
        }finally {

            try{
                if(leitor != null){
                    leitor.close();
                    LOGGER.fine("Arquivo fechado com sucesso");
                }
            }catch (Exception e){
                LOGGER.log(Level.WARNING, "Erro no fechamento do arquivo", e);
            }

        }
        return lista;
    }

}
