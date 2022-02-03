/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author USE
 */
public class Recruiter {

    private int recruiterId;
    private String name;
    private String address;
    private String avatar;
    private String banner;
    private String phone;
    private String website;
    private String description;
    private String employeeQuantity;
    private String contacterName;
    private String contacterPhone;
    private Date createAt;
    private Date updateAt;

    public Recruiter() {
    }

    public Recruiter(int recruiterId, String name, String avatar) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.avatar= avatar;
    }

    public Recruiter(int recruiterId, String name, String address, String avatar, String banner, String phone, String website, String description, String employeeQuantity, String contacterName, String contacterPhone) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.banner = banner;
        this.phone = phone;
        this.website = website;
        this.description = description;
        this.employeeQuantity = employeeQuantity;
        this.contacterName = contacterName;
        this.contacterPhone = contacterPhone;
    }

    public Recruiter(int recruiterId, String name, String address, String avatar, String banner, String phone, String website, String description, String employeeQuantity, String contacterName, String contacterPhone, Date createAt, Date updateAt) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.banner = banner;
        this.phone = phone;
        this.website = website;
        this.description = description;
        this.employeeQuantity = employeeQuantity;
        this.contacterName = contacterName;
        this.contacterPhone = contacterPhone;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(String employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
    }

    public String getContacterName() {
        return contacterName;
    }

    public void setContacterName(String contacterName) {
        this.contacterName = contacterName;
    }

    public String getContacterPhone() {
        return contacterPhone;
    }

    public void setContacterPhone(String contacterPhone) {
        this.contacterPhone = contacterPhone;
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
