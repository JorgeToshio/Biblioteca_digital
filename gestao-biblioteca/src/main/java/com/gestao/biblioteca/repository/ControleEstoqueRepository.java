package com.gestao.biblioteca.repository;

import com.gestao.biblioteca.model.ControleEstoque;
import com.gestao.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ControleEstoqueRepository extends JpaRepository<ControleEstoque, Integer> {
        ControleEstoque findByLivro(Livro livro); // Método correto
    }

