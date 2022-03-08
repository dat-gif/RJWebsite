/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.JobDAO;
import dao.SkillDAO;
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
@WebServlet(name = "EditSkillController", urlPatterns = {"/EditSkillController"})
public class EditSkillController extends HttpServlet {

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
        String op = request.getParameter("op");
        String name = request.getParameter("name");
        String uid = request.getParameter("id");
        int id = Integer.parseInt(request.getParameter("id"));
        SkillDAO sdao = new SkillDAO();
        Skill skill = sdao.getSkillById(id);
        if ("Update".equals(op)) {
            try {
                int Id = Integer.parseInt(request.getParameter("id"));
                String Name = request.getParameter("name");
                String Description = request.getParameter("description");
                Skill s = new Skill();
                s.setId(Id);
                s.setName(Name);
                s.setDepscription(Description);
                sdao.updateSkill(s);
            } catch (Exception e) {
                System.out.println("error" + e);
            }
            JobDAO jdao = new JobDAO();
            request.setAttribute("skills", jdao.getAllSkill());
            request.getRequestDispatcher("SkillDashboard").forward(request, response);
        } else {

            request.setAttribute("skill", skill);
            request.getRequestDispatcher("EditSkill.jsp").forward(request, response);
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
