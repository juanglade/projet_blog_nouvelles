package servlets.persons;

import dao.DAOFactory;
import dao.DAOVote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * authors: Sid et Florine
 *
 */
@WebServlet(urlPatterns = "/story")
public class Story extends HttpServlet {
    Long id;
    int quality;
    private final String VIEW = "/WEB-INF/jsp/story.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération de la liste des histoires depuis la base de données
        Collection<beans.Story> stories = DAOFactory.getDAOStory().all();
        String idParam = request.getParameter("id");

        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }

        if (id != null) {
            request.setAttribute(
                "id",
                DAOFactory.getDAOStory().find(id));
            request.setAttribute(
                "quality",
                DAOFactory.getDAOVote().findByQuality(0));
            // Ajout de la liste des histoires à l'objet request
            request.setAttribute("stories", stories);
        }
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
        request.getRequestDispatcher("/WEB-INF/jsp/story.jsp").forward(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        throws ServletException, IOException {
//        //je veux que mon dopost renvoi mon vote.quality dans countLikeByIdStory
//        // mon countLikeByIdStory enregiste le nombre de votes
//        //il prend le nombre de Like(quality 1) et divise le nb de Like par le nb de votes
//        int totalLikes = DAOFactory.getDAOVote().countLikeByIdStory(id, 0);
//        int totalVotes = DAOFactory.getDAOVote().count();
//        }
//    }
}
