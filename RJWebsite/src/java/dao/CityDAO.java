/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.idao.ICity;
import context.DBContext;
import entity.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The data access object executes a data query from the City table.
 *
 * @author Nguyen Dinh Dat
 */
public class CityDAO extends DBContext implements ICity {

    @Override
    public List<City> getAllCity() {
        List<City> allCity = new ArrayList<>();
        String query = "SELECT  [city]\n"
                + "      ,[province]\n"
                + "      ,[area]\n"
                + "      ,[population]\n"
                + "  FROM [SWP391].[dbo].[vn_city]\n"
                + " ORDER BY [city]";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allCity.add(new City(rs.getString("city"), rs.getString("province")));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return allCity;
    }
}
