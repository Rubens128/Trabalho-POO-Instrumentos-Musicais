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

/**
 *
 * @author Nascimento
 */

public class InstrumentoParteEMaterialControl {
   private final InstrumentoTemParteEMaterialDAO parteEMaterialDAO;
   
    public InstrumentoParteEMaterialControl(InstrumentoTemParteEMaterialDAO parteEMaterialDAO){
        this.parteEMaterialDAO = parteEMaterialDAO;
    }      
    
    public void adcionarParteEMaterial(ParteEMaterial a) throws SQLException {
        ParteEMaterial pm = new ParteEMaterial(a.getInstrumentoId(), a.getMaterialId(), a.getParteNome());
        
        parteEMaterialDAO.inserir(pm);
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
