/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.InstrumentoDAO;
import model.Instrumento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Afinacao;
import model.AlcanceInstrumento;
import model.Audio;
import model.FamiliaInstrumento;
import model.ParteEMaterial;

/**
 *
 * @author Nascimento
 */
public class InstrumentosControl {
    private final InstrumentoDAO instrumentoDAO;
    
    public InstrumentosControl(InstrumentoDAO instrumentoDAO){
        this.instrumentoDAO = instrumentoDAO;
    }
    
    public void adcionarInstrumento(long id, long familiaId, String nome, String classificacaoSonoridade, String descricao, String historia, FamiliaInstrumento familiaInstrumento, AlcanceInstrumento alcanceInstrumento, ArrayList<Audio> audios, ArrayList<ParteEMaterial> partesEMateriais, ArrayList<Afinacao> afinacoes){
       Instrumento i = new Instrumento.Builder(id, familiaId, nome, classificacaoSonoridade)
                .setDescricao(descricao)
                .setHistoria(historia)
                .setFamiliaInstrumento(familiaInstrumento)
                .setAlcanceInstrumento(alcanceInstrumento)
                .setAudios(audios)
                .partesEMateriais(partesEMateriais)
                .afinacoes(afinacoes)
                .build();
       
       instrumentoDAO.inserir(i, );
    }
    
    public List<Instrumento> listarInstrumentos(){
        return instrumentoDAO.listarTodos();
    }
}
