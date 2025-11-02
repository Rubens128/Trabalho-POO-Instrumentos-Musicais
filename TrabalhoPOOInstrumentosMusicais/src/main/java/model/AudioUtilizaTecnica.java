/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author predrim
 */
public class AudioUtilizaTecnica {
    
    private long audioId;
    private long tecnicaId;

    public AudioUtilizaTecnica(long audioId, long tecnicaId) {
        this.audioId = audioId;
        this.tecnicaId = tecnicaId;
    }

    public long getAudioId() {
        return audioId;
    }

    public void setAudioId(long audioId) {
        this.audioId = audioId;
    }

    public long getTecnicaId() {
        return tecnicaId;
    }

    public void setTecnicaId(long tecnicaId) {
        this.tecnicaId = tecnicaId;
    }
}
