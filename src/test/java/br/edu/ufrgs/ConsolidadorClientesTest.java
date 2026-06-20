package br.edu.ufrgs;

import org.junit.jupiter.api.Test;
import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.service.ConsolidadorClientes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsolidadorClientesTest {

    @Test
    public void deveConsolidarVendasDeUmUnicoCliente() {
        ConsolidadorClientes consolidador = new ConsolidadorClientes();
        
        // Simulação de linhas lidas pelo LeitorCSV
        List<String> linhasCSV = Arrays.asList(
            "V001,10,Alice,100.00,Eletronicos",
            "V002,10,Alice,200.00,Vestuario"
        );

        Collection<Cliente> clientes = consolidador.consolidar(linhasCSV);

        // Deve resultar em apenas 1 cliente com 2 vendas
        assertEquals(1, clientes.size());
        
        Cliente alice = clientes.iterator().next();
        assertEquals(10, alice.getIdCliente());
        assertEquals("Alice", alice.getNome());
        assertEquals(2, alice.getVendas().size());
        assertEquals(300.00, alice.getValorTotalVendas(), 0.001);
    }

    @Test
    public void deveAgruparVendasDeMultiplosClientesSeparadamente() {
        ConsolidadorClientes consolidador = new ConsolidadorClientes();
        List<String> linhasCSV = Arrays.asList(
            "V001,10,Alice,100.00,Eletronicos",
            "V002,20,Bruno,500.00,Vestuario",
            "V003,10,Alice,200.00,Outros"
        );

        Collection<Cliente> clientes = consolidador.consolidar(linhasCSV);

        // Deve resultar em exatamente 2 clientes distintos
        assertEquals(2, clientes.size());
        
        // Localiza o cliente "Bruno" na coleção gerada
        Cliente bruno = clientes.stream()
                .filter(c -> c.getIdCliente() == 20)
                .findFirst()
                .orElse(null);
                
        assertNotNull(bruno);
        assertEquals("Bruno", bruno.getNome());
        assertEquals(500.00, bruno.getValorTotalVendas(), 0.001);
        assertEquals(1, bruno.getVendas().size());
    }

    @Test
    public void deveRetornarColecaoVaziaSeListaDeStringsForVazia() {
        ConsolidadorClientes consolidador = new ConsolidadorClientes();
        List<String> linhasCSV = Arrays.asList();

        Collection<Cliente> clientes = consolidador.consolidar(linhasCSV);

        assertTrue(clientes.isEmpty());
    }

    @Test
    public void deveIgnorarLinhasMalFormatadasEProcessarAsValidas() {
        ConsolidadorClientes consolidador = new ConsolidadorClientes();
        List<String> linhasCSV = Arrays.asList(
            "V001,10,Alice,100.00,Eletronicos",  // linha valida
            "V002,20,FaltaColuna,300.00",        // coluna faltando
            "V003,abc,IdInvalido,400.00,Outros", // id nao numerico
            "V004,30,ValorInvalido,xyz,Vestuario", // valor nao numerico
            "",                                   // linha vazia
            "V005,10,Alice,200.00,Outros"        // linha valida
        );
        Collection<Cliente> clientes = consolidador.consolidar(linhasCSV);
        // Apenas a Alice (id 10) deve ser processada, com 2 vendas validas
        assertEquals(1, clientes.size());
        Cliente alice = clientes.iterator().next();
        assertEquals(10, alice.getIdCliente());
        assertEquals(2, alice.getVendas().size());
        assertEquals(300.00, alice.getValorTotalVendas(), 0.001);
    }

    
}