/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ruben
 */

import java.sql.SQLException;
import java.sql.Connection;

public interface ConexaoBD {
    
    Connection obterConexao() throws SQLException, ClassNotFoundException;
    
    void fecharConexao(Connection c) throws SQLException;
}
