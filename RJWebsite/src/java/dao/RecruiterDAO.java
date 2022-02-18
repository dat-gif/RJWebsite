/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The data access object performs the data query and updates from the Recruiter
 * table or main business related to the Recruiter table and the Recruiter
 * entity.
 *
 * @author
 */
public class RecruiterDAO extends DBContext implements dao.idao.IRecruiter {

    /**
     *
     * Change from string type to array type form searching
     * </br>
     *
     * @param string
     * @return List<String>
     */
    public List<String> changeStringToListAndSort(String string) {
        string = string.trim();
        String[] arrayString = string.split(" ");
        List<String> unSortList = new ArrayList<String>();
        List<String> sortList = new ArrayList<>();
        Collections.addAll(unSortList, arrayString);
        unSortList.add(string);
        while (unSortList.size() > 0) {
            String longest = unSortList.stream().
                    max(Comparator.comparingInt(String::length)).get();
            sortList.add(longest);
            unSortList.remove(unSortList.indexOf(longest));
        }
        return sortList;
    }

    /**
     * Get recruiter info by recruiter id
     * <br>
     *
     * @param recruiterId int
     * @return Recruiter recruiter,<code>entity.Recruiter</code>
     */
    @Override
    public Recruiter getRecruiterById(int recruiterId) {
        String query = " SELECT recruiter.recruiter_id\n"
                + "      ,recruiter.name\n"
                + "      ,recruiter.address\n"
                + "      ,recruiter.avatar\n"
                + "      ,recruiter.banner\n"
                + "      ,recruiter.phone\n"
                + "      ,recruiter.website\n"
                + "      ,recruiter.description\n"
                + "      ,recruiter.employee_quantity\n"
                + "      ,recruiter.contacter_name\n"
                + "      ,recruiter.contacter_phone\n"
                + "      ,recruiter.createAt\n"
                + "      ,recruiter.updateAt\n"
                + " FROM [SWP391].[dbo].[recruiter]\n"
                + " where recruiter.recruiter_id= ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("name").trim(),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getString("banner"),
                        rs.getString("phone"),
                        rs.getString("website"),
                        rs.getString("description"),
                        rs.getString("employee_quantity"),
                        rs.getString("contacter_name"),
                        rs.getString("contacter_phone"),
                        rs.getDate("createAt"),
                        rs.getDate("updateAt"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /**
     * Create temporary table in session, contain recruiter data
     */
    @Override
    public void createRecruiterTempoTableSearchData() {
        String createTempTable = " IF OBJECT_ID('tempdb..##TempRecruiterTable') IS NULL\n"
                + "BEGIN\n"
                + "CREATE TABLE ##TempRecruiterTable  (\n"
                + "	   [recruiter_id] INT\n"
                + "      ,[name] NVARCHAR(MAX)\n"
                + "      ,[address]NVARCHAR(MAX)\n"
                + "      ,[city]NVARCHAR(MAX)\n"
                + "      ,[avatar]NVARCHAR(MAX)\n"
                + "      ,[banner]NVARCHAR(MAX)\n"
                + "      ,[phone]NVARCHAR(MAX)\n"
                + "      ,[website]NVARCHAR(MAX)\n"
                + "      ,[description]NVARCHAR(MAX)\n"
                + "      ,[employee_quantity]NVARCHAR(MAX)\n"
                + "      ,[contacter_name]NVARCHAR(MAX)\n"
                + "      ,[contacter_phone]NVARCHAR(MAX)\n"
                + "      ,[createAt] date\n"
                + "      ,[updateAt] date\n"
                + "	 ) \n"
                + "END \n";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(createTempTable);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Insert data to recruiter temporary table (##TempRecruiterTable).
     *
     * @param txtSearch String, text in searching box.
     * @param cityValue String, name of city.
     */
    @Override
    public void insertRecruiter(String txtSearch, String cityValue) {
//        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
        String clearQuery = "DELETE FROM ##TempRecruiterTable  \n ";
        String insertTable = "INSERT INTO ##TempRecruiterTable  ( \n"
                + "       [recruiter_id] \n"
                + "      ,[name] \n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt] \n"
                + "      ,[updateAt])\n ";

        String queryTable = "select [recruiter_id]\n"
                + "      ,[name]\n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "from recruiter \n"
                + "where freetext([name],?) or freetext(phone,?)\n";

        String mainQuery = clearQuery + insertTable + queryTable;
        System.out.println(mainQuery);
        if (!cityValue.equalsIgnoreCase("All")) {
            String queryWhere = "where [city] like ?";
            mainQuery = mainQuery + queryWhere;
        }
        try {
            int count = 1;
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(mainQuery);
            ps.setString(1, txtSearch);
            ps.setString(2, txtSearch);

            if (!cityValue.equalsIgnoreCase("All")) {
                ps.setString(3, "%" + cityValue + "%");
            }
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Bug insert: " + e);
        }
    }

    /**
     * Count total number of row in ##TempRecruiterTable
     *
     * @return total int, total number row in recruiter table
     */
    @Override
    public int getTotalRecruiterRow() {
        int total = 0;
        String query = "select count(*) as num  from (select recruiter_id from recruiter group by recruiter_id) as countTable";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("bug get row job " + e);
        }
        return total;
    }

    /**
     * Count total number of row in Recruiter Table
     *
     * @return total int, total number row in recruiter table
     */
    @Override
    public int getTotalTempRecruiterRow() {
        int total = 0;
        String query = "select count(*) as num  from (select recruiter_id from ##TempRecruiterTable group by recruiter_id) as countTable";
        try {

            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("bug get row job " + e);
        }
        return total;
    }

    /**
     * Get record of recruiter form ##TempRecruiterTable
     *
     * @param pageNumber int, current page number
     * @param recordNumber int, number of record
     * @return
     */
    @Override
    public ArrayList<Recruiter> getRecruiterPaging(int pageNumber, int recordNumber) {
        ArrayList<Recruiter> recruitersList = new ArrayList<>();
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber= ? \n"
                + "SET @RowsOfPage= ? \n"
                + " SELECT [recruiter_id]\n"
                + "      ,[name]\n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "FROM ##TempRecruiterTable\n"
                + "ORDER BY [recruiter_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pageNumber);
            ps.setInt(2, recordNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recruiter r = new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("city"),
                        rs.getString("name").trim(),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getString("banner"),
                        rs.getString("phone"),
                        rs.getString("website"),
                        rs.getString("description"),
                        rs.getString("employee_quantity"),
                        rs.getString("contacter_name"),
                        rs.getString("contacter_phone")
                );
                r.setSkillList(getRecruiterSkill(r.getRecruiterId()));
                r.setSkillListName(getSkillNameByRecruiterId(r.getRecruiterId()));
                recruitersList.add(r);
            }
        } catch (Exception e) {
            System.out.println("getRecruiterPaging :" + e);
        }
        return recruitersList;
    }

    @Override
    public ArrayList<Recruiter> getAllRecruiter(int pageNumber, int recordNumber) {
        ArrayList<Recruiter> recruitersList = new ArrayList<>();
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber= ? \n"
                + "SET @RowsOfPage= ? \n"
                + " SELECT [recruiter_id]\n"
                + "      ,[name]\n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "FROM [SWP391].[dbo].[recruiter]\n"
                + "ORDER BY [recruiter_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pageNumber);
            ps.setInt(2, recordNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recruiter r = new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("city"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getString("banner"),
                        rs.getString("phone"),
                        rs.getString("website"),
                        rs.getString("description"),
                        rs.getString("employee_quantity"),
                        rs.getString("contacter_name"),
                        rs.getString("contacter_phone")
                );
                r.setSkillList(getRecruiterSkill(r.getRecruiterId()));
                r.setSkillListName(getSkillNameByRecruiterId(r.getRecruiterId()));
                recruitersList.add(r);
            }
        } catch (Exception e) {
            System.out.println("getAllRecruiter :" + e);
        }
        return recruitersList;
    }

    @Override

    public ArrayList<Skill> getRecruiterSkill(int recruiterId) {
        ArrayList<Skill> skillList = new ArrayList<>();
        String query = "SELECT skill.skill_id, skill.name, skill.icon, skill.description\n"
                + "from recruiter\n"
                + "inner join recruiter_skill on recruiter_skill.recruiter_id= recruiter.recruiter_id\n"
                + "inner join skill on recruiter_skill.skill_id= skill.skill_id\n"
                + "where recruiter.recruiter_id= ?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillList.add(new Skill(rs.getInt("skill_id"),
                        rs.getNString("name"),
                        rs.getNString("icon"),
                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return skillList;
    }

    @Override
    public void insertRecruiterFilterByCity(String cityValue) {
        String clearQuery = "DELETE FROM ##TempRecruiterTable  \n ";
        String insertTable = "INSERT INTO ##TempRecruiterTable  ( \n"
                + "       [recruiter_id] \n"
                + "      ,[name] \n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt] \n"
                + "      ,[updateAt]) \n";
        String queryTable = "select [recruiter_id]\n"
                + "      ,[name]\n"
                + "      ,[address]\n"
                + "      ,[city]\n"
                + "      ,[avatar]\n"
                + "      ,[banner]\n"
                + "      ,[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "from [SWP391].[dbo].[recruiter]\n"
                + "where recruiter.city like ?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(clearQuery + insertTable + queryTable);
            ps.setString(1, "%" + cityValue + "%");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<String> getSkillNameByRecruiterId(int recruiterId) {

        ArrayList<String> skillList = new ArrayList<>();
        String query = "SELECT skill.name\n"
                + "from recruiter\n"
                + "inner join recruiter_skill on recruiter_skill.recruiter_id= recruiter.recruiter_id\n"
                + "inner join skill on recruiter_skill.skill_id= skill.skill_id\n"
                + "where recruiter.recruiter_id= ?";

        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillList.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return skillList;

    }

    /**
     * Check if the candidate has been following the recruiter (company)
     *
     * @param recruiterId int, recruiter id
     * @param candidateId int, candidate id
     * @return
     */
    @Override
    public boolean checkRecruiterBeenFollowing(int recruiterId, int candidateId) {
        String query = "SELECT [id]\n"
                + "      ,[candidate_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "  FROM [SWP391].[dbo].[follow]\n"
                + "  where candidate_id= ? and recruiter_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ps.setInt(2, candidateId);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Bug acc" + e);
        }
        return true;
    }

    /**
     *
     * @param recruiterId
     * @param candidateId
     */
    @Override
    public void createRequestFollowingCompany(int recruiterId, int candidateId) {
        String query = "insert into [SWP391].[dbo].[follow](recruiter_id, candidate_id)\n"
                + "values (?,?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ps.setInt(2, candidateId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Bug createRequestFollowingCompany: " + e);
        }
    }

    @Override
    public void deleteRequestFollowingCompany(int recruiterId, int candidateId) {
        String query = "delete from  [SWP391].[dbo].[follow] where recruiter_id=? and candidate_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ps.setInt(2, candidateId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteRequestFollowingCompany: " + e);
        }
    }

    @Override
    public ArrayList<Recruiter> getCandidateFollowingRecruiterList(int accountId, int currentPage, int recordQuantity) {
        ArrayList<Recruiter> list = new ArrayList<>();
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber= ? \n"
                + "SET @RowsOfPage= ?\n"
                + "SELECT recruiter.[recruiter_id]\n"
                + "      ,[name]\n"
                + "      ,recruiter.[address]\n"
                + "      ,[city]\n"
                + "      ,recruiter.[avatar]\n"
                + "      ,recruiter.[banner]\n"
                + "      ,recruiter.[phone]\n"
                + "      ,[website]\n"
                + "      ,[description]\n"
                + "      ,[employee_quantity]\n"
                + "      ,[contacter_name]\n"
                + "      ,[contacter_phone]\n"
                + "      ,recruiter.[createAt]\n"
                + "      ,recruiter.[updateAt]\n"
                + "FROM [SWP391].[dbo].[recruiter]\n"
                + "inner join follow on follow.recruiter_id = recruiter.recruiter_id\n"
                + "inner join candidate on candidate.candidate_id= follow.candidate_id\n"
                + "where candidate.account_id = ?\n"
                + "ORDER BY [follow].id desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";

        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, currentPage);
            ps.setInt(2, recordQuantity);
            ps.setInt(3, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recruiter recruiter = new Recruiter(rs.getInt("recruiter_id"), rs.getString("city"), rs.getString("name"), rs.getString("address"), rs.getString("avatar"),
                        rs.getString("banner"), rs.getString("phone"), rs.getString("website"),
                        rs.getString("description"), rs.getString("employee_quantity"),
                        rs.getString("contacter_name"), rs.getString("contacter_phone"));
                list.add(recruiter);
            }
        } catch (Exception e) {
            System.out.println("Bug ApplyJobPanging:" + e);
        }
        return list;
    }

    /**
     *
     * @param candidateAccountId
     * @return
     */
    @Override
    public int getCandidateTotalFollowingCompany(int candidateAccountId) {
        int totalRow = 0;
        String query = "SELECT count(*) as num\n"
                + "FROM [SWP391].[dbo].[recruiter]\n"
                + "inner join follow on follow.recruiter_id = recruiter.recruiter_id\n"
                + "inner join candidate on candidate.candidate_id= follow.candidate_id\n"
                + "where candidate.account_id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateAccountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("bug getCandidateTotalFollowingCompany: " + e);
        }
        return totalRow;
    }

//get top 8 recruter
    @Override
    public ArrayList<Recruiter> getTop8Recruiter() {
        ArrayList<Recruiter> recruitersList = new ArrayList<>();
        String query = "select top 8 * from recruiter";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recruiter recruiter = new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("city").trim(),
                        rs.getString("name").trim(),
                        rs.getString("address").trim(),
                        rs.getString("avatar").trim(),
                        rs.getString("banner").trim(),
                        rs.getString("phone").trim(),
                        rs.getString("website"),
                        rs.getString("description"),
                        rs.getString("employee_quantity"),
                        rs.getString("contacter_name"),
                        rs.getString("contacter_phone")
                );
                recruitersList.add(recruiter);
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return recruitersList;
    }

    @Override
    public ArrayList<Job> getRecruimnetByRecruiterIdPagening(String recruiterId, int currentPage, int recordQuantity) {
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber= 1 \n"
                + "SET @RowsOfPage= 10\n"
                + "SELECT job.[job_id]\n"
                + "      ,job.recruiter_id\n"
                + "      ,[title]\n"
                + "      ,job.[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,job.[status]\n"
                + "	  ,job.[createAt]\n"
                + "      ,job.[updateAt] \n"
                + "FROM job \n"
                + "inner join recruiter on recruiter.recruiter_id= job.recruiter_id\n"
                + "where recruiter.recruiter_id=1\n"
                + "ORDER BY job.[job_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";
        ArrayList<Job> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("salary_range"),
                        rs.getString("quantity"),
                        rs.getString("role"),
                        rs.getString("experience"),
                        rs.getString("location"),
                        rs.getString("hire_date"),
                        rs.getBoolean("status")
                );

                list.add(job);
            }
        } catch (Exception e) {
            System.out.println("getJobLandingPage() :" + e);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Job> list = new ArrayList<>();
        RecruiterDAO dao = new RecruiterDAO();

       

    }
}
