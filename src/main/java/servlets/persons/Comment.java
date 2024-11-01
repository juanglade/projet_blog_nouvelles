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
 * @author Florine Pérabout
 */
@WebServlet(name = "Comments", urlPatterns = {"/comments"})
public class Comment extends HttpServlet {

    private final String VIEW = "/WEB-INF/jsp/back/comments.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
            "comments", DAOFactory.getDAOComment().all()
        );
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
    }
}
