package br.edu.ufrgs;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.persistence.GerenciadorDeArquivos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GerenciadorDeArquivosTest {
    @Test
    public void deveLerEGerarColecaoDeClientesAgrupada(@TempDir Path tempDir) throws Exception {
        Path arquivoOrigem = tempDir.resolve("vendas_mock.csv");
        
        // Substituído List.of por Arrays.asList
        Files.write(arquivoOrigem, Arrays.asList(
            "id_venda,cliente_id,nome_cliente,valor,categoria",
            "V01,99,Zeca,500.00,Eletronicos",
            "V02,99,Zeca,100.00,Vestuario"
        ));

        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
        Collection<Cliente> clientes = gerenciador.lerVendas(arquivoOrigem.toString());

        assertNotNull(clientes);
        assertEquals(1, clientes.size(), "As duas vendas devem ser agrupadas no mesmo cliente");
        
        Cliente zeca = clientes.iterator().next();
        assertEquals(99, zeca.getIdCliente());
        assertEquals(600.00, zeca.getValorTotalVendas(), 0.001);
    }
}