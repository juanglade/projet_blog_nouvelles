package servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Herbert Caffarel
 */
@WebServlet(urlPatterns = "/back/home")
public class BackHome extends HttpServlet {

    private final String VIEW = "/WEB-INF/jsp/back/adminHome.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(VIEW)
            .forward(request, response);
    }

}
