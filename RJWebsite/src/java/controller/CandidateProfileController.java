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
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.AppUtils;
import utils.FileUtils;
import validation.Validation;

/**
 *
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 2MB
        maxFileSize = 1024 * 1024 * 8, // (1024 bytes = 1 KB) x (1024 = 1 MB) x 7 = 8 MB 
        maxRequestSize = 1024 * 1024 * 15)//(1024 bytes = 1 KB) x (1024 = 1 MB) x 15 = 15 MB
public class CandidateProfileController extends HttpServlet {

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
        Account loginedUser = AppUtils.getLoginedUser(request.getSession());
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileById(loginedUser.getAccId());
        Candidate candidateTemp = iCandidate.getCandidateProfileById(loginedUser.getAccId());

        CandidateCV candidateCV = iCandidate.getCandidateCVByCandidateId(candidateInfo.getCandIdateId());
        String cvImgDecode = "data:image/png;base64," + candidateCV.getOriginCv();
        List< Education> educations = iCandidate.getEducationByCandidateId(candidateInfo.getCandIdateId());
        List<Skill> listSkill = iCandidate.getSkillByCandidateId(candidateInfo.getCandIdateId());
        List<Certificate> certificates = iCandidate.getCertificateByCandidateId(candidateInfo.getCandIdateId());
        List<Experience> experiences = iCandidate.getExperienceByCandidateId(candidateInfo.getCandIdateId());
        List<CandidateProject> projects = iCandidate.getCandidateProjectByCandidateId(candidateInfo.getCandIdateId());

        request.setAttribute("cvLink", candidateCV.getMediaCv());
        request.setAttribute("imgDecode", cvImgDecode);
        request.setAttribute("eduList", educations);
        request.setAttribute("skillList", listSkill);
        request.setAttribute("certList", certificates);
        request.setAttribute("expList", experiences);
        request.setAttribute("projectList", projects);
        request.setAttribute("candidateInfo", candidateInfo);
        request.setAttribute("candidateTemp", candidateTemp);

        //Seting modal display
        request.setAttribute("isShowEdu", "false");
        request.setAttribute("isPersonalModalShow", "false");
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
        request.setCharacterEncoding("UTF-8");
        ICandidate iCandidate = new CandidateDAO();
        Account loginedUser = AppUtils.getLoginedUser(request.getSession());
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileById(loginedUser.getAccId());

        CandidateCV candidateCV = iCandidate.getCandidateCVByCandidateId(candidateInfo.getCandIdateId());
        String cvImgDecode = "data:image/png;base64," + candidateCV.getOriginCv();
        List< Education> educations = iCandidate.getEducationByCandidateId(candidateInfo.getCandIdateId());
        List<Skill> listSkill = iCandidate.getSkillByCandidateId(candidateInfo.getCandIdateId());
        List<Certificate> certificates = iCandidate.getCertificateByCandidateId(candidateInfo.getCandIdateId());
        List<Experience> experiences = iCandidate.getExperienceByCandidateId(candidateInfo.getCandIdateId());
        List<CandidateProject> projects = iCandidate.getCandidateProjectByCandidateId(candidateInfo.getCandIdateId());

        request.setAttribute("cvLink", candidateCV.getMediaCv());
        request.setAttribute("imgDecode", cvImgDecode);
        request.setAttribute("eduList", educations);
        request.setAttribute("skillList", listSkill);
        request.setAttribute("certList", certificates);
        request.setAttribute("expList", experiences);
        request.setAttribute("projectList", projects);
        request.setAttribute("candidateInfo", candidateInfo);

        // Modal logic
        String action = request.getParameter("action");
        switch (action) {
            case "personalInfo":
        //Personal modal
        try {
                Part fileWallpaper = request.getPart("fileWallpaper");
                Part fileAvatar = request.getPart("fileAvatar");

                String firstName = request.getParameter("fisrtname").trim();
                String lastName = request.getParameter("lastname").trim();
                String phoneNumber = request.getParameter("phoneNumber").trim();
                String dob = request.getParameter("dob");
                String address = request.getParameter("address").trim();
                String fileWallpaperName = fileUtils.extractFileName(fileWallpaper);
                String fileAvatarName = fileUtils.extractFileName(fileAvatar);

                if (fileWallpaperName != null && fileWallpaperName.length() > 0) {
                    InputStream is = fileWallpaper.getInputStream();
                    long fileWallpaperSize = fileWallpaper.getSize();
                    String encodedFileWallpaper = fileUtils.inputStreamToBase64(is, fileWallpaperSize);
                }
                if (fileAvatarName != null && fileAvatarName.length() > 0) {
                    InputStream is = fileAvatar.getInputStream();
                    long fileAvatarNameSize = fileWallpaper.getSize();
                    String encodedFileAvatar = fileUtils.inputStreamToBase64(is, fileAvatarNameSize);
                }

                if (!checkPersonalInfo(request, firstName, lastName, phoneNumber, dob, address)) {
                    Candidate candidateTemp = new Candidate();
                    candidateTemp.setFirstName(firstName);
                    candidateTemp.setLastName(lastName);
                    candidateTemp.setPhone(phoneNumber);
                    candidateTemp.setBirthDate(dob);
                    candidateTemp.setAddress(address);
                    request.setAttribute("candidateTemp", candidateTemp);
                    request.getRequestDispatcher("CandidateProfilePage.jsp").forward(request, response);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        }

    }

    public boolean checkPersonalInfo(HttpServletRequest request, String fisrtName, String lastName, String phoneNumber, String dob, String address) {
        Validation validation = new Validation();
        boolean isDataCorrect = true;
        if (!validation.nameValidation(fisrtName, lastName)) {
            request.setAttribute("nameErrorMesg", "Name is incorrect format. The name cannot be empty, there are no numbers or special characters, "
                    + "and the maximum length is 150 characters.");
            isDataCorrect = false;
        }
        if (!validation.phoneNumberValidation(phoneNumber)) {
            request.setAttribute("phoneErrorMesg", "Phone is incorrect format.");
            isDataCorrect = false;
        }
        if (address.length() > 355) {
            request.setAttribute("addressErrorMesg", "Address is too long (max is 355 character).");
            isDataCorrect = false;
        }
        if (!isDataCorrect) {
            request.setAttribute("isPersonalModalShow", "true");
        } else {
            request.setAttribute("isPersonalModalShow", "false");
        }
        return isDataCorrect;
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
