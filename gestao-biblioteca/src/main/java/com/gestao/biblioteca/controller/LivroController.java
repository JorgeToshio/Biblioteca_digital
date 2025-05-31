package com.gestao.biblioteca.controller;

import com.gestao.biblioteca.model.Livro;
import com.gestao.biblioteca.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Listar livros
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.listarTodos();
        model.addAttribute("livros", livros);
        return "livros";
    }

    // Formulário para novo livro
    @GetMapping("/novo")
    public String novoLivroForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "novo-livro";
    }

    // Cadastro de livro
    @PostMapping("/cadastrar")
    public String adicionarLivro(@ModelAttribute Livro livro) {
        livroService.salvarLivro(livro);
        return "redirect:/livros";
    }

    // Formulário para edição
    @GetMapping("/editar/{id}")
    public String editarLivroForm(@PathVariable Integer id, Model model) {
        Livro livro = livroService.buscarPorId(id);
        model.addAttribute("livro", livro);
        return "editar-livro";
    }

    // Atualizar livro
    @PostMapping("/editar/{id}")
    public String atualizarLivro(@PathVariable Integer id, @ModelAttribute Livro livro) {
        livroService.atualizarLivro(id, livro);
        return "redirect:/livros";
    }

    // Excluir livro
    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Integer id) {
        livroService.deletarLivro(id);
        return "redirect:/livros";
    }
}