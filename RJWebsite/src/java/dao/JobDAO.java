/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import IDao.IJob;
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
 *
 * @author Admin
 */
public class JobDAO implements IJob {

    Connection conn = null;//ket noi sql
    PreparedStatement ps = null; //truyen querry sang sql
    ResultSet rs = null; //nhan tra ve, với các func gọi chéo nhau, nên tạo rs riêng

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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
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
            System.out.println("Bug :" + e);
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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, recruterId);
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

    /**
     * Get id and name of recruiter by job id
     *
     * @param recruiterId the id of recruiter
     * @return Recruiter(int recruiterId, String recruiterName)
     */
    @Override
    public Recruiter getRecruiterIdNameById(int recruiterId) {
        String query = " SELECT recruiter.recruiter_id\n"
                + "      ,recruiter.name\n"
                + "      ,recruiter.avatar\n"
                + " FROM [SWP391].[dbo].[job]\n"
                + " inner join recruiter on recruiter.recruiter_id = job.recruiter_id\n"
                + " where job.job_id= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Recruiter(rs.getInt("recruiter_id"),
                        rs.getString("name").trim(), rs.getString("avatar"));
            }
        } catch (Exception e) {
            System.err.println(e);
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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skillName.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return skillName;
    }
// Test JobDao

    /**
     * Get all job record in database
     *
     * @return ArrayList<Job>
     */
    @Override
    public ArrayList<Job> getAllJob() {
        ArrayList<Job> list = new ArrayList<>();
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
                + "      ,[status]\n"
                + "  FROM [SWP391].[dbo].[job]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
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
            System.out.println("Bug :" + e);
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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            query = query.concat("\n where skill_id = 2");
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

    public void createTempoTableSearchData(String txtSearch) {
        String createTempTable = "IF OBJECT_ID('##TempTable') IS NULL\n"
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
        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
        String queryUnionTable = "select TOP (9223372036854775807) unionTable.[job_id]\n"
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
                + "      ,unionTable.[updateAt]  \n"
                + "from (\n";
        String queryTable = "select job.[job_id]\n"
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
                + "from job \n"
                + "inner join job_skill on job_skill.job_id = job.job_id\n"
                + "inner join skill on job_skill.skill_id = skill.skill_id\n"
                + "where job.title  like N'%" + wordSearchList.get(0) + "%' or skill.name like N'%" + wordSearchList.get(0) + "%'";
//
//        for (String string : wordSearchList) {
//            queryTable = queryTable.concat("\n union  \n" + "select job.[job_id]\n"
//                    + "      ,job.[recruiter_id]\n"
//                    + "      ,job.[title]\n"
//                    + "      ,job.[description]\n"
//                    + "      ,job.[salary_range]\n"
//                    + "      ,job.[quantity]\n"
//                    + "      ,job.[role]\n"
//                    + "      ,job.[experience]\n"
//                    + "      ,job.[location]\n"
//                    + "      ,job.[hire_date]\n"
//                    + "      ,job.[questions]\n"
//                    + "      ,job.[status]\n"
//                    + "      ,job.[createAt]\n"
//                    + "      ,job.[updateAt]\n"
//                    + "from job \n"
//                    + "inner join job_skill on job_skill.job_id = job.job_id\n"
//                    + "inner join skill on job_skill.skill_id = skill.skill_id\n"
//                    + "where job.title  like N'%" + string + "%' or skill.name like N'%" + string + "%'");
//        }
        queryTable = queryTable + ")as unionTable \n";
        String querry
                = "SELECT DISTINCT [job_id]\n"
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
                + "FROM ##TempTable";

        try {
            ArrayList<Job> list = new ArrayList<>();
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(createTempTable + insertTable + queryUnionTable + queryTable);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(createTempTable + insertTable + queryUnionTable + queryTable);
            System.err.println("Create table bug :" + e);
        }
    }

    @Override
    public ArrayList<Job> searhingJob(String txtSearch, String skillValue, String cityValue) {
        createTempoTableSearchData(txtSearch);
        ArrayList<Job> list = new ArrayList<>();
        String querry
                = "SELECT DISTINCT [job_id]\n"
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
                + "FROM ##TempTable";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
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
            System.err.println(e);
        }

        return list;
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
            queryWhere = queryWhere + "skill.skill_id = ?";
            if (!cityValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + " and";
            }
        }
        if (!cityValue.equalsIgnoreCase("All")) {
            mainQuery = mainQuery.concat(cityJoinQuery);
            queryWhere = queryWhere + "recruiter.city like ?";
        }
        mainQuery = mainQuery.concat(queryWhere);

        int countCondition = 1;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(mainQuery);
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
        }
        return list;
    }

    public static void main(String[] args) {
        IJob jobDao = new JobDAO();

        ArrayList<Job> testList = jobDao.searhingJob("PH", "1", "Hà Nội");
        for (Job job : testList) {
            System.out.println("Main :" + job.toString());
        }

    }

    @Override
    public void insertJobByTextSearch(String txtSearch, String skillValue, String cityValue) {
        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
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
    }

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
                queryWhere = queryWhere + " and";
            }
        }
        if (!cityValue.equalsIgnoreCase("All")) {
            selectQuery = selectQuery.concat(cityJoinQuery);
            queryWhere = queryWhere + "recruiter.city like ?";
        }
        selectQuery = selectQuery.concat(queryWhere);

        int countCondition = 1;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(clearQuery + insertTable + selectQuery);
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
        }
    }

    @Override
    public ArrayList<Job> getJobSearching() {
        ArrayList<Job> list = new ArrayList<>();
        String querry
                = "SELECT DISTINCT [job_id]\n"
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
                + "FROM ##TempTable";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
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
            System.err.println(e);
        }
        return list;
    }

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
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(createTempTable);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
