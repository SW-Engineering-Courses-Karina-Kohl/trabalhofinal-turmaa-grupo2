package br.edu.ufrgs.persistence;

<<<<<<< HEAD
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import br.edu.ufrgs.model.Venda;
import com.opencsv.bean.CsvToBeanBuilder;

public class LeitorCSV {
    public List<Venda> lerVendas(InputStream inputStream) throws Exception {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            return new CsvToBeanBuilder<Venda>(reader)
                    .withType(Venda.class)
                    .withSeparator(',') // ou ';' dependendo do seu CSV
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        }
    }
=======
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorCSV {

    /**
     * Método responsável por ler o arquivo CSV.
     *
     * @param caminho caminho do arquivo CSV a ser lido
     * @return lista é uma lista com todas as strings sem formatacao do arquivo retorna
     * lista vazia quando o caminho nao foi encontrado
     *
     * @author Luis Antonio
     */

    private static final Logger LOGGER = Logger.getLogger(LeitorCSV.class.getName());

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

>>>>>>> origin/main
}
