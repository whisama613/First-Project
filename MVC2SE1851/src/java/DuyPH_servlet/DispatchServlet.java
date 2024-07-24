/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuyPH_servlet;

import java.io.IOException;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String lOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_CONTROLLER = "StartupServlet";
    private final String ADD_BOOK_TO_CART = "AddBookToCartServlet";
    private final String REMOVE_BOOK_FROM_CART = "RemoveBookFromCartServlet";
    private final String VIEW_CART_PAGE = "Cart.jsp";
    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
    
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

        //1. What button did user click ?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;

        try {
            if (null == button) {//first time and app start up
                //tranfer login page
                //check cookies to determine which function is tranfered
                
            } else {
                switch (button) {
                    case "Login":
                        //user click login
                        url = lOGIN_CONTROLLER;
                        break;
                    case "Search":
                        //user click search
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                    case "delete":
                        //user click
                        url = DELETE_ACCOUNT_CONTROLLER;
                        break;
                    case "Add to Cart":
                        url = ADD_BOOK_TO_CART;
                        break;
                    case "View your Cart":
                        url = VIEW_CART_PAGE;
                        break;
                    case "Remove Selected Item":
                        url = REMOVE_BOOK_FROM_CART;
                        break;
                    case "Create New Account":
                        url = CREATE_NEW_ACCOUNT_CONTROLLER;
                        break;
                    default:
                        break;
                }
            }
        } finally {
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
