/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

/**
 *
 * @author ruben
 */

import dao.*;
import model.*;
import java.util.List;
import java.util.Map;

public class TrabalhoPOOInstrumentosMusicais {

    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(() -> {
            
            try{
            
                TelaInstrumentos telaInstrumentos = new TelaInstrumentos();
                telaInstrumentos.setVisible(true);
            
            } catch(Exception e){
                
                System.out.println("Erro: " + e.getMessage());
            }
            
        });
  
    }
 }     