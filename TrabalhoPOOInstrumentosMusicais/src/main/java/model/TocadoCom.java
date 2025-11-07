/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public enum TocadoCom {
    BAQUETA("baqueta"),
    MAO("mao"),
    HIBRIDO("hibrido"),
    OUTRO("outro"),
    NAOINFORMADO("nãoInformado");

    private final String valor;

    TocadoCom(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
