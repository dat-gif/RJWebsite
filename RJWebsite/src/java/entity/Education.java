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
public class Education {

    int id;
    String school;
    String degree;
    String field;
    String startTime;
    String endTime;
    String description;
    String media;
    String link;
    String candidateId;

    public Education() {
    }

    public Education(int id, String school, String degree, String field, String startTime, String endTime, String description, String media, String link, String candidateId) {
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.field = field;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    @Override
    public String toString() {
        return "Education{" + "id=" + id + ", school=" + school + ", degree=" + degree + ", field=" + field + ", startTime=" + startTime + ", endTime=" + endTime + ", description=" + description + ", media=" + media + ", link=" + link + ", candidateId=" + candidateId + '}';
    }

}
