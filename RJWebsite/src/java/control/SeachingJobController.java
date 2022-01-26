/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import IDao.ICity;
import IDao.IJob;
import dao.CityDAO;
import dao.DAO;
import dao.JobDAO;
import entity.City;
import entity.Job;
import entity.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SeachingJobController extends HttpServlet {

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
        String txtSearch = request.getParameter("txt");
        IJob daoJob = new JobDAO();
        ICity daoCity = new CityDAO();
        try {
            List<Job> listJob = daoJob.getJobLandingPage();
            List<Skill> listSkill = daoJob.getAllSkill();
            List<City> listCity = daoCity.getAllCity();
            request.setAttribute("listCity", listCity);
            request.setAttribute("listSkill", listSkill);
            request.setAttribute("listJob", listJob);
            request.getRequestDispatcher("SearchingJobPage.jsp").forward(request, response);
        } catch (Exception e) {
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
        request.setCharacterEncoding("UTF-8");
        IJob daoJob = new JobDAO();
        ICity daoCity = new CityDAO();
        String txtSearch = request.getParameter("txtSearch");
        String citySelect = request.getParameter("citySelect");
        String skillSelect = request.getParameter("skillSelect");
        ArrayList<Job> listJob = new ArrayList<>();
        List<Skill> listSkill = daoJob.getAllSkill();
        List<City> listCity = daoCity.getAllCity();
        request.setAttribute("listCity", listCity);
        request.setAttribute("listSkill", listSkill);
        if (txtSearch.isEmpty()) {
            if (!skillSelect.equalsIgnoreCase("All") || !citySelect.equalsIgnoreCase("All")) {
                listJob = daoJob.getJobBySkillAndCity(skillSelect, citySelect);
            } else {
                listJob = daoJob.getAllJob();
            }

        } else {

        }
        request.setAttribute("listJob", listJob);
        request.getRequestDispatcher("SearchingJobPage.jsp").forward(request, response);
        System.out.println(txtSearch + ".." + citySelect + ".." + skillSelect);
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
