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
public class Skill {
    private int id;
    private String name;
    private String iconBase64;
    private String depscription;

    public Skill() {
    }

    public Skill(int id, String name, String iconBase64) {
        this.id = id;
        this.name = name;
        this.iconBase64 = iconBase64;
    }
    public Skill(int id, String name, String iconBase64, String depscription) {
        this.id = id;
        this.name = name;
        this.iconBase64 = iconBase64;
        this.depscription = depscription;
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

    public String getIconBase64() {
        return iconBase64;
    }

    public void setIconBase64(String iconBase64) {
        this.iconBase64 = iconBase64;
    }

    public String getDepscription() {
        return depscription;
    }

    public void setDepscription(String depscription) {
        this.depscription = depscription;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", name=" + name + ", iconBase64=" + iconBase64 + ", depscription=" + depscription + '}';
    }
    
}
