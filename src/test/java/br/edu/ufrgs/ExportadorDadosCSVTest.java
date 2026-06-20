package br.edu.ufrgs;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;
import br.edu.ufrgs.persistence.ExportadorDadosCSV;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExportadorDadosCSVTest {

    @Test
    public void deveExportarClientesParaArquivoCSVCorretamente(@TempDir Path tempDir) throws Exception {
        Path arquivoDestino = tempDir.resolve("exportacao.csv");

        // Preparação dos dados fictícios
        Cliente cliente1 = new Cliente(10, "Alice");
        cliente1.adicionarVenda(new Venda("V1", 1500.50, "Eletronicos"));
        cliente1.setCashBackAcumulado(120.0);

        Cliente cliente2 = new Cliente(20, "Bob");
        cliente2.adicionarVenda(new Venda("V2", 200.00, "Outros"));
        cliente2.setCashBackAcumulado(2.0);

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        // Execução da Exportação
        ExportadorDadosCSV exportador = new ExportadorDadosCSV();
        exportador.exportaRelatorio(clientes, arquivoDestino.toString());

        // Validação: o ficheiro tem de existir
        assertTrue(Files.exists(arquivoDestino));

        // Validação: o conteúdo tem de estar com as casas decimais corretas (formato US com ponto)
        List<String> linhasExportadas = Files.readAllLines(arquivoDestino);
        assertEquals(2, linhasExportadas.size());
        
        // O formato esperado: id,nome,valorTotal,cashback,tier
        assertEquals("10,Alice,1500.50,120.00,NORMAL", linhasExportadas.get(0));
        assertEquals("20,Bob,200.00,2.00,NORMAL", linhasExportadas.get(1));
    }

    @Test
    public void naoDeveLancarExcecaoAoExportarListaVaziaOuNula(@TempDir Path tempDir) {
        Path arquivoDestino = tempDir.resolve("vazio.csv");
        ExportadorDadosCSV exportador = new ExportadorDadosCSV();
        
        // Testa com nulo e lista vazia
        exportador.exportaRelatorio(null, arquivoDestino.toString());
        exportador.exportaRelatorio(Arrays.asList(), arquivoDestino.toString());
        
        // O ficheiro não deve sequer ser criado caso não existam clientes
        assertTrue(Files.notExists(arquivoDestino), "O ficheiro não deve ser criado para listas vazias.");
    }
}