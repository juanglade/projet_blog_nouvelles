/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package work;

import beans.Comment;
import beans.Person;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 *
 * @author stag
 */
public class CreateCommentFormChecker extends FormChecker<Comment> {

    Person person = new Person();

    public CreateCommentFormChecker(HttpServletRequest request) {
        super(request, new Comment());
        person = (Person) request.getSession().getAttribute("person");
    }

    @Override
    public Comment checkForm() {
        String title = request.getParameter("title").trim();
        String content = request.getParameter("content").trim();
        bean.setTitle(title);
        bean.setContent(content);
        bean.setId_person(person.getId());
        bean.setCreated(LocalDate.now());
        if (title.length() > 20) {
            errors.put("title",
                new RuntimeException("Titre trop long (20 caractères maximum"));
        }
        if (content.length() > 150) {
            errors.put("content",
                new RuntimeException("Contenu trop long (150 caractères maximum"));
        }
        return bean;
    }

}
