/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Blob;

/**
 *
 * @author Admin
 */
public class CandidateCV {

    int id;
    String mediaCv;
    Blob originCv;

    public CandidateCV(int id, String mediaCv, Blob originCv) {
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

    public Blob getOriginCv() {
        return originCv;
    }

    public void setOriginCv(Blob originCv) {
        this.originCv = originCv;
    }

}
