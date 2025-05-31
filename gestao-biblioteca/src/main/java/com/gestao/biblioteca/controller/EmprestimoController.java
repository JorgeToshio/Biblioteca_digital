package com.gestao.biblioteca.controller;

import com.gestao.biblioteca.service.EmprestimoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public String listarEmprestimos(Model model, @RequestParam(required = false) String mensagem) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());

        if (mensagem != null) {
            model.addAttribute("mensagem", mensagem);
        }

        return "emprestimos";
    }

    @PostMapping("/emprestar")
    public String emprestarLivro(@RequestParam("id_livro") Integer id_livro, @RequestParam("id_usuario") Integer id_usuario) {
        try {
            emprestimoService.realizarEmprestimo(id_livro, id_usuario);
            return "redirect:/emprestimos?mensagem=Empréstimo realizado com sucesso!";
        } catch (RuntimeException e) {
            String mensagemUtf8 = new String(e.getMessage().getBytes(), StandardCharsets.UTF_8);
            return "redirect:/emprestimos?mensagem=" + mensagemUtf8;
        }
    }

    @PostMapping("/devolver")
    public String devolverLivro(@RequestParam("id_emprestimo") Integer id_emprestimo) {
        try {
            emprestimoService.realizarDevolucao(id_emprestimo);
            return "redirect:/emprestimos?mensagem=Livro devolvido com sucesso!";
        } catch (RuntimeException e) {
            return "redirect:/emprestimos?mensagem=" + e.getMessage();
        }
    }
}