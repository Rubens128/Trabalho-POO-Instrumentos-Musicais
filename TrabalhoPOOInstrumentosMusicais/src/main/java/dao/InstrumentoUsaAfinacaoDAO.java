/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import model.InstrumentoUsaAfinacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class InstrumentoUsaAfinacaoDAO {
    
    // Abstração de conexãoBD    
    private final ConexaoBD conexao = new ConexaoMySQL();
    
    // Códigos HTTP para indicar sucesso/erro lógico
    private final long bemSucedido = 200;
    private final long malSucedido = 404;
    
    public InstrumentoUsaAfinacaoDAO(){}
    
    // Fecha operações comuns a todos os momentos
    // Restaura autocommit para true e fecha conexão
    // Erro != 0 indica fechamento de um metódo que não utiliza 'retornos' e nem 'erros'
    private void finalizarMetodos(Connection c, Map<String, Long> retornos, long erros) {
        
        if (c != null) {
            try {
                if(erros != 0) c.setAutoCommit(true);
                conexao.fecharConexao(c);
            } catch (SQLException e) {
                System.out.println("Erro ao restaurar o autocommit: " + e.getMessage());
                if (erros != 0) retornos.put("ErroFecharConexao", (long)e.getErrorCode());
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
                if (erros != 0) retornos.put("ErroFecharConexao", erros);
            }
        }
    }
    
    // Tenta realizar rollback caso haja falha.
    private void tentarRollback(Connection c, Map<String, Long> retornos) {
        if (c == null) {
            System.out.println("Rollback mal-sucedido, sem conexão com o banco de dados.");
            return;
        }
        try {
            c.rollback();
            System.out.println("Rollback realizado com sucesso!");
        } catch (SQLException rollbackEx) { 
            System.out.println("Erro ao tentar rollback" + rollbackEx.getMessage());
            retornos.put("ErroRollback", (long)rollbackEx.getErrorCode()); 
        }
    }
    
    //Método de verificação se o usuário digitou uma escolha válida
    boolean isEscolha(String escolha) {
        
        if(escolha.equals("instrumento")
           || escolha.equals("afinacao")) {
            
            return true;
            
        } else {
            
            System.out.printf("\nEscolha inválida, tente uma das opções abaixo:\n"
                    + "\n\"instrumento\""
                    + "\n\"afinacao\"\n");
            
            return false;
        }
    }
    
    /*
    Insere valores na tabela instrumento_afinacao. 
    */
    public Map<String, Long> inserir(InstrumentoUsaAfinacao instrumentoUsaAfinacao){
        
        String sql = "INSERT INTO instrumento_afinacao (instrumento_id, afinacao_id, contexto) VALUES (?, ?, ?)";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        boolean sucesso = false;
        
        try {
            c = conexao.obterConexao();
            c.setAutoCommit(false);
            
            try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                
                ps.setLong(1, instrumentoUsaAfinacao.getInstrumentoId());
                ps.setLong(2, instrumentoUsaAfinacao.getAfinacaoId());
                ps.setString(3, instrumentoUsaAfinacao.getContexto());
                
                ps.executeUpdate();
               
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()) retornos.put("ID", rs.getLong(1));
                    else System.out.println("Erro ao retornar ID");
                }
                
                 c.commit(); 
                 sucesso = true; 
                 System.out.println("Relação instrumento-afinação inserida com sucesso!");
            } 
            
        } catch(SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
            retornos.put("Codigo", (long)e.getErrorCode()); 
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);
        } catch(Exception e) {
            System.out.println("Erro inesperado ao inserir relação instrumento-afinação: " + e.getMessage());
            retornos.put("Codigo", erros); 
            erros -= 1;
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);
        } finally {
            finalizarMetodos(c, retornos, erros);
        }
 
        if(sucesso) retornos.put("Codigo", (long)bemSucedido);
        return retornos;
    }
    
    /*
    Seleciona todos os valores da tabela instrumento_afinacao.
    */
    public List<InstrumentoUsaAfinacao> listarTodos(){
        
        List<InstrumentoUsaAfinacao> instrumentosAfinacoes = new ArrayList<>();
        String sql = "SELECT * FROM instrumento_afinacao";
        Connection c = null;
        
        try {
            c = conexao.obterConexao();
            try (PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()){
                
                /* Criação dos objetos a partir dos registros da tabela */
                while(rs.next()){
                    InstrumentoUsaAfinacao ia = new InstrumentoUsaAfinacao(
                            
                            rs.getLong("instrumento_id"),
                            rs.getLong("afinacao_id"),
                            rs.getString("contexto")        
                    );
                    
                    instrumentosAfinacoes.add(ia);
                }
            }  
            
        } catch(SQLException e) {
            
            System.out.println("Erro SQL: " + e.getMessage());
            
        } catch(Exception e) {
            
            System.out.println("Erro inesperado ao listar relações instrumento-afinação: " + e.getMessage());
            
        } finally {
            
            /*
            Nos métodos de SELECT não há varíavel 'retorno' e nem o marcador 'erro'
            pois em caso de erro, o objeto será null, e isso é tratado na view. 
            Além disso, métodos relacionados ao comando SELECT não possuem commit e rollback
            por não se tratar de uma operação de alteração de dados.
            Portanto, são passados como null e 0 para finalizarMetodos(), para indicar que o 
            método a ser finalizado está relacionado a uma operação de SELECT.
            */
            finalizarMetodos(c, null, 0); 
        }
        return instrumentosAfinacoes;
    }
    
    /*
    Seleciona todos os valores da tabela instrumento_afinacao na tupla especificada pelo ID
    */
    public InstrumentoUsaAfinacao buscarPorID(String escolha, long id){
        
        String sql;
        InstrumentoUsaAfinacao instrumentoUsaAfinacao = null;
        Connection c = null;
        
        if(isEscolha(escolha)) {
        
            if(escolha.equals("instrumento")) sql = "SELECT * FROM instrumento_afinacao WHERE instrumento_id = ?";
            else sql = "SELECT * FROM instrumento_afinacao WHERE afinacao_id = ?";

            try {
                c = conexao.obterConexao();
                try (PreparedStatement ps = c.prepareStatement(sql)){
                     ps.setLong(1, id);
                     try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {

                           instrumentoUsaAfinacao = new InstrumentoUsaAfinacao(

                                   rs.getLong("instrumento_id"),
                                   rs.getLong("afinacao_id"),
                                   rs.getString("contexto")
                           );

                       } else {
                           System.out.println("Nenhuma relação instrumento-afinação encontrada com o ID informado.");
                       }
                    }
                }

            } catch (SQLException e) {

                System.out.println("Erro SQL ao buscar relação instrumento-afinação: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Erro inesperado ao buscar relação instrumento-afinação: " + e.getMessage());

            } finally {

                finalizarMetodos(c, null, 0);
            }
        }    
        
        return instrumentoUsaAfinacao;
    }
    
    /*
    Atualiza valores da tupla especificada através do ID na tabela instrumento_afinacao  
    */
    public Map<String, Long> atualizar(InstrumentoUsaAfinacao instrumentoUsaAfinacao, String escolha, long id){
        
        String sql;
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        if(isEscolha(escolha)) {
        
            if(escolha.equals("instrumento")) sql = "UPDATE instrumento_afinacao SET instrumento_id = ?, afinacao_id = ?, contexto = ? WHERE instrumento_id = ?";
            else sql = "UPDATE instrumento_afinacao SET instrumento_id = ?, afinacao_id = ?, contexto = ? WHERE afinacao_id = ?";

            try {

                c = conexao.obterConexao();
                c.setAutoCommit(false);

                try (PreparedStatement ps = c.prepareStatement(sql)) {

                    ps.setLong(1, instrumentoUsaAfinacao.getInstrumentoId());
                    ps.setLong(2, instrumentoUsaAfinacao.getAfinacaoId());
                    ps.setString(3, instrumentoUsaAfinacao.getContexto());
                    ps.setLong(4, id);

                    int atualizou = ps.executeUpdate();
                    c.commit();

                    if(atualizou > 0) System.out.println("Relação instrumento-afinação atualizada com sucesso!");
                    else  {
                        System.out.println("Nenhuma relação instrumento-afinação encontrada para atualizar.");
                        retornos.put("Codigo", malSucedido);
                        System.out.println("Tentando rollback...");
                        tentarRollback(c, retornos);
                    }
                }    
            } catch (SQLException e) {

                System.out.println("Erro SQL ao atualizar relação instrumento-afinação: " + e.getMessage());
                retornos.put("Codigo", (long)e.getErrorCode()); 
                System.out.println("Tentando rollback...");
                tentarRollback(c, retornos);

            } catch (Exception e) {

                System.out.println("Erro inesperado ao atualizar relação instrumento-afinação: " + e.getMessage());
                retornos.put("Codigo", erros);  
                System.out.println("Tentando rollback...");
                tentarRollback(c, retornos);

            } finally {

                finalizarMetodos(c, retornos, erros);
            }

            if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        }
        
        return retornos;
    }
    
    /*
    Deleta valores da tupla especificada através do ID na tabela instrumento_afinacao  
    */
    public Map<String, Long> deletar( String escolha, long id) {
        
        String sql;
        
        if(escolha.equals("instrumento")) sql = "DELETE FROM instrumento_afinacao WHERE instrumento_id = ?";
        else sql = "DELETE FROM instrumento_afinacao WHERE afinacao_id = ?";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        try {
            
            c = conexao.obterConexao();
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                
                ps.setLong(1, id);
                int deletou = ps.executeUpdate();
                c.commit();

                if (deletou > 0) {
                    
                    System.out.println("Relação instrumento-afinação deletada com sucesso!");
                    
                } else {
                    
                    System.out.println("Nenhuma relação instrumento-afinação encontrada com esse ID.");
                    retornos.put("Codigo", malSucedido);
                    System.out.println("Tentando rollback...");
                    tentarRollback(c, retornos);
                }
            }
        } catch (SQLException e) {
            // 1451 - erro de chave estrangeira (FK constraint)
            if (e.getErrorCode() == 1451) 
                System.out.println("Não é possível deletar: existem dados de outra entidade relacionados a esta relação instrumento-afinação."); 
            else System.out.println("Erro SQL: " + e.getMessage());
            retornos.put("Codigo", (long)e.getErrorCode());
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);
        } catch (Exception e) {
            System.out.println("Erro inesperado ao deletar relação instrumento-afinação: " + e.getMessage());
            retornos.put("Codigo", erros);
        } finally {
            finalizarMetodos(c, retornos, erros);
        }
        
        if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        return retornos;
    }
}
