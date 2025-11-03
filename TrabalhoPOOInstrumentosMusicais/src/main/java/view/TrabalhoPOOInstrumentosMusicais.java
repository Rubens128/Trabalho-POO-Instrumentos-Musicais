/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;
import dao.AudioDAO;
import model.Audio;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ruben
 */
public class TrabalhoPOOInstrumentosMusicais {

    public static void main(String[] args) {
        /*java.awt.EventQueue.invokeLater(() -> {
            
            /*TelaInstrumentos telaInstrumentos = new TelaInstrumentos();;;
            telaInstrumentos.setVisible(true);
            
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);
            telaInstrumentos.addInstrumentoCard("teste2", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", true);*/
            
            /*InterfaceInputPadrao interfaceTeste = new InterfaceInputPadrao("Afinação");;;
            interfaceTeste.setVisible(true);
            
            InterfaceInputAudio interfaceTeste = new InterfaceInputAudio();
            interfaceTeste.setVisible(true);
        });*/
            
            AudioDAO a = new AudioDAO();
            
            Audio audio = a.buscarPorID(3);
            
            System.out.println(audio.getId());
    }
}
