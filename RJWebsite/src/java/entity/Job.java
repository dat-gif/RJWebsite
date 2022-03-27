/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Job {

    private int jId;
    private Recruiter recruiter;
    private String title;
    private String description;
    private String salaryRange;
    private String quantity;
    private String role;
    private String experience;
    private String location;
    private String hireDate;
    private ArrayList<Skill> skillList;
    private List<String> skillListName; // 
    private boolean status;
    private String applyStatus;

    public Job() {
    }

    public Job(int jId, String title, String description, String salaryRange, String quantity, String role, String experience, String location, String hireDate) {
        this.jId = jId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.quantity = quantity;
        this.role = role;
        this.experience = experience;
        this.location = location;
        this.hireDate = hireDate;
    }

    public Job(int jId, String title, String description, String salaryRange, String quantity, String role, String experience, String location, String hireDate, boolean status) {
        this.jId = jId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.quantity = quantity;
        this.role = role;
        this.experience = experience;
        this.location = location;
        this.hireDate = hireDate;
        this.status = status;
    }

    public Job(int jId, String title, String description, String salaryRange, String quantity, String role, String experience, String location, String hireDate, boolean status, String applyStatus) {
        this.jId = jId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.quantity = quantity;
        this.role = role;
        this.experience = experience;
        this.location = location;
        this.hireDate = hireDate;
        this.status = status;
        this.applyStatus = applyStatus;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
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
        return hireDate;
    }

    public void setHire_date(String hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(ArrayList<Skill> skillList) {
        this.skillList = skillList;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public List<String> getSkillListName() {
        return skillListName;
    }

    public void setSkillListName(List<String> skillListName) {
        this.skillListName = skillListName;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return "Job{" + "jId=" + jId + ", recruiter=" + recruiter.getName() + ", title=" + title + ", salaryRange=" + salaryRange + ", quantity=" + quantity + ", role=" + role + ", experience=" + experience + ", location=" + location + ", hireDate=" + hireDate + ", status=" + status + '}';
    }

}
