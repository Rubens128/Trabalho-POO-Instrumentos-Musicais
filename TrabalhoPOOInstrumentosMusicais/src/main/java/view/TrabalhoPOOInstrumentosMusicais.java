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
       /* java.awt.EventQueue.invokeLater(() -> {
            
            TelaInstrumentos telaInstrumentos = new TelaInstrumentos();;;;
            telaInstrumentos.setVisible(true);
            
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste2", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
           
        });*/
   
                
        InstrumentoDAO dao = new InstrumentoDAO();

long instrId = 2;  // deve existir em instrumento(id)
long afinId  = 10;   // deve existir em afinacao(id)}

        Map<String, Long> r3 = dao.deletar("harmonico", 10);
System.out.println("Deleção -> " + r3);

    }
 }     