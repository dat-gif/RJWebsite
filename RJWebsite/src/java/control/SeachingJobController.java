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
    
    int pageNumber = 1;
    static int recordNumber = 8;

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
        request.setAttribute("txtSearch", "");
        
        IJob daoJob = new JobDAO();
        ICity daoCity = new CityDAO();
        try {
            List<Job> listJob = daoJob.getAllJob(pageNumber, recordNumber);
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
        String[] arrayString = skillSelect.split(",");
        System.out.println("Test: " + request.getParameter("test"));
        ArrayList<Job> listJob = new ArrayList<>();
        List<Skill> listSkill = daoJob.getAllSkill();
        List<City> listCity = daoCity.getAllCity();
        
        if (txtSearch.isEmpty()) {
            // if user not iput search text but still using filter
            if (!arrayString[0].equalsIgnoreCase("All") || !citySelect.equalsIgnoreCase("All")) {
                daoJob.createTempoTableSearchJobData();
                daoJob.insertJobByFilter(arrayString[0], citySelect);
                listJob = daoJob.getJobSearching(pageNumber, recordNumber);
            } else {
                // if user not input search text
                listJob = daoJob.getAllJob(pageNumber, recordNumber);
            }
        } else {
            // if user enters search text, (can choose filter or not)
            daoJob.createTempoTableSearchJobData();
            daoJob.insertJobByTextSearch(txtSearch.trim(), arrayString[0], citySelect);
            listJob = daoJob.getJobSearching(pageNumber, recordNumber);
        }
        request.setAttribute("test", "OK");
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("citySelect", citySelect);
        request.setAttribute("skillSelect", arrayString[1]);
        request.setAttribute("skillSelectId", skillSelect);
        request.setAttribute("listCity", listCity);
        request.setAttribute("listSkill", listSkill);
        request.setAttribute("listJob", listJob);
        request.getRequestDispatcher("SearchingJobPage.jsp").forward(request, response);
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
