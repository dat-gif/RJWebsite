/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.idao.IJob;
import context.DBContext;
import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The data access object performs the data query and updates from the Job table
 * or main business related to the Job table and the Job entity.
 *
 * @author
 */
public class JobDAO extends DBContext implements IJob {

    /**
     * Get first 12 job record in database
     *
     * @return list of Job
     */
    @Override
    public ArrayList<Job> getJobLandingPage() {
        ArrayList<Job> list = new ArrayList<>();

        String query = "SELECT TOP (12) [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[status]\n"
                + "  FROM [SWP391].[dbo].[job]";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title").trim(),
                        rs.getString("description").trim(),
                        rs.getString("salary_range").trim(),
                        rs.getString("quantity").trim(),
                        rs.getString("role").trim(),
                        rs.getString("experience").trim(),
                        rs.getString("location").trim(),
                        rs.getString("hire_date").trim(),
                        rs.getBoolean("status")
                );

                job.setSkillListName(getSkillNameByJobId(job.getjId()));
                job.setRecruiter(getRecruiterIdNameById(rs.getInt("recruiter_id")));
                list.add(job);
            }
        } catch (Exception e) {
            System.out.println("getJobLandingPage() :" + e);
            throw new Error(e);
        }
        return list;
    }

    /**
     * Get skill list of job by job id
     *
     * @param jobId The id of job
     * @return ArrayList of skill object
     */
    @Override
    public ArrayList<Skill> getSkillByJobId(int jobId) {
        ArrayList<Skill> skillList = new ArrayList<>();
        String query = "SELECT skill.skill_id, skill.name, skill.icon, skill.description\n"
                + "  FROM [SWP391].[dbo].[job]\n"
                + "  inner join job_skill on job.job_id = job_skill.job_id\n"
                + "  inner join skill on job_skill.skill_id= skill.skill_id\n"
                + "  where job.job_id= ? ";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillList.add(new Skill(rs.getInt("skill_id"),
                        rs.getNString("name"),
                        rs.getNString("icon"),
                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
            throw new Error(e);
        }
        return skillList;
    }

    /**
     * Get full information of recruiter by id
     *
     * @param recruterId the id of recruiter
     * @return Recruiter Object
     */
    @Override
    public Recruiter getRecruterById(int recruterId) {
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
            ps.setInt(1, recruterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("name"),
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
            throw new Error(e);
        }
        return null;

    }

    /**
     * Get id and name of recruiter by job id
     *
     * @param recruiterId the id of recruiter
     * @return Recruiter(int recruiterId, String recruiterName)
     */
    @Override
    public Recruiter getRecruiterIdNameById(int recruiterId) {
        String query = "SELECT recruiter.recruiter_id\n"
                + ",recruiter.name\n"
                + ",recruiter.avatar\n"
                + "FROM [SWP391].[dbo].[recruiter]\n"
                + "where recruiter.recruiter_id=?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                return new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("name").trim(), rs.getString("avatar"));
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new Error(e);
        }
        return null;
    }

    @Override
    public ArrayList<String> getSkillNameByJobId(int jobId) {
        ArrayList<String> skillName = new ArrayList<>();
        String query = "SELECT skill.name\n"
                + "  FROM [SWP391].[dbo].[job]\n"
                + "  inner join job_skill on job.job_id = job_skill.job_id\n"
                + "  inner join skill on job_skill.skill_id= skill.skill_id\n"
                + "  where job.job_id= ? ";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillName.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
            throw new Error(e);
        }
        return skillName;
    }
// Test JobDao

    /**
     * Get all job record in database
     *
     * @param pageNumber
     * @param recordNumber
     * @return ArrayList<Job>
     */
    @Override
    public ArrayList<Job> getAllJob(int pageNumber, int recordNumber) {
        ArrayList<Job> list = new ArrayList<>();
        String querry
                = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber=?\n"
                + "SET @RowsOfPage=?\n"
                + "SELECT [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[status]\n"
                + "  FROM [SWP391].[dbo].[job]"
                + "ORDER BY [job_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setInt(1, pageNumber);
            ps.setInt(2, recordNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title").trim(),
                        rs.getString("description").trim(),
                        rs.getString("salary_range").trim(),
                        rs.getString("quantity").trim(),
                        rs.getString("role").trim(),
                        rs.getString("experience").trim(),
                        rs.getString("location").trim(),
                        rs.getString("hire_date").trim(),
                        rs.getBoolean("status")
                );
                job.setSkillListName(getSkillNameByJobId(job.getjId()));
                job.setRecruiter(getRecruiterIdNameById(rs.getInt("recruiter_id")));
                list.add(job);
            }
        } catch (Exception e) {
            System.out.println("getAllJob :" + e);
            throw new Error(e);
        }
        return list;
    }

    @Override
    public ArrayList<Skill> getAllSkill() {
        ArrayList<Skill> skillList = new ArrayList<>();
        String query = "SELECT  [skill_id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[question]\n"
                + "      ,[icon]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "  FROM [SWP391].[dbo].[skill] ";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillList.add(new Skill(rs.getInt("skill_id"),
                        rs.getNString("name"),
                        rs.getNString("icon"),
                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("getAllSkill() :" + e);
            throw new Error(e);
        }
        return skillList;
    }

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
     * Get Job List if user only enter city and skill value
     *
     * @param skillValue
     * @param cityValue
     * @return
     */
    @Override
    public ArrayList<Job> getJobBySkillAndCity(String skillValue, String cityValue) {
        ArrayList<Job> list = new ArrayList<>();
        String mainQuery = "select DISTINCT job.[job_id]\n"
                + "      ,job.[recruiter_id]\n"
                + "      ,job.[title]\n"
                + "      ,job.[description]\n"
                + "      ,job.[salary_range]\n"
                + "      ,job.[quantity]\n"
                + "      ,job.[role]\n"
                + "      ,job.[experience]\n"
                + "      ,job.[location]\n"
                + "      ,job.[hire_date]\n"
                + "      ,job.[questions]\n"
                + "      ,job.[status]\n"
                + "      ,job.[createAt]\n"
                + "      ,job.[updateAt]\n"
                + "from job\n";
        String skillJoinQuery = "inner join job_skill on job_skill.job_id = job.job_id\n"
                + "inner join skill on job_skill.skill_id = skill.skill_id\n";
        // City of job in database can get by recruiter info
        String cityJoinQuery = "inner join recruiter on recruiter.recruiter_id = job.recruiter_id\n";
        String queryWhere = "where ";
        if (!skillValue.equalsIgnoreCase("All")) {
            mainQuery = mainQuery.concat(skillJoinQuery);
            queryWhere = queryWhere + "skill.skill_id = ?\n";
            if (!cityValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + " and\n";
            }
        }
        if (!cityValue.equalsIgnoreCase("All")) {
            mainQuery = mainQuery.concat(cityJoinQuery);
            queryWhere = queryWhere + "recruiter.city like ?";
        }
        mainQuery = mainQuery.concat(queryWhere);

        int countCondition = 1;
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(mainQuery);
            if (!skillValue.equalsIgnoreCase("All")) {
                ps.setString(countCondition, skillValue);
                countCondition++;
            }
            if (!cityValue.equalsIgnoreCase("All")) {
                ps.setString(countCondition, '%' + cityValue + '%');
                countCondition++;
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title").trim(),
                        rs.getString("description").trim(),
                        rs.getString("salary_range").trim(),
                        rs.getString("quantity").trim(),
                        rs.getString("role").trim(),
                        rs.getString("experience").trim(),
                        rs.getString("location").trim(),
                        rs.getString("hire_date").trim(),
                        rs.getBoolean("status")
                );
                job.setSkillListName(getSkillNameByJobId(job.getjId()));
                job.setRecruiter(getRecruiterIdNameById(rs.getInt("recruiter_id")));
                list.add(job);
            }
        } catch (Exception e) {
            System.err.println("Job City Skill" + e);
            throw new Error(e);
        }
        return list;
    }

    /**
     * Create temporary table contain job info when searching job
     */
    @Override
    public void createTempoTableSearchJobData() {
        String createTempTable = "IF OBJECT_ID('tempdb..##TempTable') IS NULL\n"
                + "BEGIN\n"
                + "CREATE TABLE ##TempTable (job_id INT\n"
                + "      ,recruiter_id NVARCHAR(MAX)\n"
                + "      ,[title] NVARCHAR(MAX)\n"
                + "      ,[description] NVARCHAR(MAX)\n"
                + "      ,[salary_range] NVARCHAR(MAX)\n"
                + "      ,[quantity] NVARCHAR(MAX)\n"
                + "      ,[role] NVARCHAR(MAX)\n"
                + "      ,[experience] NVARCHAR(MAX)\n"
                + "      ,[location] NVARCHAR(MAX)\n"
                + "      ,[hire_date] NVARCHAR(MAX)\n"
                + "      ,[questions] NVARCHAR(MAX)\n"
                + "      ,[status] bit\n"
                + "      ,[createAt] date\n"
                + "      ,[updateAt] date\n"
                + "	 ) \n"
                + "END \n";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(createTempTable);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            throw new Error(e);
        }
    }

    /**
     * Insert data to temporary job table by text searching, skill id, city name
     *
     * @param txtSearch
     * @param skillValue
     * @param cityValue
     */
    @Override
    public void insertJobByTextSearch(String txtSearch, String skillValue, String cityValue) {
//        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
        String clearQuery = "DELETE FROM ##TempTable \n ";
        String insertTable = "INSERT INTO ##TempTable ( [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt])\n";

        String freeTextQuery = "  select TOP (9223372036854775807) unionTable.[job_id]\n"
                + "      ,unionTable.[recruiter_id]\n"
                + "      ,unionTable.[title]\n"
                + "      ,unionTable.[description]\n"
                + "      ,unionTable.[salary_range]\n"
                + "      ,unionTable.[quantity]\n"
                + "      ,unionTable.[role]\n"
                + "      ,unionTable.[experience]\n"
                + "      ,unionTable.[location]\n"
                + "      ,unionTable.[hire_date]\n"
                + "      ,unionTable.[questions]\n"
                + "      ,unionTable.[status]\n"
                + "      ,unionTable.[createAt]\n"
                + "      ,unionTable.[updateAt]\n"
                + "from (\n"
                + " select DISTINCT job.[job_id]\n"
                + ",job.[recruiter_id]\n"
                + ",job.[title]\n"
                + ",job.[description]\n"
                + ",job.[salary_range]\n"
                + ",job.[quantity]\n"
                + ",job.[role]\n"
                + ",job.[experience]\n"
                + ",job.[location]\n"
                + ",job.[hire_date]\n"
                + ",job.[questions]\n"
                + ",job.[status]\n"
                + ",job.[createAt]\n"
                + ",job.[updateAt]\n"
                + "from job\n"
                + "left join job_skill on job_skill.job_id = job.job_id\n"
                + "left join skill on job_skill.skill_id = skill.skill_id\n"
                + "where (freetext(job.title, ? ) or freetext(skill.name , ? )) and job.status=1) \n"
                + "as unionTable\n";

        String mainQuery = clearQuery + insertTable + freeTextQuery;

        if (!skillValue.equalsIgnoreCase("All") || !cityValue.equalsIgnoreCase("All")) {
            String joinQuery = "";
            String skillJoinQuery = "inner join job_skill on job_skill.job_id = unionTable.job_id\n"
                    + "inner join skill on job_skill.skill_id = skill.skill_id\n";
            // City info of job in database can get by recruiter info
            String cityJoinQuery = "inner join recruiter on recruiter.recruiter_id = unionTable.recruiter_id\n";
            String queryWhere = "where ";
            if (!skillValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + "skill.skill_id = ?";
                if (!cityValue.equalsIgnoreCase("All")) {
                    queryWhere = queryWhere + " and\n";
                }
                joinQuery = joinQuery + skillJoinQuery;
            }
            if (!cityValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + "recruiter.city like ?";
                joinQuery = joinQuery + cityJoinQuery;
            }
            mainQuery = mainQuery + joinQuery + queryWhere;
        }
        try {
            int count = 2;
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(mainQuery);
            ps.setString(1, txtSearch);
            ps.setString(2, txtSearch);
            if (!skillValue.equalsIgnoreCase("All") || !cityValue.equalsIgnoreCase("All")) {
                count++;
                if (!skillValue.equalsIgnoreCase("All")) {
                    ps.setString(count, skillValue);
                    if (!cityValue.equalsIgnoreCase("All")) {
                        count++;
                    }
                }
                if (!cityValue.equalsIgnoreCase("All")) {
                    ps.setString(count, "%" + cityValue + "%");
                }
            }
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("insertJobByTextSearch: " + e);
            throw new Error(e);
        }
    }

    /**
     * Get and insert data to temporary table contanin job info filter by skill
     * and city
     *
     * @param skillValue String id of skill
     * @param cityValue String id of city
     */
    @Override
    public void insertJobByFilter(String skillValue, String cityValue) {
        String clearQuery = "DELETE FROM ##TempTable \n ";
        String insertTable = "INSERT INTO ##TempTable ( [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt])\n";
        String selectQuery = "select job.[job_id]\n"
                + "      ,job.[recruiter_id]\n"
                + "      ,job.[title]\n"
                + "      ,job.[description]\n"
                + "      ,job.[salary_range]\n"
                + "      ,job.[quantity]\n"
                + "      ,job.[role]\n"
                + "      ,job.[experience]\n"
                + "      ,job.[location]\n"
                + "      ,job.[hire_date]\n"
                + "      ,job.[questions]\n"
                + "      ,job.[status]\n"
                + "      ,job.[createAt]\n"
                + "      ,job.[updateAt]\n"
                + "from job\n";
        String skillJoinQuery = "inner join job_skill on job_skill.job_id = job.job_id\n"
                + "inner join skill on job_skill.skill_id = skill.skill_id\n";
        // City of job in database can get by recruiter info
        String cityJoinQuery = "inner join recruiter on recruiter.recruiter_id = job.recruiter_id\n";
        String queryWhere = "where ";
        if (!skillValue.equalsIgnoreCase("All")) {
            selectQuery = selectQuery.concat(skillJoinQuery);
            queryWhere = queryWhere + "skill.skill_id = ?";
            if (!cityValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + " and\n";
            }
        }
        if (!cityValue.equalsIgnoreCase("All")) {
            selectQuery = selectQuery.concat(cityJoinQuery);
            queryWhere = queryWhere + "recruiter.city like ?";
        }
        selectQuery = selectQuery.concat(queryWhere);

        int countCondition = 1;
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(clearQuery + insertTable + selectQuery);
            if (!skillValue.equalsIgnoreCase("All")) {
                ps.setString(countCondition, skillValue);
                countCondition++;
            }
            if (!cityValue.equalsIgnoreCase("All")) {
                ps.setString(countCondition, '%' + cityValue + '%');
                countCondition++;
            }
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Job City Skill" + e);
            throw new Error(e);
        }
    }

    /**
     * Get data form temporary job
     *
     * @param pageNumber
     * @param recordNumber number record get from database
     * @return ArrayList<Job>
     */
    @Override
    public ArrayList<Job> getJobSearching(int pageNumber, int recordNumber) {

        ArrayList<Job> list = new ArrayList<>();

        String querry
                = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber=?\n"
                + "SET @RowsOfPage=?\n"
                + "SELECT DISTINCT [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "FROM ##TempTable\n"
                + "ORDER BY [job_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setInt(1, pageNumber);
            ps.setInt(2, recordNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title").trim(),
                        rs.getString("description").trim(),
                        rs.getString("salary_range").trim(),
                        rs.getString("quantity").trim(),
                        rs.getString("role").trim(),
                        rs.getString("experience").trim(),
                        rs.getString("location").trim(),
                        rs.getString("hire_date").trim(),
                        rs.getBoolean("status")
                );
                job.setSkillListName(getSkillNameByJobId(job.getjId()));

                job.setRecruiter(getRecruiterIdNameById(rs.getInt("recruiter_id")));

                list.add(job);
            }

        } catch (Exception e) {
            System.err.println("getJobSearching " + e);
//            throw new Error(e);
        }
        return list;
    }

    @Override
    public int getTotalJobRow() {
        int totalRow = 0;
        String query = "select count(*) as num from job";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("getTotalJobRow()  " + e);
            throw new Error(e);
        }
        return totalRow;

    }

    @Override
    public int getTotalTempJobRow() {
        int totalRow = 0;
        String query = " select count(*) as num  from (select job_id from ##TempTable group by job_id) as countTable";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("getTotalTempJobRow() " + e);

        }
        return totalRow;

    }

    @Override
    public Job getJobById(int id) {
        String query = "SELECT [job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "  FROM [SWP391].[dbo].[job]\n"
                + "  where job_id = ?";

        Job job = new Job();
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Job(rs.getInt("job_id"),
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
                job.setRecruiter(getRecruterById(rs.getInt("recruiter_id")));
                job.setSkillListName(getSkillNameByJobId(job.getjId()));
            }
        } catch (Exception e) {
            System.out.println("getJobById: " + e);
            throw new Error(e);
        }
        return job;
    }

    /**
     * Get the list of job candidates who are applying, built-in pagination.
     * Requiem know current page and number of records want to get.
     *
     * @param accountId int
     * @param currentPage
     * @param recordQuantity
     * @return
     */
    @Override
    public ArrayList<Job> getApplyJobPangingByAccountId(int accountId, int currentPage, int recordQuantity) {
        ArrayList<Job> list = new ArrayList<>();
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber= ? \n"
                + "SET @RowsOfPage= ?\n"
                + "SELECT job.[job_id]\n"
                + "      ,[recruiter_id]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[salary_range]\n"
                + "      ,[quantity]\n"
                + "      ,[role]\n"
                + "      ,[experience]\n"
                + "      ,[location]\n"
                + "      ,[hire_date]\n"
                + "      ,[questions]\n"
                + "      ,job.[status]\n"
                + "	 ,job.[createAt]\n"
                + "      ,job.[updateAt] \n"
                + "	 ,candidate_job_apply.status as job_apply_status\n"
                + "FROM job \n"
                + "inner join candidate_job_apply on candidate_job_apply.job_id= job.job_id\n"
                + "inner join candidate on candidate.candidate_id= candidate_job_apply.candidate_id\n"
                + "where candidate.account_id = ?\n"
                + "ORDER BY [candidate_job_apply].applyNo desc\n"
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
                Job job = new Job(rs.getInt("job_id"),
                        rs.getString("title").trim(),
                        rs.getString("description").trim(),
                        rs.getString("salary_range").trim(),
                        rs.getString("quantity").trim(),
                        rs.getString("role").trim(),
                        rs.getString("experience").trim(),
                        rs.getString("location").trim(),
                        rs.getString("hire_date").trim(),
                        rs.getBoolean("status"),
                        rs.getString("job_apply_status").trim()
                );

                job.setSkillListName(getSkillNameByJobId(job.getjId()));
                job.setRecruiter(getRecruiterIdNameById(rs.getInt("recruiter_id")));
                list.add(job);
            }
        } catch (Exception e) {
            System.out.println("Bug ApplyJobPanging:" + e);
            throw new Error(e);
        }
        return list;
    }

    /**
     * Get total record of candidate apply job.
     *
     * @param candidateAccountId int
     * @return
     */
    @Override
    public int getTotalApplyJobRow(int candidateAccountId) {
        int totalRow = 0;
        String query = "SELECT count(*) as num\n"
                + "FROM job \n"
                + "inner join candidate_job_apply on candidate_job_apply.job_id= job.job_id\n"
                + "inner join candidate on candidate.candidate_id= candidate_job_apply.candidate_id\n"
                + "where candidate.account_id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateAccountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt("num");
            }
        } catch (Exception e) {
            System.out.println("bug get row Temp job " + e);
            throw new Error(e);
        }
        return totalRow;
    }

    /**
     * Check if job has been apply by that candidate or not
     *
     * @param jobId int
     * @param candidateId int
     * @return
     */
    @Override
    public boolean checkJobBeenApply(int jobId, int candidateId) {
        String query = "SELECT TOP (1000) [applyNo]\n"
                + "      ,[candidate_id]\n"
                + "      ,[job_id]\n"
                + "      ,[status]\n"
                + "  FROM [SWP391].[dbo].[candidate_job_apply]\n"
                + "  Where job_id= ? and candidate_id= ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ps.setInt(2, candidateId);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("checkJobBeenApply " + e);
            throw new Error(e);
        }
        return true;
    }

    /**
     * Insert request apply for job to database
     *
     * @param jobId
     * @param candidateId
     */
    @Override
    public void createRequestApplyJob(int jobId, int candidateId) {
        String query = "INSERT INTO [SWP391].[dbo].[candidate_job_apply](candidate_id,job_id,status)\n"
                + "values(?,?,'PENDING')";

        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("createRequestApplyJob: " + e);
            throw new Error(e);
        }
    }

    /**
     * Edit status request apply for job
     *
     * @param jobId
     * @param candidateId
     * @param status
     */
    @Override
    public void editRequestStatusApplyJob(int jobId, int candidateId, String status) {
        String query = "UPDATE candidate_job_apply\n"
                + "SET status=? \n"
                + "WHERE candidate_id=? and job_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, candidateId);
            ps.setInt(3, jobId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Bug editRequestApplyJob: " + e);
            throw new Error(e);
        }
    }

    /**
     * Delete request apply for job
     *
     * @param jobId
     * @param candidateId
     */
    @Override
    public void deleteRequestApplyJob(int jobId, int candidateId) {
        String query = "delete from candidate_job_apply\n"
                + "WHERE candidate_id=? and job_id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Bug delete apply job: " + e);
            throw new Error(e);
        }
    }

//insert job moi vao db
    @Override
    public int insertRecruitment(int recruiterId, String title, String description, String salary, String quantity, String role, String experience, String location, String hiredDate) {
        int totalRow = 0;
        String query = "insert into job values(?,?,?,?,?,?,?,?,?,null,1,null,null)";
        try {
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, salary);
            ps.setString(5, quantity);
            ps.setString(6, role);
            ps.setString(7, experience);
            ps.setString(8, location);
            ps.setString(9, hiredDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("bug get row Temp job " + e);
            throw new Error(e);
        }
        return totalRow;
    }

//insert job skill moi vao db
    @Override
    public int insertJobSkill(int jobId, int skillId) {
        int totalRow = 0;
        String query = "insert into job values(?,?,null,null)";
        try {
            //mo ket noi, set du lieu vao cac dau ? va lay du lieu tra ve
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ps.setInt(2, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalRow = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("bug get row Temp job " + e);
            throw new Error(e);
        }
        return totalRow;
    }

//lay ra id cua job moi nhat duoc them vao bang
    @Override
    public int getLatestUpdatedJobId() {
        String query = "select top 1 * from job order by job_id desc";
        try {
            //mo ket noi, lay du lieu tra ve
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getLatestUpdatedJobId " + e);
            throw new Error(e);
        }
        return 0;

    }

    public static void main(String[] args) {
        IJob jobDao = new JobDAO();

        System.out.println(jobDao.getJobById(26).toString());

    }

}
