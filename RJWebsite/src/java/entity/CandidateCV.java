/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class CandidateCV {
    int id;
    String mediaCv;
    String originCv;

    public CandidateCV(int id, String mediaCv, String originCv) {
        this.id = id;
        this.mediaCv = mediaCv;
        this.originCv = originCv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaCv() {
        return mediaCv;
    }

    public void setMediaCv(String mediaCv) {
        this.mediaCv = mediaCv;
    }

    public String getOriginCv() {
        return originCv;
    }

    public void setOriginCv(String originCv) {
        this.originCv = originCv;
    }
    
    
}
