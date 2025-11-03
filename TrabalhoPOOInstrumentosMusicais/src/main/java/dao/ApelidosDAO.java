/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import model.Apelidos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/*
REVER LÓGICA DE RETORNO DA CHAVE PRIMARIA
E SE TROCA PRO SINGULAR
*/

public class ApelidosDAO {
    
    // Abstração de conexãoBD    
    private final ConexaoBD conexao = new ConexaoMySQL();
    
    // Códigos HTTP para indicar sucesso/erro lógico
    private final long bemSucedido = 200;
    private final long malSucedido = 404;
    
    public ApelidosDAO(){}
    
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
    // Se o Rollback falhar o codigo de erro é introduzido em 'retornos'
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
    
    /*
    Insere valores na tabela apelidos. 
    Os seguintes padrões seguem para os métodos de atualizar(), e deletar():
    
    Introduz em 'retornos' a chave "Codigo" com valor 200 em caso de conseguir capturar o ID com sucesso.
    
    Em caso de erro retorna um código de erro ou um marcador em caso de erro genérico
    como valor de "Codigo". 
    */
    public Map<String, Long> inserir(Apelidos apelidos){
        
        String sql = "INSERT INTO apelidos (instrumento_id, apelido)"
                + "VALUES (?, ?)";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1; // usado como marcador de erro genérico
        boolean sucesso = false; // flag de sucesso para definir "Codigo" 200 ao final
        
        try {
            
            c = conexao.obterConexao();
            
            c.setAutoCommit(false); // inicia transação
            
            // try-with-resources garante fechamento de PreparedStatement e ResultSet
            try (PreparedStatement ps = c.prepareStatement(sql)){
                
                ps.setLong(1, apelidos.getInstrumentoId());
                ps.setString(2, apelidos.getApelidos());

                ps.executeUpdate();
                
                 c.commit(); // commit do insert
                 
                 sucesso = true; // alteração da flag para indicar operação bem-sucedida
                 
                 System.out.println("Apelidos inseridos com sucesso!");
            } 
            
        } catch(SQLException e) {

                System.out.println("Erro SQL: " + e.getMessage());
                retornos.put("Codigo", (long)e.getErrorCode()); 
                
                System.out.println("Tentando rollback...");
                
                tentarRollback(c, retornos);

        } catch(Exception e) {

               System.out.println("Erro inesperado ao inserir apelidos: " + e.getMessage());
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
    Seleciona todos os valores da tabela apelidos.
    */
    
    public List<Apelidos> listarTodos(){
        
        List<Apelidos> apelidos = new ArrayList<>();
        String sql = "SELECT * FROM apelidos";
        Connection c = null;
        
        try {
            
            c = conexao.obterConexao();
            
            try (PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()){
                
                /* 
                Criação dos objetos, a partir dos registros da tabela,
                enquanto e se existerem.
                */
                while(rs.next()){

                    Apelidos a = new Apelidos(rs.getLong("instrumento_id"), rs.getString("apelido"));
                    apelidos.add(a);
                }
            }  
            
        } catch(SQLException e) {
         
            System.out.println("Erro SQL: " + e.getMessage());
            
        } catch(Exception e) {
            
            System.out.println("Erro inesperado ao listar apelidos: " + e.getMessage());
            
        } finally {
            
            /*
            Nos métodos de SELECT não há varíavel 'retorno' e nem o marcador 'erro'
            pois em caso de erro, o objeto será null, e isso é tratado na view. 
            Além disso metódos relacionados ao comando SELECT não possuem commit e rollback
            por não se tratar de uma operação de alteração de dados.
            Portanto são passados como null e 0 para finalizarMetodos(), para indicar que o 
            método a ser finalizado está relacionado a operação de SELECT.
            */
            finalizarMetodos(c, null, 0); 
        }
        
        return apelidos;
    }
    
    /*
    Seleciona todos os valores da tabela apelidos na tupla especificada pelo ID
    */
    
    public Apelidos buscarPorID(long instrumento_id){
        
        String sql = "SELECT * FROM apelidos WHERE instrumento_id = ?";
        Apelidos apelidos = null;
        Connection c = null;
        
        try {
            
            c = conexao.obterConexao();
        
            try (PreparedStatement ps = c.prepareStatement(sql)){

                 ps.setLong(1, instrumento_id);
 
                 try (ResultSet rs = ps.executeQuery()) {
                     
                    // Criação do objeto a partir dos registros da tupla especificada, se houver.
                    if (rs.next()) {

                    apelidos = new Apelidos(rs.getLong("instrumento_id"), rs.getString("apelido"));
                        
                   } else {

                       System.out.println("Nenhum registro de apelidos encontrado com o ID informado.");
                   }
                }
            }

        } catch (SQLException e) {

            System.out.println("Erro SQL ao buscar apelidos: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Erro inesperado ao buscar apelidos: " + e.getMessage());
            
        } finally {
            
            finalizarMetodos(c, null, 0);
        }
        
        return apelidos;
    }
    
    /*
    Atualiza valores da tupla especificada através do instrumento_id na tabela apelidos  
    */
    
    public Map<String, Long> atualizar(Apelidos apelidos){
        
        String sql = "UPDATE apelidos SET apelido = ? WHERE instrumento_id = ?";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        try {
            
            c = conexao.obterConexao();
            
            c.setAutoCommit(false);
        
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                
                ps.setString(1, apelidos.getApelidos());
                ps.setLong(2, apelidos.getInstrumentoId());

                int atualizou = ps.executeUpdate();
                
                c.commit();

                if(atualizou > 0) System.out.println("Apelidos atualizados com sucesso!");
                else  {
                    
                    System.out.println("Nenhum registro de apelidos encontrado para atualizar.");
                    retornos.put("Codigo", malSucedido);
                    
                    System.out.println("Tentando rollback...");
                    tentarRollback(c, retornos);
                }
            }    

        } catch (SQLException e) {

            System.out.println("Erro SQL ao atualizar apelidos: " + e.getMessage());
            
            retornos.put("Codigo", (long)e.getErrorCode()); 
                
            System.out.println("Tentando rollback...");
                
            tentarRollback(c, retornos);

        } catch (Exception e) {

            System.out.println("Erro inesperado ao atualizar apelidos: " + e.getMessage());
            retornos.put("Codigo", erros);  
            
            System.out.println("Tentando rollback...");
                
            tentarRollback(c, retornos);
            
        } finally {
            
            finalizarMetodos(c, retornos, erros);
        }
        
        if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        
        return retornos;
    }
    
    /*
    Deleta valores da tupla especificada através do instrumento_id na tabela apelidos  
    */
    
    public Map<String, Long> deletar(long instrumento_id) {
        
        String sql = "DELETE FROM apelidos WHERE instrumento_id = ?";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        try {
            
            c = conexao.obterConexao();
            c.setAutoCommit(false);
            
            try (PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setLong(1, instrumento_id);

                int deletou = ps.executeUpdate();
                
                c.commit();

                if (deletou > 0) {

                    System.out.println("Apelidos deletados com sucesso!");

                } else {

                    System.out.println("Nenhum registro de apelidos encontrado com esse ID.");
                    retornos.put("Codigo", malSucedido);
                    
                    System.out.println("Tentando rollback...");
                    tentarRollback(c, retornos);
                }
            }

        } catch (SQLException e) {
            // 1451 - erro de chave estrangeira (FK constraint)
            if (e.getErrorCode() == 1451) System.out.println("Não é possível deletar: existem dados de outra entidade relacionados a estes apelidos."); 
            else System.out.println("Erro SQL: " + e.getMessage());

            retornos.put("Codigo", (long)e.getErrorCode());
            
            System.out.println("Tentando rollback...");
                
            tentarRollback(c, retornos);

        } catch (Exception e) {

            System.out.println("Erro inesperado ao deletar apelidos: " + e.getMessage());
            retornos.put("Codigo", erros);
            
        } finally {
            
            finalizarMetodos(c, retornos, erros);
        }
        
        if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        
        return retornos;
    }
}