/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import model.ParteEMaterial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class InstrumentoTemParteEMaterialDAO {
    
    // Abstração de conexãoBD    
    private final ConexaoBD conexao = new ConexaoMySQL();
    
    // Códigos HTTP para indicar sucesso/erro lógico
    private final long bemSucedido = 200;
    private final long malSucedido = 404;
    
    public InstrumentoTemParteEMaterialDAO(){}
    
    // Fecha operações comuns a todos os momentos
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
           || escolha.equals("material")) {
            
            return true;
            
        } else {
            
            System.out.printf("\nEscolha inválida, tente uma das opções abaixo:\n"
                    + "\n\"instrumento\""
                    + "\n\"material\"\n");
            
            return false;
        }
    }
    
    /*
    Insere valores na tabela instrumento_parte_material. 
    */
    public Map<String, Long> inserir(ParteEMaterial instrumentoTemParteEMaterial){
        
        String sql = "INSERT INTO instrumento_material_parte (instrumento_id, material_id, parte_nome) VALUES (?, ?, ?)";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        boolean sucesso = false;
        
        try {
            c = conexao.obterConexao();
            c.setAutoCommit(false);
            
            try (PreparedStatement ps = c.prepareStatement(sql)){
                
                ps.setLong(1, instrumentoTemParteEMaterial.getInstrumentoId());
                ps.setLong(2, instrumentoTemParteEMaterial.getMaterialId());
                ps.setString(3, instrumentoTemParteEMaterial.getParteNome());
                
                ps.executeUpdate();
                
                 c.commit(); 
                 sucesso = true; 
                 System.out.println("Relação instrumento-parte-material inserida com sucesso!");
            } 
            
        } catch(SQLException e) {
            
            System.out.println("Erro SQL: " + e.getMessage());
            retornos.put("Codigo", (long)e.getErrorCode()); 
            
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);
            
        } catch(Exception e) {
            
            System.out.println("Erro inesperado ao inserir relação instrumento-parte-material: " + e.getMessage());
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
    Seleciona todos os valores da tabela instrumento_parte_material.
    */
    public List<ParteEMaterial> listarTodos(){
        
        List<ParteEMaterial> instrumentosPartesMateriais = new ArrayList<>();
        String sql = "SELECT * FROM instrumento_material_parte";
        Connection c = null;
        
        try {
            c = conexao.obterConexao();
            try (PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()){
                
                /* Criação dos objetos a partir dos registros da tabela */
                while(rs.next()){
                    ParteEMaterial ipm = new ParteEMaterial(
                            
                            rs.getLong("instrumento_id"),
                            rs.getLong("material_id"),
                            rs.getString("parte_nome")            
                    );
                    
                    instrumentosPartesMateriais.add(ipm);
                }
            }  
        } catch(SQLException e) {
            
            System.out.println("Erro SQL: " + e.getMessage());
            
        } catch(Exception e) {
            
            System.out.println("Erro inesperado ao listar relações instrumento-parte-material: " + e.getMessage());
            
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
        return instrumentosPartesMateriais;
    }
    
    /*
    Seleciona todos os valores da tabela instrumento_parte_material na tupla especificada pelo ID
    */
    public ParteEMaterial buscarPorID(long id, String escolha){
        
        String sql = null;
        ParteEMaterial instrumentoTemParteEMaterial = null;
        Connection c = null;
        
        if(isEscolha(escolha)) {
        
            if(escolha.equals("instrumento")) sql = "SELECT * FROM instrumento_material_parte WHERE instrumento_id = ?";
            else sql = "SELECT * FROM instrumento_material_parte WHERE material_id = ?";

            try {
                c = conexao.obterConexao();
                try (PreparedStatement ps = c.prepareStatement(sql)){
                     ps.setLong(1, id);
                     try (ResultSet rs = ps.executeQuery()) {

                        if (rs.next()) {

                           instrumentoTemParteEMaterial = new ParteEMaterial(

                                rs.getLong("instrumento_id"),
                                rs.getLong("material_id"),
                                rs.getString("parte_nome")            
                            );

                       } else {

                           System.out.println("Nenhuma relação instrumento-parte-material encontrada com o ID informado.");
                       }
                    }
                }
            } catch (SQLException e) {

                System.out.println("Erro SQL ao buscar relação instrumento-parte-material: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Erro inesperado ao buscar relação instrumento-parte-material: " + e.getMessage());

            } finally {

                finalizarMetodos(c, null, 0);
            }     
        }
        
        return instrumentoTemParteEMaterial;
    }
    
    /*
    Atualiza valores da tupla especificada através do ID na tabela instrumento_parte_material  
    */
    public Map<String, Long> atualizar(ParteEMaterial instrumentoTemParteEMaterial, String escolha, long id){
        
        String sql;  
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        if(isEscolha(escolha)) {
        
            if(escolha.equals("instrumento")) sql = "UPDATE instrumento_material_parte SET instrumento_id = ?, material_id = ?, parte_nome = ? WHERE instrumento_id = ?";
            else sql = "UPDATE instrumento_material_parte SET instrumento_id = ?, material_id = ?, parte_nome = ? WHERE material_id = ?";

            try {
                c = conexao.obterConexao();
                c.setAutoCommit(false);

                try (PreparedStatement ps = c.prepareStatement(sql)) {

                    ps.setLong(1, instrumentoTemParteEMaterial.getInstrumentoId());
                    ps.setLong(2, instrumentoTemParteEMaterial.getMaterialId());
                    ps.setString(3, instrumentoTemParteEMaterial.getParteNome());
                    ps.setLong(4, id);

                    int atualizou = ps.executeUpdate();
                    c.commit();

                    if(atualizou > 0) System.out.println("Relação instrumento-parte-material atualizada com sucesso!");
                    else  {

                        System.out.println("Nenhuma relação instrumento-parte-material encontrada para atualizar.");
                        retornos.put("Codigo", malSucedido);
                        System.out.println("Tentando rollback...");
                        tentarRollback(c, retornos);
                    }
                }    
            } catch (SQLException e) {

                System.out.println("Erro SQL ao atualizar relação instrumento-parte-material: " + e.getMessage());
                retornos.put("Codigo", (long)e.getErrorCode()); 
                System.out.println("Tentando rollback...");
                tentarRollback(c, retornos);

            } catch (Exception e) {

                System.out.println("Erro inesperado ao atualizar relação instrumento-parte-material: " + e.getMessage());
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
    Deleta valores da tupla especificada através do ID na tabela instrumento_parte_material  
    */
    public Map<String, Long> deletar(String escolha, long id) {
        
        String sql;
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        if(isEscolha(escolha)) {
        
            if(escolha.equals("instrumento")) sql = "DELETE FROM instrumento_material_parte WHERE instrumento_id = ?";
            else sql = "DELETE FROM instrumento_material_parte WHERE material_id = ?";

            try {
                c = conexao.obterConexao();
                c.setAutoCommit(false);
                try (PreparedStatement ps = c.prepareStatement(sql)) {

                    ps.setLong(1, id);
                    int deletou = ps.executeUpdate();
                    c.commit();

                    if (deletou > 0) {
                        System.out.println("Relação instrumento-parte-material deletada com sucesso!");
                    } else {
                        System.out.println("Nenhuma relação instrumento-parte-material encontrada com esse ID.");
                        retornos.put("Codigo", malSucedido);
                        System.out.println("Tentando rollback...");
                        tentarRollback(c, retornos);
                    }
                }

            } catch (SQLException e) {
                // 1451 - erro de chave estrangeira (FK constraint)

                if (e.getErrorCode() == 1451) System.out.println("Não é possível deletar: existem dados de outra entidade relacionados a esta relação instrumento-parte-material."); 
                else System.out.println("Erro SQL: " + e.getMessage());

                retornos.put("Codigo", (long)e.getErrorCode());
                System.out.println("Tentando rollback...");
                tentarRollback(c, retornos);

            } catch (Exception e) {

                System.out.println("Erro inesperado ao deletar relação instrumento-parte-material: " + e.getMessage());
                retornos.put("Codigo", erros);

            } finally {

                finalizarMetodos(c, retornos, erros);
            }

            if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        
        }
        
        return retornos;
    }
}
