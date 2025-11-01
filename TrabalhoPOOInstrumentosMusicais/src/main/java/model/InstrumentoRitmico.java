/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class InstrumentoRitmico extends Instrumento {
    private boolean alturaDefinida;
    private CategoriaPercussao categoriaPercussao;
    private TocadoCom tocadoCom; 
    
    protected InstrumentoRitmico(Builder builder) {
        super(builder);
        this.alturaDefinida = builder.alturaDefinida;
        this.categoriaPercussao = builder.categoriaPercussao;
        this.tocadoCom = builder.tocadoCom;
    }

    // Getters e Setters
    public boolean isAlturaDefinida() {
        return alturaDefinida;
    }

    public void setAlturaDefinida(boolean alturaDefinida) {
        this.alturaDefinida = alturaDefinida;
    }

    public CategoriaPercussao getCategoriaPercussao() {
        return categoriaPercussao;
    }

    public void setCategoriaPercussao(CategoriaPercussao categoriaPercussao) {
        this.categoriaPercussao = categoriaPercussao;
    }

    public TocadoCom getTocadoCom() {
        return tocadoCom;
    }

    public void setTocadoCom(TocadoCom tocadoCom) {
        this.tocadoCom = tocadoCom;
    }
    
    // Classe Builder
    public static class Builder extends Instrumento.Builder<Builder> {

        private final boolean alturaDefinida;
        private final CategoriaPercussao categoriaPercussao;
        private final TocadoCom tocadoCom;

        public Builder(
                long id,
                long familiaId,
                String nome,
                String classificacaoSonoridade,
                boolean alturaDefinida,
                CategoriaPercussao categoriaPercussao,
                TocadoCom tocadoCom
        ) {

            super(id, familiaId, nome, classificacaoSonoridade);
            this.alturaDefinida = alturaDefinida;
            this.categoriaPercussao = categoriaPercussao;
            this.tocadoCom = tocadoCom;
        }

        @Override
        public InstrumentoRitmico build() {
            return new InstrumentoRitmico(this);
        }
    }
}
