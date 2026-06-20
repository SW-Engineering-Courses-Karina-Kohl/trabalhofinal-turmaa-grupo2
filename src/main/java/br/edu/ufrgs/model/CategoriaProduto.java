package br.edu.ufrgs.model;

/**
 * Representa a categoria de um produto (por exemplo, Eletronicos,
 * Vestuario ou Outros), usada no calculo do cashback por categoria.
 */
public class CategoriaProduto {
    private String categoria;

    /**
     * Cria uma categoria com o nome informado.
     *
     * @param categoria o nome da categoria do produto
     */

    public CategoriaProduto(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return o nome da categoria do produto
     */

    public String getCategoria() {
        return categoria;
    }
}
