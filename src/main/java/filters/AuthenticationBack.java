/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import beans.Person;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Anglade Julien
 */
@WebFilter(urlPatterns = "/back/*")
public class AuthenticationBack implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        // Travail sur la requête : vérifier qu'un utilisateur est en session
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        if (person == null || person.getId() != 1) {  //seulement l'utilisateur 1 (admin) peut y accéder
            response.sendError(403);
            return;
        }
        // appel filtre suivant
        fc.doFilter(sr, sr1);
    }
}
