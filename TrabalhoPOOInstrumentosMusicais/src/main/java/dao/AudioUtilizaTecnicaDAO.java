/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import model.AudioUtilizaTecnica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AudioUtilizaTecnicaDAO {
    
    // Abstração de conexãoBD    
    private final ConexaoBD conexao = new ConexaoMySQL();
    
    // Códigos HTTP para indicar sucesso/erro lógico
    private final long bemSucedido = 200;
    private final long malSucedido = 404;
    
    public AudioUtilizaTecnicaDAO(){}
    
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
    Insere valores na tabela audio_tecnica. 
    Os seguintes padrões seguem para os métodos de atualizar(), e deletar():
    
    Introduz em 'retornos' a chave "Codigo" com valor 200 em caso de conseguir capturar o ID com sucesso.
    
    Em caso de erro retorna um código de erro ou um marcador em caso de erro genérico
    como valor de "Codigo". 
    */
    public Map<String, Long> inserir(AudioUtilizaTecnica audioUtilizaTecnica){
        
        String sql = "INSERT INTO audio_tecnica (audio_id, tecnica_id) VALUES (?, ?)";      
                
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1; // usado como marcador de erro genérico
        boolean sucesso = false; // flag de sucesso para definir "Codigo" 200 ao final
        
        
        try {
            
            c = conexao.obterConexao();
            
            c.setAutoCommit(false); // inicia transação
            
            try (PreparedStatement ps = c.prepareStatement(sql)){
                
                ps.setLong(1, audioUtilizaTecnica.getAudioId());
                ps.setLong(2, audioUtilizaTecnica.getTecnicaId());

                ps.executeUpdate();
                
                 c.commit(); 
                 sucesso = true; 
                 System.out.println("Relação áudio-técnica inserida com sucesso!");
            } 
            
        } catch(SQLException e) {

                System.out.println("Erro SQL: " + e.getMessage());
                retornos.put("Codigo", (long)e.getErrorCode()); 
                
                System.out.println("Tentando rollback...");
                tentarRollback(c, retornos);

        } catch(Exception e) {

               System.out.println("Erro inesperado ao inserir relação áudio-técnica: " + e.getMessage());
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
    Seleciona todos os valores da tabela audio_tecnica.
    */
    
    public List<AudioUtilizaTecnica> listarTodos(){
        
        List<AudioUtilizaTecnica> audiosTecnicas = new ArrayList<>();
        String sql = "SELECT * FROM audio_tecnica";
        Connection c = null;
        
        try {
            
            c = conexao.obterConexao();
            
            try (PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()){
                
                /* 
                Criação dos objetos, a partir dos registros da tabela,
                enquanto e se existirem.
                */
                while(rs.next()){

                    AudioUtilizaTecnica at = new AudioUtilizaTecnica(
                            
                            rs.getLong("audio_id"),
                            rs.getLong("tecnica_id")
                    );
       
                    audiosTecnicas.add(at);
                }
            }  
            
        } catch(SQLException e) {
         
            System.out.println("Erro SQL: " + e.getMessage());
            
        } catch(Exception e) {
            
            System.out.println("Erro inesperado ao listar relações áudio-técnica: " + e.getMessage());
            
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
        
        return audiosTecnicas;
    }
    
    /*
    Seleciona todos os valores da tabela audio_tecnica na tupla especificada pelo ID
    */
    
    public AudioUtilizaTecnica buscarPorID(long id, String escolha){
        
        String sql;
        
        if(escolha.equals("audio")) sql = "SELECT * FROM audio_tecnica WHERE audio_id = ?";
        else sql = "SELECT * FROM audio_tecnica WHERE tecnica_id = ?";
        
        AudioUtilizaTecnica audioUtilizaTecnica = null;
        Connection c = null;
        
        try {
            
            c = conexao.obterConexao();
        
            try (PreparedStatement ps = c.prepareStatement(sql)){

                 ps.setLong(1, id);
 
                 try (ResultSet rs = ps.executeQuery()) {
                     
                    if (rs.next()) {

                       audioUtilizaTecnica = new AudioUtilizaTecnica(

                               rs.getLong("audio_id"),
                               rs.getLong("tecnica_id")
                       );     

                   } else {

                       System.out.println("Nenhuma relação áudio-técnica encontrada com o ID informado.");
                   }
                }
            }

        } catch (SQLException e) {

            System.out.println("Erro SQL ao buscar relação áudio-técnica: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Erro inesperado ao buscar relação áudio-técnica: " + e.getMessage());
            
        } finally {
            
            finalizarMetodos(c, null, 0);
        }
        
        return audioUtilizaTecnica;
    }
    
    /*
    Atualiza valores da tupla especificada através do ID na tabela audio_tecnica  
    */
    
    public Map<String, Long> atualizar(AudioUtilizaTecnica audioUtilizaTecnica, long id, String escolha){
        
        String sql;
        
        if(escolha.equals("audio")) sql = "SELECT * FROM audio_tecnica WHERE audio_id = ?";
        else sql = "SELECT * FROM audio_tecnica WHERE tecnica_id = ?";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        try {
            
            c = conexao.obterConexao();
            
            c.setAutoCommit(false);
        
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                
                ps.setLong(1, audioUtilizaTecnica.getAudioId());
                ps.setLong(1, audioUtilizaTecnica.getTecnicaId());

                int atualizou = ps.executeUpdate();
                
                c.commit();

                if(atualizou > 0) System.out.println("Relação áudio-técnica atualizada com sucesso!");
                else  {
                    
                    System.out.println("Nenhuma relação áudio-técnica encontrada para atualizar.");
                    retornos.put("Codigo", malSucedido);
                    
                    System.out.println("Tentando rollback...");
                    tentarRollback(c, retornos);
                }
            }    

        } catch (SQLException e) {

            System.out.println("Erro SQL ao atualizar relação áudio-técnica: " + e.getMessage());
            
            retornos.put("Codigo", (long)e.getErrorCode()); 
                
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);

        } catch (Exception e) {

            System.out.println("Erro inesperado ao atualizar relação áudio-técnica: " + e.getMessage());
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
    Deleta valores da tupla especificada através do ID na tabela audio_tecnica  
    */
    
    public Map<String, Long> deletar(long id, String escolha) {
        
        String sql;
        
        if(escolha.equals("audio_id")) sql = "SELECT * FROM audio_tecnica WHERE audio_id = ?";
        else sql = "SELECT * FROM audio_tecnica WHERE tecnica_id = ?";
        
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

                    System.out.println("Relação áudio-técnica deletada com sucesso!");

                } else {

                    System.out.println("Nenhuma relação áudio-técnica encontrada com esse ID.");
                    retornos.put("Codigo", malSucedido);
                    
                    System.out.println("Tentando rollback...");
                    tentarRollback(c, retornos);
                }
            }

        } catch (SQLException e) {
            // 1451 - erro de chave estrangeira (FK constraint)
            if (e.getErrorCode() == 1451) System.out.println("Não é possível deletar: existem dados de outra entidade relacionados a esta relação áudio-técnica."); 
            else System.out.println("Erro SQL: " + e.getMessage());

            retornos.put("Codigo", (long)e.getErrorCode());
            
            System.out.println("Tentando rollback...");
            tentarRollback(c, retornos);

        } catch (Exception e) {

            System.out.println("Erro inesperado ao deletar relação áudio-técnica: " + e.getMessage());
            retornos.put("Codigo", erros);
            
        } finally {
            
            finalizarMetodos(c, retornos, erros);
        }
        
        if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
        
        return retornos;
    }
}
