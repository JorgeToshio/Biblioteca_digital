package com.gestao.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_controle_estoque")
public class ControleEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Integer id_estoque;

    @Column(name = "qtd_atual") // Nome correto no banco
    private Integer qtd_atual;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    // Getters e Setters
    public Integer getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(Integer id_estoque) {
        this.id_estoque = id_estoque;
    }

    public Integer getQtd_atual() {
        return qtd_atual;
    }

    public void setQtd_atual(Integer qtd_atual) {
        this.qtd_atual = qtd_atual;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}