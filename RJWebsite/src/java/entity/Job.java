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
public class Job {

    private int jId;
    private int recId;
    private String title;
    private String description;
    private String salaryRange;
    private String quantity;
    private String role;
    private String experience;
    private String location;
    private String hire_date;
    private boolean status;

    public Job() {
    }

    public Job(int jId, int recId, String title, String description, String salaryRange, String quantity, String role, String experience, String location, String hire_date, boolean status) {
        this.jId = jId;
        this.recId = recId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.quantity = quantity;
        this.role = role;
        this.experience = experience;
        this.location = location;
        this.hire_date = hire_date;
        this.status = status;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" + "jId=" + jId + ", recId=" + recId + ", title=" + title + ", description=" + description + ", salaryRange=" + salaryRange + ", quantity=" + quantity + ", role=" + role + ", experience=" + experience + ", location=" + location + ", hire_date=" + hire_date + ", status=" + status + '}';
    }

}
