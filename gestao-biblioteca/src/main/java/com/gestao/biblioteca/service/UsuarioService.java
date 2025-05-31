package com.gestao.biblioteca.service;

import com.gestao.biblioteca.model.Usuario;
import com.gestao.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarPorId(Integer id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    // Salvar ou atualizar usuário
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Excluir usuário por ID
    public void excluirUsuario(Integer id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }
    // Para atualizar o usuario
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}