/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.idao.ICity;
import dao.idao.IJob;
import dao.CityDAO;
import dao.JobDAO;
import entity.City;
import entity.Job;
import entity.Skill;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
    int totalPage = 8;

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

        request.setCharacterEncoding("UTF-8");

        IJob daoJob = new JobDAO();
        ICity daoCity = new CityDAO();
        int totalRecordNumber = 1;
        try {
            //Get Cookie
            Cookie[] cookies = request.getCookies();
            Map<String, String> cookieMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                String decodeValue = java.net.URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
                cookieMap.put(cookie.getName(), decodeValue);
            }
            String spageNumber = request.getParameter("page");
            if (spageNumber != null && !spageNumber.isEmpty()) {
                pageNumber = Integer.parseInt(spageNumber);
            } else if (spageNumber == null) {
                pageNumber = 1;
            }

            String cookieSearch = cookieMap.get("txtSearch");
            String cookieSkill = cookieMap.get("skillSelect");
            String cookieCity = cookieMap.get("citySelect");

            ArrayList<Job> listJob = new ArrayList<>();
            List<Skill> listSkill = daoJob.getAllSkill();
            List<City> listCity = daoCity.getAllCity();

            //Check if cookie null
            if (cookieSearch == null) {
                cookieSearch = "";
            }
            if (cookieSkill == null) {
                cookieSkill = "All_All Skill";
            }
            if (cookieCity == null || cookieCity.isEmpty()) {
                cookieCity = "All";
            }
            if (cookieSearch.isEmpty() || cookieSearch.equalsIgnoreCase("All")) {
                if (!cookieSkill.split("_")[0].equalsIgnoreCase("All") || !cookieCity.equalsIgnoreCase("All")) {
                    // Get data in tempo search table
                    listJob = daoJob.getJobSearching(pageNumber, recordNumber);
                    totalRecordNumber = daoJob.getTotalTempJobRow();
                } else {
                    // If user not input search text or filter data
                    // Get data direct in job table
                    listJob = daoJob.getAllJob(pageNumber, recordNumber);
                    totalRecordNumber = daoJob.getTotalJobRow();
                }
            } else {
                // if user enters search text, (can choose filter or not)
                listJob = daoJob.getJobSearching(pageNumber, recordNumber);
                totalRecordNumber = daoJob.getTotalTempJobRow();
            }

//Calculate total pagning
            totalPage = (int) Math.ceil((double) totalRecordNumber / recordNumber);

            request.setAttribute("page", pageNumber);
            request.setAttribute("txtSearch", cookieSearch);
            request.setAttribute("citySelect", cookieCity);
            request.setAttribute("skillSelectId", cookieSkill);
            request.setAttribute("skillSelect", cookieSkill.split("_")[1]);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("listCity", listCity);
            request.setAttribute("listSkill", listSkill);
            request.setAttribute("listJob", listJob);

            request.getRequestDispatcher("SearchingJobPage.jsp").forward(request, response);

        } catch (Exception e) {
        }
    }

    private void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) throws IOException {
        Cookie cookie = new Cookie(nom, URLEncoder.encode(valeur, "UTF-8"));
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        try {

            IJob daoJob = new JobDAO();
            ICity daoCity = new CityDAO();
//Get cookies
            Cookie[] cookies = request.getCookies();
            Map<String, String> cookieMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                String decodeValue = java.net.URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
                cookieMap.put(cookie.getName(), decodeValue);
            }
            String cookieSearch = cookieMap.get("txtSearch");
            String cookieSkill = cookieMap.get("skillSelect");
            String cookieCity = cookieMap.get("citySelect");

            //Request parameter
            String txtSearch = request.getParameter("txtSearch");
            String citySelect = request.getParameter("citySelect");
            String skillSelect = request.getParameter("skillSelect");

            //Check parameter null
            if (txtSearch == null) {
                txtSearch = "";
            }
            if (skillSelect == null) {
                skillSelect = "All_All Skill";
            }
            String skillSelectId = skillSelect.split("_")[0];
            if (citySelect == null || citySelect.isEmpty()) {
                citySelect = "All";
            }
            //Logic create search table 
            if (txtSearch.isEmpty() || txtSearch.equalsIgnoreCase("All")) {
                if (!skillSelectId.equalsIgnoreCase("All") || !citySelect.equalsIgnoreCase("All")) {
                    // create tempo search table and insert data
                    daoJob.createTempoTableSearchJobData();
                    daoJob.insertJobByFilter(skillSelectId, citySelect);

                } else {
                    // get data direct in job table
                }
            } else {
                // if user enters search text, (can choose filter or not)
                // create tempo search table and insert data
                daoJob.createTempoTableSearchJobData();
                daoJob.insertJobByTextSearch(txtSearch.trim(), skillSelectId, citySelect);
            }

//Set user input to cookie
            if (cookieSearch == null || !cookieSearch.equalsIgnoreCase(txtSearch)) {
                setCookie(response, "txtSearch", txtSearch, -1);
            }
            if (cookieSkill == null || !cookieSkill.equalsIgnoreCase(skillSelect)) {
                setCookie(response, "skillSelect", skillSelect, -1);
            }
            if (cookieCity == null || !cookieCity.equalsIgnoreCase(citySelect)) {
                setCookie(response, "citySelect", citySelect, -1);
            }
            response.sendRedirect("seachingjob");
        } catch (Exception e) {
            System.out.println(e);
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
