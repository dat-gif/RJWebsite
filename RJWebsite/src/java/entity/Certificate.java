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
public class Certificate {

    int id;
    String name;
    String host;
    String certificateTime;
    String media;
    String link;
    int candidateId;

    public Certificate(int id, String name, String host, String certificateTime, String media, String link, int candidateId) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.certificateTime = certificateTime;
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

    public String getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(String certificateTime) {
        this.certificateTime = certificateTime;
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

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    @Override
    public String toString() {
        return "Certificate{" + "id=" + id + ", name=" + name + ", host=" + host + ", certificateTime=" + certificateTime + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }

}
