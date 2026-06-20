# Sistema de Cashback — Fidelidade Dinâmica

**Projeto Final — INF01120 (Desenvolvimento de Software) — Grupo 2, Turma A**

## Descrição

Este projeto é uma aplicação web em Java desenvolvida para automatizar o processamento de vendas mensais e o cálculo de fidelidade (**Cashback**). O diferencial está na lógica de **Bonificação Dinâmica**: o retorno financeiro é calculado com base na categoria do produto e escalonado conforme o gasto total do cliente, através de níveis de fidelidade (Tiers).

## Funcionalidades

- **Importação de Dados:** processamento de arquivos `.csv` com suporte a múltiplas transações por cliente.
- **Consolidação Automática:** agrupamento do histórico de compras por `cliente_id` antes da aplicação das regras.
- **Motor de Cálculo:**
  - Cashback por categoria: Eletrônicos (5%), Vestuário (3%) e Outros (1%).
  - Bônus progressivo por Tier, conforme o gasto total.
- **Interface Web:** tela para upload do arquivo, visualização do extrato e busca por ID de cliente.
- **Exportação:** geração de um relatório consolidado em formato CSV para download.

## Arquitetura

O projeto segue os princípios de **Clean Code** e **Separação de Preocupações (SoC)**, organizado nos seguintes pacotes:

- **`model`**: classes de domínio — `Cliente` (e as subclasses `ClienteNormal`, `ClienteGold`, `ClientePlatinum`), `Venda` e `CategoriaProduto`.
- **`service`**: lógica de negócio — `CalculadoraCashback`, `ConsolidadorClientes` e `FabricaCliente`.
- **`persistence`**: camada de entrada e saída — `LeitorCSV`, `GerenciadorDeArquivos` e `ExportadorDadosCSV`.
- **`controller`**: camada web — `CashbackServlet`.
- **`webapp`**: interface do usuário (`index.jsp`).

### Conceitos de OO aplicados
Herança e polimorfismo (níveis de cliente), padrão Factory (`FabricaCliente`), padrão Fachada (`GerenciadorDeArquivos`) e separação em camadas.

### Tecnologias
- Java 17
- Apache Tomcat
- Docker / docker-compose
- Maven
- JUnit e Jacoco (testes e cobertura)

## Regras de Negócio

O cashback total de cada cliente é a soma do cashback de cada venda (por categoria) com o bônus do seu Tier.

### Tabela de Tiers

| Tier | Critério (gasto total) | Bônus adicional |
| :--- | :--- | :--- |
| **Normal** | até R$ 1.000,00 | nenhum |
| **Gold** | acima de R$ 1.000,00 | R$ 50,00 fixo |
| **Platinum** | acima de R$ 5.000,00 | R$ 50,00 fixo + 2% do total |

> A comparação usa "maior que" (`>`): um gasto de exatamente R$ 1.000 permanece Normal, e exatamente R$ 5.000 permanece Gold.

## Como Rodar

A aplicação é executada via **Docker**, então não é necessário instalar Java ou Tomcat localmente.

**Pré-requisitos:** ter o [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e em execução.

1. Clone o repositório:
   ```bash
   git clone https://github.com/SW-Engineering-Courses-Karina-Kohl/trabalhofinal-turmaa-grupo2.git
   ```
2. Entre na pasta do projeto e suba o container:
   ```bash
   docker-compose up --build -d
   ```
3. Acesse no navegador:
   ```
   http://localhost:8080
   ```
4. Faça o upload de um arquivo CSV de vendas (ex.: `cliente.csv`), visualize o extrato e exporte o resultado.

Para parar a aplicação:
```bash
docker-compose down
```

### Formato do CSV de entrada
```
id_venda,cliente_id,nome_cliente,valor,categoria
```

## Testes

O projeto possui testes unitários (JUnit) cobrindo a lógica de negócio, com cobertura medida pelo Jacoco. Para rodar os testes:

```bash
docker run --rm -v "${PWD}:/app" -w /app maven:3.9-eclipse-temurin-17 mvn test
```


