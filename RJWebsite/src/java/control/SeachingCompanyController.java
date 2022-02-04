/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import IDao.ICity;
import IDao.IRecruiter;
import dao.CityDAO;
import dao.JobDAO;
import dao.RecruiterDAO;
import entity.City;
import entity.Job;
import entity.Recruiter;
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
public class SeachingCompanyController extends HttpServlet {

    int pageNumber = 1;
    static int recordNumber = 2;
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
        IRecruiter daoRecruiter = new RecruiterDAO();
        ICity daoCity = new CityDAO();
        try {
            //Get Cookie
            Cookie[] cookies = request.getCookies();
            Map<String, String> cookieMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                String decodeValue = java.net.URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
                cookieMap.put(cookie.getName(), decodeValue);
            }
            //Get current page number
            String spageNumber = request.getParameter("page");
            if (spageNumber != null && !spageNumber.isEmpty()) {
                pageNumber = Integer.parseInt(spageNumber);
            } else if (spageNumber == null) {
                pageNumber = 1;
            }
            String cookieSearch = cookieMap.get("txtSearch");
            String cookieCity = cookieMap.get("citySelect");
            ArrayList<Recruiter> listJob = new ArrayList<>();

            List<City> listCity = daoCity.getAllCity();
            request.getRequestDispatcher("SearchingCompanyPage.jsp").forward(request, response);
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
