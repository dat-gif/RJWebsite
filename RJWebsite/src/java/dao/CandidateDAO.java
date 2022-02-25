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
import entity.Social;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * The data access object executes a data query from the Candidate table or main
 * relate with Candidate information.
 *
 * @author Admin
 */
public class CandidateDAO extends DBContext implements ICandidate {

    /**
     * Get basic info about candidate like email, address,etc...
     *
     * @param account_id int, id account access to website
     * @return
     */
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Education> getEducationByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Exception> getExceptionByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Social> getSocialByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
