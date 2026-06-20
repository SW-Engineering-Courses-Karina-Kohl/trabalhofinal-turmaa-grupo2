package br.edu.ufrgs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import br.edu.ufrgs.persistence.LeitorCSV;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeitorCSVTest {

@Test
    public void deveLerArquivoEIgnorarOCabecalho(@TempDir Path tempDir) throws Exception {
        Path arquivo = tempDir.resolve("teste_leitura.csv");
        
        // Substituído por Arrays.asList
        Files.write(arquivo, Arrays.asList(
            "cabecalho1,cabecalho2,cabecalho3",
            "V001,10,Alice,100.0,Eletronicos",
            "V002,20,Bob,50.0,Outros"
        ));

        LeitorCSV leitor = new LeitorCSV();
        List<String> resultado = leitor.lerArquivo(arquivo.toString());

        assertEquals(2, resultado.size());
        assertEquals("V001,10,Alice,100.0,Eletronicos", resultado.get(0));
        assertEquals("V002,20,Bob,50.0,Outros", resultado.get(1));
    }

    @Test
    public void deveRetornarListaVaziaSeArquivoNaoExistir() {
        LeitorCSV leitor = new LeitorCSV();
        // Um caminho que não existe
        List<String> resultado = leitor.lerArquivo("caminho_inexistente_12345.csv");
        
        // Deve capturar o erro e retornar a lista vazia sem quebrar a aplicação
        assertTrue(resultado.isEmpty());
    }
}