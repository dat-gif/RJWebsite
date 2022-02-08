/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import IDao.IAccount;
import context.DBContext;
import entity.Account;
import entity.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AccountDAO implements IAccount {

    Connection conn = null;//ket noi sql
    PreparedStatement ps = null; //truyen querry sang sql
    ResultSet rs = null; //nhan tra ve, với các func gọi chéo nhau, nên tạo rs riêng

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
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "'" + email + "'");
            ps.setString(2, "'" + password + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("role_name"), rs.getString("email"), rs.getString("phone"));
            }

        } catch (Exception e) {
        }
        return null;
    }

}
