

package controller;

import dao.MaterialDAO;
import java.sql.SQLException;
import java.util.HashMap;
import model.Material;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nascimento
 */

public class MaterialControl {
    private MaterialDAO materialDAO;
    
    public MaterialControl( ){
        this.materialDAO = new MaterialDAO();
    }
    
    public Map<String, Long> adicionarMaterial(long id, String nome, String descricao) throws SQLException {
        
        Map<String, Long> retornos = new HashMap<>();
        
        Material m = new Material(id, nome, descricao);
        
        retornos = materialDAO.inserir(m);
        
        return retornos;
    }

    public List<Material> listarMaterial() throws SQLException{
        return materialDAO.listarTodos();
    }
    
    public List<Material> listarMaterialporID(long id) throws SQLException{
        return (List<Material>) materialDAO.buscarPorID(id);
    }
    
    public Map<String, Long> deletarMaterial(long id) throws SQLException {
        return materialDAO.deletar(id);
    }
    
    public Map<String, Long> atualizarMaterial(Material material) throws SQLException{
        return materialDAO.atualizar(material);
    }
}
