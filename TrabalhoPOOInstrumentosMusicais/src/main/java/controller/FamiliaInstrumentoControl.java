/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.FamiliaInstrumento;
import dao.FamiliaInstrumentoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Nascimento
 */
public class FamiliaInstrumentoControl {
    private FamiliaInstrumentoDAO familiaInstrumentoDAO;
    
    public FamiliaInstrumentoControl(FamiliaInstrumentoDAO familiaInstrumento){
        this.familiaInstrumentoDAO = familiaInstrumentoDAO;
    }
    
    public void adicionarFamilia(FamiliaInstrumento fi) throws SQLException {
        FamiliaInstrumento f = new FamiliaInstrumento(fi.getId(), fi.getNome(), fi.getDescricao());
        
        familiaInstrumentoDAO.inserir(f);
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
