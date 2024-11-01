/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.persons;

import beans.Person;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.LoginFormChecker;

/**
 *
 * @author Caroline Casteras
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final String VIEW = "/WEB-INF/jsp/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getSession().getAttribute("person") != null) {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        LoginFormChecker form = new LoginFormChecker(request);
        //vérification du compte de l'utilisateur
        Person person = form.checkForm();
        
        //si pas d'erreur on ouvre la session
        if (form.getErrors().isEmpty()) {
            request.setAttribute("message", "Connexion réussie");
            request.getSession().setAttribute("person", person);
            //redirection vers la page profil d'utilisateur
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            request.setAttribute("message", "Connexion échouée : Erreurs de saisie");
            request.getSession().invalidate();
            request.setAttribute("errors", form.getErrors());
            request.setAttribute("bean", person);
            getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
        }
    }
}
