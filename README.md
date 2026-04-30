[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/RBBavBFg)
# projeto-base
#Projeto CashBack Grupo 2 (Desenvolvimento de Software)

---

# 💰 Sistema de Cashback - Fidelidade Dinâmica

## 📝 Descrição
Este projeto é uma aplicação Java desenvolvida para automatizar o processamento de vendas mensais e cálculo de fidelidade (**Cashback**). O diferencial da engine está na lógica de **Bonificação Dinâmica**, onde o retorno financeiro é calculado com base na categoria do produto e escalonado conforme o gasto total do cliente através de níveis de fidelidade (Tiers).

---

## 🚀 Funcionalidades
*   **Importação de Dados:** Processamento de arquivos `.csv` com suporte a múltiplas transações por cliente.
*   **Consolidação Automática:** Agrupamento inteligente de histórico de compras por `cliente_id` antes da aplicação das regras de bonificação.
*   **Motor de Cálculo:** 
    *   Cashback fixo por categoria: Eletrônicos (5%), Vestuário (3%) e Outros (1%).
    *   Bônus progressivo por Tier (acumulativo para gastos acima de R$ 1.000,00 e R$ 5.000,00).
*   **Relatórios:** Geração de extratos individuais e exportação de resultados consolidados em formato CSV.

---

## 🛠️ Arquitetura e Tech Stack
O projeto foi estruturado seguindo princípios de **Clean Code** e **Separação de Preocupações (SoC)**, organizado nos seguintes pacotes lógicos:

*   **`model`**: Classes de domínio (`Cliente`, `Venda`, `Tier`).
*   **`service`**: Engine de cálculo e implementação da lógica de negócio.
*   **`persistence`**: Camada de I/O responsável pelo *parsing* e geração dos arquivos CSV.
*   **`view`**: Interface para interação e visualização do extrato.

### Tecnologias:
- Java 17+
- JUnit (para validação do motor de cashback)
- Maven/Gradle (opcional)

---

## 📊 Regras de Negócio (Engine)
O cálculo do benefício segue a lógica matemática abaixo:

$$Cashback_{Total} = \sum (Valor_{Item} \times \%_{Categoria}) + Bônus_{Tier}$$

### Tabela de Tiers e Bonificação

| Tier | Critério | Bônus Adicional |
| :--- | :--- | :--- |
| **Normal** | < R$ 1.000,00 | Apenas % da Categoria |
| **Gold** | > R$ 1.000,00 | R$ 50,00 Fixo |
| **Platinum** | > R$ 5.000,00 | R$ 50,00 Fixo + 2% sobre o valor total |

---

## 📥 Como Rodar

1. **Pré-requisitos:** Certifique-se de ter o **JDK 17** ou superior instalado e configurado no seu PATH.
2. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
3. **Preparação:** Coloque o arquivo `vendas_mensais.csv` na pasta raiz do projeto.
4. **Execução:**
   Compile e rode a classe principal através do terminal:
   ```bash
   javac Main.java && java Main
   ```

---

