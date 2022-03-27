/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.JobDAO;
import dao.SkillDAO;
import dao.idao.IJob;
import dao.idao.ISkill;
import entity.Skill;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.FileUtils;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddSkillController", urlPatterns = {"/AddSkillController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 2MB
        maxFileSize = 1024 * 1024 * 8, // (1024 bytes = 1 KB) x (1024 = 1 MB) x 7 = 8 MB 
        maxRequestSize = 1024 * 1024 * 15)//(1024 bytes = 1 KB) x (1024 = 1 MB) x 15 = 15 MB
public class AddSkillController extends HttpServlet {

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
        IJob jdao = new JobDAO();
        ISkill sDAO = new SkillDAO();
        Skill skill = new Skill();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        List<Skill> list = jdao.getAllSkill();
        String cName = name.toLowerCase();
        try {
            if (name.length() > 20) {
                request.setAttribute("error", "Require maximum of 20 characters");
                request.getRequestDispatcher("AddSkill.jsp").forward(request, response);
            } else if (sDAO.checkExistedSkillName(cName)) {
                request.setAttribute("error", "Skill name is already exist");
                request.getRequestDispatcher("AddSkill.jsp").forward(request, response);
            } else if (name.isEmpty()) {
                request.setAttribute("error", "Please fill out this field");
                request.getRequestDispatcher("AddSkill.jsp").forward(request, response);
            } else if (description.isEmpty()) {
                request.setAttribute("error2", "Please fill out this field");
                request.getRequestDispatcher("AddSkill.jsp").forward(request, response);
            } else {
                FileUtils fileUtils = new FileUtils();
                for (Part part : request.getParts()) {
                    String fileName = fileUtils.extractFileName(part);
                    if (fileName != null && fileName.length() > 0) {
                        // Get data file.
                        InputStream is = part.getInputStream();
                        // Encode file to base64.
                        long size = part.getSize();
                        String encoded = fileUtils.inputStreamToBase64(is, size);
                        skill.setIconBase64(encoded);
                    }
                }
                skill.setName(name);
                skill.setDepscription(description);
                sDAO.insertSkill(skill);
                request.getRequestDispatcher("SkillDashboard").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("error" + e);
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
