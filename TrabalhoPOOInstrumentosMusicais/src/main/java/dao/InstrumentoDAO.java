/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import model.Instrumento;
import model.InstrumentoHarmonico;
import model.InstrumentoMelodico;
import model.InstrumentoRitmico;
import model.AfinacaoTransposicao;
import model.CategoriaPercussao;
import model.TocadoCom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class InstrumentoDAO {
    
    // Abstração de conexãoBD    
    private final ConexaoBD conexao = new ConexaoMySQL();
    
    // Códigos HTTP para indicar sucesso/erro lógico
    private final long bemSucedido = 200;
    private final long malSucedido = 404;
    
    public InstrumentoDAO(){}
    
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
    
    //Método para verificar se o usuário inseriu uma especialização válida de 'instrumento'
    boolean isEspecializacao(String especializacao) {
        
        if(especializacao.equals("harmonico")
           || especializacao.equals("melodico")
           || especializacao.equals("ritmico")) {
            
            return true;
            
        } else {
            
            System.out.printf("\nEspecialização inválida, tente uma das opções abaixo:\n"
                    + "\n\"harmonico\""
                    + "\n\"melodico\""
                    + "\n\"ritmico\"\n");
            
            return false;
        }
    }
    
    /*
    Insere valores na tabela instrumento. 
    Os seguintes padrões seguem para os métodos de atualizar(), e deletar():
    
    Introduz em 'retornos' a chave "ID" e o ID gerado como valor, 
    assim como a chave "Codigo" com valor 200 em caso de conseguir capturar o ID com sucesso.
    
    Em caso de erro retorna um código de erro ou um marcador em caso de erro genérico
    como valor de "Codigo".
    
    Introduz uma nova variavel chamada 'especializacao' dedicada a mudar a query conforme
    a especialização do intrumento inserido. O mesmo se repete para os outros métodos.
    */
    public Map<String, Long> inserir(Instrumento instrumento, String especializacao){
        
        isEspecializacao(especializacao);
        
        String sql = "INSERT INTO instrumento (familia_id, nome, descricao, historia, classificacao_sonoridade)"
                + "VALUES (?, ?, ?, ?, ?)";
        
        String sqlEspecializacao = "INSERT INTO instrumento_" + especializacao + " VALUES (?,?,?,?)";
          
        
        Map<String, Long> retornos = new HashMap<>();                                       
        Connection c = null;                                                    
        long erros = -1; // usado como marcador de erro genérico
        boolean sucesso = false; // flag de sucesso para definir "Codigo" 200 ao final
        long novoID = 0;
        
        if(isEspecializacao(especializacao)) {
        
            try {

                c = conexao.obterConexao();

                c.setAutoCommit(false); // inicia transação

                // try-with-resources garante fechamento de PreparedStatement e ResultSet
                try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

                    ps.setLong(1, instrumento.getFamiliaId());
                    ps.setString(2, instrumento.getNome());
                    ps.setString(3, instrumento.getDescricao());
                    ps.setString(4, instrumento.getHistoria());
                    ps.setString(5, instrumento.getClassificacaoSonoridade());

                    ps.executeUpdate();


                    try(ResultSet rs = ps.getGeneratedKeys()){

                        if (rs.next()) {

                            novoID = rs.getLong(1);
                            retornos.put("ID", rs.getLong(1));
                        }
                        else System.out.println("Erro ao retornar ID");
                    }

                    //Verifica a especialização do objeto instrumento passado
                    //e executa uma ou mais de suas respectivas queries

                    try (PreparedStatement ps2 = c.prepareStatement(sqlEspecializacao)) {

                        if (instrumento instanceof InstrumentoHarmonico ih) {

                            ps2.setLong(1, novoID);
                            ps2.setLong(2, ih.getPolifoniaMax());
                            ps2.setBoolean(3, ih.isPossuiPedalSustain());
                            ps2.setBoolean(4, ih.isSuportaAcordes());

                        } else if (instrumento instanceof InstrumentoMelodico im) {

                            ps2.setLong(1, novoID);
                            ps2.setBoolean(2, im.isTranspositor());
                            ps2.setString(3, im.getAfinacaoTransposicao().getValor());
                            ps2.setBoolean(4, im.isMicrotonalidadeSuportada());

                        } else if (instrumento instanceof InstrumentoRitmico ir) {

                            ps2.setLong(1, novoID);
                            ps2.setBoolean(2, ir.isAlturaDefinida());
                            ps2.setString(3, ir.getCategoriaPercussao().getValor());
                            ps2.setString(4, ir.getTocadoCom().getValor());
                        }

                        ps2.executeUpdate();
                    }

                     c.commit(); // commit do insert

                     sucesso = true; // alteração da flag para indicar operação bem-sucedida

                     System.out.println("Instrumento inserido com sucesso!");
                } 

            } catch(SQLException e) {

                    System.out.println("Erro SQL: " + e.getMessage());
                    retornos.put("Codigo", (long)e.getErrorCode()); 

                    System.out.println("Tentando rollback...");

                    tentarRollback(c, retornos);

            } catch(Exception e) {

                   System.out.println("Erro inesperado ao inserir instrumento: " + e.getMessage());
                   retornos.put("Codigo", erros); 
                   erros -= 1;

                   System.out.println("Tentando rollback...");

                   tentarRollback(c, retornos);

            } finally {

                finalizarMetodos(c, retornos, erros);
            }

            if(sucesso) retornos.put("Codigo", (long)bemSucedido);
        }
        
        return retornos;
    }
    
    /*
    Seleciona todos os valores da tabela instrumento.
    */
    
    public List<Instrumento> listarTodos(String especializacao){
        
        List<Instrumento> instrumentos = new ArrayList<>();
        String sql = "SELECT * FROM instrumento_" + especializacao + " e "
                + "LEFT JOIN instrumento i "
                + "ON e.instrumento_id = i.id";
        
        Connection c = null;
        
        if(isEspecializacao(especializacao)) {
        
            try {

                c = conexao.obterConexao();

                try (PreparedStatement ps = c.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()){

                    /* 
                    Criação dos objetos, a partir dos registros da tabela,
                    enquanto e se existerem.
                    */
                    
                    while(rs.next()){

                        Instrumento i = null;

                        if (especializacao.equals("harmonico")) {

                             i = new InstrumentoHarmonico.Builder(

                                    rs.getLong("id"),
                                    rs.getLong("familia_id"),
                                    rs.getString("nome"),
                                    rs.getString("classificacao_sonoridade"),
                                    rs.getLong("polifonia_max"),
                                    rs.getBoolean("possui_pedal_sustain"),
                                    rs.getBoolean("suporta_acordes")

                            ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();

                        } else if (especializacao.equals("melodico")){

                            i = new InstrumentoMelodico.Builder(

                                    rs.getLong("id"),
                                    rs.getLong("familia_id"),
                                    rs.getString("nome"),
                                    rs.getString("classificacao_sonoridade"),
                                    rs.getBoolean("transpositor"),
                                    AfinacaoTransposicao.valueOf(
                                        rs.getString("afinacao_transposicao")
                                    ),
                                    rs.getBoolean("microtonalidade_suportada")

                            ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();

                        } else if (especializacao.equals("ritmico")) {

                            i = new InstrumentoRitmico.Builder(

                                    rs.getLong("id"),
                                    rs.getLong("familia_id"),
                                    rs.getString("nome"),
                                    rs.getString("classificacao_sonoridade"),
                                    rs.getBoolean("altura_definida"),
                                    CategoriaPercussao.valueOf(
                                        rs.getString("categoria_percussao")
                                    ),
                                    TocadoCom.valueOf(
                                        rs.getString("tocado_com")
                                    )

                            ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();
                        }

                        instrumentos.add(i);
                    }
                }  

            } catch(SQLException e) {

                System.out.println("Erro SQL: " + e.getMessage());

            } catch(Exception e) {

                System.out.println("Erro inesperado ao listar instrumentos: " + e.getMessage());

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
        }
        
        return instrumentos;
    }
    
    /*
    Seleciona todos os valores da tabela instrumento na tupla especificada pelo ID
    */
    
    public Instrumento buscarPorID(String especializacao, long id){
        
        String sql = "SELECT * FROM instrumento_" + especializacao + " e "
                + "LEFT JOIN instrumento i "
                + "ON e.instrumento_id = i.id "
                + "WHERE i.id = ?";
        Instrumento instrumento = null;
        Connection c = null;
        
        if(isEspecializacao(especializacao)) {
        
            try {

                c = conexao.obterConexao();

                try (PreparedStatement ps = c.prepareStatement(sql)){

                     ps.setLong(1, id);

                     try (ResultSet rs = ps.executeQuery()) {

                        // Criação do objeto a partir dos registros da tupla especificada, se houver.
                        if (rs.next()) {

                           if (especializacao.equals("harmonico")) {

                             instrumento = new InstrumentoHarmonico.Builder(

                                    rs.getLong("id"),
                                    rs.getLong("familia_id"),
                                    rs.getString("nome"),
                                    rs.getString("classificacao_sonoridade"),
                                    rs.getLong("polifonia_max"),
                                    rs.getBoolean("possui_pedal_sustain"),
                                    rs.getBoolean("suporta_acordes")

                            ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();

                        } else if (especializacao.equals("melodico")){

                            instrumento = new InstrumentoMelodico.Builder(

                                        rs.getLong("id"),
                                        rs.getLong("familia_id"),
                                        rs.getString("nome"),
                                        rs.getString("classificacao_sonoridade"),
                                        rs.getBoolean("transpositor"),
                                        AfinacaoTransposicao.valueOf(
                                            rs.getString("afinacao_transposicao")
                                        ),
                                        rs.getBoolean("microtonalidade_suportada")

                                ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();

                        } else if (especializacao.equals("ritmico")) {

                            instrumento = new InstrumentoRitmico.Builder(

                                        rs.getLong("id"),
                                        rs.getLong("familia_id"),
                                        rs.getString("nome"),
                                        rs.getString("classificacao_sonoridade"),
                                        rs.getBoolean("altura_definida"),
                                        CategoriaPercussao.valueOf(
                                            rs.getString("categoria_percussao")
                                        ),
                                        TocadoCom.valueOf(
                                            rs.getString("tocado_com")
                                        )

                                ).descricao(rs.getString("descricao")).historia(rs.getString("historia")).build();
                        }     

                       } else {

                           System.out.println("Nenhum instrumento encontrado com o ID informado.");
                       }
                    }
                }

            } catch (SQLException e) {

                System.out.println("Erro SQL ao buscar instrumento: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Erro inesperado ao buscar instrumento: " + e.getMessage());

            } finally {

                finalizarMetodos(c, null, 0);
            }
        }
        
        return instrumento;
    }
    
    /*
    Atualiza valores da tupla especificada através do ID na tabela instrumento  
    */
    
    public Map<String, Long> atualizar(Instrumento instrumento, String especializacao){
        
        String coluna1 = null;
        String coluna2 = null;
        String coluna3 = null;     
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        if(isEspecializacao(especializacao)) {
        
            if (especializacao.equals("harmonico")){

                coluna1 = "polifonia_max";
                coluna2 = "possui_pedal_sustain";
                coluna3 = "suporta_acordes";

            } else if (especializacao.equals("melodico")) {

                coluna1 = "transpositor";
                coluna2 = "afinacao_transpositor";
                coluna3 = "microtonalidade_suportada";

            } else if (especializacao.equals("ritmico")) {

                coluna1 = "altura_definida";
                coluna2 = "categoria_percussao";
                coluna3 = "tocado_com";
            }

            String sql = "UPDATE instrumento i "
                    + "LEFT JOIN instrumento_" + especializacao + " e "
                    + "ON i.id = e.instrumento_id "
                    + "SET familia_id = ?, nome = ?, descricao = ?, historia = ?, "
                    + "classificacao_sonoridade = ?, " 
                    + coluna1 + " = ?, "
                    + coluna2 + " = ?, "
                    + coluna3 + " = ? "
                    + "WHERE i.id = ?";

            try {

                c = conexao.obterConexao();

                c.setAutoCommit(false);

                try (PreparedStatement ps = c.prepareStatement(sql)) {

                    ps.setLong(1, instrumento.getFamiliaId());
                    ps.setString(2, instrumento.getNome());
                    ps.setString(3, instrumento.getDescricao());
                    ps.setString(4, instrumento.getHistoria());
                    ps.setString(5, instrumento.getClassificacaoSonoridade());

                        if (instrumento instanceof InstrumentoHarmonico ih) {

                            ps.setLong(6, ih.getPolifoniaMax());
                            ps.setBoolean(7, ih.isPossuiPedalSustain());
                            ps.setBoolean(8, ih.isSuportaAcordes());

                        } else if (instrumento instanceof InstrumentoMelodico im) {

                            ps.setBoolean(6, im.isTranspositor());
                            ps.setString(7, im.getAfinacaoTransposicao().getValor());
                            ps.setBoolean(8, im.isMicrotonalidadeSuportada());

                        } else if (instrumento instanceof InstrumentoRitmico ir) {

                            ps.setBoolean(6, ir.isAlturaDefinida());
                            ps.setString(7, ir.getCategoriaPercussao().getValor());
                            ps.setString(8, ir.getTocadoCom().getValor());
                        }

                    ps.setLong(9, instrumento.getId());

                    int atualizou = ps.executeUpdate();

                    c.commit();

                    if(atualizou > 0) System.out.println("Instrumento atualizado com sucesso!");
                    else  {

                        System.out.println("Nenhum instrumento encontrado para atualizar.");
                        retornos.put("Codigo", malSucedido);

                        System.out.println("Tentando rollback...");
                        tentarRollback(c, retornos);
                    }
                }    

            } catch (SQLException e) {

                System.out.println("Erro SQL ao atualizar instrumento: " + e.getMessage());

                retornos.put("Codigo", (long)e.getErrorCode()); 

                System.out.println("Tentando rollback...");

                tentarRollback(c, retornos);

            } catch (Exception e) {

                System.out.println("Erro inesperado ao atualizar instrumento: " + e.getMessage());
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
    Deleta valores da tupla especificada através do ID na tabela instrumento  
    */
    
    public Map<String, Long> deletar(String especializacao, long id) {
        
        String sqlEspecializacao = "DELETE FROM instrumento_"+ especializacao + " WHERE instrumento_id = ? ";
        String sql = "DELETE FROM instrumento WHERE id = ?";
        
        Map<String, Long> retornos = new HashMap<>();
        Connection c = null;
        long erros = -1;
        
        if (isEspecializacao(especializacao)) {
        
            try {

                c = conexao.obterConexao();
                c.setAutoCommit(false);

                try (PreparedStatement ps = c.prepareStatement(sqlEspecializacao);
                     PreparedStatement ps2 = c.prepareStatement(sql)) {

                    ps.setLong(1, id);
                    ps2.setLong(1, id);

                    int deletouEspecializacao = ps.executeUpdate();
                    int deletou = ps2.executeUpdate();

                    c.commit();

                    if (deletouEspecializacao > 0 && deletou > 0) {

                        System.out.println("Instrumento deletado com sucesso!");

                    } else {

                        System.out.println("Nenhum instrumento encontrado com esse ID.");
                        retornos.put("Codigo", malSucedido);

                        System.out.println("Tentando rollback...");
                        tentarRollback(c, retornos);
                    }
                }

            } catch (SQLException e) {
                // 1451 - erro de chave estrangeira (FK constraint)
                if (e.getErrorCode() == 1451) System.out.println("Não é possível deletar: existem dados de outra entidade relacionados a este instrumento."); 
                else System.out.println("Erro SQL: " + e.getMessage());

                retornos.put("Codigo", (long)e.getErrorCode());

                System.out.println("Tentando rollback...");

                tentarRollback(c, retornos);

            } catch (Exception e) {

                System.out.println("Erro inesperado ao deletar instrumento: " + e.getMessage());
                retornos.put("Codigo", erros);

            } finally {

                finalizarMetodos(c, retornos, erros);
            }

            if (retornos.isEmpty()) retornos.put("Codigo", bemSucedido);
            
        }    
      
        return retornos;
    }
}
