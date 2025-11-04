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

// Audio testado
// Afinacao falta testar o delete (Relacionamento com instrumento_afinacao)
// AlcanceInstrumento testado
// Apelidos testado
// Familia Instrumento falta testar o delete (Relacionamento com instrumento)
// Material testado
// Parte testada
// Tecnica falta testar o delete (Relacionamento com audio_tecnica)

/*
AINDA FALTA TESTAR COMPLETAMENTE:
    
    AudioUtilizaTecnica
    Instrumento
    InstrumentoTemParteEMaterial
    InstrumentoUsaAfinacao
*/


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
   
                                            /*  =============================
                                                === PADRÃO DE TESTE USADO ===
                                                =============================
       
  
                    TecnicaDAO dao = new TecnicaDAO();

                long idTeste = 1L;
                String nomeBase = "Palm Muting";
                String descBase = "Técnica de abafar as cordas com a palma da mão.";
                String nomeAtualizado = "Palm Muting (avançado)";
                String descAtualizada = "Versão aprimorada da técnica, usada em estilos modernos.";

                System.out.println("\n=== TESTE INSERIR ===");
                Tecnica nova = new Tecnica(0L, nomeBase, descBase);
                Map<String, Long> r1 = dao.inserir(nova);
                System.out.println("Inserção -> " + r1);

                System.out.println("\n=== TESTE LISTAR TODOS ===");
                List<Tecnica> lista = dao.listarTodos();
                for (Tecnica t : lista) {
                    System.out.println("id=" + t.getId() +
                            ", nome=" + t.getNome() +
                            ", descricao=" + t.getDescricao());
                }

                System.out.println("\n=== TESTE BUSCAR POR ID ===");
                Tecnica tecnicaBuscada = dao.buscarPorID(idTeste);
                System.out.println("Busca -> " + tecnicaBuscada);

                System.out.println("\n=== TESTE ATUALIZAR ===");
                Tecnica atualizar = new Tecnica(idTeste, nomeAtualizado, descAtualizada);
                Map<String, Long> r2 = dao.atualizar(atualizar);
                System.out.println("Atualização -> " + r2);

                System.out.println("\n=== TESTE DELETAR ===");
                Map<String, Long> r3 = dao.deletar(idTeste);
                System.out.println("Deleção -> " + r3);

                System.out.println("\n=== FIM DOS TESTES ===");
            }
       
       */
    }
}
