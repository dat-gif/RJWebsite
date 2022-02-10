/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.idao.IAccount;
import context.DBContext;
import entity.Account;
import entity.City;
import entity.Recruiter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext implements IAccount {

    @Override
    public Account getAccountByEmailAndPassword(String email, String password) {
        String query = "SELECT [account_id]\n"
                + "      ,[role_id]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[password]\n"
                + "      ,[status]\n"
                + "      ,[createAt]\n"
                + "      ,[updateAt]\n"
                + "	  ,role_name\n"
                + "  FROM [SWP391].[dbo].[account]\n"
                + "  inner join role on role.id= account.role_id\n"
                + "  Where email = ? and password = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("role_name"), rs.getString("email"), rs.getString("phone"));
            }

        } catch (Exception e) {
            System.out.println("Bug acc" + e);
        }
        return null;
    }

    /**
     * Check if account already exist in database
     *
     * @param email
     * @return
     */
    @Override
    public boolean checkExistAccountEmail(String email) {
        String query = "SELECT [email]\n"
                + "FROM [SWP391].[dbo].[account]\n"
                + "where email= ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Bug acc" + e);
        }
        return true;
    }

    /**
     * Insert account register as candidate into database
     *
     * @param account
     */
    @Override
    public void insertCandidateAccount(Account account) {
        String query = "INSERT INTO account\n"
                + "([role_id],[email],[phone],[password],[status])\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, String.valueOf(account.getRoleId()));
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPhone());
            ps.setString(4, account.getPassword());
            ps.setString(5, "1");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Bug insertAccount: " + e);
        }
    }

    /**
     * Insert account register as recruiter into database, insert in 2 table
     * account and recruiter
     *
     * @param account Object Account
     * @param recruiterBasicInfo Object Recruiter
     */
    @Override
    public void insertRecruitorAccount(Account account, Recruiter recruiterBasicInfo) {
        String query = "BEGIN TRANSACTION\n"
                + "INSERT INTO account\n"
                + "([role_id],[email],[phone],[password],[status])\n"
                + "VALUES (?, ?, ?, ?, 1)\n"
                + "DECLARE @LASTID int\n"
                + "SET @LASTID = IDENT_CURRENT('dbo.account') \n"
                + "INSERT INTO recruiter (account_id, name, address,contacter_name) VALUES (@LASTID, ?, ?, ?);\n"
                + "COMMIT";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, String.valueOf(account.getRoleId()));
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPhone());
            ps.setString(4, account.getPassword());
            ps.setString(5, recruiterBasicInfo.getName());
            ps.setString(6, recruiterBasicInfo.getAddress());
            ps.setString(7, recruiterBasicInfo.getContacterName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Bug insertAccountRecruiter: " + e);
        }
    }
}


