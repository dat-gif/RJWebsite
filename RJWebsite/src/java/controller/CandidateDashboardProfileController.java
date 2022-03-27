/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CandidateDAO;
import dao.CityDAO;
import dao.idao.ICandidate;
import dao.idao.ICity;
import entity.Account;
import entity.Candidate;
import entity.CandidateCV;
import entity.CandidateProject;
import entity.Certificate;
import entity.City;
import entity.Education;
import entity.Experience;
import entity.Skill;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
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
import validation.Validation;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CandidateDashboardProfileController", urlPatterns = {"/CandidateDashboardProfileController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 6, // (1024 bytes = 1 KB) x (1024 = 1 MB) x 5 = 5 MB 
        maxRequestSize = 1024 * 1024 * 6)//(1024 bytes = 1 KB) x (1024 = 1 MB) x 5 = 5 MB
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
        ICity daoCity = new CityDAO();
        List<City> listCity = daoCity.getAllCity();
        listCity.remove(0);
        int id = Integer.parseInt(request.getParameter("id"));
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileByCandidateId(id);
        Candidate candidateTemp = iCandidate.getCandidateProfileByCandidateId(id);
        String avatarBase64 = candidateInfo.getAvatar();
        String bannerBase64 = candidateInfo.getBanner();

        CandidateCV candidateCV = iCandidate.getCandidateCVByCandidateId(candidateInfo.getCandIdateId());
        String cvImgDecode = "";
        String cvLink = "";
        if (candidateCV != null && !candidateCV.getOriginCv().isEmpty()) {
            cvImgDecode = candidateCV.getOriginCv();
        }
        if (candidateCV != null && !candidateCV.getMediaCv().isEmpty()) {
            cvLink = candidateCV.getMediaCv();
        }

        List< Education> educations = iCandidate.getEducationByCandidateId(candidateInfo.getCandIdateId());
        List<Skill> listSkill = iCandidate.getSkillByCandidateId(candidateInfo.getCandIdateId());
        List<Certificate> certificates = iCandidate.getCertificateByCandidateId(candidateInfo.getCandIdateId());
        List<Experience> experiences = iCandidate.getExperienceByCandidateId(candidateInfo.getCandIdateId());
        List<CandidateProject> projects = iCandidate.getCandidateProjectByCandidateId(candidateInfo.getCandIdateId());

        request.setAttribute("banner", bannerBase64);
        request.setAttribute("avatar", avatarBase64);
        request.setAttribute("listCity", listCity);
        request.setAttribute("citySelect", candidateInfo.getCity());
        request.setAttribute("cvLink", cvLink);
        request.setAttribute("imgDecode", cvImgDecode);
        request.setAttribute("eduList", educations);
        request.setAttribute("skillList", listSkill);
        request.setAttribute("certList", certificates);
        request.setAttribute("expList", experiences);
        request.setAttribute("projectList", projects);
        request.setAttribute("candidateInfo", candidateInfo);
        request.setAttribute("candidateTemp", candidateTemp);

        
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

    }
}
