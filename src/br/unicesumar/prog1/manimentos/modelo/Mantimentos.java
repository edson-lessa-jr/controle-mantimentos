package br.unicesumar.prog1.manimentos.modelo;

public class Mantimentos {
    private String nome;
    private String unidade;
    private int quantidadeEmEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    @Override
    public String toString() {
        return "NOME: " + nome + "\n" +
                "UNIDADE: " + unidade + "\n" +
                "QUANTIDADE: " + quantidadeEmEstoque;
    }

    public void setAdicionarQuantidade(int quantidade) {
        this.quantidadeEmEstoque+=quantidade;
    }

    public void setDiminuirQuantidade(int quantidade) {
        this.quantidadeEmEstoque-=quantidade;
    }
}
