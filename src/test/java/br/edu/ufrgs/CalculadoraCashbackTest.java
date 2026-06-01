package br.edu.ufrgs;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.model.Venda;
import br.edu.ufrgs.service.CalculadoraCashback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraCashbackTest {

    private CalculadoraCashback calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraCashback();
    }

    @Test
    public void deveCalcularCashbackPorCategoriaCorretamente() {
        // Criando vendas de teste conforme as regras do negócio
        Venda vendaEletronico = new Venda("V01", 100.0, "Eletronicos"); // 5% = 5.0
        Venda vendaVestuario = new Venda("V02", 100.0, "Vestuario");   // 3% = 3.0
        Venda vendaOutros = new Venda("V03", 100.0, "Outros");         // 1% = 1.0

        // Validando cada percentual
        assertEquals(5.0, calculadora.calcularCashbackCategoria(vendaEletronico), 0.001);
        assertEquals(3.0, calculadora.calcularCashbackCategoria(vendaVestuario), 0.001);
        assertEquals(1.0, calculadora.calcularCashbackCategoria(vendaOutros), 0.001);
    }

    @Test
    public void deveCalcularBonusDoTierNormal() {
        Cliente cliente = new Cliente(1, "João");
        cliente.adicionarVenda(new Venda("V01", 500.0, "Outros")); // Gasto total: 500.0 (< 1000)

        // Tier Normal não ganha bônus extra fixo (retorna 0.0)
        assertEquals(0.0, calculadora.calcularBonusTier(cliente), 0.001);
    }

    @Test
    public void deveCalcularBonusDoTierGold() {
        Cliente cliente = new Cliente(2, "Maria");
        cliente.adicionarVenda(new Venda("V01", 1500.0, "Outros")); // Gasto total: 1500.0 (> 1000)

        // Tier Gold ganha bônus fixo de R$ 50.00
        assertEquals(50.0, calculadora.calcularBonusTier(cliente), 0.001);
    }

    @Test
    public void deveCalcularBonusDoTierPlatinum() {
        Cliente cliente = new Cliente(3, "Pedro");
        cliente.adicionarVenda(new Venda("V01", 6000.0, "Outros")); // Gasto total: 6000.0 (> 5000)

        // Tier Platinum ganha R$ 50.00 fixo + 2% do valor total (50 + 120 = 170.0)
        double esperado = 50.0 + (6000.0 * 0.02);
        assertEquals(esperado, calculadora.calcularBonusTier(cliente), 0.001);
    }

    @Test
    public void deveCalcularCashbackFinalSomandoTudo() {
        Cliente cliente = new Cliente(4, "Ana");
        // Gasto total: R$ 2000.00 -> Tier Gold (Bônus fixo de R$ 50.00)
        // Cashback de Categoria: 2000.0 * 5% (Eletrônicos) = R$ 100.00
        // Total esperado: 100.00 + 50.00 = R$ 150.00
        cliente.adicionarVenda(new Venda("V01", 2000.0, "Eletronicos"));

        assertEquals(150.0, calculadora.calcularCashbackFinal(cliente), 0.001);
    }
}