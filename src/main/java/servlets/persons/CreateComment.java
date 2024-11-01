/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.persons;

import beans.Comment;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.CreateCommentFormChecker;

/**
 *
 * @author Florine Pérabout
 */
@WebServlet(name = "CreateComment", urlPatterns = {"/comment/create"})
public class CreateComment extends HttpServlet {

    Long id;

    private final String VIEW = "/WEB-INF/jsp/story.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        if (id != null) {
            request.setAttribute(
                "comment",
                DAOFactory.getDAOComment().find(id));
        }
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        CreateCommentFormChecker checker = new CreateCommentFormChecker(request);
        Comment comment = checker.checkForm();
        comment.setId(id);
        if (checker.getErrors().isEmpty()) {
            DAOFactory.getDAOComment().persist(comment);
            response.sendRedirect(
                getServletContext().getContextPath() + "/story");
        } else {
            request.setAttribute("msg", "Votre commentaire contient des erreurs");
            request.setAttribute("errors", checker.getErrors());
            request.setAttribute("comment", comment);
            getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
        }
    }
}

