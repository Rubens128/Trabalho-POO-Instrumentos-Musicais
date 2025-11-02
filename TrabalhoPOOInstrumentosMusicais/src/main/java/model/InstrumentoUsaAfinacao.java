/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class InstrumentoUsaAfinacao {
    private long instrumentoId;
    private long afinacaoId;
    private String contexto;
    
    public InstrumentoUsaAfinacao(long instrumentoId, long afinacaoId, String contexto) {
        this.instrumentoId = instrumentoId;
        this.afinacaoId = afinacaoId;
        this.contexto = contexto;
    }
    
    // Getters e Setters
    public long getInstrumentoId() {
        return instrumentoId;
    }
    
    public void setInstrumentoId(long novoInstrumentoId) {
        this.instrumentoId = novoInstrumentoId;
    }
    
    public long getAfinacaoId() {
        return afinacaoId;
    }

    public void setAfinacaoId(long afinacaoId) {
        this.afinacaoId = afinacaoId;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
}
