/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.Master;
import beans.ServiceDir;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Andrey Belov
 */
@WebServlet(name = "MasterSaveServ", urlPatterns = {"/mastersave"})
public class MasterSaveServ extends HttpServlet {

    @Resource(lookup = "jdbc/SessPool")
    private DataSource ds;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Master master = new Master();
        try { 
            master.setCon(ds);
            master.setFName(request.getParameter("f_name"));
            master.setLName(request.getParameter("l_name"));
            master.setLocation(request.getParameter("location"));
            String idStr = request.getParameter("id");
            int id = idStr.isEmpty() ? -1 : Integer.parseInt(idStr);
            master.setMasterId(id);
            master.save();
            HttpSession session = request.getSession();
            ServiceDir dir = (ServiceDir) session.getAttribute("masterDir");
            dir.fill(ds);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("masterdir.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServSaveServ.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                master.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(ServSaveServ.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MasterSaveServ.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MasterSaveServ.class.getName()).log(Level.SEVERE, null, ex);
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