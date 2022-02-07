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
public class Account {

    private int accId;
    private int roleId;
    private String roleName;
    private String email; // use as user name
    private String phone;
    private String password;

    public Account() {
    }

    public Account(String roleName, String phone, String password) {
        this.roleName = roleName;
        this.phone = phone;
        this.password = password;
    }



    public Account(String email, String password, int roleId) {
        this.roleId = roleId;
        this.email = email;
        this.password = password;
    }

    public Account(int accId, int roleId, String email, String phone, String password) {
        this.accId = accId;
        this.roleId = roleId;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "accId=" + accId + ", roleId=" + roleId + ", email=" + email + ", phone=" + phone + ", password=" + password + '}';
    }

}
