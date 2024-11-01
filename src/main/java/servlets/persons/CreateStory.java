package servlets.persons;

import beans.Story;
import dao.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.CreateStoryFormChecker;

import java.io.IOException;

/**
 * Servlet pour créer une nouvelle histoire.
 * @author Sid révisé par Caroline Casteras
 */
@WebServlet(urlPatterns = "/story/create")
public class CreateStory extends HttpServlet {
    Long id;
    private final String VIEW = "/WEB-INF/jsp/createStory.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/createStory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validation du formulaire
        CreateStoryFormChecker form = new CreateStoryFormChecker(request);
        Story story = form.checkForm();

        //Récupération de l'id
        String idParam = request.getParameter("id");
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        story.setId(id);
        if (form.getErrors().isEmpty()) {
            // Enregistrement de l'histoire dans la base de données
            DAOFactory.getDAOStory().create(story);
            // Redirection vers la liste des histoires après création
            response.sendRedirect(
                getServletContext().getContextPath() + "/home");
        } else {
            request.setAttribute("message", "Votre formulaire contient des erreurs");
            request.setAttribute("errors", form.getErrors());
            request.setAttribute("story", story);
            // Si des erreurs sont présentes, on renvoie au formulaire avec les erreurs
            getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
        }
    }
}
