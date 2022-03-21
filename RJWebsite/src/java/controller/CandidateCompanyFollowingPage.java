/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.CandidateApplyJobPageController.recordNumber;
import dao.RecruiterDAO;
import dao.idao.IRecruiter;
import entity.Account;
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
public class CandidateCompanyFollowingPage extends HttpServlet {

    int pageNumber = 1;
    static int recordNumber = 8;
    int totalPage = 8;

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

            IRecruiter iRecruiter = new RecruiterDAO();
            Account loginedUser = AppUtils.getLoginedUser(request.getSession());
            request.setAttribute("account", loginedUser);
            String currentPage = request.getParameter("page");
            if (currentPage != null) {
                pageNumber = Integer.parseInt(currentPage);
            }
            int totalRecordNumber = iRecruiter.getCandidateTotalFollowingCompany(loginedUser.getAccId());
            totalPage = (int) Math.ceil((double) totalRecordNumber / recordNumber);

            ArrayList<Recruiter> listRecruiter = iRecruiter.getCandidateFollowingRecruiterList(loginedUser.getAccId(), pageNumber, recordNumber);
            request.setAttribute("listRecruiter", listRecruiter);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("CandidateFollowingCompanyPage.jsp").forward(request, response);
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
