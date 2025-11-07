

package controller;

import dao.ParteDAO;
import model.Parte;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Nascimento
 */
public class ParteControl {
    private ParteDAO parteDAO;
    
    public ParteControl(){
        this.parteDAO = new ParteDAO();
    }
    
    public Map<String, Long> adicionarParte(long instrumentoId, String nome, String descricao){
        
        Map<String, Long> retornos = new HashMap<>();
        
        Parte p = new Parte(instrumentoId, nome, descricao);
        
        retornos = parteDAO.inserir(p);
        
        return retornos;
    }
    
    public List<Parte> listarPartes(){
        return parteDAO.listarTodos();
    }

    public Parte listarPartesporID(long instrumentoId){
        return parteDAO.buscarPorID(instrumentoId);
    }
    
    public Map<String, Long> deleterParte(long instrumentoId){
        return parteDAO.deletar(instrumentoId);
    }
    
    public Map<String, Long> atualizarParte(Parte parte, long instrumentoId){
        
        return parteDAO.atualizar(parte, instrumentoId);
    }
}

