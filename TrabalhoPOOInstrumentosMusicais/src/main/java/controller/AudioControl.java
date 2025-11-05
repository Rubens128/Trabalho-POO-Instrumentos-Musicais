/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AudioDAO;
import dao.AudioUtilizaTecnicaDAO;
import dao.TecnicaDAO;
import model.Audio;
import model.AudioUtilizaTecnica;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import model.Tecnica;


/**
 *
 * @author Nascimento
 */
public class AudioControl {
    private final AudioDAO audioDAO;
    private AudioUtilizaTecnicaDAO audioTecnicaDAO;
    private final TecnicaDAO tecnicaDAO;
    
    public AudioControl(AudioDAO audioDAO, AudioUtilizaTecnica audioUltilizaTecnicaDAO, TecnicaDAO tecnicaDAO){
        this.audioDAO = audioDAO;
        this.audioTecnicaDAO = audioTecnicaDAO;
        this.tecnicaDAO = tecnicaDAO;
    }
    
    public void adicionarAudio(Audio a, Tecnica t) throws SQLException {
        
        Tecnica tecnicaEncontrar = tecnicaDAO.buscarPorID(t.getId()); 
        if (tecnicaEncontrar == null){
           System.out.println("tecnica nao encontrada");
           return;
        } 

        Audio ad = new Audio.Builder(a.getId(), a.getInstrumentoID(), a.getTitulo(), a.getNota(), a.getOitava(), a.getArquivo())
                .descricao(a.getDescricao())
                .bpm(a.getBpm())
                .creditoGravacao(a.getCreditoGravacao())
                .tecnica(tecnicaEncontrar)
                .build();
        
        audioDAO.inserir(ad); 
        
        AudioUtilizaTecnica rela = new AudioUtilizaTecnica(a.getId(), tecnicaEncontrar.getId());
                
        audioTecnicaDAO.inserir(rela);
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
