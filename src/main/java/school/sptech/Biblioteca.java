package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "nome='" + nome + '\'' +
                ", livros=" + livros +
                '}';
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null  || livro.getAutor() == null || livro.getTitulo() == null || livro.getDataPublicacao() == null || livro.getAutor().isBlank() || livro.getTitulo().isBlank()) {
            throw new ArgumentoInvalidoException("Nenhum campo pode ser nulo!");
        } else {
            livros.add(livro);
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException("O título está inválido!");
        } else {

        Integer contador = 0;
        for (int i = 0; i < livros.size(); i++) {
            Livro livroAtual = livros.get(i);

            if(livroAtual.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livroAtual);
                contador++;
            }
        }
        if(contador == 0) {
            throw new LivroNaoEncontradoException("O livro não foi econtrado!");
            }
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if(titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException("O título está inválido!");
        }

        Integer contador = 0;
        for (Livro livroAtual : livros) {
            if(livroAtual.getTitulo().equalsIgnoreCase(titulo)) {
                contador++;
                return livroAtual;
            }
        }
        throw new LivroNaoEncontradoException("O livro não foi encontrado!");
    }

    public Integer contarLivros() {
        Integer contador = 0;

        for (Livro livroAtual : livros) {
            contador++;
        }
        return contador;
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosAteAno = new ArrayList<>();

        for (Livro livroAtual : livros) {
            Integer anoAtual = livroAtual.getDataPublicacao().getYear();
            if (anoAtual <= ano) {
                livrosAteAno.add(livroAtual);
            }
        }
        return livrosAteAno;
    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> livrosMaiorMedia = new ArrayList<>();
        Collections.sort(livros, Comparator.comparing(Livro::calcularMediaAvaliacoes).reversed());

        for (int i = 0; i < livros.size(); i++) {
            Livro livroAtual = livros.get(i);
            if(i <= 4) {
                livrosMaiorMedia.add(livroAtual);
            }
        }
        return livrosMaiorMedia;
    }
}
