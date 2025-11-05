
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.InstrumentoDAO;
import dao.FamiliaInstrumentoDAO;
import dao.AudioDAO;
import dao.AfinacaoDAO;
import dao.MaterialDAO;
import dao.ParteDAO;
import dao.InstrumentoTemParteEMaterialDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import model.InstrumentoHarmonico;
import model.InstrumentoMelodico;
import model.InstrumentoRitmico;
import model.Instrumento;
import model.FamiliaInstrumento;
import model.Audio;
import model.Afinacao;
import model.Material;
import model.Parte;
import model.AfinacaoTransposicao;
import model.CategoriaPercussao;
import model.TocadoCom;
import model.ParteEMaterial;

/**
 *
 * @author Nascimento
 */
public class InstrumentosControl {

    private final InstrumentoDAO instrumentoDAO;
    private final FamiliaInstrumentoDAO familiaInstrumentoDAO;
    private final AudioDAO audioDAO;
    private final AfinacaoDAO afinacaoDAO;
    private final MaterialDAO materialDAO;
    private final ParteDAO parteDAO;
    private final InstrumentoTemParteEMaterialDAO instrumentoTemParteEMaterialDAO;

    public InstrumentosControl() {
        this.instrumentoDAO = new InstrumentoDAO();
        this.familiaInstrumentoDAO = new FamiliaInstrumentoDAO();
        this.audioDAO = new AudioDAO();
        this.afinacaoDAO = new AfinacaoDAO();
        this.materialDAO = new MaterialDAO();
        this.parteDAO = new ParteDAO();
        this.instrumentoTemParteEMaterialDAO = new InstrumentoTemParteEMaterialDAO();
    }

    public Map<String, Long> adicionarInstrumento(long id, Long familiaId, String nome, String classificacaoSonoridade, String historia,
            String descricao, Long afinacoesId, Long audioIds, String parteNome, String parteDescricao, Long materialId, 
            String especializacao, Object opcao1, Object opcao2, Object opcao3) throws SQLException { 

        Instrumento InstrumentoEspecializado = null;
                
        Map<String, Long> retornos = new HashMap<>();
        
        FamiliaInstrumento familiaInstrumento = null;
        ArrayList<Audio> audio = new ArrayList();
        ArrayList<Afinacao> afinacao = new ArrayList();
        ArrayList<Material> material = new ArrayList();
        
        if(familiaId != null){
            
            familiaInstrumento = familiaInstrumentoDAO.buscarPorID(familiaId);
            
            if(familiaInstrumento == null) retornos.put("Familia", 404L);
        }
        
        if(audioIds != null){
            
            audio.add(audioDAO.buscarPorID(audioIds)); 

            if(audio.isEmpty()) retornos.put("Audio", 404L);
            
        }
        
        if(afinacoesId != null){
            
            afinacao.add(afinacaoDAO.buscarPorID(afinacoesId));
            
            if(afinacao.isEmpty()) retornos.put("Afinacao", 404L);
        }
        
        if(materialId != null){
            
            material.add(materialDAO.buscarPorID(materialId));
            
            if(material.isEmpty()) retornos.put("Material", 404L);
        }
        
        if(familiaInstrumento == null || audio.isEmpty() || afinacao.isEmpty() || material.isEmpty()) return retornos;
        
        ArrayList<Parte> parte = new ArrayList();
        
        ParteControl parteControl = new ParteControl();
        
        retornos = parteControl.adicionarParte(id, parteNome, parteDescricao);
        
        if(retornos.get("Codigo") != 200){
            
            return retornos;
        }
        
        parte.addAll(parteControl.listarPartesporID(retornos.get("ID")));
        
        retornos = new HashMap<>();
        
        if(parte.isEmpty()){
            
            retornos.put("Codigo", 500L);
            
            return retornos;   
        }
        
        
        retornos = new HashMap<>();
        
        //Switch fara a separacao de cada tipo de especializacao;
        //caso nao tenha, ele o instanteof no dao vai fazer tudo e deixar sem;

        switch (especializacao) {
            case "harmonico" -> {
                InstrumentoEspecializado = new InstrumentoHarmonico.Builder(
                        id,
                        familiaId,
                        nome,
                        classificacaoSonoridade,
                        (int) opcao1,
                        (boolean) opcao2,
                        (boolean) opcao3
                )
                        .familiaInstrumento(familiaInstrumento)
                        .historia(historia != null ? historia : null)
                        .descricao(descricao != null ? descricao : null)
                        .afinacoes(afinacao)
                        .audios(audio)
                        .partes(parte)
                        .materiais(material)
                        .build();
            }
            case "melodico" -> {
                InstrumentoEspecializado = new InstrumentoMelodico.Builder(
                        id,
                        familiaId,
                        nome,
                        classificacaoSonoridade,
                        (boolean) opcao1,
                        (AfinacaoTransposicao) opcao2,
                        (boolean) opcao3
                )
                        .familiaInstrumento(familiaInstrumento)
                        .historia(historia != null ? historia : null)
                        .descricao(descricao != null ? descricao : null)
                        .afinacoes(afinacao)
                        .audios(audio)
                        .partes(parte)
                        .materiais(material)
                        .build();
            }
            case "ritmico" -> {
                InstrumentoEspecializado = new InstrumentoRitmico.Builder(
                        id,
                        familiaId,
                        nome,
                        classificacaoSonoridade,
                        (boolean)opcao1,
                        (CategoriaPercussao) opcao2,
                        (TocadoCom) opcao3
                )
                        .familiaInstrumento(familiaInstrumento)
                        .historia(historia != null ? historia : null)
                        .descricao(descricao != null ? descricao : null)
                        .afinacoes(afinacao)
                        .audios(audio)
                        .partes(parte)
                        .materiais(material)
                        .build();
            }
            default ->
                System.out.println("especialidade nao definada ou invalida");
        }
        if(InstrumentoEspecializado != null){
        
            retornos = instrumentoDAO.inserir(InstrumentoEspecializado, especializacao);
        
            if(retornos.get("Codigo") != 200){
                
                return retornos;
            }
            
        }
        
        ParteEMaterial parteEMaterial = new ParteEMaterial(retornos.get("ID"), materialId, parteNome);
        
        retornos = new HashMap<>();
        
        retornos = instrumentoTemParteEMaterialDAO.inserir(parteEMaterial);
        
        return retornos;
    }

    public List<Instrumento> listarInstrumentos(String especializacao) throws SQLException{
        return instrumentoDAO.listarTodos(especializacao);
    }

    public Instrumento listarInstrumentosporID(long id, String especializacao) throws SQLException{
        return instrumentoDAO.buscarPorID(especializacao, id);
    }

    public Map<String, Long> atualizarInstrumento(Instrumento instrumento, String especializacao) throws SQLException {
        return instrumentoDAO.atualizar(instrumento, especializacao);
    }

    public Map<String, Long> deletarInstrumento(String especializacao, long id) throws SQLException {
        return instrumentoDAO.deletar(especializacao, id);
    }
}
