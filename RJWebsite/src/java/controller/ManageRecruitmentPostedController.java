/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.JobDAO;
import dao.RecruiterDAO;
import dao.idao.IJob;
import dao.idao.IRecruiter;
import entity.Job;
import entity.Recruiter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USE
 */
public class ManageRecruitmentPostedController extends HttpServlet {

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
//            String id = request.getParameter("recruiterId");
//            int recruiterId = Integer.parseInt(id);
            int recruiterId = 1;

            String index = request.getParameter("index");

            //neu index null thi trang khoi tao se là trang dau tien
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);

            //get ra cac record va phan trang
            IJob iJob = new JobDAO();
            List<Job> listJob = iJob.getJobByRecruiterId(recruiterId, indexPage);

            //get ra tong so trang theo dieu kien search
            int maxPage = iJob.getNumberPageManageRecruitment(recruiterId);

            //set cac attribute len trang jsp
            request.setAttribute("listJob", listJob);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("maxPage", maxPage);

            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("ManageRecruitmentPosted.jsp").forward(request, response);
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
