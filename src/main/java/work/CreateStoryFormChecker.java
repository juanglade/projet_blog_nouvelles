package work;

import beans.Person;
import beans.Story;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
/**
 *
 * @author Sid révisé par Caroline Casteras
 */
public class CreateStoryFormChecker extends FormChecker<Story>{

    /**
     * Vérifie les données du formulaire de création d'une histoire.
     *
     * @param request La requête HTTP contenant les paramètres du formulaire.
     * @return Un map contenant les erreurs, si des erreurs existent.
     */
    Person person;

    public CreateStoryFormChecker(HttpServletRequest request) {
        super(request, new Story());
        person = (Person) request.getSession().getAttribute("person");
    }

    @Override
    public Story checkForm() {
        // Récupération des paramètres du formulaire
        String title = request.getParameter("title").trim();
        String content = request.getParameter("content").trim();
        // Création d'une nouvelle instance de Story
        bean.setTitle(title);
        bean.setContent(content);
        bean.setCreated(LocalDate.now()); // Définit la date de création à aujourd'hui
        bean.setId_person(person.getId()); // Remplacez par l'ID réel de l'utilisateur connecté

        // Vérifier le titre
        if (title == null || title.trim().isEmpty()) {
            errors.put("title", 
                    new RuntimeException("Le titre ne peut pas être vide."));
        } else if (title.length() > 100) { // Limite de 100 caractères pour le titre
            errors.put("title", 
                    new RuntimeException("Le titre ne doit pas dépasser 100 caractères."));
        }
        // Vérifier le contenu
        if (content == null || content.trim().isEmpty()) {
            errors.put("content", 
                    new RuntimeException("Le contenu ne peut pas être vide."));
        } else if (content.length() > 2000) { // Limite de 2000 caractères pour le contenu
            errors.put("content", 
                    new RuntimeException("Le contenu ne doit pas dépasser 2000 caractères."));
        }
        return bean;
    }
}
