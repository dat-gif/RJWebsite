/*
 * R & IT J:
 * Recruitment And IT Job Search Website
 */
package dao;

import context.DBContext;
import dao.idao.IJob;
import dao.idao.ISkill;
import entity.Job;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Skill> getSkills(int index, int size) {
        List<Skill> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("with x as(SELECT ROW_NUMBER() over (order by skill_id asc) as r ,* from skill) "
                    + " select * from x where r between ?*?-(?-1) and ?*?");
            ps.setInt(1, index);
            ps.setInt(2, size);
            ps.setInt(3, size);
            ps.setInt(4, index);
            ps.setInt(5, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Skill(rs.getInt("skill_id"),
                        rs.getNString("name"),
                        rs.getNString("icon"),
                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("getJobs() :" + e);
        }

        return list;
    }

    @Override
    public List<Skill> getSkillDashboardSearching(String txtSearch, int index, int size) {
        List<Skill> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("with x as(SELECT ROW_NUMBER() over (order by skill_id asc) as r ,* from skill "
                    + "where name like ?)\n"
                    + " select * from x where r between ?*?-(?-1) and ?*?");
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, index);
            ps.setInt(3, size);
            ps.setInt(4, size);
            ps.setInt(5, index);
            ps.setInt(6, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Skill(rs.getInt("skill_id"),
                        rs.getNString("name"),
                        rs.getNString("icon"),
                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("getJobs() :" + e);
        }
        return list;
    }

    @Override
    public int countTotalSkill() {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) from skill");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public int countTotalSkillSearch(String txtSearch) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) from skill where name like ?");
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * Update skill to record
     *
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

    @Override
    public boolean checkExistedSkillName(String n) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM skill");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (n.equals(rs.getString("name").toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("InsertSkill() :" + e);
        }
        return false;
    }

    /**
     * Add skill to Database
     *
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

    public static void main(String[] args) {
        SkillDAO sDao = new SkillDAO();
        int count = sDao.countTotalSkillSearch("Angular");
        List<Skill> list = sDao.getSkillDashboardSearching("angular", 1, 6);
        for (Skill s : list) {
            System.out.println(count);
        }

    }

}
