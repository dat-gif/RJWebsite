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

    /**
     * get Skill from record filter by skill_id
     *
     * @param Id
     * @return Skill
     */
    @Override
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

    /**
     * Update skill to record
     * @param s
     */
    @Override
    public void updateSkill(Skill s) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE skill SET name = ?, description = ? where skill_id = ?");
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepscription());
            ps.setInt(3, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateSkill() :" + e);
        }
    }
/**Add skill to Database
     * @param s
*/
    @Override
    public void insertSkill(Skill s) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO skill (name, description, icon) VALUES (?, ?, ?)");
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepscription());
            ps.setString(3, s.getIconBase64());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("InsertSkill() :" + e);
        }
    }
}
