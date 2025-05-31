package com.gestao.biblioteca.controller;

import com.gestao.biblioteca.model.Usuario;
import com.gestao.biblioteca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Renderiza usuarios.html
    }

    @GetMapping("/editar/{id_usuario}")
    public String editarUsuario(@PathVariable Integer id_usuario, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        model.addAttribute("usuario", usuario);
        return "editar_usuario"; // Página de edição
    }


    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return "redirect:/usuarios"; // Redireciona para a lista de usuários
    }

    @PostMapping("/atualizar/{id_usuario}")
    public String atualizarUsuario(@PathVariable Integer id_usuario, @ModelAttribute Usuario usuario) {
        usuario.setId_usuario(id_usuario); // Garante que o ID seja mantido
        usuarioService.salvarUsuario(usuario); // Atualiza no banco
        return "redirect:/usuarios"; // Redireciona para a lista de usuários
    }
    @PostMapping("/excluir/{id_usuario}")
    public String excluirUsuario(@PathVariable Integer id_usuario) {
        usuarioService.excluirUsuario(id_usuario); // Remove do banco
        return "redirect:/usuarios"; // Redireciona para a lista de usuários
    }


}