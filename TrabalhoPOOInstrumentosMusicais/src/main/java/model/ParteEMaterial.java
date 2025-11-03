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
    private long instrumentoId;
    private long materialId;
    private String parteNome;
    
    public ParteEMaterial(long instrumentoId, long materialId, String parteNome) {
        
        this.instrumentoId = instrumentoId;
        this.materialId = materialId;
        this.parteNome = parteNome;
    }

    // Getters e Setters
    public long getInstrumentoId() {
        return instrumentoId;
    }
       
    public void setInstrumentoId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    public long getMaterialId() {
        return materialId;
    }
    
    public void setMaterialID(long materialId) {
        this.materialId = materialId;
    }

    public String getParteNome() {
        return parteNome;
    }

    public void setParteNome(String parteNome) {
        this.parteNome = parteNome;
    }
}