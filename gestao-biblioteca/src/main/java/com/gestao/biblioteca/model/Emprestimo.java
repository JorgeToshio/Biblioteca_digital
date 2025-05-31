package com.gestao.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo")
    private Integer id_emprestimo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate data_emprestimo;

    @Column(name = "data_devolucao")
    private LocalDate data_devolucao;

    // Getters e Setters
    public Integer getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(Integer id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}