/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package work;

import beans.Person;
import com.sun.jna.platform.win32.Netapi32Util.User;
import dao.DAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import tools.PasswordAuthentication;

/**
 *
 * @author Caroline Casteras
 */
public class SignupFormChecker extends FormChecker<Person>{
    
    public SignupFormChecker(HttpServletRequest request) {
        super(request, new Person());
    }

    @Override
    public Person checkForm() {
        //récupère et stock les data
        String login = request.getParameter("login").trim();
        String pwd = request.getParameter("pwd");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name").trim();
        PasswordAuthentication pa = new PasswordAuthentication();
        bean.setLogin(login);
        bean.setPwd(pa.hash(pwd.toCharArray()));
        bean.setName(name);
        
        //vérification relative à la conformité du remplissage des champs
        //vérifie la longueur du nom d'utilisateur
        try {
            if (name.length() < 5) {
                throw new RuntimeException("Nom d'utilisateur trop court");
            }
        } catch (RuntimeException ex) {
            errors.put("name", ex);
        }
        //vérifie le login (mail)
        try {
            if (!login.contains("@")) {
                throw new RuntimeException("Email invalide");
            }
        } catch (RuntimeException ex) {
            errors.put("login", ex);
        }
        //vérifie la longueur du pwd
        try {
            if (pwd.length() < 3) {
                throw new RuntimeException("Mot de passe trop court");
            }
        } catch (RuntimeException ex) {
            errors.put("pwd", ex);
        }
        //vérifie la concordance des pwd
        try {
            if (!pwd.equals(confirm)) {
                throw new RuntimeException("Mots de passe non concordants");
            }
        } catch (RuntimeException ex) {
            errors.put("confirm", ex);
        }
        //vérification de l'existance d'un utilisateur au meme login
        try {
            Person person = DAOFactory.getDAOPerson().findByLogin(login);
            if (person != null) {
                throw new RuntimeException("Mail utilisateur déjà existant");
            }
        } catch (RuntimeException ex) {
            errors.put("login", ex);
        }
        return bean;
    }
}
