/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import dao.idao.IJob;
import dao.idao.ISkill;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class SkillDAO extends DBContext implements ISkill {

    public Skill getSkillById(int Id) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM skill WHERE skill_id = ?");
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getInt("skill_id"));
                s.setName(rs.getString("name"));
                s.setIconBase64("icon");
                s.setDepscription(rs.getString("description"));
                return s;
            }
        } catch (Exception e) {
            System.out.println("getSkillById() :" + e);
        }
        return null;
    }

    @Override
    public void updateSkill(Skill s) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE skill SET name = ?, description = ?, icon = ? where skill_id = ?");
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepscription());
            ps.setString(3, s.getIconBase64());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateSkill() :" + e);
        }
    }

    public static void main(String[] args) {
        ISkill sdao = new SkillDAO();

        System.out.println(sdao.getSkillById(3).toString());

    }
}
