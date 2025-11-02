/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ruben
 */
public class FamiliaInstrumento {
    
    private long id;
    private String nome;
    private String descricao;
    
    public FamiliaInstrumento(long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public FamiliaInstrumento(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Getters e Setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }
}

