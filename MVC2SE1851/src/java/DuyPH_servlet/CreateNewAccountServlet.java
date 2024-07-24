/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuyPH_servlet;

import DuyPH.registration.RegistrationCreateError;
import DuyPH.registration.RegistrationDAO;
import DuyPH.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author whisa
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "Registration.jsp";
    private final String LOGIN_PAGE = "login.html";
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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all params
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        boolean foundError =false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        
        try {
            //2. handle all users errors
            if (username.trim().length() < 6 || username.trim().length() > 20){
                foundError = true;
                errors.setUsenameLenghError("Username requied typing from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30){
                foundError = true;
                errors.setPasswordLenghError("Password requied typing from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())){
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50){
                foundError = true;
                errors.setFullNameLenghError("Full name requied typing from 2 to 50 characters");
            }
            if (foundError){
                // catching a specific attribute then go to error page to show
                request.setAttribute("CREATE_ERRORS", errors);
            } else {// no errors
                //3. insert to DB --> call Model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if (result) {
                    url = LOGIN_PAGE;
                }
            } // no errors
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL: " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsenameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
            
        } catch (ClassNotFoundException ex) {
            log("CreateNewAccountServlet _ Class Not Found: " + ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
