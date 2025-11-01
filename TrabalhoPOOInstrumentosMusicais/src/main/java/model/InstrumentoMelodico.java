/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class InstrumentoMelodico extends Instrumento {
    private boolean transpositor;
    private AfinacaoTransposicao afinacaoTransposicao;
    private boolean microtonalidadeSuportada;
    
    protected InstrumentoMelodico(Builder builder) {
        super(builder);
        this.transpositor = builder.transpositor;
        this.afinacaoTransposicao = builder.afinacaoTransposicao;
        this.microtonalidadeSuportada = builder.microtonalidadeSuportada;
    }

    // Getters e Setters
    public boolean isTranspositor() {
        return transpositor;
    }

    public void setTranspositor(boolean transpositor) {
        this.transpositor = transpositor;
    }

    public AfinacaoTransposicao getAfinacaoTransposicao() {
        return afinacaoTransposicao;
    }

    public void setAfinacaoTransposicao(AfinacaoTransposicao afinacaoTransposicao) {
        this.afinacaoTransposicao = afinacaoTransposicao;
    }

    public boolean isMicrotonalidadeSuportada() {
        return microtonalidadeSuportada;
    }

    public void setMicrotonalidadeSuportada(boolean microtonalidadeSuportada) {
        this.microtonalidadeSuportada = microtonalidadeSuportada;
    }
    

    // Classe Builder
    public static class Builder extends Instrumento.Builder<Builder> {

        private boolean transpositor;
        private AfinacaoTransposicao afinacaoTransposicao;
        private boolean microtonalidadeSuportada;

        public Builder(long id, 
                long familiaId, 
                String nome, 
                String classificacaoSonoridade, 
                boolean transpositor, 
                AfinacaoTransposicao afinacaoTransposicao, 
                boolean microtonalidadeSuportada) {
            super(id, familiaId, nome, classificacaoSonoridade);
            this.transpositor = transpositor;
            this.afinacaoTransposicao = afinacaoTransposicao;
            this.microtonalidadeSuportada = microtonalidadeSuportada;
        }

        @Override
        public InstrumentoMelodico build() {
            return new InstrumentoMelodico(this);
        }
    }
}

