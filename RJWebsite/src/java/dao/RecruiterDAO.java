/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Job;
import entity.Recruiter;
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
public class RecruiterDAO implements IDao.IRecruiter {

    Connection conn = null;//ket noi sql
    PreparedStatement ps = null; //truyen querry sang sql
    ResultSet rs = null; //nhan tra ve, với các func gọi chéo nhau, nên tạo rs riêng

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

    @Override
    public void createRecruiterTempoTableSearchData() {
        String createTempTable = "IF OBJECT_ID('tempdb..##TempRecruiterTable') IS NULL\n"
                + "BEGIN\n"
                + "CREATE TABLE ##TempRecruiterTable (job_id INT\n"
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

    @Override
    public void insertRecruiter(String txtSearch, String cityValue) {
        List<String> wordSearchList = changeStringToListAndSort(txtSearch);
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
                + "where job.title like ? or skill.name like ?";
        for (int i = 0; i < wordSearchList.size(); i++) {
            queryUnionTable = queryUnionTable + queryTable;
            if (i != wordSearchList.size() - 1) {
                queryUnionTable = queryUnionTable
                        + "\n union \n ";
            }
        }
        queryUnionTable = queryUnionTable + " )as unionTable \n";
       
        if (!cityValue.equalsIgnoreCase("All")) {
            String joinQuery = "";
            String queryWhere = "where ";
            if (!cityValue.equalsIgnoreCase("All")) {
                queryWhere = queryWhere + "recruiter.city like ?";
            }
            queryUnionTable = queryUnionTable + joinQuery + queryWhere;
        }

        try {
            int count = 1;
            conn = new DBContext().getConnection();
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
    public ArrayList<Job> getRecruterPaging(int pageNumber, int recordNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalJobRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalTempJobRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
