/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public enum CategoriaPercussao {
    MEMBRANOFONE("membranofone"),
    IDIOFONE("idiofone"),
    CORDOFONE("cordofone"),
    AEROFONE("aerofone"),
    ELETROFONE("eletrofone"),
    OUTRO("outro"),
    NAOINFORMADO("nãoInformado");

    private final String valor;

    CategoriaPercussao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
