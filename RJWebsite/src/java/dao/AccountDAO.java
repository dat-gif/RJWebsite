/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AccountDAO {
// Fake dao for test filter

    private static final Map<String, Account> mapUsers = new HashMap<String, Account>();

    static {
        initUsers();
    }

    private static void initUsers() {

        // User này có 1 vai trò là EMPLOYEE.
        Account emp = new Account("e1", "123", "cadidate");

        // User này có 2 vai trò EMPLOYEE và MANAGER.
        Account mng = new Account("manager1", "123", "recruiter");

        mapUsers.put(emp.getEmail(), emp);
        mapUsers.put(mng.getEmail(), mng);
    }

    // Tìm kiếm người dùng theo userName và password.
    public static Account findUser(String userName, String password) {
        Account u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

}
