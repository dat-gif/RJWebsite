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
 *
 * @author Admin
 */
public class RecruiterDAO extends DBContext implements dao.idao.IRecruiter {

    /**
     *
     * Change from string type to array type form searching
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
     *
     * @param recruiterId
     * @return
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
                        rs.getString("address").trim(),
                        rs.getString("avatar").trim(),
                        rs.getString("banner").trim(),
                        rs.getString("phone").trim(),
                        rs.getString("website").trim(),
                        rs.getString("description").trim(),
                        rs.getString("employee_quantity").trim(),
                        rs.getString("contacter_name").trim(),
                        rs.getString("contacter_phone").trim(),
                        rs.getDate("createAt"),
                        rs.getDate("updateAt"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

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

    @Override
    public void insertRecruiter(String txtSearch, String cityValue) {
        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
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
                + "      ,[updateAt]) ";
        String queryUnionTable = "select TOP (9223372036854775807) unionTable.[recruiter_id]\n"
                + "      ,unionTable.[name]\n"
                + "      ,unionTable.[address]\n"
                + "      ,unionTable.[city]\n"
                + "      ,unionTable.[avatar]\n"
                + "      ,unionTable.[banner]\n"
                + "      ,unionTable.[phone]\n"
                + "      ,unionTable.[website]\n"
                + "      ,unionTable.[description]\n"
                + "      ,unionTable.[employee_quantity]\n"
                + "      ,unionTable.[contacter_name]\n"
                + "      ,unionTable.[contacter_phone]\n"
                + "      ,unionTable.[createAt]\n"
                + "      ,unionTable.[updateAt]\n"
                + "from (";
        String queryTable = "select [recruiter_id]\n"
                + "      ,[account_id]\n"
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
                + "where recruiter.name like ? or recruiter.contacter_phone like ?\n";
        for (int i = 0; i < wordSearchList.size(); i++) {
            queryUnionTable = queryUnionTable + queryTable;
            if (i != wordSearchList.size() - 1) {
                queryUnionTable = queryUnionTable
                        + "\n union \n ";
            }
        }
        queryUnionTable = queryUnionTable + " )as unionTable \n";
        if (!cityValue.equalsIgnoreCase("All")) {
            String queryWhere = "where ";
            queryWhere = queryWhere + "unionTable.[city] like ?";
            queryUnionTable = queryUnionTable + queryWhere;
        }
        try {
            int count = 1;
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(clearQuery + insertTable + queryUnionTable);
            for (int i = 0; i < wordSearchList.size(); i++) {
                String get = wordSearchList.get(i);
                ps.setString(count, "%" + get + "%");
                ps.setString(++count, "%" + get + "%");
                count++;
            }

            if (!cityValue.equalsIgnoreCase("All")) {
                ps.setString(count, "%" + cityValue + "%");
            }
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Bug insert: " + e);
        }
    }

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
     * Get recruiter
     *
     * @param pageNumber
     * @param recordNumber
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
                r.setSkillList(getRecruiterSkill(r.getRecruiterId()));
                r.setSkillListName(getSkillNameByRecruiterId(r.getRecruiterId()));
                recruitersList.add(r);
            }
        } catch (Exception e) {
            System.out.println("get panig recruiter :" + e);
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
                r.setSkillList(getRecruiterSkill(r.getRecruiterId()));
                r.setSkillListName(getSkillNameByRecruiterId(r.getRecruiterId()));
                recruitersList.add(r);
            }
        } catch (Exception e) {
            System.out.println("get all recruiter :" + e);
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

    public static void main(String[] args) {
        List<Recruiter> list = new ArrayList<>();
        RecruiterDAO dao = new RecruiterDAO();
        list = dao.getAllRecruiter(1, 10);
        for (Recruiter recruiter : list) {
            System.out.println(recruiter.toString());
        }
        dao.createRecruiterTempoTableSearchData();
        dao.insertRecruiter("ACE", "All");

        list = dao.getRecruiterPaging(1, 10);
        for (Recruiter recruiter : list) {
            System.out.println(recruiter.toString());
        }
        dao.insertRecruiterFilterByCity("Hà Nội");
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
            Connection conn = new DBContext().getConnection();
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
     * @param recruiterId
     * @param cadidateId
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

}
