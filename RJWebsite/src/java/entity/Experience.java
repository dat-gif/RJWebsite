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
public class Experience {

    int id;
    String companyName;
    String workingRole;
    String startTime;
    String endTime;
    String description;
    String media;
    String link;
    int candidateId;

    public Experience(int id, String companyName, String workingRole, String startTime, String endTime, String description, String media, String link, int candidateId) {
        this.id = id;
        this.companyName = companyName;
        this.workingRole = workingRole;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkingRole() {
        return workingRole;
    }

    public void setWorkingRole(String workingRole) {
        this.workingRole = workingRole;
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
        return "Experience{" + "id=" + id + ", companyName=" + companyName + ", workingRole=" + workingRole + ", startTime=" + startTime + ", endTime=" + endTime + ", description=" + description + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }

}
