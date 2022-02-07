/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Account;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AppUtils {

    // Store user login info into session.
    public static void storeLoginedUser(HttpSession session, Account loginedUser) {
        // Trên JSP có thể truy cập thông qua ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Get use login info form session.
    public static Account getLoginedUser(HttpSession session) {
        Account loginedUser = (Account) session.getAttribute("loginedUser");
        return loginedUser;
    }
}
