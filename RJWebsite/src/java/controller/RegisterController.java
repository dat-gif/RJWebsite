/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.idao.IAccount;
import entity.Account;
import entity.Recruiter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.reflect.generics.visitor.Reifier;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

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
            request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
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
        processRequest(request, response);
        IAccount accountDao = new AccountDAO();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        Account registerAccount = new Account();
        registerAccount.setEmail(email);
        registerAccount.setPassword(password);
        registerAccount.setPhone(phone);
        try {

            if (!accountDao.checkExistAccountEmail(email)) {
                request.setAttribute("registerErrorMesg", "Email already in use");
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("password", password);
                request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
            }

            if (role.equalsIgnoreCase("candidate")) {
                registerAccount.setRoleId(2);
                accountDao.insertCandidateAccount(registerAccount);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register Account Successful!, back to Login page');");
                out.println("location='Login.jsp';");
                out.println("</script>");
            }

            if (role.equalsIgnoreCase("recruiter")) {
                registerAccount.setRoleId(1);
                Recruiter recruiter = new Recruiter();
                recruiter.setName(request.getParameter("companyName"));
                recruiter.setAddress(request.getParameter("address"));
                recruiter.setContacterName(request.getParameter("recruiterName"));
                accountDao.insertRecruitorAccount(registerAccount, recruiter);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register Account Successful!, back to Login page');");
                out.println("location='Login.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
