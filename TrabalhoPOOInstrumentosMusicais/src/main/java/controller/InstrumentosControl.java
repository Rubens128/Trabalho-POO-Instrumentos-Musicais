/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.InstrumentoDAO;
import model.Instrumento;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import model.InstrumentoHarmonico;
import model.InstrumentoMelodico;
import model.InstrumentoRitmico;

/**
 *
 * @author Nascimento
 */
public class InstrumentosControl {

    private final InstrumentoDAO instrumentoDAO;

    public InstrumentosControl(InstrumentoDAO instrumentoDAO) {
        this.instrumentoDAO = instrumentoDAO;
    }

    public void adcionarInstrumento(Instrumento i, String especializacao, InstrumentoHarmonico iho, InstrumentoMelodico imo, InstrumentoRitmico irm) throws SQLException { 

        Instrumento InstrumentoEspecializado = null;
        
        switch (especializacao) {
            case "harmonico" -> {
                InstrumentoEspecializado = new InstrumentoHarmonico.Builder(
                        i.getId(),
                        i.getFamiliaId(),
                        i.getNome(),
                        i.getClassificacaoSonoridade(),
                        iho.getPolifoniaMax(),
                        iho.isPossuiPedalSustain(),
                        iho.isSuportaAcordes()
                )
                        .familiaInstrumento(i.getFamiliaInstrumento())
                        .historia(i.getHistoria())
                        .descricao(i.getDescricao())
                        .afinacoes(i.getAfinacoes())
                        .audios(i.getAudios())
                        .partesEMateriais(i.getPartesEMateriais())
                        .build();
            }
            case "melodico" -> {
                InstrumentoEspecializado = new InstrumentoMelodico.Builder(
                        i.getId(),
                        i.getFamiliaId(),
                        i.getNome(),
                        i.getClassificacaoSonoridade(),
                        imo.isTranspositor(),
                        imo.getAfinacaoTransposicao(),
                        imo.isMicrotonalidadeSuportada()
                )
                        .familiaInstrumento(i.getFamiliaInstrumento())
                        .historia(i.getHistoria())
                        .descricao(i.getDescricao())
                        .afinacoes(i.getAfinacoes())
                        .audios(i.getAudios())
                        .partesEMateriais(i.getPartesEMateriais())
                        .build();
            }
            case "ritmico" -> {
                InstrumentoEspecializado = new InstrumentoRitmico.Builder(
                        i.getId(),
                        i.getFamiliaId(),
                        i.getNome(),
                        i.getClassificacaoSonoridade(),
                        irm.isAlturaDefinida(),
                        irm.getCategoriaPercussao(),
                        irm.getTocadoCom()
                )
                        .familiaInstrumento(i.getFamiliaInstrumento())
                        .historia(i.getHistoria())
                        .descricao(i.getDescricao())
                        .afinacoes(i.getAfinacoes())
                        .audios(i.getAudios())
                        .partesEMateriais(i.getPartesEMateriais())
                        .build();
            }
            default ->
                System.out.println("especialidade nao definada ou invalida");
        }
        if(InstrumentoEspecializado != null){
            instrumentoDAO.inserir(InstrumentoEspecializado, especializacao);
        }
    }

    public List<Instrumento> listarInstrumentos(String especializacao) throws SQLException{
        return instrumentoDAO.listarTodos(especializacao);
    }

    public Instrumento listarInstrumentosporID(long id, String especializacao) throws SQLException{
        return instrumentoDAO.buscarPorID(especializacao, id);
    }

    public Map<String, Long> atualizarInstrumento(Instrumento instrumento, String especializacao) throws SQLException {
        return instrumentoDAO.atualizar(instrumento, especializacao);
    }

    public Map<String, Long> deletarInstrumento(String especializacao, long id) throws SQLException {
        return instrumentoDAO.deletar(especializacao, id);
    }
}
