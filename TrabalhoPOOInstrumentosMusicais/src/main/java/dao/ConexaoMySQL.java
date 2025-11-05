/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ribei
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements ConexaoBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/musica";
    private static final String USUARIO = "TrabalhoPOO";
    private static final String SENHA = "Trabalho";
    
    @Override
    public Connection obterConexao() throws SQLException, ClassNotFoundException {
   
        Class.forName("com.mysql.cj.jdbc.Driver");
            
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            
        return conexao;
    }
    
    @Override
    public void fecharConexao(Connection conexao) throws SQLException {

        if (conexao != null && !conexao.isClosed()) conexao.close();
        System.out.println("Conexão com o banco de dados fechada com sucesso!");
    }
}
