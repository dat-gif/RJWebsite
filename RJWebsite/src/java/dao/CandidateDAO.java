/*
 * R & IT J:
 * Recruitment And IT Job Search Website
 */
package dao;

import context.DBContext;
import dao.idao.ICandidate;
import entity.Candidate;

import entity.CandidateCV;
import entity.CandidatePrize;
import entity.CandidateProject;
import entity.Certificate;
import entity.Education;
import entity.Experience;
import entity.Skill;
import entity.Social;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FileUtils;

/**
 * The data access object executes a data query from the Candidate table or main
 * relate with Candidate information.
 *
 * @author Admin
 */
public class CandidateDAO extends DBContext implements ICandidate {

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
    public int getNumberPageSearchCandidate(String txtSearch, String city) {
        int totalPage = 0;
        String cityQuery = " and candidate.city = ? ";
        String query = "select count(*) from candidate join candidate_skill on candidate.candidate_id = candidate_skill.candidate_id \n"
                + "join skill on candidate_skill.skill_id = skill.skill_id\n"
                + "where skill.[name] like ?";
        try {
            if (!city.equalsIgnoreCase("All")) {
                query = query + cityQuery;
            }
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            if (!city.equalsIgnoreCase("All")) {
                ps.setString(2, city);
            }
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
            System.out.println("getNumberPageSearchCandidate :" + e);
        }
        return 0;
    }

    /**
     * Phan trang theo dieu kien search
     *
     * @param index int
     * @param txtSearch String
     * @param city
     * @return
     */
    @Override
    public ArrayList<Candidate> getCandidateSearchPaging(int index, String txtSearch, String city) {
        ArrayList<Candidate> candidateList = new ArrayList<>();

        String cityQuery = "and candidate.city = ? ";
        String orderBy = " order by candidate.candidate_id offset ? rows fetch first 2 rows only";

        //query select cac record tuong ung voi indexPage (2 record/trang)
        String query = "select * from candidate join candidate_skill on candidate.candidate_id = candidate_skill.candidate_id \n"
                + "join skill on candidate_skill.skill_id = skill.skill_id\n"
                + "where skill.[name] like ? ";
        try {
            if (!city.equalsIgnoreCase("All")) {
                query = query + cityQuery + orderBy;
            } else {
                query = query + orderBy;
            }
            System.out.println(query);
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            if (!city.equalsIgnoreCase("All")) {
                ps.setString(2, city);
                ps.setInt(3, (index - 1) * 2);
            } else {
                ps.setInt(2, (index - 1) * 2);
            }
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
            System.out.println("getCandidateSearchPaging :" + e);
        }
        return candidateList;
    }

    /**
     * get all candidates from Record;
     *
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
        String query = "SELECT [id]\n"
                + "      ,[media_cv]\n"
                + "      ,[origin_cv]\n"
                + "  FROM [dbo].[cv]\n"
                + "  inner join candidate on candidate.cv_manage_id = cv.id\n"
                + "  where candidate.candidate_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new CandidateCV(candidateId, rs.getString("media_cv"), rs.getString("origin_cv"));
            }
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * Add candidate cv if not exist, update if cv update.
     *
     *
     * @param candidateId <code>int</code> candidate id
     * @param fileDataBase64 <code>String</code> file after transform to base64
     * type
     * @param link <code>String</code> link cv of candidate
     */
    @Override
    public void editCandidateCVByCandidateId(int candidateId, String fileDataBase64, String link) {
        String query = "IF EXISTS (\n"
                + " SELECT 1 \n"
                + " FROM cv  \n"
                + " inner join candidate on candidate.cv_manage_id= cv.id\n"
                + " WHERE candidate.candidate_id=1)\n"
                + " BEGIN\n"
                + "     UPDATE cv \n"
                + "     SET  media_cv = ISNULL(?,media_cv),\n"
                + "	     origin_cv=ISNULL(?,origin_cv)\n"
                + "     FROM cv\n"
                + "     inner join candidate on candidate.cv_manage_id= cv.id\n"
                + "     WHERE candidate.candidate_id=? ;\n"
                + " END\n"
                + " ELSE\n"
                + " BEGIN\n"
                + " INSERT INTO cv ([media_cv],[origin_cv])\n"
                + " values (?, ?) \n"
                + " DECLARE @LASTID int\n"
                + " SET @LASTID = IDENT_CURRENT('dbo.cv')\n"
                + " Update candidate\n"
                + " set cv_manage_id=isnull(@LASTID, cv_manage_id)\n"
                + " where candidate.candidate_id=?\n"
                + " end";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("link: ");
        System.out.println("link: "+link);
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(3, candidateId);
            ps.setInt(6, candidateId);
            ps.setString(1, link);
            ps.setString(4, link);
            ps.setString(2, fileDataBase64);
            ps.setString(5, fileDataBase64);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Error();
        }
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

    /**
     * Get candidate working project by candidate id
     *
     * @param candidateId <code>int</code> id of candidate
     * @return
     */
    @Override
    public List<CandidateProject> getCandidateProjectByCandidateId(int candidateId) {
        String query = "SELECT TOP (5) [id]\n"
                + "      ,[name]\n"
                + "      ,[role]\n"
                + "      ,[start_time]\n"
                + "      ,[end_time]\n"
                + "      ,[description]\n"
                + "      ,[media]\n"
                + "      ,[link]\n"
                + "      ,[candidate_id]\n"
                + "  FROM [SWP391].[dbo].[project]";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CandidateProject> candidateProjects = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                CandidateProject candidateProject = new CandidateProject(rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getNString("role"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("description"),
                        rs.getString("media"),
                        rs.getString("link"),
                        candidateId);
                candidateProjects.add(candidateProject);
            }
        } catch (Exception e) {
            System.err.println("getCandidateProjectByCandidateId: " + e);
            throw new Error(e);
        } finally {
            try {
                closeConnection(rs, ps, conn);
            } catch (SQLException ex) {
                Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new Error(ex);
            }
        }
        return candidateProjects;
    }

}
