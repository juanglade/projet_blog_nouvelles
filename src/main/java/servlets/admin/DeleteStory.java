package servlets.admin;

import dao.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet pour supprimer une histoire.
 */
@WebServlet(urlPatterns = "/back/delete/story")
public class DeleteStory extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Récupérer l'ID de l'histoire à partir de la requête
        String idParam = request.getParameter("id");
        Long id;

        try {
            // Convertir l'ID en Long
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            // Si l'ID n'est pas un nombre valide, on le considère comme null
            id = null;
        }

        // Si l'ID est null ou invalide, rediriger vers la page d'accueil
        if (id == null) {
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/back/home");
        } else {
            // Supprimer l'histoire en appelant la méthode delete du DAO
            DAOFactory.getDAOStory().delete(id);
            // Rediriger vers la liste des histoires après suppression
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/back/story");
        }
    }
}
