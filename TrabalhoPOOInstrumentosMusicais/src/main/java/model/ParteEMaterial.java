/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class ParteEMaterial {

    private long id;
    private long instrumentoId;
    private String parteNome;
    private String materialNome;
    private String parteDescricao;
    private String materialDescricao;
    
    private ParteEMaterial(Builder builder) {
        this.id = builder.id;
        this.instrumentoId = builder.instrumentoId;
        this.parteNome = builder.parteNome;
        this.materialNome = builder.materialNome;
        this.parteDescricao = builder.parteDescricao;
        this.materialDescricao = builder.materialDescricao;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getInstrumentoId() {
        return instrumentoId;
    }

    public String getParteNome() {
        return parteNome;
    }

    public String getMaterialNome() {
        return materialNome;
    }

    public String getParteDescricao() {
        return parteDescricao;
    }

    public String getMaterialDescricao() {
        return materialDescricao;
    }

    // Setters 
    public void setId(long id) {
        this.id = id;
    }

    public void setInstrumentoId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    public void setParteNome(String parteNome) {
        this.parteNome = parteNome;
    }

    public void setMaterialNome(String materialNome) {
        this.materialNome = materialNome;
    }

    public void setParteDescricao(String parteDescricao) {
        this.parteDescricao = parteDescricao;
    }

    public void setMaterialDescricao(String materialDescricao) {
        this.materialDescricao = materialDescricao;
    }
    

    // Padrão Builder para lidar com parâmetros opcionais.
    public static class Builder {

        // Parâmetros Obrigatórios
        private final long id;
        private final long instrumentoId;
        private final String parteNome;
        private final String materialNome;

        // Parâmetros Opcionais
        private String parteDescricao = null;
        private String materialDescricao = null;

        // Construtor do Builder (só com obrigatórios)
        public Builder(long id, long instrumentoId, String parteNome, String materialNome) {
            this.id = id;
            this.instrumentoId = instrumentoId;
            this.parteNome = parteNome;
            this.materialNome = materialNome;
        }

        // Métodos para parâmetros opcionais
        public Builder parteDescricao(String val) {
            this.parteDescricao = val;
            return this;
        }

        public Builder materialDescricao(String val) {
            this.materialDescricao = val;
            return this;
        }

        // Método para criar o objeto ParteEMaterial
        public ParteEMaterial build() {
            return new ParteEMaterial(this);
        }
    }
}