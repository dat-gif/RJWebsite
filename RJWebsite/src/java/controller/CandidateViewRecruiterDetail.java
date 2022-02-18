/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.RecruiterDAO;
import dao.idao.IAccount;
import dao.idao.IRecruiter;
import entity.Account;
import entity.Job;
import entity.Recruiter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AppUtils;

/**
 *
 * @author Admin
 */
public class CandidateViewRecruiterDetail extends HttpServlet {

    int pageNumber = 1;
    static int recordNumber = 8;
    int totalPage = 10;

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
        String recruiterId = request.getParameter("recruiterId");
        IRecruiter iRecruiter = new RecruiterDAO();
        int totalRecordNumber = 1;
        String currentPage = request.getParameter("page");
        try {

            Recruiter recruiter = iRecruiter.getRecruiterById(Integer.parseInt(recruiterId));
            IAccount iAccount = new AccountDAO();
            //Check if user are login
            Account userAccount = AppUtils.getLoginedUser(request.getSession());
            if (userAccount == null) {
                request.setAttribute("followButton", "Follow");
            }
            //Set current page, default = 1
            if (currentPage != null) {
                pageNumber = Integer.parseInt(currentPage);
            }

            ArrayList<Job> jobs = iRecruiter.getRecruimnetByRecruiterIdPagening(recruiterId, pageNumber, recordNumber);
            totalRecordNumber = iRecruiter.getTotalRecruimnetByRecruiterId(recruiterId);
            totalPage = (int) Math.ceil((double) totalRecordNumber / recordNumber);
            request.setAttribute("recruiterId", recruiter.getRecruiterId());
            request.setAttribute("description", recruiter.getDescription());
            request.setAttribute("avatar", recruiter.getAvatar());
            request.setAttribute("banner", recruiter.getBanner());
            request.setAttribute("name", recruiter.getName());
            request.setAttribute("website", recruiter.getWebsite());
            request.setAttribute("qEmployee", recruiter.getEmployeeQuantity());
            request.setAttribute("address", recruiter.getAddress());
            request.setAttribute("phone", recruiter.getPhone());
            request.setAttribute("jobTotalPage", totalPage);
            request.setAttribute("jobList", jobs);
        } catch (Exception e) {
        }

        request.getRequestDispatcher("CandidateViewRecruiterDetail.jsp").forward(request, response);
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
