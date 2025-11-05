package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.InstrumentoUsaAfinacaoDAO;
import model.InstrumentoUsaAfinacao;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;


/**
 *
 * @author Nascimento
 */
public class InstrumentoAfinacaoControl {
    private InstrumentoUsaAfinacaoDAO instrumentoAfiacaoDAO;
    
    public InstrumentoAfinacaoControl(InstrumentoUsaAfinacaoDAO instrumentoAfiacaoDAO){
        this.instrumentoAfiacaoDAO = instrumentoAfiacaoDAO;
    }
    
    public void adcionarInstrumentoAfiacao(InstrumentoUsaAfinacao ia){
        InstrumentoUsaAfinacao i = new InstrumentoUsaAfinacao(ia.getAfinacaoId(), ia.getInstrumentoId(), ia.getContexto());
        
        instrumentoAfiacaoDAO.inserir(i);
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

