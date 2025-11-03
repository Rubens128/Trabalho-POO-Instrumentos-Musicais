/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class Material {
    private long id;
    private String nome;
    private String descricao;
    
    public Material(long id, String nome, String descricao) {
        
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public Material(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}