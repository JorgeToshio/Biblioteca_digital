package com.gestao.biblioteca.service;

import com.gestao.biblioteca.model.Emprestimo;
import com.gestao.biblioteca.model.ControleEstoque;
import com.gestao.biblioteca.model.Livro;
import com.gestao.biblioteca.repository.EmprestimoRepository;
import com.gestao.biblioteca.repository.ControleEstoqueRepository;
import com.gestao.biblioteca.repository.LivroRepository;
import com.gestao.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ControleEstoqueRepository controleEstoqueRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             ControleEstoqueRepository controleEstoqueRepository,
                             LivroRepository livroRepository,
                             UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.controleEstoqueRepository = controleEstoqueRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo realizarEmprestimo(Integer id_livro, Integer id_usuario) {
        Livro livro = livroRepository.findById(id_livro)
                .orElseThrow(() -> new RuntimeException("Livro sem cadastro!"));

        ControleEstoque estoque = controleEstoqueRepository.findByLivro(livro);
        if (estoque == null || estoque.getQtd_atual() <= 0) {
            throw new RuntimeException("Estoque insuficiente!");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario inexistente!")));
        emprestimo.setData_emprestimo(LocalDate.now());

        // 🚀 Atualizar estoque: subtrair quantidade
        estoque.setQtd_atual(estoque.getQtd_atual() - 1);
        controleEstoqueRepository.save(estoque);

        return emprestimoRepository.save(emprestimo);
    }

    public void realizarDevolucao(Integer id_emprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(id_emprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));

        if (emprestimo.getData_devolucao() != null) {
            throw new RuntimeException("Este livro foi devolvido!");
        }

        ControleEstoque estoque = controleEstoqueRepository.findByLivro(emprestimo.getLivro());
        if (estoque != null) {
            // 🚀 Atualizar estoque: adicionar quantidade
            estoque.setQtd_atual(estoque.getQtd_atual() + 1);
            controleEstoqueRepository.save(estoque);
        }

        emprestimo.setData_devolucao(LocalDate.now());
        emprestimoRepository.save(emprestimo);
    }
}