/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.FamiliaInstrumento;
import dao.FamiliaInstrumentoDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Nascimento
 */
public class FamiliaInstrumentoControl {
    private final FamiliaInstrumentoDAO familiaInstrumentoDAO;
    
    public FamiliaInstrumentoControl(){
        this.familiaInstrumentoDAO = new FamiliaInstrumentoDAO();
    }
    
    public Map<String, Long> adicionarFamilia(String nome, String descricao) throws SQLException {
        
        FamiliaInstrumento f = new FamiliaInstrumento(0 ,nome, descricao);
        
        Map<String, Long> retornos = new HashMap<>();
        
        retornos = familiaInstrumentoDAO.inserir(f);
        
        return retornos;
    }
    
    public List<FamiliaInstrumento> listarFamilia() throws SQLException {
        return familiaInstrumentoDAO.listarTodos();
    }
    
    public FamiliaInstrumento listarFamiliaporId(long id) throws SQLException { 
        return familiaInstrumentoDAO.buscarPorID(id);
    }
    
    public Map<String, Long> atualizarFamilia(FamiliaInstrumento familiaInstrumento) throws SQLException {
        
        return familiaInstrumentoDAO.atualizar(familiaInstrumento);
    }
    
    public Map<String, Long> deletarFamilia(long id) throws SQLException {
        return familiaInstrumentoDAO.deletar(id);
    }
        
        
}
