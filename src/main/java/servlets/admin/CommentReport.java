/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.admin;

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
@WebServlet(name = "CommentReport", urlPatterns = {"/back/comments/report"})
public class CommentReport extends HttpServlet {

    Long id;

    private final String VIEW = "/WEB-INF/jsp/back/comments.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //convertir la chaine de caractère idParam en Long
        String idParam = request.getParameter("id");
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        if (id != null) {
            //récupère le commentaire dans la DB grâce à la DAO
            request.setAttribute(
                "comment",
                DAOFactory.getDAOComment().findByStatus(0));
        }
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idParam = request.getParameter("status");
        CreateCommentFormChecker form = new CreateCommentFormChecker(request);
        Comment comment = form.checkForm();
        comment.setStatus(0);
        if (form.getErrors().isEmpty()) {
            DAOFactory.getDAOComment().persist(comment);
            response.sendRedirect(
                getServletContext().getContextPath() + "/back/comments");
        } else {
            DAOFactory.getDAOComment().updateStatus(id, 1);
            request.setAttribute("msg", "Le commentaire est supprimé");
            getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
        }
    }


}
