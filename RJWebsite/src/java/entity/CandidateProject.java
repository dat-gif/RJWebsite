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
public class CandidateProject {

    int id;
    String name;
    String role;
    String startTime;
    String endTime;
    String description;
    String media;
    String link;
    int candidateId;

    public CandidateProject(int id, String name, String role, String startTime, String endTime, String description, String media, String link, int candidateId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    @Override
    public String toString() {
        return "CandidateProject{" + "id=" + id + ", name=" + name + ", role=" + role + ", startTime=" + startTime + ", endTime=" + endTime + ", description=" + description + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }

}
