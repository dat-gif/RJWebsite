package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.AccountDAO;
import dao.idao.IJob;
import dao.JobDAO;
import dao.idao.IAccount;
import entity.Account;
import entity.Candidate;
import entity.Job;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.ir.ContinueNode;
import utils.AppUtils;

/**
 *
 * @author Admin
 */
public class JobDetailController extends HttpServlet {

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
            request.getRequestDispatcher("ViewJobDetailPage.jsp").forward(request, response);
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
