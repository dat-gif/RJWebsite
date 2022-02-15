/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Candidate {

    private int candIdateId;
    private int accountId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String avatar;
    private boolean gender;
    private String banner;
    private String phone;
    private boolean findingJob;
    private int cvManageId;
    private int experienceManageId;
    private int educationMangeId;
    private int socialManageId;
    private int projectManageId;
    private int certificateManageId;
    private int prizeManageId;
    private Date createAt;
    private Date updateAt;

    public Candidate() {
    }

    public Candidate(int candIdateId, int accountId, String firstName, String lastName, String birthDate, String address, String avatar, boolean gender, String banner, String phone, boolean findingJob, int cvManageId, int experienceManageId, int educationMangeId, int socialManageId, int projectManageId, int certificateManageId, int prizeManageId) {
        this.candIdateId = candIdateId;
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.avatar = avatar;
        this.gender = gender;
        this.banner = banner;
        this.phone = phone;
        this.findingJob = findingJob;
        this.cvManageId = cvManageId;
        this.experienceManageId = experienceManageId;
        this.educationMangeId = educationMangeId;
        this.socialManageId = socialManageId;
        this.projectManageId = projectManageId;
        this.certificateManageId = certificateManageId;
        this.prizeManageId = prizeManageId;
    }

    public int getCandIdateId() {
        return candIdateId;
    }

    public void setCandIdateId(int candIdateId) {
        this.candIdateId = candIdateId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFindingJob() {
        return findingJob;
    }

    public void setFindingJob(boolean findingJob) {
        this.findingJob = findingJob;
    }

    public int getCvManageId() {
        return cvManageId;
    }

    public void setCvManageId(int cvManageId) {
        this.cvManageId = cvManageId;
    }

    public int getExperienceManageId() {
        return experienceManageId;
    }

    public void setExperienceManageId(int experienceManageId) {
        this.experienceManageId = experienceManageId;
    }

    public int getEducationMangeId() {
        return educationMangeId;
    }

    public void setEducationMangeId(int educationMangeId) {
        this.educationMangeId = educationMangeId;
    }

    public int getSocialManageId() {
        return socialManageId;
    }

    public void setSocialManageId(int socialManageId) {
        this.socialManageId = socialManageId;
    }

    public int getProjectManageId() {
        return projectManageId;
    }

    public void setProjectManageId(int projectManageId) {
        this.projectManageId = projectManageId;
    }

    public int getCertificateManageId() {
        return certificateManageId;
    }

    public void setCertificateManageId(int certificateManageId) {
        this.certificateManageId = certificateManageId;
    }

    public int getPrizeManageId() {
        return prizeManageId;
    }

    public void setPrizeManageId(int prizeManageId) {
        this.prizeManageId = prizeManageId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


}
