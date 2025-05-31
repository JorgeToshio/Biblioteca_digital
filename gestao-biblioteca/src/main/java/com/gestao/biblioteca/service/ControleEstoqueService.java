package com.gestao.biblioteca.service;

import com.gestao.biblioteca.model.ControleEstoque;
import com.gestao.biblioteca.repository.ControleEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleEstoqueService {

    private final ControleEstoqueRepository controleEstoqueRepository;

    public ControleEstoqueService(ControleEstoqueRepository controleEstoqueRepository) {
        this.controleEstoqueRepository = controleEstoqueRepository;
    }

    public List<ControleEstoque> listarTodos() {
        return controleEstoqueRepository.findAll();
    }

    public void atualizarQuantidade(Integer id_estoque, Integer qtd_nova) {
        ControleEstoque estoque = controleEstoqueRepository.findById(id_estoque)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado!"));

        estoque.setQtd_atual(qtd_nova);
        controleEstoqueRepository.save(estoque);
    }
}