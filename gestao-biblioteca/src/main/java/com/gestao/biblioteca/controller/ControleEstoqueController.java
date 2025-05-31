package com.gestao.biblioteca.controller;

import com.gestao.biblioteca.service.ControleEstoqueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/estoque")
public class ControleEstoqueController {

    private final ControleEstoqueService controleEstoqueService;

    public ControleEstoqueController(ControleEstoqueService controleEstoqueService) {
        this.controleEstoqueService = controleEstoqueService;
    }

    @GetMapping
    public String listarEstoque(Model model, @RequestParam(required = false) String mensagem) {
        model.addAttribute("controleEstoque", controleEstoqueService.listarTodos());

        if (mensagem != null) {
            model.addAttribute("mensagem", mensagem.trim());
        }

        return "estoque";
    }

    @PostMapping("/atualizar")
    public String atualizarQuantidade(@RequestParam Integer id_estoque, @RequestParam Integer qtd_nova) {
        controleEstoqueService.atualizarQuantidade(id_estoque, qtd_nova);
        return "redirect:/estoque?mensagem=Estoque atualizado com sucesso!";
    }
}