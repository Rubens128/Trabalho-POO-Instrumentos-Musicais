/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public enum AfinacaoTransposicao {
    C("C"),
    BB("Bb"),
    EB("Eb"),
    F("F"),
    OUTRO("outro"),
    NAOINFORMADO("nãoInformado");

    private String valor;

    AfinacaoTransposicao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}

