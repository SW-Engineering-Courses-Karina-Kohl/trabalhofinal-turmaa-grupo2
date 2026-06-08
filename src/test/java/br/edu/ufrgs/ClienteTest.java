package br.edu.ufrgs;

import org.junit.jupiter.api.Test;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}