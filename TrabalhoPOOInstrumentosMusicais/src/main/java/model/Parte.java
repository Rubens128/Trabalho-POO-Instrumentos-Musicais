/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class Parte {
    private long instrumentoId;
    private String nome;
    private String descricao;
    
    public Parte(long instrumentoId, String nome, String descricao) {
        
        this.instrumentoId = instrumentoId;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public Parte(long instrumentoId, String nome) {
        this.instrumentoId = instrumentoId;
        this.nome = nome;
    }
    
    // Getters e Setters
    public long getInstrumentoId() {
        return instrumentoId;
    }
    
    public void setInstrumentoId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
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
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
