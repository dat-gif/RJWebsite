/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.JobDAO;
import dao.idao.IAccount;
import dao.idao.IJob;
import entity.Account;
import entity.Candidate;
import entity.Job;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AppUtils;

/**
 *
 * @author admin
 */
@WebServlet(name = "DashboardJobDetailController", urlPatterns = {"/DashboardJobDetailController"})
public class DashboardJobDetailController extends HttpServlet {

    private String jobDetailId;

    public String getJobDetailId() {
        return jobDetailId;
    }

    public void setJobDetailId(String jobDetailId) {
        this.jobDetailId = jobDetailId;
    }

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
        IJob daoJob = new JobDAO();
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        try {
            Job job = daoJob.getJobById(jobId);
            request.setAttribute("recruiterId", job.getRecruiter().getRecruiterId());
            request.setAttribute("avatar", job.getRecruiter().getAvatar());
            request.setAttribute("jobTile", job.getTitle());
            request.setAttribute("jobCompany", job.getRecruiter().getName());
            request.setAttribute("endDate", job.getHireDate());
            request.setAttribute("salary", job.getSalaryRange());
            request.setAttribute("role", job.getRole());
            request.setAttribute("quantity", job.getQuantity());
            request.setAttribute("experience", job.getExperience());
            request.setAttribute("location", job.getLocation());
            request.setAttribute("description", job.getDescription());
            request.setAttribute("skill", daoJob.getSkillNameByJobId(jobId));
            request.setAttribute("recruiterId", job.getRecruiter().getRecruiterId());
            request.getRequestDispatcher("DashboardJobDetail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("bug get detail " + e);
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
