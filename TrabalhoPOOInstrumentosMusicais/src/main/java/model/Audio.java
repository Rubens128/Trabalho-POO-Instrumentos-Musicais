/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class Audio {
    
    private long id;
    private long instrumentoID;
    private String titulo;
    private String descricao;
    private String nota;
    private long oitava; 
    private long bpm;
    private String arquivo;
    private String creditoGravacao;
    private Tecnica tecnica;
    
    
    private Audio(Builder builder) {
        this.id = builder.id;
        this.instrumentoID = builder.instrumentoID;
        this.titulo = builder.titulo;
        this.arquivo = builder.arquivo;
        this.descricao = builder.descricao;
        this.nota = builder.nota;
        this.oitava = builder.oitava;
        this.bpm = builder.bpm;
        this.creditoGravacao = builder.creditoGravacao;
        this.tecnica = builder.tecnica;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getInstrumentoID() {
        return instrumentoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNota() {
        return nota;
    }

    public long getOitava() {
        return oitava;
    }

    public long getBpm() {
        return bpm;
    }

    public String getArquivo() {
        return arquivo;
    }

    public String getCreditoGravacao() {
        return creditoGravacao;
    }

    public Tecnica getTecnica() {
        return tecnica;
    }
    
    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setInstrumentoID(long instrumentoID) {
        this.instrumentoID = instrumentoID;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setOitava(long oitava) {
        this.oitava = oitava;
    }

    public void setBpm(long bpm) {
        this.bpm = bpm;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void setCreditoGravacao(String creditoGravacao) {
        this.creditoGravacao = creditoGravacao;
    }

    public void setTecnica(Tecnica tecnica) {
        this.tecnica = tecnica;
    }


    // Padrão Builder para lidar com parâmetros opcionais.
    public static class Builder {
        
        // Parâmetros Obrigatórios.
        private long id;
        private long instrumentoID;
        private String titulo;
        private String nota;
        private long oitava;
        private String arquivo;

        //Parâmetros Opcionais.
        private String descricao = null;
        private long bpm = 0;
        private String creditoGravacao = null;
        private Tecnica tecnica = null;
        
        // Construtor do Builder (só com obrigatórios)
        public Builder(long id, long instrumentoID, String titulo, String nota, long oitava, String arquivo) {
            this.id = id;
            this.instrumentoID = instrumentoID;
            this.titulo = titulo;
            this.nota = nota;
            this.oitava = oitava;
            this.arquivo = arquivo;
        }
        
        // Métodos para parâmetros opcionais
        public Builder descricao(String val) {
            this.descricao = val;
            return this;
        }

        public Builder bpm(long val) {
            this.bpm = val;
            return this;
        }

        public Builder creditoGravacao(String val) {
            this.creditoGravacao = val;
            return this;
        }

        public Builder tecnica(Tecnica val) {
            this.tecnica = val;
            return this;
        }

        // Método para criar o objeto Audio
        public Audio build() {
            return new Audio(this);
        }
    }
}