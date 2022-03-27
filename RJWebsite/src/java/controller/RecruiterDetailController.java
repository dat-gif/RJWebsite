/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CandidateDAO;
import dao.RecruiterDAO;
import dao.idao.ICandidate;
import dao.idao.IRecruiter;
import entity.Candidate;
import entity.Recruiter;
import entity.Skill;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USE
 */
public class RecruiterDetailController extends HttpServlet {

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
        try {
            //lay ve recruiterId tu trang RecruitmentPosted.jsp
            int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
            IRecruiter iRecruiter = new RecruiterDAO();
            ICandidate iCandidate = new CandidateDAO();

            //lay ra danh sach cac skill va recruiter theo id nhan lai
            List<Skill> listSkill = iRecruiter.getRecruiterSkill(recruiterId);
            Recruiter recruiter = iRecruiter.getRecruiterById(recruiterId);
            List<Candidate> listCandidate = iCandidate.getCandidateListByRecruiterId(recruiterId);

            //set cac attribute len trang jsp
            request.setAttribute("recruiter", recruiter);
            request.setAttribute("listSkill", listSkill);
            request.setAttribute("listCandidate", listCandidate);

            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("RecruiterDetail.jsp").forward(request, response);
        } catch (Exception e) {
            //neu co loi thi chuyen huong den trang bao loi
            request.setAttribute("error", e);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
