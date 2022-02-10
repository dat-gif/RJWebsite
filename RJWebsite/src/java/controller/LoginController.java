/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.idao.IAccount;
import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AppUtils;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getSession().invalidate();
            Cookie[] cookies = request.getCookies();
            Map<String, String> cookieMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                String decodeValue = java.net.URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
                cookieMap.put(cookie.getName(), decodeValue);
            }
            String isWrongAccountMesage = cookieMap.get("isWrongAccount");
            if (isWrongAccountMesage != null && isWrongAccountMesage.length() > 0) {
                request.setAttribute("isWrongAccount", isWrongAccountMesage);
            }
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IAccount accountDao = new AccountDAO();
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        Account userAccount = accountDao.getAccountByEmailAndPassword(userEmail, password);

        if (userAccount != null) {
            AppUtils.storeLoginedUser(request.getSession(), userAccount);
            setCookie(response, "isWrongAccount", "", -1);
            response.sendRedirect("landingpage");
        } else {
            setCookie(response, "isWrongAccount", "Wrong email / password", -1);
            response.sendRedirect("login");
        }

    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) throws IOException {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
