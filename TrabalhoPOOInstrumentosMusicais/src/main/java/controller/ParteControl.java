package controller;

import dao.ParteDAO;
import model.Parte;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Nascimento
 */
public class ParteControl {
    private ParteDAO parteDAO;
    
    public ParteControl(ParteDAO parteDAO){
        this.parteDAO = parteDAO;
    }
    
    public void adicionarParte(long instrumentoId, String nome, String descricao){
        Parte p = new Parte(instrumentoId, nome, descricao);
        parteDAO.inserir(p);
    }
    
    public List<Parte> listarPartes(){
        return parteDAO.listarTodos();
    }

    public List<Parte> listarPartesporID(long instrumentoId){
        return (List<Parte>) parteDAO.buscarPorID(instrumentoId);
    }
    
    public Map<String, Long> deleterParte(long instrumentoId){
        return parteDAO.deletar(instrumentoId);
    }
    
    public Map<String, Long> atualizarParte(Parte parte){
        return parteDAO.atualizar(parte);
    }
}

