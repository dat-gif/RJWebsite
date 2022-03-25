/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CandidateDAO;
import dao.idao.ICandidate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USE
 */
public class ChangeStatusApplyController extends HttpServlet {

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

        try {
            String cid = request.getParameter("candidateId");
            String jid = request.getParameter("jobId");

            request.setAttribute("candidateId", cid);
            request.setAttribute("jobId", jid);

            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("ChangeStatusApply.jsp").forward(request, response);
        } catch (Exception e) {
            //neu co loi thi chuyen huong den trang bao loi
            request.setAttribute("error", e);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
        try {
            String msg = "";
            String status = request.getParameter("status");

            String cid = request.getParameter("candidateId");
            String jid = request.getParameter("jobId");

//            int candidateId = Integer.parseInt(cid);
//            int jobId = Integer.parseInt(jid);
            int candidateId = 1;
            int jobId = 1;

            ICandidate candidateDAO = new CandidateDAO();
            int total = candidateDAO.changeStatusApply(status, candidateId, jobId);
            if (total > 0) {
                msg = "Change status succesfully";
            }

            request.setAttribute("msg", msg);

            //chuyen huong den trang jsp dich
            request.getRequestDispatcher("ChangeStatusApply.jsp").forward(request, response);
        } catch (Exception e) {
            //neu co loi thi chuyen huong den trang bao loi
            request.setAttribute("error", e);
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
