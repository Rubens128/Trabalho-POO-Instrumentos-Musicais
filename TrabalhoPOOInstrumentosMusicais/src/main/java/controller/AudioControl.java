/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AudioDAO;
import dao.AudioUtilizaTecnicaDAO;
import dao.InstrumentoDAO;
import dao.TecnicaDAO;
import model.Audio;
import model.AudioUtilizaTecnica;
import model.Instrumento;
import model.Tecnica;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author Nascimento
 */
public class AudioControl {
    private final AudioDAO audioDAO;
    private AudioUtilizaTecnicaDAO audioTecnicaDAO;
    private final TecnicaDAO tecnicaDAO;
    private final InstrumentoDAO instrumentoDAO;
    
    public AudioControl(){
        this.audioDAO = new AudioDAO();
        this.audioTecnicaDAO = new AudioUtilizaTecnicaDAO();
        this.tecnicaDAO = new TecnicaDAO();
        this.instrumentoDAO = new InstrumentoDAO();
    }
    
    public Map<String, Long> adicionarAudio(long instrumentoID, String titulo, String nota, 
            Long oitava, String arquivo, String descricao, Long bpm, String creditos, long tecnicaID) throws SQLException {
        
        //ele ira verificar se o usuario colocou uma id valida de tecnica no audio
        //caso positico ele relacionar o id audio com a tecnica, cao contrario ele ira direto

        Map<String, Long> retornos = new HashMap<>();
        
        Tecnica tecnicaEncontrar = tecnicaDAO.buscarPorID(tecnicaID);
        
        if (tecnicaEncontrar == null && tecnicaID != -1L){
           
           retornos.put("Tecnica", 404L);
           
           return retornos;
        }
        
        Instrumento instrumentoEncontrar = instrumentoDAO.buscarPorID("harmonico", instrumentoID);
        if(instrumentoEncontrar == null) instrumentoEncontrar = instrumentoDAO.buscarPorID("melodico", instrumentoID);
        if(instrumentoEncontrar == null) instrumentoEncontrar = instrumentoDAO.buscarPorID("ritmico", instrumentoID);
        
        if (instrumentoEncontrar == null){
           
           retornos.put("Instrumento", 404L);
           
           return retornos;
        }

        Audio ad = new Audio.Builder(0, instrumentoID, titulo, nota, oitava, arquivo)
                .descricao(descricao != null ? descricao : null)
                .bpm(bpm != null ? bpm : null)
                .creditoGravacao(creditos != null ? creditos : null)
                .tecnica(tecnicaID == -1 ? null : tecnicaEncontrar)
                .build();
        
         retornos = audioDAO.inserir(ad); 
         
         if(retornos.get("Codigo") != 200){
             
             return retornos;
         }
         
        AudioUtilizaTecnica rela;
        
        if(tecnicaEncontrar != null){
            
            rela = new AudioUtilizaTecnica(retornos.get("ID"), tecnicaEncontrar.getId());
            
            retornos = audioTecnicaDAO.inserir(rela);
        }
        
        return retornos;
    }
    
    public List<Audio> listarAudio() throws SQLException {
        return audioDAO.listarTodos();
    }
    
    public Audio listarAudioporId(long id) throws SQLException {
        return audioDAO.buscarPorID(id);
    }
    
    public Map<String, Long> atualizarAudio(Audio audio) throws SQLException {
       
        return audioDAO.atualizar(audio);
    }
    
    public Map<String, Long> deletarAudio(long id) throws SQLException {
        return audioDAO.deletar(id);
    }
    
    public List<AudioUtilizaTecnica> listarRelaAudioTecnica() throws SQLException {
        return audioTecnicaDAO.listarTodos();
    }

    public AudioUtilizaTecnica listarAudioTecnicaporId(long id) throws SQLException {
        return audioTecnicaDAO.buscarPorID(id, "audio");
    }    
    
    public Map<String, Long> deletarAudioTecnico(long id) throws SQLException {
        return audioTecnicaDAO.deletar(id, "audio");
    }
    
    public Map<String, Long> atualizarAudioTecnico(AudioUtilizaTecnica audioUtilizaTecnica, long id) throws SQLException {
        return audioTecnicaDAO.atualizar(audioUtilizaTecnica, id, "audio");
    }
    
}
