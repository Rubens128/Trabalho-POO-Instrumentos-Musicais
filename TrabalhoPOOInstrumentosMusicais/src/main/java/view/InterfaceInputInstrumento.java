/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.AfinacaoControl;
import controller.AudioControl;
import controller.FamiliaInstrumentoControl;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import controller.InstrumentosControl;
import controller.MaterialControl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import model.Afinacao;
import model.AfinacaoTransposicao;
import model.Audio;
import model.CategoriaPercussao;
import model.FamiliaInstrumento;
import model.Instrumento;
import model.InstrumentoHarmonico;
import model.InstrumentoMelodico;
import model.InstrumentoRitmico;
import model.Material;
import model.TocadoCom;

/**
 *
 * @author ruben
 */
public class InterfaceInputInstrumento extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfaceInputInstrumento.class.getName());
    private InterfaceInputAudio telaInputAudio;
    private InterfaceInputPadrao telaInputFamilia, telaInputAfinacao, telaInputMaterial; 
    private boolean unicaTela;
    private TelaListarPadrao telaListarAudio, telaListarFamilia, telaListarAfinacao, telaListarMaterial;
    private String especializacao;
    private Object dado;
    private boolean editar;
    private final TelaInstrumentos telaPrincipal;
    
    Border bordaVermelha = BorderFactory.createLineBorder(Color.RED, 3);
    
    Border bordaBranca = BorderFactory.createLineBorder(Color.WHITE, 3);
    
    /**
     * Creates new form InterfaceInputInstrumento
     * @param especializacao variavel para identificar a especialização do instrumento
     */
    public InterfaceInputInstrumento(String especializacao, boolean unicaTela, boolean editar, Object dado, TelaInstrumentos telaPrincipal) {
        initComponents();
        
        this.telaPrincipal = telaPrincipal;
        
        setLocationRelativeTo(null);
        
        this.especializacao = especializacao;
        this.dado = dado;
        this.editar = editar;
        
        if(editar){
            
            Titulo.setText("Instrumento" + " (Não digitar nada mantém o valor atual):");
            
            Descricao.setText("Descrição:");
            Historia.setText("História:");
            Afinacao_ids.setText("Afinação_ids:");
            Audio_ids.setText("Audio_ids:");
        }
        
        if(especializacao.equalsIgnoreCase("harmonico")){
            
            Opcao1.setText("Polifonia_max:");
            Opcao2.setText("Tem_pedal_sustain?");
            Opcao3.setText("Suporta_acordes?");
            
        }else if(especializacao.equalsIgnoreCase("melodico")){
            
            Opcao1.setText("Transpositor?");
            Opcao2.setText("Afinacao_transposicao:");
            Opcao3.setText("Microtonalidade_suportada?");
            
        }else if(especializacao.equalsIgnoreCase("ritmico")){
            
            Opcao1.setText("Altura_definida?");
            Opcao2.setText("Categoria_percussao:");
            Opcao3.setText("Tocado_com:");
        }
        
        this.unicaTela = unicaTela;
        
        SwingUtilities.invokeLater(() -> {
        
            for(Component componente: Tela.getComponents()){
                
                if(componente instanceof JTextField campoTexto){
                    
                    campoTexto.setBorder(bordaBranca);
                    
                }else if(componente instanceof JScrollPane areaTextoScroll){
                    
                    areaTextoScroll.setBorder(bordaBranca);
                   
                }
            }
            
            KeyAdapter funcaoSoNumero = new KeyAdapter() {
                
                @Override
                public void keyTyped(KeyEvent e){
                    
                    char caractere = e.getKeyChar();
                    
                    if(!Character.isDigit(caractere)){
                        e.consume();
                    }
                }
            };
            
            InputAudio_ids.addKeyListener(funcaoSoNumero);
            
            InputFamInsId.addKeyListener(funcaoSoNumero);
                    
            InputAfinacao_ids.addKeyListener(funcaoSoNumero);
                    
            InputMaterial.addKeyListener(funcaoSoNumero);
            
        });
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
        Titulo = new javax.swing.JLabel();
        Classificacao_sonoridade = new javax.swing.JLabel();
        InputClassificacao_sonoridade = new javax.swing.JTextField();
        Descricao = new javax.swing.JLabel();
        InputNome = new javax.swing.JTextField();
        Nome = new javax.swing.JLabel();
        ScrollDescricao = new javax.swing.JScrollPane();
        InputDescricao = new javax.swing.JTextArea();
        Historia = new javax.swing.JLabel();
        ScrollHistoria = new javax.swing.JScrollPane();
        InputHistoria = new javax.swing.JTextArea();
        Audio_ids = new javax.swing.JLabel();
        Apelidos = new javax.swing.JLabel();
        AdicionarFamInsId = new javax.swing.JButton();
        ListarAudio_ids = new javax.swing.JButton();
        InputAfinacao_ids = new javax.swing.JTextField();
        Familia_Instrumento_id = new javax.swing.JLabel();
        InputAudio_ids = new javax.swing.JTextField();
        AdicionarAudio_ids = new javax.swing.JButton();
        ListarFamInsId = new javax.swing.JButton();
        Afinacao_ids = new javax.swing.JLabel();
        AdicionarAfinacao_ids = new javax.swing.JButton();
        ListarAfinacao_ids = new javax.swing.JButton();
        InputFamInsId = new javax.swing.JTextField();
        InputOpcao1 = new javax.swing.JTextField();
        NotaMinNotaMax = new javax.swing.JLabel();
        InputApelidos = new javax.swing.JTextField();
        ParteNome = new javax.swing.JLabel();
        InputOpcao2 = new javax.swing.JTextField();
        Opcao3 = new javax.swing.JLabel();
        AdicionarMaterial = new javax.swing.JButton();
        ListarMaterial = new javax.swing.JButton();
        InputMaterial = new javax.swing.JTextField();
        Material = new javax.swing.JLabel();
        InputNotaMinNotaMax = new javax.swing.JTextField();
        Opcao1 = new javax.swing.JLabel();
        InputParte = new javax.swing.JTextField();
        Opcao2 = new javax.swing.JLabel();
        InputOpcao3 = new javax.swing.JTextField();
        BotaoRegistrar = new javax.swing.JButton();
        BotaoCancelar = new javax.swing.JButton();

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
        Tela.setPreferredSize(new java.awt.Dimension(1530, 900));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        Titulo.setForeground(java.awt.Color.white);
        Titulo.setText("Instrumento:");

        Classificacao_sonoridade.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Classificacao_sonoridade.setForeground(java.awt.Color.white);
        Classificacao_sonoridade.setText("Classificacao_sonoridade:");
        Classificacao_sonoridade.setToolTipText("");

        InputClassificacao_sonoridade.setBackground(new java.awt.Color(11, 27, 70));
        InputClassificacao_sonoridade.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputClassificacao_sonoridade.setForeground(java.awt.Color.white);
        InputClassificacao_sonoridade.setBorder(null);
        InputClassificacao_sonoridade.setCaretColor(java.awt.Color.white);
        InputClassificacao_sonoridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputClassificacao_sonoridadeActionPerformed(evt);
            }
        });

        Descricao.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Descricao.setForeground(java.awt.Color.white);
        Descricao.setText("Descrição(opcional):");
        Descricao.setToolTipText("");

        InputNome.setBackground(new java.awt.Color(11, 27, 70));
        InputNome.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputNome.setForeground(java.awt.Color.white);
        InputNome.setBorder(null);
        InputNome.setCaretColor(java.awt.Color.white);
        InputNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomeActionPerformed(evt);
            }
        });

        Nome.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Nome.setForeground(java.awt.Color.white);
        Nome.setText("Nome:");
        Nome.setToolTipText("");

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
        InputDescricao.setName("opcional"); // NOI18N
        ScrollDescricao.setViewportView(InputDescricao);

        Historia.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Historia.setForeground(java.awt.Color.white);
        Historia.setText("História(opcional):");
        Historia.setToolTipText("");

        ScrollHistoria.setBorder(null);

        InputHistoria.setBackground(new java.awt.Color(11, 27, 70));
        InputHistoria.setColumns(20);
        InputHistoria.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputHistoria.setForeground(java.awt.Color.white);
        InputHistoria.setLineWrap(true);
        InputHistoria.setRows(3);
        InputHistoria.setWrapStyleWord(true);
        InputHistoria.setBorder(null);
        InputHistoria.setCaretColor(java.awt.Color.white);
        InputHistoria.setName("opcional"); // NOI18N
        ScrollHistoria.setViewportView(InputHistoria);

        Audio_ids.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Audio_ids.setForeground(java.awt.Color.white);
        Audio_ids.setText("Audio_ids(opcional):");
        Audio_ids.setToolTipText("");

        Apelidos.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Apelidos.setForeground(java.awt.Color.white);
        Apelidos.setText("Apelidos:");
        Apelidos.setToolTipText("");

        AdicionarFamInsId.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarFamInsId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarFamInsId.setBorder(null);
        AdicionarFamInsId.setBorderPainted(false);
        AdicionarFamInsId.setContentAreaFilled(false);
        AdicionarFamInsId.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarFamInsId.setFocusPainted(false);
        AdicionarFamInsId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarFamInsIdActionPerformed(evt);
            }
        });

        ListarAudio_ids.setBackground(new java.awt.Color(11, 27, 58));
        ListarAudio_ids.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarAudio_ids.setBorder(null);
        ListarAudio_ids.setBorderPainted(false);
        ListarAudio_ids.setContentAreaFilled(false);
        ListarAudio_ids.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarAudio_ids.setFocusPainted(false);
        ListarAudio_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarAudio_idsActionPerformed(evt);
            }
        });

        InputAfinacao_ids.setBackground(new java.awt.Color(11, 27, 70));
        InputAfinacao_ids.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputAfinacao_ids.setForeground(java.awt.Color.white);
        InputAfinacao_ids.setBorder(null);
        InputAfinacao_ids.setCaretColor(java.awt.Color.white);
        InputAfinacao_ids.setName("opcional"); // NOI18N
        InputAfinacao_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputAfinacao_idsActionPerformed(evt);
            }
        });

        Familia_Instrumento_id.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Familia_Instrumento_id.setForeground(java.awt.Color.white);
        Familia_Instrumento_id.setText("Familia_Instrumento_id:");
        Familia_Instrumento_id.setToolTipText("");

        InputAudio_ids.setBackground(new java.awt.Color(11, 27, 70));
        InputAudio_ids.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputAudio_ids.setForeground(java.awt.Color.white);
        InputAudio_ids.setBorder(null);
        InputAudio_ids.setCaretColor(java.awt.Color.white);
        InputAudio_ids.setName("opcional"); // NOI18N
        InputAudio_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputAudio_idsActionPerformed(evt);
            }
        });

        AdicionarAudio_ids.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarAudio_ids.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarAudio_ids.setBorder(null);
        AdicionarAudio_ids.setBorderPainted(false);
        AdicionarAudio_ids.setContentAreaFilled(false);
        AdicionarAudio_ids.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarAudio_ids.setFocusPainted(false);
        AdicionarAudio_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarAudio_idsActionPerformed(evt);
            }
        });

        ListarFamInsId.setBackground(new java.awt.Color(11, 27, 58));
        ListarFamInsId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarFamInsId.setBorder(null);
        ListarFamInsId.setBorderPainted(false);
        ListarFamInsId.setContentAreaFilled(false);
        ListarFamInsId.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarFamInsId.setFocusPainted(false);
        ListarFamInsId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarFamInsIdActionPerformed(evt);
            }
        });

        Afinacao_ids.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Afinacao_ids.setForeground(java.awt.Color.white);
        Afinacao_ids.setText("Afinação_ids(opcional):");
        Afinacao_ids.setToolTipText("");

        AdicionarAfinacao_ids.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarAfinacao_ids.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarAfinacao_ids.setBorder(null);
        AdicionarAfinacao_ids.setBorderPainted(false);
        AdicionarAfinacao_ids.setContentAreaFilled(false);
        AdicionarAfinacao_ids.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarAfinacao_ids.setFocusPainted(false);
        AdicionarAfinacao_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarAfinacao_idsActionPerformed(evt);
            }
        });

        ListarAfinacao_ids.setBackground(new java.awt.Color(11, 27, 58));
        ListarAfinacao_ids.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarAfinacao_ids.setBorder(null);
        ListarAfinacao_ids.setBorderPainted(false);
        ListarAfinacao_ids.setContentAreaFilled(false);
        ListarAfinacao_ids.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarAfinacao_ids.setFocusPainted(false);
        ListarAfinacao_ids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarAfinacao_idsActionPerformed(evt);
            }
        });

        InputFamInsId.setBackground(new java.awt.Color(11, 27, 70));
        InputFamInsId.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputFamInsId.setForeground(java.awt.Color.white);
        InputFamInsId.setBorder(null);
        InputFamInsId.setCaretColor(java.awt.Color.white);
        InputFamInsId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputFamInsIdActionPerformed(evt);
            }
        });

        InputOpcao1.setBackground(new java.awt.Color(11, 27, 70));
        InputOpcao1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputOpcao1.setForeground(java.awt.Color.white);
        InputOpcao1.setBorder(null);
        InputOpcao1.setCaretColor(java.awt.Color.white);
        InputOpcao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputOpcao1ActionPerformed(evt);
            }
        });

        NotaMinNotaMax.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        NotaMinNotaMax.setForeground(java.awt.Color.white);
        NotaMinNotaMax.setText("Nota_min e Nota_max:");
        NotaMinNotaMax.setToolTipText("");

        InputApelidos.setBackground(new java.awt.Color(11, 27, 70));
        InputApelidos.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputApelidos.setForeground(java.awt.Color.white);
        InputApelidos.setBorder(null);
        InputApelidos.setCaretColor(java.awt.Color.white);
        InputApelidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputApelidosActionPerformed(evt);
            }
        });

        ParteNome.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        ParteNome.setForeground(java.awt.Color.white);
        ParteNome.setText("Parte(s)_nome(s):");
        ParteNome.setToolTipText("");

        InputOpcao2.setBackground(new java.awt.Color(11, 27, 70));
        InputOpcao2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputOpcao2.setForeground(java.awt.Color.white);
        InputOpcao2.setBorder(null);
        InputOpcao2.setCaretColor(java.awt.Color.white);
        InputOpcao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputOpcao2ActionPerformed(evt);
            }
        });

        Opcao3.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Opcao3.setForeground(java.awt.Color.white);
        Opcao3.setText("Opção3:");
        Opcao3.setToolTipText("");

        AdicionarMaterial.setBackground(new java.awt.Color(11, 27, 58));
        AdicionarMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_32px.png"))); // NOI18N
        AdicionarMaterial.setBorder(null);
        AdicionarMaterial.setBorderPainted(false);
        AdicionarMaterial.setContentAreaFilled(false);
        AdicionarMaterial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdicionarMaterial.setFocusPainted(false);
        AdicionarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarMaterialActionPerformed(evt);
            }
        });

        ListarMaterial.setBackground(new java.awt.Color(11, 27, 58));
        ListarMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list_32px.png"))); // NOI18N
        ListarMaterial.setBorder(null);
        ListarMaterial.setBorderPainted(false);
        ListarMaterial.setContentAreaFilled(false);
        ListarMaterial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarMaterial.setFocusPainted(false);
        ListarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarMaterialActionPerformed(evt);
            }
        });

        InputMaterial.setBackground(new java.awt.Color(11, 27, 70));
        InputMaterial.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputMaterial.setForeground(java.awt.Color.white);
        InputMaterial.setBorder(null);
        InputMaterial.setCaretColor(java.awt.Color.white);
        InputMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputMaterialActionPerformed(evt);
            }
        });

        Material.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Material.setForeground(java.awt.Color.white);
        Material.setText("Material_partes_ids:");
        Material.setToolTipText("");

        InputNotaMinNotaMax.setBackground(new java.awt.Color(11, 27, 70));
        InputNotaMinNotaMax.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputNotaMinNotaMax.setForeground(java.awt.Color.white);
        InputNotaMinNotaMax.setBorder(null);
        InputNotaMinNotaMax.setCaretColor(java.awt.Color.white);
        InputNotaMinNotaMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNotaMinNotaMaxActionPerformed(evt);
            }
        });

        Opcao1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Opcao1.setForeground(java.awt.Color.white);
        Opcao1.setText("Opção1:");
        Opcao1.setToolTipText("");

        InputParte.setBackground(new java.awt.Color(11, 27, 70));
        InputParte.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputParte.setForeground(java.awt.Color.white);
        InputParte.setBorder(null);
        InputParte.setCaretColor(java.awt.Color.white);
        InputParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputParteActionPerformed(evt);
            }
        });

        Opcao2.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        Opcao2.setForeground(java.awt.Color.white);
        Opcao2.setText("Opção2:");
        Opcao2.setToolTipText("");

        InputOpcao3.setBackground(new java.awt.Color(11, 27, 70));
        InputOpcao3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        InputOpcao3.setForeground(java.awt.Color.white);
        InputOpcao3.setBorder(null);
        InputOpcao3.setCaretColor(java.awt.Color.white);
        InputOpcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputOpcao3ActionPerformed(evt);
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

        javax.swing.GroupLayout TelaLayout = new javax.swing.GroupLayout(Tela);
        Tela.setLayout(TelaLayout);
        TelaLayout.setHorizontalGroup(
            TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TelaLayout.createSequentialGroup()
                                        .addComponent(Familia_Instrumento_id)
                                        .addGap(18, 18, 18)
                                        .addComponent(AdicionarFamInsId)
                                        .addGap(18, 18, 18)
                                        .addComponent(ListarFamInsId))
                                    .addComponent(ScrollDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputFamInsId, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TelaLayout.createSequentialGroup()
                                        .addComponent(Afinacao_ids)
                                        .addGap(18, 18, 18)
                                        .addComponent(AdicionarAfinacao_ids)
                                        .addGap(18, 18, 18)
                                        .addComponent(ListarAfinacao_ids))
                                    .addComponent(ScrollHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputAfinacao_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(TelaLayout.createSequentialGroup()
                                        .addComponent(Material)
                                        .addGap(18, 18, 18)
                                        .addComponent(AdicionarMaterial)
                                        .addGap(18, 18, 18)
                                        .addComponent(ListarMaterial))))
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(InputNome, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputParte, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Opcao2)
                                    .addComponent(InputOpcao2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Descricao))
                                .addGap(36, 36, 36)
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Historia)
                                    .addComponent(InputOpcao3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Opcao3)
                                    .addComponent(Classificacao_sonoridade)
                                    .addComponent(InputClassificacao_sonoridade, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ParteNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaLayout.createSequentialGroup()
                                .addComponent(Apelidos, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(314, 314, 314))
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(InputNotaMinNotaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputApelidos, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(TelaLayout.createSequentialGroup()
                                        .addComponent(Audio_ids)
                                        .addGap(18, 18, 18)
                                        .addComponent(AdicionarAudio_ids)
                                        .addGap(18, 18, 18)
                                        .addComponent(ListarAudio_ids))
                                    .addComponent(InputAudio_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NotaMinNotaMax)
                                    .addComponent(Opcao1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaLayout.createSequentialGroup()
                                        .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(BotaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 1404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        TelaLayout.setVerticalGroup(
            TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo)
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TelaLayout.createSequentialGroup()
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Classificacao_sonoridade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Apelidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TelaLayout.createSequentialGroup()
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(InputNome, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputClassificacao_sonoridade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InputApelidos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Historia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Audio_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ListarAudio_ids)
                                .addComponent(AdicionarAudio_ids, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ScrollDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ScrollHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(InputAudio_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Afinacao_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ListarAfinacao_ids)
                            .addComponent(AdicionarAfinacao_ids)
                            .addComponent(Familia_Instrumento_id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ListarFamInsId)
                                .addComponent(AdicionarFamInsId, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addComponent(NotaMinNotaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputFamInsId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputAfinacao_ids, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputNotaMinNotaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ParteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Material, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ListarMaterial)
                    .addComponent(AdicionarMaterial)
                    .addComponent(Opcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputParte, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Opcao2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Opcao3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BotaoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InputOpcao3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(InputOpcao2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputClassificacao_sonoridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputClassificacao_sonoridadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputClassificacao_sonoridadeActionPerformed

    private void InputNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNomeActionPerformed

    private void InputAfinacao_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputAfinacao_idsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputAfinacao_idsActionPerformed

    private void ListarAudio_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarAudio_idsActionPerformed
         if(telaListarAudio != null){
            
            if(!telaListarAudio.isDisplayable()){
                
                telaListarAudio = null;
            
            }else{
                
                return;
            }
        }
        
        try{
            
            telaListarAudio = new TelaListarPadrao("Audio", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        telaListarAudio.setVisible(true);
    }//GEN-LAST:event_ListarAudio_idsActionPerformed

    private void InputAudio_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputAudio_idsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputAudio_idsActionPerformed

    private void ListarFamInsIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarFamInsIdActionPerformed
        if(telaListarFamilia != null){
            
            if(!telaListarFamilia.isDisplayable()){
                
                telaListarFamilia = null;
            
            }else{
                
                return;
            }
        }
        
        try{
            
            telaListarFamilia = new TelaListarPadrao("Familia_Instrumento", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        telaListarFamilia.setVisible(true);
    }//GEN-LAST:event_ListarFamInsIdActionPerformed

    private void ListarAfinacao_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarAfinacao_idsActionPerformed
        if(telaListarAfinacao != null){
            
            if(!telaListarAfinacao.isDisplayable()){
                
                telaListarAfinacao = null;
            
            }else{
                
                return;
            }
        }
        
        try{
            
            telaListarAfinacao = new TelaListarPadrao("Afinação", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        telaListarAfinacao.setVisible(true);
    }//GEN-LAST:event_ListarAfinacao_idsActionPerformed

    private void InputFamInsIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputFamInsIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputFamInsIdActionPerformed

    
    private void InputOpcao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputOpcao1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputOpcao1ActionPerformed

    private void InputApelidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputApelidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputApelidosActionPerformed

    private void InputOpcao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputOpcao2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputOpcao2ActionPerformed

    private void ListarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarMaterialActionPerformed
        if(telaListarMaterial != null){
            
            if(!telaListarMaterial.isDisplayable()){
                
                telaListarMaterial = null;
            
            }else{
                
                return;
            }
        }
        
        try{
            
            telaListarMaterial = new TelaListarPadrao("Material", null, telaPrincipal);
        
        } catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        telaListarMaterial.setVisible(true);
    }//GEN-LAST:event_ListarMaterialActionPerformed

    private void InputMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputMaterialActionPerformed

    private void InputNotaMinNotaMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNotaMinNotaMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNotaMinNotaMaxActionPerformed

    private void InputParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputParteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputParteActionPerformed

    private void InputOpcao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputOpcao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputOpcao3ActionPerformed

    private void finalizarTela(Map<String, Long> retornos){
        Timer temporizador = new Timer(3000, evento -> {
            
            this.dispose();
            
            if(!unicaTela) return;

            TelaInstrumentos telaInstrumento = null;

            telaPrincipal.dispose();
            
            try{
                
                telaInstrumento = new TelaInstrumentos();
            
            }catch (Exception e){
                
                System.out.println("Erro: " + e.getMessage());
            }
            
            telaInstrumento.setVisible(true);
            
        });
        
        temporizador.setRepeats(false);
        
        temporizador.start();
        
        BotaoCancelar.setEnabled(false);
        BotaoRegistrar.setEnabled(false);
        
        final Confirmacao telaConfirmacao;
        
        if(retornos.get("Codigo") != 200){
            
            
            if(editar) telaConfirmacao = new Confirmacao("Instrumento", "Objeto não Atualizado", false);
            else telaConfirmacao = new Confirmacao("Instrumento", "Objeto não Registrado", false);
            
            telaConfirmacao.setVisible(true);
        
            return;
        }
        
        if(editar) telaConfirmacao = new Confirmacao("Instrumento", "Objeto Atualizado", true);
        else telaConfirmacao = new Confirmacao("Instrumento", "Objeto Registrado", true);
        
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
        
        String nome = InputNome.getText() == null || InputNome.getText().isBlank() ? null : InputNome.getText();
        String descricao = InputDescricao.getText() == null || InputDescricao.getText().isBlank() ? null : InputDescricao.getText();
        
        String classificacaoSonoridade = InputClassificacao_sonoridade.getText() == null || 
                InputClassificacao_sonoridade.getText().isBlank() ? null : InputClassificacao_sonoridade.getText();
        
        String historia = InputHistoria.getText() == null || InputHistoria.getText().isBlank() ? null : InputHistoria.getText();
        Long familia_id = InputFamInsId.getText() == null || InputFamInsId.getText().isBlank() ? -1L : Long.parseLong(InputFamInsId.getText());
        
        Long afinacao_id = InputAfinacao_ids.getText() == null ||
                InputAfinacao_ids.getText().isBlank() ? -1L : Long.parseLong(InputAfinacao_ids.getText());
        
        String parteNome = InputParte.getText() == null || InputParte.getText().isBlank() ? null : InputParte.getText();
        Long material_id = InputMaterial.getText() == null || InputMaterial.getText().isBlank() ? -1L : Long.parseLong(InputMaterial.getText());
        
        Long audio_id = InputAudio_ids.getText() == null || InputAudio_ids.getText().isBlank() ? -1L : Long.parseLong(InputAudio_ids.getText());
        
        
        Object opcao1 = null, opcao2 = null, opcao3 = null;
        
        if(faltaCampo) return;
        
        Map<String, Long> retornos = new HashMap<>();
        
        InstrumentosControl instrumentoController = new InstrumentosControl();
        
        if(especializacao.equalsIgnoreCase("harmonico")){
            
            if(Opcao1.getText().chars().allMatch(Character::isDigit)){
                
                opcao1 = Long.parseLong(Opcao1.getText());
                
            }else{
                
                opcao1 = 0L;

            }
            
            if(Opcao2.getText().equalsIgnoreCase("verdadeiro") || Opcao2.getText().equalsIgnoreCase("true")){
                
                opcao2 = true;
                
            }else{
                
                opcao2 = false;
                
            }
            
            if(Opcao3.getText().equalsIgnoreCase("verdadeiro") || Opcao3.getText().equalsIgnoreCase("true")){
                
                opcao3 = true;
                
            }else{
                
                opcao3 = false;
                
            }
            
            
        }else if(especializacao.equalsIgnoreCase("melodico")){
            
            if(Opcao1.getText().equalsIgnoreCase("verdadeiro") || Opcao1.getText().equalsIgnoreCase("true")){
                
                opcao1 = true;
                
            }else{
                
                opcao1 = false;
            }
            
            if(List.of("c", "bb", "eb", "f", "outro").contains(Opcao2.getText().toLowerCase())){
                
                opcao2 = Opcao2.getText().toUpperCase();
                
            }else{
                
                opcao2 = "NAOINFORMADO";
                
            }
            
            if(Opcao3.getText().equalsIgnoreCase("verdadeiro") || Opcao3.getText().equalsIgnoreCase("true")){
                
                opcao3 = true;
                
            }else{
                
                opcao3 = false;
                
            }
            
        }else if(especializacao.equalsIgnoreCase("ritmico")){
            
            if(Opcao1.getText().equalsIgnoreCase("verdadeiro") || Opcao1.getText().equalsIgnoreCase("true")){
                
                opcao1 = true;
                
            }else{
                
                opcao1 = false;
               
            }
            
            if(List.of("membranofone", "idiofone", "cordofone", "aerofone", "eletrofone", "outro").contains(Opcao2.getText().toLowerCase())){
                
                opcao2 = Opcao2.getText().toUpperCase();
                
            }else{
                
                opcao2 = "NAOINFORMADO";
                
            }
            
            if(List.of("baqueta", "mao", "hibrido", "outro").contains(Opcao3.getText().toLowerCase())){
                
                opcao3 = Opcao3.getText().toUpperCase();
                
            }else{
                
                opcao3 = "NAOINFORMADO";
                
            }
        }
        
        try{
            
            if(editar){
                
                Instrumento instrumento = (Instrumento) dado;
                
                /*if(nome != null) instrumento.setNome(nome);;
                if(descricao != null) instrumento.setDescricao(descricao);
                if(classificacaoSonoridade != null) instrumento.setClassificacaoSonoridade(classificacaoSonoridade);
                if(historia != null) instrumento.setHistoria(historia);*/
                
                
                if(familia_id != -1L){
                    
                    FamiliaInstrumentoControl familiaInstrumentoController = new FamiliaInstrumentoControl();
                    
                    FamiliaInstrumento familiaInstrumento = familiaInstrumentoController.listarFamiliaporId(familia_id);
                    
                    if(familiaInstrumento != null){

                        instrumento.setFamiliaId(familia_id);

                    }else{

                        ErroTextNome.setText("Não existe Familia com esse ID");

                        NomeErro.show(InputFamInsId, 0, InputFamInsId.getHeight());

                        return;
                    }
                }

                if(afinacao_id != -1L){
                    
                    AfinacaoControl afinacaoController = new AfinacaoControl();
                    
                    ArrayList<Afinacao> afinacao = new ArrayList<>();
                    
                    afinacao.add(afinacaoController.listarAfinacaoporID(afinacao_id));
                   
                    if(afinacao.get(0) != null){

                        instrumento.setAfinacoes(afinacao);

                    }else{

                        ErroTextNome.setText("Não existe Afinação com esse ID");

                        NomeErro.show(InputAfinacao_ids, 0, InputAfinacao_ids.getHeight());

                        return;
                    }
                }
                    
                if(material_id != -1L){
                    
                    MaterialControl materialController = new MaterialControl();
                    
                    ArrayList<Material> material = new ArrayList<>();
                    
                    material.add(materialController.listarMaterialporID(material_id));
                    
                    if(material.get(0) != null){

                        instrumento.setMateriais(material);

                    }else{

                        ErroTextNome.setText("Não existe Material com esse ID");

                        NomeErro.show(InputMaterial, 0, InputMaterial.getHeight());

                        return;
                    }
                }
                    
                if(audio_id != -1L){
                    
                    AudioControl audioController = new AudioControl();
                    
                    ArrayList<Audio> audio = new ArrayList<>();
                    
                    audio.add(audioController.listarAudioporId(audio_id));
                    
                    if(audio.get(0) != null){

                        instrumento.setAudios(audio);

                    }else{

                        ErroTextNome.setText("Não existe Audio com esse ID");

                        NomeErro.show(InputAudio_ids, 0, InputAudio_ids.getHeight());

                        return;
                    }
                }
                
                if(especializacao.equalsIgnoreCase("harmonico")){
                    
                    InstrumentoHarmonico ih = (InstrumentoHarmonico) instrumento;
                    
                    if(opcao1 != null && Opcao1.getText() != null) ih.setPolifoniaMax((Long)opcao1);
                    if(opcao2 != null && Opcao2.getText() != null) ih.setPossuiPedalSustain((boolean) opcao2);
                    if(opcao3 != null && Opcao3.getText() != null) ih.setSuportaAcordes((boolean) opcao3);
                    
                    retornos = instrumentoController.atualizarInstrumento(ih, especializacao);
                            
                }else if(especializacao.equalsIgnoreCase("melodico")){
                    
                    InstrumentoMelodico im = (InstrumentoMelodico) instrumento;
                    
                    if(opcao1 != null && Opcao1.getText() != null) im.setTranspositor((boolean)opcao1);
                    if(opcao2 != null && Opcao2.getText() != null) im.setAfinacaoTransposicao(AfinacaoTransposicao.valueOf((String)opcao2));
                    if(opcao3 != null && Opcao3.getText() != null) im.setMicrotonalidadeSuportada((boolean) opcao3);
                    
                    retornos = instrumentoController.atualizarInstrumento(im, especializacao);
                    
                }else if(especializacao.equalsIgnoreCase("ritmico")){
                    
                    InstrumentoRitmico ir = (InstrumentoRitmico) instrumento;
                    
                    if(opcao1 != null && Opcao1.getText() != null) ir.setAlturaDefinida((boolean)opcao1);
                    if(opcao2 != null && Opcao2.getText() != null) ir.setCategoriaPercussao(CategoriaPercussao.valueOf((String)opcao2));
                    if(opcao3 != null && Opcao3.getText() != null) ir.setTocadoCom(TocadoCom.valueOf((String) opcao3));
                    
                    retornos = instrumentoController.atualizarInstrumento(ir, especializacao);
                    
                }
                
                finalizarTela(retornos);
                
                return;
            }
            
        }catch(Exception e){
            
            System.out.println("Erro: " + e.getMessage());
        }
        
        try{
            
            retornos = instrumentoController.adicionarInstrumento(familia_id, nome, classificacaoSonoridade, 
                historia, descricao, afinacao_id, audio_id, parteNome, descricao, material_id, 
                especializacao, opcao1, opcao2, opcao3);
            
        }catch(Exception e){
            
            System.out.println("ErroAqui: " + e.getMessage());
        }
        
        if(retornos.containsKey("Familia")){
            
            ErroTextNome.setText("Não existe Familia com esse ID");
            
            NomeErro.show(InputFamInsId, 0, InputFamInsId.getHeight());
            
            return;
            
        }else if (retornos.containsKey("Audio")){
            
            ErroTextNome.setText("Não existe Audio com esse ID");
            
            NomeErro.show(InputAudio_ids, 0, InputAudio_ids.getHeight());
            
            return;
            
        }else if (retornos.containsKey("Afinacao")){
            
            ErroTextNome.setText("Não existe Afinacao com esse ID");
            
            NomeErro.show(InputAfinacao_ids, 0, InputAfinacao_ids.getHeight());
            
            return;
           
        }else if (retornos.containsKey("Material")){
            
            ErroTextNome.setText("Não existe Material com esse ID");
            
            NomeErro.show(InputMaterial, 0, InputMaterial.getHeight());
            
            return;
        }
        
        finalizarTela(retornos);
        
    }//GEN-LAST:event_BotaoRegistrarActionPerformed

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
        this.dispose();
        
        if(!unicaTela) return;
        
        if(telaInputFamilia != null) telaInputFamilia.dispose();
        if(telaInputAudio != null) telaInputAudio.dispose();
        if(telaInputAfinacao != null) telaInputAfinacao.dispose();
        if(telaInputMaterial != null) telaInputMaterial.dispose();
        
        TelaInstrumentos telaInstrumento = null;
                 
        if(telaPrincipal != null) telaPrincipal.setVisible(true);
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void AdicionarFamInsIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarFamInsIdActionPerformed
        
        if(telaInputFamilia != null){
            
            if(!telaInputFamilia.isDisplayable()){
                
                telaInputFamilia = null;
            
            }else{
                
                return;
            }
        }
        
        telaInputFamilia = new InterfaceInputPadrao("Familia_Instrumento", false, false, null, telaPrincipal);
            
        telaInputFamilia.setVisible(true);
    }//GEN-LAST:event_AdicionarFamInsIdActionPerformed

    private void AdicionarAfinacao_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarAfinacao_idsActionPerformed
        
        if(telaInputAfinacao != null){
            
            if(!telaInputAfinacao.isDisplayable()){
                
                telaInputAfinacao = null;
            
            }else{
                
                return;
            }
        }
        
        telaInputAfinacao = new InterfaceInputPadrao("Afinação", false, false, null, telaPrincipal);
            
        telaInputAfinacao.setVisible(true);
    }//GEN-LAST:event_AdicionarAfinacao_idsActionPerformed

    private void AdicionarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarMaterialActionPerformed
        
        if(telaInputMaterial != null){
            
            if(!telaInputMaterial.isDisplayable()){
                
                telaInputMaterial = null;
            
            }else{
                
                return;
            }
        }
        
        telaInputMaterial = new InterfaceInputPadrao("Material", false, false, null, telaPrincipal);
            
        telaInputMaterial.setVisible(true);
    }//GEN-LAST:event_AdicionarMaterialActionPerformed

    private void AdicionarAudio_idsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarAudio_idsActionPerformed
        
        if(telaInputAudio != null){
            
            if(!telaInputAudio.isDisplayable()){
                
                telaInputAudio = null;
            
            }else{
                
                return;
            }
        }
        
        telaInputAudio = new InterfaceInputAudio(false, false, null, telaPrincipal);
            
        telaInputAudio.setVisible(true);
    }//GEN-LAST:event_AdicionarAudio_idsActionPerformed

    private void ErroTextNomepadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErroTextNomepadraoActionPerformed

    }//GEN-LAST:event_ErroTextNomepadraoActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdicionarAfinacao_ids;
    private javax.swing.JButton AdicionarAudio_ids;
    private javax.swing.JButton AdicionarFamInsId;
    private javax.swing.JButton AdicionarMaterial;
    private javax.swing.JLabel Afinacao_ids;
    private javax.swing.JLabel Apelidos;
    private javax.swing.JLabel Audio_ids;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoRegistrar;
    private javax.swing.JLabel Classificacao_sonoridade;
    private javax.swing.JLabel Descricao;
    private javax.swing.JMenuItem ErroTextNome;
    private javax.swing.JLabel Familia_Instrumento_id;
    private javax.swing.JLabel Historia;
    private javax.swing.JTextField InputAfinacao_ids;
    private javax.swing.JTextField InputApelidos;
    private javax.swing.JTextField InputAudio_ids;
    private javax.swing.JTextField InputClassificacao_sonoridade;
    private javax.swing.JTextArea InputDescricao;
    private javax.swing.JTextField InputFamInsId;
    private javax.swing.JTextArea InputHistoria;
    private javax.swing.JTextField InputMaterial;
    private javax.swing.JTextField InputNome;
    private javax.swing.JTextField InputNotaMinNotaMax;
    private javax.swing.JTextField InputOpcao1;
    private javax.swing.JTextField InputOpcao2;
    private javax.swing.JTextField InputOpcao3;
    private javax.swing.JTextField InputParte;
    private javax.swing.JButton ListarAfinacao_ids;
    private javax.swing.JButton ListarAudio_ids;
    private javax.swing.JButton ListarFamInsId;
    private javax.swing.JButton ListarMaterial;
    private javax.swing.JLabel Material;
    private javax.swing.JLabel Nome;
    private javax.swing.JPopupMenu NomeErro;
    private javax.swing.JLabel NotaMinNotaMax;
    private javax.swing.JLabel Opcao1;
    private javax.swing.JLabel Opcao2;
    private javax.swing.JLabel Opcao3;
    private javax.swing.JLabel ParteNome;
    private javax.swing.JScrollPane ScrollDescricao;
    private javax.swing.JScrollPane ScrollHistoria;
    private javax.swing.JPanel Tela;
    private javax.swing.JLabel Titulo;
    // End of variables declaration//GEN-END:variables
}
