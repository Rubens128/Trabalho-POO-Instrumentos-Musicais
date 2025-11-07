/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ApelidosDAO;
import model.Apelidos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Nascimento
 */
public class ApelidosControl {
    private final ApelidosDAO apelidosDAO;
    
    public ApelidosControl() throws SQLException {
        this.apelidosDAO = new ApelidosDAO();
    }
    
    public Map<String, Long> adicionarApelido(long instrumentoId, String apelido) throws SQLException {
        
        Map<String, Long> retornos = new HashMap<>();
        
        Apelidos ap = new Apelidos(instrumentoId, apelido);
        
        retornos = apelidosDAO.inserir(ap);
        
        return retornos;
    }
    
    public List<Apelidos> listarApelidos() throws SQLException {
        return apelidosDAO.listarTodos();
    }
    
    public Apelidos listarApelidosporID(long id) throws SQLException {
        return apelidosDAO.buscarPorID(id);
    }
    
    public Map<String, Long> atualizarApelidos(Apelidos apelidos) throws SQLException {
        
        return apelidosDAO.atualizar(apelidos);
    }
    
    public Map<String, Long> deletarApelidos(long instrumentoId) throws SQLException {
        return apelidosDAO.deletar(instrumentoId);
    }
}
