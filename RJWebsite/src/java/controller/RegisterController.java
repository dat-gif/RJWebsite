/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.CityDAO;
import dao.idao.IAccount;
import dao.idao.ICity;
import entity.Account;
import entity.City;
import entity.Recruiter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validation.Validation;

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
        request.setCharacterEncoding("UTF-8");
        try {
            ICity daoCity = new CityDAO();
            ArrayList<City> listCity = (ArrayList<City>) daoCity.getAllCity();
            listCity.remove(0);
            String citySelect = request.getParameter("citySelect");
            request.setAttribute("listCity", listCity);
            request.setAttribute("citySelect", citySelect);
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
        request.setCharacterEncoding("UTF-8");
        IAccount accountDao = new AccountDAO();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        String companyName = request.getParameter("companyName");
        String address = request.getParameter("address");
        String recruiterName = request.getParameter("recruiterName");
        String citySelect = request.getParameter("citySelect");
        Account registerAccount = new Account();
        ICity daoCity = new CityDAO();
        ArrayList<City> listCity = (ArrayList<City>) daoCity.getAllCity();
        listCity.remove(0);
        request.setAttribute("listCity", listCity);
        request.setAttribute("citySelect", citySelect);
        try {
            if (!checkAccountBasicInfo(request, email, phone, password, confirmPassword, citySelect)) {
                // If account basic info wrong format, redirect to Register Page
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("password", password);
                request.setAttribute("companyName", companyName);
                request.setAttribute("address", address);

                request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
            } else {
                registerAccount.setEmail(email.trim());
                registerAccount.setPassword(password.trim());
                registerAccount.setPhone(phone.trim());
                // Add candidate account
                if (role.equalsIgnoreCase("candidate")) {
                    registerAccount.setRoleId(2);
                    accountDao.insertCandidateAccount(registerAccount, citySelect);
                    try {
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Register Account Successful!, back to Login page');");
                        out.println("location='Login.jsp';");
                        out.println("</script>");
                        out.flush();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                // Add recruiter account
                if (role.equalsIgnoreCase("recruiter") && checkAccountAdditionalInformation(request, companyName, address, recruiterName)) {
                    registerAccount.setRoleId(1);
                    Recruiter recruiter = new Recruiter();
                    recruiter.setName(companyName);
                    recruiter.setAddress(address);
                    recruiter.setContacterName(recruiterName);
                    recruiter.setCity(citySelect);
                    accountDao.insertRecruitorAccount(registerAccount, recruiter);
                    try {
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Register Account Successful!, back to Login page');");
                        out.println("location='Login.jsp';");
                        out.println("</script>");
                        out.flush();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {

                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("password", password);
                    request.setAttribute("companyName", companyName);
                    request.setAttribute("address", address);

                    request.setAttribute("recruiterName", recruiterName);
                    request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
                }
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

    /**
     * Check account basic info: email(user name), phone number, password,
     * confirmPassword.
     *
     * @param request
     * @param email
     * @param phoneNumber
     * @param password
     * @param confirmPassword
     * @return
     */
    public boolean checkAccountBasicInfo(HttpServletRequest request, String email, String phoneNumber, String password, String confirmPassword, String city) {
        IAccount accountDao = new AccountDAO();
        Validation validation = new Validation();
        boolean isAccountBasicInforCorrect = true;
        if (!accountDao.checkExistAccountEmail(email)) {
            request.setAttribute("errorEmailMesg", "Email already in use");
            isAccountBasicInforCorrect = false;
        }
        if (!validation.emailValidation(email)) {
            request.setAttribute("errorEmailMesg", "Email is incorrect format");
            isAccountBasicInforCorrect = false;
        }
        if (!validation.phoneNumberValidation(phoneNumber)) {
            request.setAttribute("phoneErrorMesg", "Phone is incorrect format.");
            isAccountBasicInforCorrect = false;
        }
        if (city.isEmpty()) {
            request.setAttribute("cityError", "Please choose city.");
            isAccountBasicInforCorrect = false;
        }
        if (!validation.passwordValidation(password)) {
            request.setAttribute("passErrorMesg", "Password must contain character, number, at least 8 characters, and no white space.");
            isAccountBasicInforCorrect = false;
        }

        if (!validation.checkConfirmPassword(password, confirmPassword)) {
            request.setAttribute("confirmPassErrorMesg", "Confirm password must match password.");
            isAccountBasicInforCorrect = false;
        }
        return isAccountBasicInforCorrect;
    }

    public boolean checkAccountAdditionalInformation(HttpServletRequest request, String companyName, String address, String recruiterName) {
        boolean isCorrect = true;
        if (companyName.isEmpty()) {
            request.setAttribute("companyNameErrorMesg", "Company name must not empty");
            isCorrect = false;
        }
        if (address.isEmpty()) {
            request.setAttribute("addressErrorMesg", "Address must not empty");
            isCorrect = false;
        }
        if (companyName.isEmpty()) {
            request.setAttribute("recruiterNameErrorMesg", "Recruiter name must not empty");
            isCorrect = false;
        }
        return isCorrect;
    }
}
