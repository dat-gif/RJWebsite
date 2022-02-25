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
public class Social {

    int id;
    String name;
    String role;
    String description;
    String media;
    String link;
    String candidateId;

    public Social(int id, String name, String role, String description, String media, String link, String candidateId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.description = description;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Social{" + "id=" + id + ", name=" + name + ", role=" + role + ", description=" + description + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }
    
    
}
