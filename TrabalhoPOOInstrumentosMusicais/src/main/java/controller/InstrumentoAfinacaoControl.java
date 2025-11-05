package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.InstrumentoUsaAfinacaoDAO;
import dao.InstrumentoDAO;
import model.InstrumentoUsaAfinacao;
import model.Instrumento;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;


/**
 *
 * @author Nascimento
 */
public class InstrumentoAfinacaoControl {
    private final InstrumentoUsaAfinacaoDAO instrumentoAfiacaoDAO;
    private final InstrumentoDAO instrumentoDAO;
    
    public InstrumentoAfinacaoControl(){
        this.instrumentoAfiacaoDAO = new InstrumentoUsaAfinacaoDAO();
        this.instrumentoDAO = new InstrumentoDAO();
    }
    
    public Map<String, Long> adicionarInstrumentoAfiacao(long instrumentoId, String contexto){
        
        Map<String, Long> retornos = new HashMap<>();
        
        Instrumento instrumento = instrumentoDAO.buscarPorID("harmonico", instrumentoId);
        
        if(instrumento == null) instrumento = instrumentoDAO.buscarPorID("ritmico", instrumentoId);
        
        if(instrumento == null) instrumento = instrumentoDAO.buscarPorID("melodico", instrumentoId);
        
        if(instrumento == null){
            
            retornos.put("Instrumento", 404L);
            
            return retornos;
        }
        
        InstrumentoUsaAfinacao i = new InstrumentoUsaAfinacao(0, instrumentoId, contexto);
        
        retornos = instrumentoAfiacaoDAO.inserir(i);
        
        return retornos;
    }
    
    public List<InstrumentoUsaAfinacao> listarInstrumentosAfinacao(){
        return instrumentoAfiacaoDAO.listarTodos();
    }
    
    public InstrumentoUsaAfinacao listarInstrumentoAfinacaoporID(String escolha, long id){
        return instrumentoAfiacaoDAO.buscarPorID(escolha, id);
    }
    
    public Map<String, Long> atualizarInstrumentosAfinacao(InstrumentoUsaAfinacao instrumentoUsaAfinacao, String escolha, long id){
        return instrumentoAfiacaoDAO.atualizar(instrumentoUsaAfinacao, escolha, id);
    }
    
    public Map<String, Long> deletarInstrumentoAfinacao(String escolha, long id){
        return instrumentoAfiacaoDAO.deletar(escolha, id);  
    }
    
    
    
}

