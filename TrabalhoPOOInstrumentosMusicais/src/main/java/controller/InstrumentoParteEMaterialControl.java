/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.InstrumentoTemParteEMaterialDAO;
import model.ParteEMaterial;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Nascimento
 */

public class InstrumentoParteEMaterialControl {
   private final InstrumentoTemParteEMaterialDAO parteEMaterialDAO;
   
    public InstrumentoParteEMaterialControl(){
        this.parteEMaterialDAO = new InstrumentoTemParteEMaterialDAO();
    }      
    
    public Map<String, Long> adicionarParteEMaterial(Long instrumentoID, Long materialID, String parteNome) throws SQLException {
        
        Map<String, Long> retornos = new HashMap<>();
        
        ParteEMaterial pm = new ParteEMaterial(instrumentoID, materialID, parteNome);
        
        retornos = parteEMaterialDAO.inserir(pm);
        
        return retornos;
    }
    
    public List<ParteEMaterial> listarTodos() throws SQLException { 
        return parteEMaterialDAO.listarTodos();
    }
    
    public ParteEMaterial listarPorID(long id, String escolha) throws SQLException {
        return parteEMaterialDAO.buscarPorID(id, escolha);
    }
    
    public Map<String, Long> atualizarParteEMaterial(ParteEMaterial instrumentoTemParteEMaterial, long id, String escolha) throws SQLException {
        return parteEMaterialDAO.atualizar(instrumentoTemParteEMaterial, escolha, id);
    }
    
    public Map<String, Long> deletarParteEMaterial(String escolha, long id) throws SQLException {
        return parteEMaterialDAO.deletar(escolha, id);   
    }
}
