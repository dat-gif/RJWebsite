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
import entity.CandidateCV;
import entity.CandidateProject;
import entity.Certificate;
import entity.Education;
import entity.Experience;
import entity.Skill;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AppUtils;
import utils.FileUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CandidateDashboardProfileController", urlPatterns = {"/CandidateDashboardProfileController"})
public class CandidateDashboardProfileController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ICandidate iCandidate = new CandidateDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileByCandidateId(id);

        CandidateCV candidateCV = iCandidate.getCandidateCVByCandidateId(id);
        String cvImgDecode = "data:image/png;base64," + candidateCV.getOriginCv();
        List< Education> educations = iCandidate.getEducationByCandidateId(id);
        List<Skill> listSkill = iCandidate.getSkillByCandidateId(id);
        List<Certificate> certificates = iCandidate.getCertificateByCandidateId(id);
        List<Experience> experiences = iCandidate.getExperienceByCandidateId(id);
        List<CandidateProject> projects = iCandidate.getCandidateProjectByCandidateId(id);

        request.setAttribute("cvLink", candidateCV.getMediaCv());
        request.setAttribute("imgDecode", cvImgDecode);
        request.setAttribute("eduList", educations);
        request.setAttribute("skillList", listSkill);
        request.setAttribute("certList", certificates);
        request.setAttribute("expList", experiences);
        request.setAttribute("projectList", projects);
        request.setAttribute("candidateInfo", candidateInfo);

        //Seting modal display
        request.setAttribute("isShowEdu", "false");

        request.getRequestDispatcher("CandidateProfilePage.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
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
