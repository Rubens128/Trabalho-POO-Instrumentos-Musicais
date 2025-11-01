/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class Afinacao {
    
    private long id;
    private String nome;
    private String descricao;
    private String referencia;
    private String contextoAfinacao;
    
    private Afinacao(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.referencia = builder.referencia;
        this.contextoAfinacao = builder.contextoAfinacao;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }
    
    public void setId(long novoId) {
        this.id = novoId;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }
    
    public String getDescricao() {
        return descricao;
    }
        
    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String novaReferencia) {
        this.referencia = novaReferencia;
    }
    
    public String getContextoAfinacao() {
        return contextoAfinacao;
    }
    
    public void setContextoAfinacao(String novoContextoAfinacao) {
        this.contextoAfinacao = novoContextoAfinacao;
    }
    
    
    // Padrão Builder para lidar com parâmetros opcionais.
    public static class Builder {
        // Parâmetros Obrigatórios.
        private long id;
        private String nome;
        
        //Parâmetros Opcionais.
        private String descricao = null;
        private String referencia = null;
        private String contextoAfinacao = null;
        
        // Construtor do Builder (só com obrigatórios)
        public Builder(long id, String nome) {
            this.id = id;
            this.nome = nome;
        }
        
        // Métodos para parâmetros opcionais
        public Builder descricao(String val) {
            this.descricao = val;
            return this;
        }
        
        public Builder referencia(String val) {
            this.referencia = val;
            return this;
        }

        public Builder contextoAfinacao(String val) {
            this.contextoAfinacao = val;
            return this;
        }
        
        // Método para criar o objeto Afinacao
        public Afinacao build() {
            return new Afinacao(this);
        }
    }
}
