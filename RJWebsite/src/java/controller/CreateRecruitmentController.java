/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.JobDAO;
import dao.idao.IJob;
import entity.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USE
 */
public class CreateRecruitmentController extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");
            //khoi dao obj dao
            IJob ijob = new JobDAO();

            //lay ra danh sach cac skill va gui attribute len dropdown list tren trang jsp
            List<Skill> listSkill = ijob.getAllSkill();
            request.setAttribute("listSkill", listSkill);

            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("CreateRecruitment.jsp").forward(request, response);
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
        try {
            String msg = "";
            //khoi dao obj dao
            IJob ijob = new JobDAO();

            //nhan lai value tu cac field tren form cua trang jsp
            int recruiterId = 1;
            String jobName = request.getParameter("jobName");
            String salary = request.getParameter("salary");
            String quantity = request.getParameter("quantity");
            String role = request.getParameter("role");
            String experience = request.getParameter("experience");
            String hireDate = request.getParameter("hireDate");
            String location = request.getParameter("location");
            String description = request.getParameter("description");

            jobName = jobName.replaceAll("\\s\\s+", " ").trim();
            salary = salary.replaceAll("\\s\\s+", " ").trim();
            quantity = quantity.replaceAll("\\s\\s+", " ").trim();
            role = role.replaceAll("\\s\\s+", " ").trim();
            experience = experience.replaceAll("\\s\\s+", " ").trim();
            location = location.replaceAll("\\s\\s+", " ").trim();
            description = description.replaceAll("\\s\\s+", " ").trim();

            if (jobName.equals("") || salary.equals("") || quantity.equals("") || role.equals("") || experience.equals("") || location.equals("") || description.equals("")) {
                request.setAttribute("error", "You have to fill something in the field!!!!");
                //chuyen huong den trang jsp dich
                request.getRequestDispatcher("CreateRecruitment.jsp").forward(request, response);
            }

            if (!jobName.equals("") || !salary.equals("") || !quantity.equals("") || !role.equals("") || !experience.equals("") || !location.equals("") || !description.equals("")) {
                //insert vao db voi data nhan lai o tren
                int total = ijob.insertRecruitment(recruiterId, role, description, salary, quantity, role, experience, location, hireDate);

                //insert vao job_skill
                String skillPicked = request.getParameter("skill");
                int skillId = Integer.parseInt(skillPicked);
                int jobId = ijob.getLatestInsertedJobId();
                ijob.insertJobSkill(jobId, skillId);

                //neu insert thanh cong hoac that bai thi hien thi message
                if (total > 0) {
                    msg = "Create Job Successfully";
                } else {
                    msg = "Create Job Failed";
                }
                request.setAttribute("msg", msg);
            }
            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("CreateRecruitment.jsp").forward(request, response);
        } catch (Exception e) {
            //neu co loi thi chuyen huong den trang bao loi
            request.setAttribute("error", e);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
