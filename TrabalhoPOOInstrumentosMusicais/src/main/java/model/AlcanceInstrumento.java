package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author predrim
 */
public class AlcanceInstrumento {

    private long instrumentoId;
    private String tipo;
    private String notaMin;
    private String notaMax;

    public AlcanceInstrumento(long instrumentoId, String tipo, String notaMin, String notaMax) {
        
        this.instrumentoId = instrumentoId;
        this.notaMin = notaMin;
        this.notaMax = notaMax;
    }
    
    // Getters e Setters
    public long getInstrumentoId() {
        return instrumentoId;
    }
    
    public void setInstrumentoId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
    } 
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNotaMin() {
        return notaMin;
    }
    
    public void setNotaMin(String novaNotaMin) {
        this.notaMin = novaNotaMin;
    }
    
    public String getNotaMax() {
        return notaMax;
    }
    
    public void setNotaMax(String novaNotaMax) {
        this.notaMax = novaNotaMax;
    } 
}
