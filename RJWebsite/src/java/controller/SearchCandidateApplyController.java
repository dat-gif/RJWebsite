/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CandidateDAO;
import dao.JobDAO;
import dao.idao.ICandidate;
import dao.idao.IJob;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchCandidateApplyController", urlPatterns = {"/SearchCandidateApplyController"})
public class SearchCandidateApplyController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int index;
            String indexString = request.getParameter("index");
            if (indexString.isEmpty()) {
                index = 1;
            } else {
                index = Integer.parseInt(indexString);
            }
            String txtSearch = request.getParameter("txtSearch");
            if (txtSearch.trim().isEmpty()) {
                txtSearch = "";
            }
            IJob cdao = new JobDAO();
            int count = cdao.countTotalApplySearchRow(id, txtSearch);
            int pageSize = 6;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            request.setAttribute("job_id", id);
            request.setAttribute("index", index);
            request.setAttribute("end", endPage);
            request.setAttribute("list", cdao.getCandidateApplyJobSearch(id, txtSearch, index, pageSize));
            request.setAttribute("save", txtSearch);
            request.getRequestDispatcher("DashboardCandidateApplyJobPage.jsp").forward(request, response);
        } catch (Exception e) {
        }
        
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
        processRequest(request, response);
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
