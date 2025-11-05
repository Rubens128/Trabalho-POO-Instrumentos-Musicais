/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.TecnicaDAO;
import model.Tecnica;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nascimento
 */
public class TecnicaControl {
    private TecnicaDAO tecnicaDAO;
    
    public TecnicaControl(TecnicaDAO tecnicaDAO){
        this.tecnicaDAO = tecnicaDAO;
    }
    
    public void adcionarTecnica(long id, String nome, String descricao) throws SQLException {
        Tecnica t = new Tecnica(id, nome, descricao);
        
        tecnicaDAO.inserir(t);
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
