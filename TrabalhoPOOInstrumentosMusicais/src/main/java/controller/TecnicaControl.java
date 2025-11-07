/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.TecnicaDAO;
import model.Tecnica;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nascimento
 */
public class TecnicaControl {
    private TecnicaDAO tecnicaDAO;
    
    public TecnicaControl(){
        this.tecnicaDAO = new TecnicaDAO();
    }
    
    public Map<String, Long> adicionarTecnica(String nome, String descricao) throws SQLException {
        
        Map<String, Long> retornos = new HashMap<>();
        
        Tecnica t = new Tecnica(0, nome, descricao);
        
        retornos = tecnicaDAO.inserir(t);
        
        return retornos;
    }
    
    public List<Tecnica> listarTecnicas() throws SQLException {
        return tecnicaDAO.listarTodos();
    }
    
    public Tecnica listarTecnicaporId(long id) throws SQLException {
        return tecnicaDAO.buscarPorID(id);
    }
    
    public Map<String, Long> atualizarTecnicas(Tecnica tecnica) throws SQLException {
  
        return tecnicaDAO.atualizar(tecnica);
    }
    
    public Map<String, Long> deleteTecnica(long id) throws SQLException {
        return tecnicaDAO.deletar(id);
    }
}
