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
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 6, // (1024 bytes = 1 KB) x (1024 = 1 MB) x 5 = 5 MB 
        maxRequestSize = 1024 * 1024 * 6)//(1024 bytes = 1 KB) x (1024 = 1 MB) x 5 = 5 MB
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
        ICity daoCity = new CityDAO();
        List<City> listCity = daoCity.getAllCity();
        listCity.remove(0);
        Account loginedUser = AppUtils.getLoginedUser(request.getSession());
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileById(loginedUser.getAccId());
        Candidate candidateTemp = iCandidate.getCandidateProfileById(loginedUser.getAccId());
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

        //Seting modal display
        request.setAttribute("isShowEdu", "false");
        request.setAttribute("isPersonalModalShow", "false");
        request.setAttribute("isEduModalShow", "false");
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
        ICity daoCity = new CityDAO();
        List<City> listCity = daoCity.getAllCity();
        listCity.remove(0);
        Validation validation = new Validation();
        Account loginedUser = AppUtils.getLoginedUser(request.getSession());
        FileUtils fileUtils = new FileUtils();

        //Get candidate info 
        Candidate candidateInfo = iCandidate.getCandidateProfileById(loginedUser.getAccId());
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
//Set current user input data
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

        try {
            // Modal logic
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("personalInfo")) {
                //Personal modal
                Part fileWallpaper = request.getPart("fileWallpaper");
                Part fileAvatar = request.getPart("fileAvatar");

                String firstName = request.getParameter("fisrtname").trim();
                String lastName = request.getParameter("lastname").trim();
                String phoneNumber = request.getParameter("phoneNumber").trim();
                String dob = request.getParameter("dob");
                String city = request.getParameter("citySelect");
                String address = request.getParameter("address").trim();
                String fileWallpaperName = fileUtils.extractFileName(fileWallpaper);
                String fileAvatarName = fileUtils.extractFileName(fileAvatar);
                String encodedFileWallpaper = null;
                String encodedFileAvatar = null;

                if (fileWallpaperName != null && fileWallpaperName.length() > 0) {
                    InputStream is = fileWallpaper.getInputStream();
                    long fileWallpaperSize = fileWallpaper.getSize();
                    encodedFileWallpaper = fileUtils.inputStreamToBase64(is, fileWallpaperSize);
                    IOUtils.closeQuietly(is);
                }
                if (fileAvatarName != null && fileAvatarName.length() > 0) {
                    InputStream is = fileAvatar.getInputStream();
                    long fileAvatarSize = fileAvatar.getSize();
                    encodedFileAvatar = fileUtils.inputStreamToBase64(is, fileAvatarSize);
                    IOUtils.closeQuietly(is);
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
                } else {
                    //Modify: remove more than 2 white place in string.
                    firstName = validation.modifyString(firstName);
                    lastName = validation.modifyString(lastName);
                    address = validation.modifyString(address);
                    phoneNumber = validation.modifyString(phoneNumber);
                    iCandidate.updateCandidatePersonalProfile(candidateInfo.getCandIdateId(), encodedFileWallpaper, encodedFileAvatar, firstName, lastName, address, city, phoneNumber, true, dob);
                    response.sendRedirect("candidateprofilecontroller");
                }

            }
            System.out.println(action);
            if (action.equalsIgnoreCase("eduInfo")) {
                String startDate = request.getParameter("startDateEdu");
                String endDate = request.getParameter("endDateEdu");
                String majors = request.getParameter("majors");
                String degree = request.getParameter("degree");
                String desrciption = request.getParameter("desrciption");
                Part fileWallpaper = request.getPart("fileEdu");
                if (!checkEduInfo(request, degree, majors, startDate, endDate, desrciption)) {
                    request.setAttribute("startDateEdu", startDate);
                    request.setAttribute("endDateEdu", endDate);
                    request.setAttribute("majors", majors);
                    request.setAttribute("degree", degree);
                    request.setAttribute("desrciption", desrciption);
                    request.getRequestDispatcher("CandidateProfilePage.jsp").forward(request, response);
                } else {

                }

            }
        } catch (IllegalStateException e) {
            request.setAttribute("isPersonalModalShow", "true");
            request.setAttribute("genderError", "File over size, please try smaller file.");
            request.getRequestDispatcher("CandidateProfilePage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("isPersonalModalShow", "true");
            request.setAttribute("genderError", "Something went wrong, please check all input data (file size, special character,etc..) or try later.");
            request.getRequestDispatcher("CandidateProfilePage.jsp").forward(request, response);
        }

    }

    public void setAddButtonStatus() {
    }

    public boolean checkEduInfo(HttpServletRequest request, String degree, String majors, String startDate, String endDate, String description) {
        Validation validation = new Validation();
        boolean isDataCorrect = true;
        if (!validation.checkDateStartEnd(startDate, endDate)) {
            request.setAttribute("dateError", "Start date must not overpass end date.");
            isDataCorrect = false;
        }
        if (description.length() > 301) {
            request.setAttribute("descError", "Description is too long, max is 300 character.");
            isDataCorrect = false;
        }
        if (degree.length() > 151) {
            request.setAttribute("degreeError", "Dregree name is too long, max is 150 character.");
            isDataCorrect = false;
        }
        if (degree.isEmpty()) {
            request.setAttribute("degreeError", "Dregree name must not empty.");
            isDataCorrect = false;
        }
        if (majors.length() > 151) {
            request.setAttribute("majorsError", "Majors name is too long, max is 150 character.");
            isDataCorrect = false;
        }
        if (majors.isEmpty()) {
            request.setAttribute("majorsError", "Majors name must not empty.");
            isDataCorrect = false;
        }
        if (!isDataCorrect) {
            System.out.println("yes");
            request.setAttribute("isEduModalShow", "true");
            request.setAttribute("isPersonalModalShow", "false");
        } else {
            request.setAttribute("isEduModalShow", "false");

        }
        System.out.println("isDataCorrect" + isDataCorrect);
        return isDataCorrect;
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
