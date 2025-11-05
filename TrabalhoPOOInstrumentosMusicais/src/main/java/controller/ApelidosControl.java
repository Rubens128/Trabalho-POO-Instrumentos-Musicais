/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ApelidosDAO;
import model.Apelidos;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Nascimento
 */
public class ApelidosControl {
    private ApelidosDAO apelidosDAO;
    
    public ApelidosControl(ApelidosDAO apelidosDAO) throws SQLException {
        this.apelidosDAO = apelidosDAO;
    }
    
    public void adcionarApelido(long instrumentoId, String apelido) throws SQLException {
        Apelidos ap = new Apelidos(instrumentoId, apelido);
        
        apelidosDAO.inserir(ap);
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
