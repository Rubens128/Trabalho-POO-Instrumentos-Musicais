/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.AfinacaoDAO;
import model.Afinacao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nascimento
 */
public class AfinacaoControl {
    private AfinacaoDAO afinacaoDAO;
    
    public AfinacaoControl(AfinacaoDAO afinacaoDAO){
        this.afinacaoDAO = afinacaoDAO;
    }
    
    public void adicionarAfinacao(long id, String nome, String descricao, String referencia, String contextoAfinacao) throws SQLException {
        Afinacao a = new Afinacao.Builder(id, nome)
                .descricao(descricao)
                .referencia(referencia)
                .contextoAfinacao(contextoAfinacao)
                .build();
        
        afinacaoDAO.inserir(a);
    }
    
    public List<Afinacao> listarAfinacao() throws SQLException {
        return afinacaoDAO.listarTodos();
    }
    
    public Afinacao listarAfinacaoporID(long id) throws SQLException {
        return afinacaoDAO.buscarPorID(id);
    }
    
    public Map<String, Long> atualizarAfinacao(Afinacao afinacao) throws SQLException {
        return afinacaoDAO.atualizar(afinacao);
    }
    
    public Map<String, Long> deletarAfinacao(long id) throws SQLException {
        return afinacaoDAO.deletar(id);
    }
}
