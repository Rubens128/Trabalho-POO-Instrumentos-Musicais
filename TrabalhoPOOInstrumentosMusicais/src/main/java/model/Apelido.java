/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class Apelido {
    private long instrumentoId;
    private String apelido;
    
    public Apelido (long instrumentoId, String apelido) {
        this.instrumentoId = instrumentoId;
        this.apelido = apelido;
    }
    
    // Getters e Setters
    public long getInstrumentoId() {
        return instrumentoId;
    }
    
    public void setInstrumentoId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }
    
    public String getApelidos() {
        return apelido;
    }
    
    public void setApelidos(String apelido) {
        this.apelido = apelido;
    }
    
}
