/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import dao.idao.ICandidate;
import entity.Candidate;
import entity.CandidateCV;
import entity.CandidatePrize;
import entity.Certificate;
import entity.City;
import entity.Education;
import entity.Experience;
import entity.Skill;
import entity.Social;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The data access object executes a data query from the Candidate table or main
 * relate with Candidate information.
 *
 * @author Admin
 */
public class CandidateDAO extends DBContext implements ICandidate {

    /**get all candidates from Record;
     * @return List;
     */
    @Override
    public List<Candidate> getCandidates() {
        List<Candidate> canList = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidate");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate can = new Candidate();
                can.setCandIdateId(rs.getInt("candidate_id"));
                can.setFirstName(rs.getString("first_name"));
                can.setLastName(rs.getString("last_name"));
                can.setBirthDate(rs.getString("birth_date"));
                can.setAddress(rs.getString("address"));
                can.setAvatar(rs.getString("avatar"));
                can.setGender(rs.getBoolean("sex"));
                can.setBanner(rs.getString("phone"));
                can.setFindingJob(rs.getBoolean("finding_job"));
                canList.add(can);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return canList;
    }

    @Override
    public Candidate getCandidateProfileById(int account_id) {
        String query = "SELECT [candidate_id]\n"
                + "      ,account.account_id\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[birth_date]\n"
                + "      ,[address]\n"
                + "      ,[avatar]\n"
                + "      ,[sex]\n"
                + "      ,[banner]\n"
                + "      ,account.phone\n"
                + "      ,[finding_job]\n"
                + "      ,[cv_manage_id]\n"
                + "      ,[experience_manage_id]\n"
                + "      ,[education_mange_id]\n"
                + "      ,[social_manage_id]\n"
                + "      ,[project_manage_id]\n"
                + "      ,[certificate_manage_id]\n"
                + "      ,[prize_manage_id]\n"
                + "      ,[city]\n"
                + "	  ,email\n"
                + "  FROM [SWP391].[dbo].[candidate]\n"
                + "  inner join account on account.account_id= candidate.account_id\n"
                + "  Where account.account_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandIdateId(rs.getInt("candidate_id"));
                candidate.setAccountId(rs.getInt("account_id"));
                candidate.setFirstName(rs.getString("first_name"));
                candidate.setLastName(rs.getString("last_name"));
                candidate.setAvatar(rs.getString("avatar"));
                candidate.setBirthDate(rs.getString("birth_date"));
                candidate.setGender(rs.getBoolean("sex"));
                candidate.setBanner(rs.getString("banner"));
                candidate.setPhone(rs.getString("phone"));
                candidate.setAddress(rs.getString("address"));
                candidate.setFindingJob(rs.getBoolean("finding_job"));
                candidate.setEmail(rs.getString("email"));
                return candidate;
            }

        } catch (Exception e) {
            System.out.println("getCandidateProfileById :" + e);
            throw new Error(e);
        }
        return null;
    }

    @Override
    public CandidateCV getCandidateCVByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private Exception Error(Exception e) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CandidatePrize> getCandidatePrizeByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Certificate> getCertificateByCandidateId(int candidateId) {
        List<Certificate> certificates = new ArrayList<>();
        String query = "SELECT TOP (5) [id]\n"
                + "      ,[name]\n"
                + "      ,[host]\n"
                + "      ,[certificate_time]\n"
                + "      ,[media]\n"
                + "      ,[link]\n"
                + "      ,[candidate_id]\n"
                + "  FROM [SWP391].[dbo].[certificate]\n"
                + "  where candidate_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Certificate c = new Certificate(rs.getInt("id"), rs.getString("name"),
                        rs.getString("host"),
                        rs.getString("certificate_time"),
                        rs.getString("media"),
                        rs.getString("link"),
                        rs.getInt("candidate_id"));
                certificates.add(c);
            }
            return certificates;
        } catch (Exception e) {
            System.err.println("getCertificateByCandidateId :" + e);
            throw new Error(e);
        }

    }

    @Override
    public List<Education> getEducationByCandidateId(int candidateId) {
        List<Education> listEducations = new ArrayList<>();
        String query = "SELECT TOP (5) [id]\n"
                + "      ,[school]\n"
                + "      ,[degree]\n"
                + "      ,[field]\n"
                + "      ,[start_time]\n"
                + "      ,[end_time]\n"
                + "      ,[description]\n"
                + "      ,[media]\n"
                + "      ,[link]\n"
                + "      ,[candidate_id]\n"
                + "  FROM [SWP391].[dbo].[education]\n"
                + "  where candidate_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Education e = new Education(rs.getInt("id"),
                        rs.getString("school"),
                        rs.getString("degree"),
                        rs.getString("field"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("description"),
                        rs.getString("media"),
                        rs.getString("link"),
                        rs.getString("candidate_id")
                );
                listEducations.add(e);
            }
            return listEducations;
        } catch (Exception e) {
            System.err.println("");
            throw new Error(e);
        } finally {

        }
    }

    @Override
    public List<Experience> getExperienceByCandidateId(int candidateId) {
        String query = "SELECT TOP (5) [id]\n"
                + "      ,[company_name]\n"
                + "      ,[working_role]\n"
                + "      ,[start_time]\n"
                + "      ,[end_time]\n"
                + "      ,[description]\n"
                + "      ,[media]\n"
                + "      ,[link]\n"
                + "      ,[candidate_id]\n"
                + "  FROM [SWP391].[dbo].[experience]\n"
                + "where candidate_id=?";
        List<Experience> list = new ArrayList();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Experience e = new Experience(rs.getInt("id"),
                        rs.getString("company_name"),
                        rs.getString("working_role"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("description"),
                        rs.getString("media"),
                        rs.getString("link"),
                        rs.getInt("candidate_id"));
                list.add(e);
            }
            return list;
        } catch (Exception e) {
            System.err.println("getExperienceByCandidateId: " + e);
            throw new Error(e);
        }

    }


    private Exception Error(Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Social> getSocialByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Skill> getSkillByCandidateId(int candidateId) {
        String query = "SELECT TOP (5) skill.[skill_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[question]\n"
                + "      ,[icon]\n"
                + "      ,[status]\n"
                + "  FROM [SWP391].[dbo].[skill]\n"
                + "  inner join candidate_skill on candidate_skill.skill_id= skill.skill_id\n"
                + "  where candidate_id=?";
        List<Skill> skills = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skill_id"),
                        rs.getString("name"),
                        rs.getString("icon"),
                        rs.getString("description"));
                skills.add(skill);
            }
            return skills;
        } catch (Exception e) {
        }
        return null;
    }


}
