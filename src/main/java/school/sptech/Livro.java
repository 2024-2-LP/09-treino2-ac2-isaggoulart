package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.avaliacoes = new ArrayList<>();
    }

    public Livro() {
        this.avaliacoes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", avaliacoes=" + avaliacoes +
                '}';
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas) {
        if (descricao == null || descricao.isBlank() || qtdEstrelas == null || qtdEstrelas > 5 || qtdEstrelas < 0) {
            throw new ArgumentoInvalidoException("Os parâmetros passados são inválidos!");
        } else {

        Avaliacao novaAvaliacao = new Avaliacao(descricao, qtdEstrelas);

        avaliacoes.add(novaAvaliacao);
        }
    }

    public Double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        } else {
            Double avaliacaoTotal = 0.0;

            for (Avaliacao avaliacaoAtual : avaliacoes) {
                avaliacaoTotal += avaliacaoAtual.getQtdEstrelas();
            }
            return avaliacaoTotal / avaliacoes.size();
        }
    }
}
