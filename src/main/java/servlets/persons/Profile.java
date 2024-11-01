/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.persons;

import beans.Person;
import dao.DAOFactory;
import dao.DAOStory;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import work.ChangePasswordFormChecker;

/**
 *
 * @author Caroline Casteras
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String VIEW = "/WEB-INF/jsp/profile.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("person") == null) {
            response.sendRedirect(
                    getServletContext().getContextPath() + "/login");
        } else {
            //Julien
            //Récupération l'objet Person de la session
            Person person = (Person) request.getSession().getAttribute("person");
            //Ajout de la liste des stories à la requête
            request.setAttribute("storiesPerson", DAOFactory.getDAOStory().listByPerson(person.getId()));
            //Transmettre la requête à la JSP
            getServletContext()
                    .getRequestDispatcher(VIEW)
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ChangePasswordFormChecker form = new ChangePasswordFormChecker(request);
        Person person = form.checkForm();
        if (form.getErrors().isEmpty()) {
            request.setAttribute("message", "Mise à jour du mot de passe réussie");
        } else {
            request.setAttribute("message", "Mise à jour du mot de passe échouée : Erreurs de saisie");
        }
        request.setAttribute("errors", form.getErrors());
        getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
    }

}
