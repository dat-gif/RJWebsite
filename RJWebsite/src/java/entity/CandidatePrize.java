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
public class CandidatePrize {

    int id;
    String name;
    String host;
    String prizeTime;
    String media;
    String link;
    String candidateId;

    public CandidatePrize(int id, String name, String host, String prizeTime, String media, String link, String candidateId) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.prizeTime = prizeTime;
        this.media = media;
        this.link = link;
        this.candidateId = candidateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(String prizeTime) {
        this.prizeTime = prizeTime;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    @Override
    public String toString() {
        return "CandidatePrize{" + "id=" + id + ", name=" + name + ", host=" + host + ", prizeTime=" + prizeTime + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }
    
    
}
