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
        String createTempTable = " IF OBJECT_ID('tempdb..#TempRecruiterTable') IS NULL\n"
                + "BEGIN\n"
                + "CREATE TABLE #TempRecruiterTable  (\n"
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
        String clearQuery = "DELETE FROM ##TempRecruiterTable  \n ";
        String insertTable = "INSERT INTO #TempRecruiterTable  ( \n"
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
            queryWhere = queryWhere + "recruiter.city like ?";
            queryUnionTable = queryUnionTable + queryWhere;
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
    public ArrayList<Recruiter> getRecruterPaging(int pageNumber, int recordNumber) {
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

    @Override
    public ArrayList<Recruiter> getAllRecruter(int pageNumber, int recordNumber) {
        ArrayList<Recruiter> recruitersList = new ArrayList<>();
        String query = "DECLARE @PageNumber AS INT\n"
                + "DECLARE @RowsOfPage AS INT\n"
                + "SET @PageNumber=1\n"
                + "SET @RowsOfPage=10\n"
                + " SELECT [recruiter_id]\n"
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
                + "FROM [SWP391].[dbo].[recruiter]\n"
                + "ORDER BY [recruiter_id] desc\n"
                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS\n"
                + "FETCH NEXT @RowsOfPage ROWS ONLY";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                recruitersList.add(new (rs.getInt("skill_id"),
//                        rs.getNString("name"),
//                        rs.getNString("icon"),
//                        rs.getNString("description")));
            }
        } catch (Exception e) {
            System.out.println("get skill :" + e);
        }
        return recruitersList;
    }

}
