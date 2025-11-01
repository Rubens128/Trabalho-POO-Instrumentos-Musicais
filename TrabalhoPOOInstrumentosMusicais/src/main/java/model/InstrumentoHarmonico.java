/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class InstrumentoHarmonico extends Instrumento {
    private long polifoniaMax;
    private boolean possuiPedalSustain;
    private boolean suportaAcordes;
    
    protected InstrumentoHarmonico(Builder builder) {
        super(builder);
        
        this.polifoniaMax = builder.polifoniaMax;
        this.possuiPedalSustain = builder.possuiPedalSustain;
        this.suportaAcordes = builder.suportaAcordes;
    }

    // Getters e Setters
    public long getPolifoniaMax() {
        return polifoniaMax;
    }

    public void setPolifoniaMax(long polifoniaMax) {
        this.polifoniaMax = polifoniaMax;
    }

    public boolean isPossuiPedalSustain() {
        return possuiPedalSustain;
    }

    public void setPossuiPedalSustain(boolean possuiPedalSustain) {
        this.possuiPedalSustain = possuiPedalSustain;
    }

    public boolean isSuportaAcordes() {
        return suportaAcordes;
    }

    public void setSuportaAcordes(boolean suportaAcordes) {
        this.suportaAcordes = suportaAcordes;
    }
    
    // Classe Builder
    public static class Builder extends Instrumento.Builder<Builder> {
        
        private long polifoniaMax;
        private boolean possuiPedalSustain;
        private boolean suportaAcordes;
        
        public Builder(long id,
                long familiaId,
                String nome,
                String classificacaoSonoridade,
                long polifoniaMax,
                boolean possuiPedalSustain,
                boolean suportaAcordes) {
            super(id, familiaId, nome, classificacaoSonoridade);
            this.polifoniaMax = polifoniaMax;
            this.possuiPedalSustain = possuiPedalSustain;
            this.suportaAcordes = suportaAcordes;
        }
        
        @Override
        public InstrumentoHarmonico build() {
            return new InstrumentoHarmonico(this);
        }
    }
}
