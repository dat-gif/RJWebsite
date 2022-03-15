/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CandidateDAO;
import dao.idao.ICandidate;
import entity.Account;
import entity.Candidate;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import utils.AppUtils;
import utils.FileUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCVController", urlPatterns = {"/updatecv"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 2MB
        maxFileSize = 1024 * 1024 * 6, // (1024 bytes = 1 KB) x (1024 = 1 MB) x 6 = 6 MB 
        maxRequestSize = 1024 * 1024 * 10)//(1024 bytes = 1 KB) x (1024 = 1 MB) x 10 = 10 MB
public class UpdateCVController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        FileUtils fileUtils = new FileUtils();
        ICandidate iCandidate = new CandidateDAO();
        String cvLink = request.getParameter("cvLink");
        Account loginedUser = AppUtils.getLoginedUser(request.getSession());
        Candidate candidateInfo = iCandidate.getCandidateProfileById(loginedUser.getAccId());

        if (cvLink.isEmpty()) {
            //Set null will not change data in database
            cvLink = null;
        }
        try {
            for (Part part : request.getParts()) {
                String fileName = fileUtils.extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    // Get data file.
                    InputStream is = part.getInputStream();
                    // Encode file to base64.
                    long size = part.getSize();
                    String encoded = fileUtils.inputStreamToBase64(is, size);

                    iCandidate.editCandidateCVByCandidateId(candidateInfo.getCandIdateId(), encoded, cvLink);
                }
            }
            response.sendRedirect("candidateprofilecontroller");
        } catch (Exception e) {
            throw new Error();
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
