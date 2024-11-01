package servlets.admin;

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
 * @author Florine Pérabout
 */
@WebServlet(name = "BackVote", urlPatterns = {"/back/vote"})
public class BackVote extends HttpServlet {

    private final String VIEW = "/WEB-INF/jsp/back/vote.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
            "vote", DAOFactory.getDAOVote().all()
        );
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
    }
}
