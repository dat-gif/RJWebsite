/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import dao.idao.ICandidate;
import entity.Candidate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author USE
 */
public class CandidateDAO extends DBContext implements ICandidate {

/**
     * get top 4 candidate
     * @return
     */
    @Override
    public ArrayList<Candidate> getTop4Candidate() {
        ArrayList<Candidate> candidateList = new ArrayList<>();
        String query = "select top 4 * from candidate";
        try {
            //mo ket noi, query va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getInt("candidate_Id"),
                        rs.getInt("account_Id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birth_date"),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getBoolean("sex"),
                        rs.getString("banner"),
                        rs.getString("phone"),
                        rs.getBoolean("finding_job"),
                        rs.getInt("cv_manage_id"),
                        rs.getInt("experience_manage_id"),
                        rs.getInt("education_mange_id"),
                        rs.getInt("social_manage_id"),
                        rs.getInt("project_manage_id"),
                        rs.getInt("certificate_manage_id"),
                        rs.getInt("prize_manage_id"));
                candidateList.add(candidate);
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return candidateList;
    }

/**
     * get candidate theo recruiterId
     *
     * @param recruiterId int
     * @return
     */
    @Override
    public ArrayList<Candidate> getCandidateListByRecruiterId(int recruiterId) {
        ArrayList<Candidate> candidateList = new ArrayList<>();
        String query = "select candidate.first_name, candidate.last_name, candidate.[address], candidate_job_apply.[status] "
                + "from candidate join candidate_job_apply on candidate.candidate_id = candidate_job_apply.candidate_id\n"
                + "join job on candidate_job_apply.job_id = job.job_id\n"
                + "join recruiter on job.recruiter_id = recruiter.recruiter_id\n"
                + "where recruiter.recruiter_id = ?";
        try {
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("status"));
                candidateList.add(candidate);
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return candidateList;
    }

/**
     * get tong so page theo dieu kien search
     *
     * @param txtSearch String
     * @return
     */
    @Override
    public int getNumberPageSearchCandidate(String txtSearch) {
        int totalPage = 0;
        String query = "select count(*) from candidate join candidate_skill on candidate.candidate_id = candidate_skill.candidate_id \n"
                + "join skill on candidate_skill.skill_id = skill.skill_id\n"
                + "where skill.[name] like ?";
        try {
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //page nay hien thi 2 record trong 1 trang nen neu record le thi + them 1 trang nua
                totalPage = rs.getInt(1);
                int countPage = 0;
                countPage = totalPage / 2;
                if (totalPage % 2 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return 0;
    }

/**
     * Phan trang theo dieu kien search
     *
     * @param index int
     * @param txtSearch String
     * @return
     */
    @Override
    public ArrayList<Candidate> getPaging(int index, String txtSearch) {
        ArrayList<Candidate> candidateList = new ArrayList<>();
        //query select cac record tuong ung voi indexPage (2 record/trang)
        String query = "select * from candidate join candidate_skill on candidate.candidate_id = candidate_skill.candidate_id \n"
                + "join skill on candidate_skill.skill_id = skill.skill_id\n"
                + "where skill.[name] like ? order by candidate.candidate_id offset ? rows fetch first 2 rows only";
        try {
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, (index - 1) * 2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getInt("candidate_Id"),
                        rs.getInt("account_Id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birth_date"),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getBoolean("sex"),
                        rs.getString("banner"),
                        rs.getString("phone"),
                        rs.getBoolean("finding_job"),
                        rs.getInt("cv_manage_id"),
                        rs.getInt("experience_manage_id"),
                        rs.getInt("education_mange_id"),
                        rs.getInt("social_manage_id"),
                        rs.getInt("project_manage_id"),
                        rs.getInt("certificate_manage_id"),
                        rs.getInt("prize_manage_id"));
                candidateList.add(candidate);
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return candidateList;
    }

    public static void main(String[] args) {
        CandidateDAO dao = new CandidateDAO();
        ArrayList<Candidate> candidateList = dao.getCandidateListByRecruiterId(1);
        System.out.println(candidateList);

    }
}
