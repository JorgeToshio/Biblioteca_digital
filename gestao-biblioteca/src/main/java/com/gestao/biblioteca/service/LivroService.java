package com.gestao.biblioteca.service;

import com.gestao.biblioteca.model.Livro;
import com.gestao.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Integer id) {
        return livroRepository.findById(id).orElse(null);
    }

    public void salvarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    public void atualizarLivro(Integer id, Livro livro) {
        Livro existente = buscarPorId(id);
        if (existente != null) {
            existente.setTitulo(livro.getTitulo());
            existente.setAutor(livro.getAutor());
            existente.setAno(livro.getAno());
            existente.setCategoria(livro.getCategoria());
            existente.setIsbn(livro.getIsbn());
            livroRepository.save(existente);
        }
    }

    public void deletarLivro(Integer id) {
        livroRepository.deleteById(id);
    }
}