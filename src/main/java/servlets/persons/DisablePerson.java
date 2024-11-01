/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.persons;

import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet(name = "DisableUser", urlPatterns = {"/person/disable"})
public class DisablePerson extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //récupération de l'id par la requête
        String idParam = request.getParameter("id");
        //conversion du paramètre idParam en un Long id
        Long id;
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        //si l'id est null on redirige vers le back/home sinon le person qui 
        //porte l'id selon
        //l'id et redirige ensuite vers la page back/persons
        if (id == null) {
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/home");
        } else {
            DAOFactory.getDAOPerson().updateStatus(id,2);
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/person/logout");
        }
    }
}
