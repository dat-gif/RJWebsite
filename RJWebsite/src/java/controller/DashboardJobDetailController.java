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
        String jobId = request.getParameter("jobId");
        setJobDetailId(jobId);

        try {
            IAccount iAccount = new AccountDAO();
            Job job = daoJob.getJobById(Integer.parseInt(jobId));
            Account userAccount = AppUtils.getLoginedUser(request.getSession());
            if (userAccount == null) {
                request.setAttribute("jobApplyButton", "Apply");
            } else {
                Candidate candidate = iAccount.getCandidateInfoByAccountId(userAccount.getAccId());
                if (daoJob.checkJobBeenApply(Integer.parseInt(jobId), candidate.getCandIdateId())) {
                    request.setAttribute("jobApplyButton", "Apply");
                } else {
                    request.setAttribute("jobApplyButton", "Withdraw");
                }
            }
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
            request.setAttribute("skill", job.getSkillListName());
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
        String jobId = getJobDetailId();
        IJob daoJob = new JobDAO();
        IAccount iAccount = new AccountDAO();
        try {
            //Get account in session
            Account userAccount = AppUtils.getLoginedUser(request.getSession());
            if (userAccount == null) {
                response.sendRedirect("login");
            } else {
                Candidate candidate = iAccount.getCandidateInfoByAccountId(AppUtils.getLoginedUser(request.getSession()).getAccId());
                // Thêm check cv trống.
                if (daoJob.checkJobBeenApply(Integer.parseInt(jobId), candidate.getCandIdateId())) {
                    daoJob.createRequestApplyJob(Integer.parseInt(jobId), candidate.getCandIdateId());
                } else {
                    daoJob.deleteRequestApplyJob(Integer.parseInt(jobId), candidate.getCandIdateId());
                }
            }
            response.sendRedirect("jobdetail?jobId=" + jobId);
        } catch (Exception e) {
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
