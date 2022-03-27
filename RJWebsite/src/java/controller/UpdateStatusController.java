/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CandidateDAO;
import dao.JobDAO;
import dao.RecruiterDAO;
import dao.SkillDAO;
import dao.idao.ICandidate;
import dao.idao.IJob;
import dao.idao.IRecruiter;
import dao.idao.ISkill;
import entity.Candidate;
import entity.Job;
import entity.Recruiter;
import entity.Skill;
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
@WebServlet(name = "UpdateStatusController", urlPatterns = {"/UpdateStatusController"})
public class UpdateStatusController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        if (action.equals("skill")) {
            ISkill sdao = new SkillDAO();
            Skill skill = sdao.getSkillById(id);
            boolean status = skill.isStatus();
            sdao.updateStatus(id, status);
            request.getRequestDispatcher("SkillDashboardSearchingController").forward(request, response);
        } else if (action.equals("candidate")) {
            ICandidate cdao = new CandidateDAO();
            Candidate candidate = cdao.getCandidateById(id);
            boolean status = candidate.isStatus();
            cdao.updateStatus(id, status);
            request.getRequestDispatcher("CandidateDashboardSearchingController").forward(request, response);
        } else if (action.equals("recruiter")) {
            IRecruiter rdao = new RecruiterDAO();
            Recruiter recruiter = rdao.getRecruiterByIdForStatus(id);
            boolean status = recruiter.isStatus();
            rdao.updateStatus(id, status);
            request.getRequestDispatcher("RecruiterDashboardSearchingController").forward(request, response);
        } else {
            IJob jdao = new JobDAO();
            Job job = jdao.getJobById(id);
            boolean status = job.isStatus();
            jdao.updateStatus(id, status);
            request.getRequestDispatcher("DashboardSearchingController").forward(request, response);
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
