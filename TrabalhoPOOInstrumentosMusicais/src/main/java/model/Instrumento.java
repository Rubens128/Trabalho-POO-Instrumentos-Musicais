/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author predrim
 */
public class Instrumento {
    private long id;
    private long familiaId;
    private String nome;
    private String descricao;
    private String historia;
    private String classificacaoSonoridade;
    private FamiliaInstrumento familiaInstrumento;
    private AlcanceInstrumento alcanceInstrumento;
    private ArrayList<String> apelidos;
    private ArrayList<Audio> audios;
    private ArrayList<ParteEMaterial> partesEMateriais;
    private ArrayList<Afinacao> afinacoes;
    
    protected Instrumento(Builder<?> builder) {
        this.id = builder.id;
        this.familiaId = builder.familiaId;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.historia = builder.historia;
        this.classificacaoSonoridade = builder.classificacaoSonoridade;
        this.familiaInstrumento = builder.familiaInstrumento;
        this.alcanceInstrumento = builder.alcanceInstrumento;
        this.apelidos = builder.apelidos;
        this.audios = builder.audios;
        this.partesEMateriais = builder.partesEMateriais;
        this.afinacoes = builder.afinacoes;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getFamiliaId() {
        return familiaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHistoria() {
        return historia;
    }

    public String getClassificacaoSonoridade() {
        return classificacaoSonoridade;
    }

    public FamiliaInstrumento getFamiliaInstrumento() {
        return familiaInstrumento;
    }

    public AlcanceInstrumento getAlcanceInstrumento() {
        return alcanceInstrumento;
    }

    public ArrayList<String> getApelidos() {
        return apelidos;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public ArrayList<ParteEMaterial> getPartesEMateriais() {
        return partesEMateriais;
    }

    public ArrayList<Afinacao> getAfinacoes() {
        return afinacoes;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setFamiliaId(long familiaId) {
        this.familiaId = familiaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public void setClassificacaoSonoridade(String classificacaoSonoridade) {
        this.classificacaoSonoridade = classificacaoSonoridade;
    }

    public void setFamiliaInstrumento(FamiliaInstrumento familiaInstrumento) {
        this.familiaInstrumento = familiaInstrumento;
    }

    public void setAlcanceInstrumento(AlcanceInstrumento alcanceInstrumento) {
        this.alcanceInstrumento = alcanceInstrumento;
    }

    public void setApelidos(ArrayList<String> apelidos) {
        this.apelidos = apelidos;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    public void setPartesEMateriais(ArrayList<ParteEMaterial> parteEMaterial) {
        this.partesEMateriais = parteEMaterial;
    }

    public void setAfinacoes(ArrayList<Afinacao> afinacao) {
        this.afinacoes = afinacao;
    }
    
    // Métodos de Ajuda para Listas
    
    // Apelidos
    public void adicionarApelido(String apelido) {
        if (this.apelidos == null) {
            this.apelidos = new ArrayList<>();
        }
        this.apelidos.add(apelido);
    }

    // Audios
    public void adicionarAudio(Audio audio) {
        if (this.audios == null) {
            this.audios = new ArrayList<>();
        }
        this.audios.add(audio);
    }
    
    // Partes e Materiais
    public void adicionarParteEMaterial(ParteEMaterial pem) {
        if (this.partesEMateriais == null) {
            this.partesEMateriais = new ArrayList<>();
        }
        this.partesEMateriais.add(pem);
    }
    
    // Afinacoes
    public void adicionarAfinacao(Afinacao afinacao) {
        if (this.afinacoes == null) {
            this.afinacoes = new ArrayList<>();
        }
        this.afinacoes.add(afinacao);
    }


    // Padrão Builder para lidar com parâmetros opcionais.
    public static class Builder<T extends Builder<T>> {

        // Parâmetros Obrigatórios
        private long id;
        private long familiaId;
        private String nome;
        private String classificacaoSonoridade;

        // Parâmetros Opcionais
        private String descricao = null;
        private String historia = null;
        private FamiliaInstrumento familiaInstrumento = null;
        private AlcanceInstrumento alcanceInstrumento = null;
        private ArrayList<String> apelidos = new ArrayList<>(); // Inicializa listas
        private ArrayList<Audio> audios = new ArrayList<>();
        private ArrayList<ParteEMaterial> partesEMateriais = new ArrayList<>();
        private ArrayList<Afinacao> afinacoes = new ArrayList<>();

        // Construtor do Builder (só com obrigatórios)
        public Builder(long id, long familiaId, String nome, String classificacaoSonoridade) {
            this.id = id;
            this.familiaId = familiaId;
            this.nome = nome;
            this.classificacaoSonoridade = classificacaoSonoridade;
        }

        // Métodos para parâmetros opcionais
        public T descricao(String val) {
            this.descricao = val;
            return (T) this;
        }

        public T historia(String val) {
            this.historia = val;
            return (T) this;
        }

        public T familiaInstrumento(FamiliaInstrumento val) {
            this.familiaInstrumento = val;
            return (T) this;
        }

        public T alcanceInstrumento(AlcanceInstrumento val) {
            this.alcanceInstrumento = val;
            return (T) this;
        }
        
        // Métodos para listas opcionais
        public T apelidos(ArrayList<String> val) {
            this.apelidos = new ArrayList<>(val); // Copia a lista
            return (T) this;
        }
        
        public T audios(ArrayList<Audio> val) {
            this.audios = new ArrayList<>(val);
            return (T) this;
        }
        
        public T partesEMateriais(ArrayList<ParteEMaterial> val) {
            this.partesEMateriais = new ArrayList<>(val);
            return (T) this;
        }
        
        public T afinacoes(ArrayList<Afinacao> val) {
            this.afinacoes = new ArrayList<>(val);
            return (T) this;
        }

        // Método para criar o objeto Instrumento
        public Instrumento build() {
            return new Instrumento(this);
        }
    }
}
