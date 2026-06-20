package br.edu.ufrgs;

import org.junit.jupiter.api.Test;
import br.edu.ufrgs.model.*;
import br.edu.ufrgs.service.FabricaCliente;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FabricaClienteTest {

    @Test
    public void deveCriarClienteNormalParaComprasAte1000() {
        FabricaCliente fabrica = new FabricaCliente();
        Cliente clienteBase = new Cliente(1, "Ana");
        
        // Limite exato do Tier Normal
        clienteBase.adicionarVenda(new Venda("V1", 1000.0, "Outros"));

        Cliente novoCliente = fabrica.fabricarCliente(clienteBase);

        assertTrue(novoCliente instanceof ClienteNormal);
        assertEquals("NORMAL", novoCliente.getNomeTier());
    }

    @Test
    public void deveCriarClienteGoldParaComprasAcimaDe1000Ate5000() {
        FabricaCliente fabrica = new FabricaCliente();
        Cliente clienteBase = new Cliente(2, "João");
        
        // 1 centavo acima de 1000 já deve virar Gold
        clienteBase.adicionarVenda(new Venda("V1", 1000.01, "Outros"));

        Cliente novoCliente = fabrica.fabricarCliente(clienteBase);

        assertTrue(novoCliente instanceof ClienteGold);
        assertEquals("GOLD", novoCliente.getNomeTier());
    }

    @Test
    public void deveCriarClientePlatinumParaComprasAcimaDe5000() {
        FabricaCliente fabrica = new FabricaCliente();
        Cliente clienteBase = new Cliente(3, "Maria");
        
        // Acima de 5000 vira Platinum
        clienteBase.adicionarVenda(new Venda("V1", 5000.01, "Outros"));

        Cliente novoCliente = fabrica.fabricarCliente(clienteBase);

        assertTrue(novoCliente instanceof ClientePlatinum);
        assertEquals("PLATINUM", novoCliente.getNomeTier());
    }

    @Test
    public void deveTransferirVendasParaONovoCliente() {
        FabricaCliente fabrica = new FabricaCliente();
        Cliente clienteBase = new Cliente(4, "Carlos");
        
        // Adiciona múltiplas vendas ao cliente genérico
        clienteBase.adicionarVenda(new Venda("V1", 500.0, "Eletronicos"));
        clienteBase.adicionarVenda(new Venda("V2", 600.0, "Vestuario"));

        // O novo cliente fabricado deve herdar a lista exata de vendas
        Cliente novoCliente = fabrica.fabricarCliente(clienteBase);

        assertEquals(2, novoCliente.getVendas().size());
        assertEquals(1100.0, novoCliente.getValorTotalVendas(), 0.001);
        assertTrue(novoCliente instanceof ClienteGold); // Garante que a soma ativou o tier correto
    }
}