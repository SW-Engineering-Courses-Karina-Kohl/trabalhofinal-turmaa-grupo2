package br.edu.ufrgs;

import org.junit.jupiter.api.Test;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {

    @Test
    public void deveCalcularValorTotalDasVendasAcumuladas() {
        Cliente cliente = new Cliente(1, "Carlos");
        
        // Inicialmente o total deve ser 0
        assertEquals(0.0, cliente.getValorTotalVendas(), 0.001);

        // Adicionando vendas
        cliente.adicionarVenda(new Venda("V1", 250.50, "Vestuario"));
        cliente.adicionarVenda(new Venda("V2", 150.00, "Eletronicos"));

        // O total deve ser a soma (400.50)
        assertEquals(400.50, cliente.getValorTotalVendas(), 0.001);
    }

    @Test
    public void deveRetornarAtributosBasicosCorretamente() {
        Cliente cliente = new Cliente(2, "Ana");
        
        assertEquals(2, cliente.getIdCliente());
        assertEquals("Ana", cliente.getNome());
    }

    @Test
    public void deveAdicionarVendaNaListaCorretamente() {
        Cliente cliente = new Cliente(3, "Pedro");
        
        // A lista deve começar vazia
        assertTrue(cliente.getVendas().isEmpty());
        
        cliente.adicionarVenda(new Venda("V003", 100.0, "Outros"));
        
        // Verifica se a venda foi adicionada
        assertEquals(1, cliente.getVendas().size());
        assertEquals("V003", cliente.getVendas().get(0).getIdVenda());
    }

    @Test
    public void deveRetornarTotalCompradoIgualAoValorTotalDeVendas() {
        Cliente cliente = new Cliente(4, "Maria");
        cliente.adicionarVenda(new Venda("V004", 300.0, "Eletronicos"));
        
        // Testa o método getTotalComprado() que funciona como alias para getValorTotalVendas()
        assertEquals(300.0, cliente.getTotalComprado(), 0.001);
    }

    @Test
    public void deveArmazenarERetornarCashbackAcumulado() {
        Cliente cliente = new Cliente(5, "João");
        
        // O cashback inicial padrão de um double é 0.0
        assertEquals(0.0, cliente.getCashBackAcumulado(), 0.001);
        
        // Atualiza o valor e verifica
        cliente.setCashBackAcumulado(150.75);
        assertEquals(150.75, cliente.getCashBackAcumulado(), 0.001);
    }

    @Test
    public void deveRetornarTierNormalPorPadraoNaClasseBase() {
        Cliente cliente = new Cliente(6, "Lucas");
        
        // A classe base Cliente deve sempre retornar "NORMAL" nos métodos de tier
        assertEquals("NORMAL", cliente.getNomeTier());
        assertEquals("NORMAL", cliente.getTier());
    }
}