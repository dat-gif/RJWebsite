/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import IDao.IJob;
import context.DBContext;
import entity.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class JobDAO implements IJob {

    Connection conn = null;//ket noi sql
    PreparedStatement ps = null; //truyen querry sang sql
    ResultSet rs = null; //nhan tra ve

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
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt("job_id"),
                        rs.getInt("recruiter_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("salary_range"),
                        rs.getString("quantity"),
                        rs.getString("role"),
                        rs.getString("experience"),
                        rs.getString("location"),
                        rs.getString("hire_date"),
                        rs.getBoolean("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Bug :" + e);
        }

        return list;
    }
// Test JobDao

    public static void main(String[] args) {
        IJob jobDao = new JobDAO();
        ArrayList<Job> testList = jobDao.getJobLandingPage();
        for (Job job : testList) {
            job.toString();
            System.out.println("1" + job.toString());
        }
    }

}
