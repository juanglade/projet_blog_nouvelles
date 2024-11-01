/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
@WebServlet(name = "DeleteComment", urlPatterns = {"/back/comments/delete"})
public class DeleteComment extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idParam = request.getParameter("id");
        Long id;
        try {
            id = Long.valueOf(idParam);
        } catch (NumberFormatException ex) {
            id = null;
        }
        if (id == null) {
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/back/comments");
        } else {
            DAOFactory.getDAOComment().delete(id);
            response.sendRedirect(
                getServletContext()
                    .getContextPath() + "/back/comments");
        }
    }
}
