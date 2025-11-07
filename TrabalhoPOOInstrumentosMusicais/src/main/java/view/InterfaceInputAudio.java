/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author ruben
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.TransferHandler;
import java.io.File;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import controller.AudioControl;
import controller.TecnicaControl;
import controller.InstrumentosControl;
import javax.swing.Timer;
import model.Audio;
import model.Tecnica;
import model.Instrumento;

public class InterfaceInputAudio extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfaceInputAudio.class.getName());
    
    private InterfaceInputPadrao telaInputTecnica;
    private InterfaceInputPadrao telaInputFamiliaID;
    private TelaListarPadrao telaListarTecnica, telaListarInstrumento;
    
    private boolean unicaTela;
    
    Border bordaVermelha = BorderFactory.createLineBorder(Color.RED, 3);
    
    Border bordaBranca = BorderFactory.createLineBorder(Color.WHITE, 3);
    
    private String arquivoCaminho;
    private boolean editar;
    private Object dado;
    private TelaInstrumentos telaPrincipal;
    
    /**
     * Creates new form InterfaceInputAudio
     */
    public InterfaceInputAudio(boolean unicaTela, boolean editar, Object dado, TelaInstrumentos telaPrincipal) {
        initComponents();
        
        this.telaPrincipal = telaPrincipal;
        
        if(editar){
            
            TituloPagina.setText("Áudio" + " (Não digitar nada mantém o valor atual):");
            
            Descricao.setText("Descrição:");
            Bpm.setText("BPM:");
            Tecnica.setText("Técnica:");
            Instrumento_ID.setText("Instrumento_ID:");
            Creditos_Gravacao.setText("Créditos_Gravação");
        }
        
        AudioDrop.setTransferHandler(new ReceberArquivo());
        
        setLocationRelativeTo(null);
        
        this.unicaTela = unicaTela;
        this.editar = editar;
        this.dado = dado;
        
         SwingUtilities.invokeLater(() -> {
        
            for(Component componente: Tela.getComponents()){
                
                if(componente instanceof JTextField campoTexto){
                    
                    campoTexto.setBorder(bordaBranca);
                    
                }else if(componente instanceof JScrollPane areaTextoScroll){
                    
                    areaTextoScroll.setBorder(bordaBranca);
                   
                }
            }
            
            AudioDrop.setBorder(bordaBranca);
            
            KeyAdapter funcaoSoNumero = new KeyAdapter() {
                
                @Override
                public void keyTyped(KeyEvent e){
                    
                    char caractere = e.getKeyChar();
                    
                    if(!Character.isDigit(caractere)){
                        e.consume();
                    }
                }
            };
            
            InputTecnica_Id.addKeyListener(funcaoSoNumero);
            
            InputInstrumento_Id.addKeyListener(funcaoSoNumero);
            
            InputOitava.addKeyListener(funcaoSoNumero);
            
            InputBpm.addKeyListener(funcaoSoNumero);
        });
    }
    
    public class ReceberArquivo extends TransferHandler{
        
        private static List<String> extencoesAceitas = List.of("mp3", "aif", "aiff", "wav", "au");
        
        @Override
        public boolean canImport(TransferSupport suporte ){
            
            boolean tipoCerto = suporte.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            if (tipoCerto) suporte.setDropAction(COPY);
            
            return tipoCerto;
        }
        
        @Override
        @SuppressWarnings("unchecked")
        public boolean importData(TransferSupport suporte){
            
            if(!canImport(suporte)) return false;
            
            try{
                List<File> arquivos = (List<File>) suporte.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                
                for (File arquivo: arquivos){
                    
                    String nome = arquivo.getName();
                    String extensao;

                    int index = nome.lastIndexOf(".");
                    
                    if(index >= 0 && index <= nome.length() -1) extensao = nome.substring(index + 1).toLowerCase();
                    else extensao = "";
                    
                    if(extencoesAceitas.contains(extensao)){
                        
                        Files.copy(arquivo.toPath(), 
                        Paths.get("src/main/resources/audios", arquivo.getName())
                        , StandardCopyOption.REPLACE_EXISTING);
                        
                        arquivoCaminho = "src/main/resources/audios/" + arquivo.getName();
                        
                        InstrucaoArquivo.setText("Arquivo Aceito!");
                        ExtensoesArquivo.setText(arquivo.getName());
                        
                        ImageIcon icone = new ImageIcon("src/main/resources/imagens/check.png");
                        
                        Icone.setIcon(icone);
                        
                        System.out.println("Arquivo aceito: " + nome);
                        
                        AudioDrop.setName("comAudio");
                    
                    }else{
                        
                        InstrucaoArquivo.setText("Extensão Inválida!");
                        ExtensoesArquivo.setText(arquivo.getName());
                        
                        ImageIcon icone = new ImageIcon("src/main/resources/imagens/close.png");
                        
                        Icone.setIcon(icone);
                        
                        System.out.println("Arquivo recusado: " + nome);
                        
                        AudioDrop.setName("semAudio");
                    }
                    
                    return true;
                }
            } catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
                
                return false;
            }
            
            return true;
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomeErro = new javax.swing.JPopupMenu();
        ErroTextNome = new javax.swing.JMenuItem();
        Tela = new javax.swing.JPanel();
        TituloPagina = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        InputNota = new javax.swing.JTextField();
        Descricao = new javax.swing.JLabel();
        ScrollDescricao = new javax.swing.JScrollPane();
        InputDescricao = new javax.swing.JTextArea();
        Bpm = new javax.swing.JLabel();
        InputTitutlo = new javax.swing.JTextField();
        Oitava = new javax.swing.JLabel();
        InputOitava = new javax.swing.JTextField();
        Nota = new javax.swing.JLabel();
        InputBpm = new javax.swing.JTextField();
        Creditos_Gravacao = new javax.swing.JLabel();
        InputCreditos_Gravacao = new javax.swing.JTextField();
        AudioDrop = new javax.swing.JPanel();
        Icone = new javax.swing.JButton();
        InstrucaoArquivo = new javax.swing.JLabel();
        ExtensoesArquivo = new javax.swing.JLabel();
        Tecnica = new javax.swing.JLabel();
        InputTecnica_Id = new javax.swing.JTextField();
        AdicionarTecnica = new javax.swing.JButton();
        ListarTecnica = new javax.swing.JButton();
        BotaoRegistrar = new javax.swing.JButton();
        BotaoCancelar = new javax.swing.JButton();
        Instrumento_ID = new javax.swing.JLabel();
        AdicionarInstrumentoID = new javax.swing.JButton();
        ListarInstrumentoID = new javax.swing.JButton();
        InputInstrumento_Id = new javax.swing.JTextField();

        NomeErro.setBackground(new java.awt.Color(11, 27, 58));
        NomeErro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NomeErro.setForeground(java.awt.Color.white);
        NomeErro.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        NomeErro.setBorderPainted(false);
        NomeErro.setPreferredSize(new java.awt.Dimension(340, 33));

        ErroTextNome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ErroTextNome.setText("Preencha o campo");
        ErroTextNome.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        ErroTextNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ErroTextNome.setPreferredSize(new java.awt.Dimension(300, 33));
        ErroTextNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ErroTextNomepadraoActionPerformed(evt);
            }
        });
        NomeErro.add(ErroTextNome);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tela.setBackground(new java.awt.Color(11, 27, 58));
        Tela.setPreferredSize(new java.awt.Dimension(1230, 843));

        TituloPagina.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        TituloPagina.setForeground(java.awt.Color.white);
        TituloPagina.setText("Audio:");

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Titulo.setForeground(java.awt.Color.white);
        Titulo.setText("Titulo:");
        Titulo.setToolTipText("");

        InputNota.setBackground(new java.awt.Color(11, 27, 70));
        InputNota.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputNota.setForeground(java.awt.Color.white);
        InputNota.setBorder(null);
        InputNota.setCaretColor(java.awt.Color.white);
        InputNota.setPreferredSize(new java.awt.Dimension(610, 27));
        InputNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNotaActionPerformed(evt);
            }
        });

        Descricao.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Descricao.setForeground(java.awt.Color.white);
        Descricao.setText("Descrição(opcional):");
        Descricao.setToolTipText("");

        ScrollDescricao.setBorder(null);

        InputDescricao.setBackground(new java.awt.Color(11, 27, 70));
        InputDescricao.setColumns(20);
        InputDescricao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputDescricao.setForeground(java.awt.Color.white);
        InputDescricao.setLineWrap(true);
        InputDescricao.setRows(3);
        InputDescricao.setWrapStyleWord(true);
        InputDescricao.setBorder(null);
        InputDescricao.setCaretColor(java.awt.Color.white);
        ScrollDescricao.setViewportView(InputDescricao);

        Bpm.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Bpm.setForeground(java.awt.Color.white);
        Bpm.setText("Bpm(opcional):");
        Bpm.setToolTipText("");

        InputTitutlo.setBackground(new java.awt.Color(11, 27, 70));
        InputTitutlo.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputTitutlo.setForeground(java.awt.Color.white);
        InputTitutlo.setBorder(null);
        InputTitutlo.setCaretColor(java.awt.Color.white);
        InputTitutlo.setPreferredSize(new java.awt.Dimension(610, 27));
        InputTitutlo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTitutloActionPerformed(evt);
            }
        });

        Oitava.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Oitava.setForeground(java.awt.Color.white);
        Oitava.setText("Oitava:");
        Oitava.setToolTipText("");

        InputOitava.setBackground(new java.awt.Color(11, 27, 70));
        InputOitava.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputOitava.setForeground(java.awt.Color.white);
        InputOitava.setBorder(null);
        InputOitava.setCaretColor(java.awt.Color.white);
        InputOitava.setPreferredSize(new java.awt.Dimension(610, 27));
        InputOitava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputOitavaActionPerformed(evt);
            }
        });

        Nota.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Nota.setForeground(java.awt.Color.white);
        Nota.setText("Nota:");
        Nota.setToolTipText("");

        InputBpm.setBackground(new java.awt.Color(11, 27, 70));
        InputBpm.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputBpm.setForeground(java.awt.Color.white);
        InputBpm.setBorder(null);
        InputBpm.setCaretColor(java.awt.Color.white);
        InputBpm.setName("opcional"); // NOI18N
        InputBpm.setPreferredSize(new java.awt.Dimension(610, 27));
        InputBpm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBpmActionPerformed(evt);
            }
        });

        Creditos_Gravacao.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Creditos_Gravacao.setForeground(java.awt.Color.white);
        Creditos_Gravacao.setText("Creditos_Gravação(opcional):");
        Creditos_Gravacao.setToolTipText("");

        InputCreditos_Gravacao.setBackground(new java.awt.Color(11, 27, 70));
        InputCreditos_Gravacao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputCreditos_Gravacao.setForeground(java.awt.Color.white);
        InputCreditos_Gravacao.setBorder(null);
        InputCreditos_Gravacao.setCaretColor(java.awt.Color.white);
        InputCreditos_Gravacao.setName("opcional"); // NOI18N
        InputCreditos_Gravacao.setPreferredSize(new java.awt.Dimension(610, 27));
        InputCreditos_Gravacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCreditos_GravacaoActionPerformed(evt);
            }
        });

        AudioDrop.setBackground(new java.awt.Color(11, 27, 70));
        AudioDrop.setForeground(java.awt.Color.white);
        AudioDrop.setToolTipText("");
        AudioDrop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AudioDrop.setName("semAudio"); // NOI18N

        Icone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/upload.png"))); // NOI18N
        Icone.setBorder(null);
        Icone.setContentAreaFilled(false);
        Icone.setEnabled(false);
        Icone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IconeActionPerformed(evt);
            }
        });

        InstrucaoArquivo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        InstrucaoArquivo.setForeground(java.awt.Color.white);
        InstrucaoArquivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InstrucaoArquivo.setText("Arraste o Arquivo Aqui");

        ExtensoesArquivo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ExtensoesArquivo.setForeground(java.awt.Color.white);
        ExtensoesArquivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExtensoesArquivo.setText(".mp3, .aif, .aiff, .wav .au");

        javax.swing.GroupLayout AudioDropLayout = new javax.swing.GroupLayout(AudioDrop);
        AudioDrop.setLayout(AudioDropLayout);
        AudioDropLayout.setHorizontalGroup(
            AudioDropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AudioDropLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AudioDropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExtensoesArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(InstrucaoArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(AudioDropLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Icone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AudioDropLayout.setVerticalGroup(
            AudioDropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AudioDropLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(InstrucaoArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Icone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ExtensoesArquivo)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Tecnica.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Tecnica.setForeground(java.awt.Color.white);
        Tecnica.setText("Tecnica(opcional):");
        Tecnica.setToolTipText("");

        InputTecnica_Id.setBackground(new java.awt.Color(11, 27, 70));
        InputTecnica_Id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputTecnica_Id.setForeground(java.awt.Color.white);
        InputTecnica_Id.setBorder(null);
        InputTecnica_Id.setCaretColor(java.awt.Color.white);
        InputTecnica_Id.setName("opcional"); // NOI18N
        InputTecnica_Id.setPreferredSize(new java.awt.Dimension(610, 27));
        InputTecnica_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTecnica_IdActionPerformed(evt);
            }
        });

        AdicionarTecnica.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarTecnica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarTecnica.setBorder(null);
        AdicionarTecnica.setBorderPainted(false);
        AdicionarTecnica.setContentAreaFilled(false);
        AdicionarTecnica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarTecnica.setFocusPainted(false);
        AdicionarTecnica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarTecnicaActionPerformed(evt);
            }
        });

        ListarTecnica.setBackground(new java.awt.Color(11, 27, 58));
        ListarTecnica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarTecnica.setBorder(null);
        ListarTecnica.setBorderPainted(false);
        ListarTecnica.setContentAreaFilled(false);
        ListarTecnica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarTecnica.setFocusPainted(false);
        ListarTecnica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarTecnicaActionPerformed(evt);
            }
        });

        BotaoRegistrar.setBackground(new java.awt.Color(252, 255, 255));
        BotaoRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        BotaoRegistrar.setText("Registrar");
        BotaoRegistrar.setBorderPainted(false);
        BotaoRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotaoRegistrar.setMaximumSize(new java.awt.Dimension(180, 50));
        BotaoRegistrar.setPreferredSize(new java.awt.Dimension(180, 50));
        BotaoRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRegistrarActionPerformed(evt);
            }
        });

        BotaoCancelar.setBackground(new java.awt.Color(252, 255, 255));
        BotaoCancelar.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        BotaoCancelar.setText("Cancelar");
        BotaoCancelar.setBorderPainted(false);
        BotaoCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotaoCancelar.setPreferredSize(new java.awt.Dimension(180, 50));
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });

        Instrumento_ID.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Instrumento_ID.setForeground(java.awt.Color.white);
        Instrumento_ID.setText("Instrumento_id:");
        Instrumento_ID.setToolTipText("");

        AdicionarInstrumentoID.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarInstrumentoID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarInstrumentoID.setBorder(null);
        AdicionarInstrumentoID.setBorderPainted(false);
        AdicionarInstrumentoID.setContentAreaFilled(false);
        AdicionarInstrumentoID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarInstrumentoID.setFocusPainted(false);
        AdicionarInstrumentoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarInstrumentoIDActionPerformed(evt);
            }
        });

        ListarInstrumentoID.setBackground(new java.awt.Color(11, 27, 58));
        ListarInstrumentoID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarInstrumentoID.setBorder(null);
        ListarInstrumentoID.setBorderPainted(false);
        ListarInstrumentoID.setContentAreaFilled(false);
        ListarInstrumentoID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarInstrumentoID.setFocusPainted(false);
        ListarInstrumentoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarInstrumentoIDActionPerformed(evt);
            }
        });

        InputInstrumento_Id.setBackground(new java.awt.Color(11, 27, 70));
        InputInstrumento_Id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputInstrumento_Id.setForeground(java.awt.Color.white);
        InputInstrumento_Id.setBorder(null);
        InputInstrumento_Id.setCaretColor(java.awt.Color.white);
        InputInstrumento_Id.setName(""); // NOI18N
        InputInstrumento_Id.setPreferredSize(new java.awt.Dimension(610, 27));
        InputInstrumento_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputInstrumento_IdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TelaLayout = new javax.swing.GroupLayout(Tela);
        Tela.setLayout(TelaLayout);
        TelaLayout.setHorizontalGroup(
            TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TelaLayout.createSequentialGroup()
                        .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                        .addComponent(Descricao)
                        .addGap(283, 283, 283))
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputTecnica_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addComponent(Instrumento_ID)
                                .addGap(18, 18, 18)
                                .addComponent(AdicionarInstrumentoID)
                                .addGap(18, 18, 18)
                                .addComponent(ListarInstrumentoID))
                            .addComponent(InputInstrumento_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AudioDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(107, 107, 107))
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(InputNota, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Bpm)
                                .addComponent(InputBpm, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(InputTitutlo, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addComponent(Tecnica)
                                .addGap(18, 18, 18)
                                .addComponent(AdicionarTecnica)
                                .addGap(18, 18, 18)
                                .addComponent(ListarTecnica)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(InputOitava, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Creditos_Gravacao)
                                .addComponent(Oitava, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(InputCreditos_Gravacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ScrollDescricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TelaLayout.createSequentialGroup()
                        .addComponent(TituloPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 1082, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        TelaLayout.setVerticalGroup(
            TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(TituloPagina)
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addComponent(InputTitutlo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ScrollDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(InputNota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Bpm, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Oitava)
                        .addGap(18, 18, 18)
                        .addComponent(InputOitava, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Creditos_Gravacao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputBpm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputCreditos_Gravacao, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Tecnica, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ListarTecnica)
                                .addComponent(AdicionarTecnica, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(AudioDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TelaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(InputTecnica_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Instrumento_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ListarInstrumentoID)
                                        .addComponent(AdicionarInstrumentoID, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGap(18, 18, 18)
                                .addComponent(InputInstrumento_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tela, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNotaActionPerformed

    private void InputTitutloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTitutloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTitutloActionPerformed

    private void InputOitavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputOitavaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputOitavaActionPerformed

    private void InputBpmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBpmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBpmActionPerformed

    private void InputCreditos_GravacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCreditos_GravacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCreditos_GravacaoActionPerformed

    private void IconeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IconeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IconeActionPerformed

    private void InputTecnica_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTecnica_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTecnica_IdActionPerformed

    private void ListarTecnicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarTecnicaActionPerformed
        if(telaListarTecnica != null){
            
            if(!telaListarTecnica.isDisplayable()){
                
                telaListarTecnica = null;
            }else{
                
                return;
            }
        }
        try{
            
            telaListarTecnica = new TelaListarPadrao("Tecnica", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        
        telaListarTecnica.setVisible(true);
    }//GEN-LAST:event_ListarTecnicaActionPerformed

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
        this.dispose();
        
        if(!unicaTela) return;
        
        if(telaPrincipal != null) telaPrincipal.setVisible(true);
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void AdicionarTecnicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarTecnicaActionPerformed
        
        if(telaInputTecnica != null){
            
            if(!telaInputTecnica.isDisplayable()){
                
                telaInputTecnica = null;
            }else{
                
                return;
            }
        }
        
        telaInputTecnica = new InterfaceInputPadrao("Tecnica", false, false, null, telaPrincipal);
            
        telaInputTecnica.setVisible(true);
    }//GEN-LAST:event_AdicionarTecnicaActionPerformed

    private void ListarInstrumentoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarInstrumentoIDActionPerformed
        if(telaListarInstrumento != null){
            
            if(!telaListarInstrumento.isDisplayable()){
                
                telaListarInstrumento = null;
            }else{
                
                return;
            }
        }
        
        try{
            
            telaListarInstrumento = new TelaListarPadrao("Instrumento", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        telaListarInstrumento.setVisible(true);
    }//GEN-LAST:event_ListarInstrumentoIDActionPerformed

    private void InputInstrumento_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputInstrumento_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputInstrumento_IdActionPerformed

    private void AdicionarInstrumentoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarInstrumentoIDActionPerformed
        
        telaInputFamiliaID = new InterfaceInputPadrao("Familia_id", false, false, null, telaPrincipal);
            
        telaInputFamiliaID.setVisible(true);
    }//GEN-LAST:event_AdicionarInstrumentoIDActionPerformed

    private void finalizarTela(Map<String, Long> retornos){
        Timer temporizador = new Timer(3000, evento -> {
            
            this.dispose();
            
            if(!unicaTela) return;

            if(telaPrincipal != null) telaPrincipal.setVisible(true);
        });
        
        temporizador.setRepeats(false);
        
        temporizador.start();
        
        BotaoCancelar.setEnabled(false);
        BotaoRegistrar.setEnabled(false);
        
        final Confirmacao telaConfirmacao;
        
        if(retornos.get("Codigo") != 200){
            
            
            if(editar) telaConfirmacao = new Confirmacao("Audio", "Objeto não Atualizado", false);
            else telaConfirmacao = new Confirmacao("Audio", "Objeto não Registrado", false);
            
            telaConfirmacao.setVisible(true);
        
            return;
        }
        
        if(editar) telaConfirmacao = new Confirmacao("Audio", "Objeto Atualizado", true);
        else telaConfirmacao = new Confirmacao("Audio", "Objeto Registrado", true);
        
        telaConfirmacao.setVisible(true);
        
        Timer temporizador2 = new Timer(2500, (evento) -> {
            
            telaConfirmacao.dispose();
        });
        
        temporizador2.setRepeats(false);
        
        temporizador2.start();
    }
    
    private void BotaoRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRegistrarActionPerformed
        boolean faltaCampo = false;
        
        for(Component componente: Tela.getComponents()){
                
            if(componente instanceof JTextField campoTexto){
                    
                if((campoTexto.getText() == null || campoTexto.getText().isBlank()) && !editar){
                    
                    if(campoTexto.getName() != null && campoTexto.getName().equalsIgnoreCase("opcional")) continue;
                    
                    faltaCampo = true;

                    campoTexto.setBorder(bordaVermelha);

                }else{

                    campoTexto.setBorder(bordaBranca);
                }
                    
            }
        }
        
        if(AudioDrop.getName().equalsIgnoreCase("semAudio") && !editar){
            
            faltaCampo = true;
            
            AudioDrop.setBorder(bordaVermelha);
        }else{
            
            AudioDrop.setBorder(bordaBranca);
        }
        
        if(faltaCampo) return;
        
        String titulo = InputTitutlo.getText() == null || InputTitutlo.getText().isBlank() ? null : InputTitutlo.getText();
        String nota = InputNota.getText() == null || InputNota.getText().isBlank() ? null : InputNota.getText();
        
        String descricao = InputDescricao.getText() == null || InputDescricao.getText().isBlank() ? null : InputDescricao.getText();
        Long oitava = InputOitava.getText() == null || InputOitava.getText().isBlank() ? -1L : Long.parseLong(InputOitava.getText());
        
        Long bpm = InputBpm.getText() == null || InputBpm.getText().isBlank() ? -1L : Long.parseLong(InputBpm.getText());
        String creditos = InputCreditos_Gravacao.getText() == null || InputCreditos_Gravacao.getText().isBlank() ? 
                null : InputCreditos_Gravacao.getText();
        
        Long tecnica_id = InputTecnica_Id.getText() == null ||
                InputTecnica_Id.getText().isBlank() ? -1L : Long.parseLong(InputTecnica_Id.getText());
        Long instrumento_id = InputInstrumento_Id.getText() == null ||
                InputInstrumento_Id.getText().isBlank() ? -1L : Long.parseLong(InputInstrumento_Id.getText());
        
        Map<String, Long> retornos = new HashMap<>();
        
        AudioControl audioController = new AudioControl();
        TecnicaControl tecnicaController = new TecnicaControl();
        InstrumentosControl instrumentoController = new InstrumentosControl();
        
        try{
            
            if(editar){
                
                Audio audio = (Audio) dado;
                
                if(titulo != null) audio.setTitulo(titulo);
                if(nota != null) audio.setNota(nota);
                if(descricao != null) audio.setDescricao(descricao);
                if(oitava != -1) audio.setOitava(oitava);
                if(bpm != -1) audio.setCreditoGravacao(creditos);
                
                if(tecnica_id != -1){
                    
                    Tecnica tecnica = tecnicaController.listarTecnicaporId(tecnica_id);
                    if(tecnica != null){

                        audio.setTecnica(tecnica);

                    }else{

                        ErroTextNome.setText("Não existe Tecnica com esse ID");

                        NomeErro.show(InputTecnica_Id, 0, InputTecnica_Id.getHeight());

                        return;
                    }
                }
                
                if(instrumento_id != -1){
                    Instrumento instrumento = instrumentoController.listarInstrumentosporID(instrumento_id, "harmonico");
                    if(instrumento == null) instrumento = instrumentoController.listarInstrumentosporID(instrumento_id, "melodico");
                    if(instrumento == null) instrumento = instrumentoController.listarInstrumentosporID(instrumento_id, "ritmico");
                                         
                    if(instrumento != null){

                        audio.setInstrumentoID(instrumento_id);

                    }else{
                        ErroTextNome.setText("Não existe Instrumento com esse ID");

                        NomeErro.show(InputInstrumento_Id, 0, InputInstrumento_Id.getHeight());

                        return;
                    }
                }   
                
                retornos = audioController.atualizarAudio(audio);

                finalizarTela(retornos);
                
                return;
            }
            
        }catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        try{
            
            retornos = audioController.adicionarAudio(instrumento_id, titulo, nota, oitava, arquivoCaminho, descricao, bpm, creditos, tecnica_id);
        
        }catch (Exception e){
         
            System.out.println("Erro: " + e.getMessage());
        }
        
        if(retornos.containsKey("Tecnica")){
            
            ErroTextNome.setText("Não existe Tecnica com esse ID");
            
            NomeErro.show(InputTecnica_Id, 0, InputTecnica_Id.getHeight());
            
            return;
            
        }else if (retornos.containsKey("Instrumento")){
            
            ErroTextNome.setText("Não existe Instrumento com esse ID");
            
            NomeErro.show(InputInstrumento_Id, 0, InputInstrumento_Id.getHeight());
            
            return;
            
        }
        
       finalizarTela(retornos); 
    }//GEN-LAST:event_BotaoRegistrarActionPerformed

    private void ErroTextNomepadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErroTextNomepadraoActionPerformed

    }//GEN-LAST:event_ErroTextNomepadraoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdicionarInstrumentoID;
    private javax.swing.JButton AdicionarTecnica;
    private javax.swing.JPanel AudioDrop;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoRegistrar;
    private javax.swing.JLabel Bpm;
    private javax.swing.JLabel Creditos_Gravacao;
    private javax.swing.JLabel Descricao;
    private javax.swing.JMenuItem ErroTextNome;
    private javax.swing.JLabel ExtensoesArquivo;
    private javax.swing.JButton Icone;
    private javax.swing.JTextField InputBpm;
    private javax.swing.JTextField InputCreditos_Gravacao;
    private javax.swing.JTextArea InputDescricao;
    private javax.swing.JTextField InputInstrumento_Id;
    private javax.swing.JTextField InputNota;
    private javax.swing.JTextField InputOitava;
    private javax.swing.JTextField InputTecnica_Id;
    private javax.swing.JTextField InputTitutlo;
    private javax.swing.JLabel InstrucaoArquivo;
    private javax.swing.JLabel Instrumento_ID;
    private javax.swing.JButton ListarInstrumentoID;
    private javax.swing.JButton ListarTecnica;
    private javax.swing.JPopupMenu NomeErro;
    private javax.swing.JLabel Nota;
    private javax.swing.JLabel Oitava;
    private javax.swing.JScrollPane ScrollDescricao;
    private javax.swing.JLabel Tecnica;
    private javax.swing.JPanel Tela;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel TituloPagina;
    // End of variables declaration//GEN-END:variables
}
