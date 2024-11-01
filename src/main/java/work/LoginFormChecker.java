/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package work;

import beans.Person;
import dao.DAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import tools.PasswordAuthentication;

/**
 *
 * @author Caroline Casteras
 */
public class LoginFormChecker extends FormChecker<Person> {

    public LoginFormChecker(HttpServletRequest request) {
        super(request, new Person());
    }
    
    @Override
    public Person checkForm() {
        //récupère et stock les data
        String login = request.getParameter("login").trim();
        String pwd = request.getParameter("pwd");
        bean.setLogin(login);
        bean.setPwd(pwd);
        
        //vérification relative à la conformité du remplissage des champs
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
            if (pwd.trim().length() < 3) {
                throw new RuntimeException("Mot de passe trop court");
            }
        } catch (RuntimeException ex) {
            errors.put("pwd", ex);
        }
        
        //vérification de l'existance d'un utilisateur : concordance du login et pwd
        //+ verif status
        if (errors.isEmpty()) {
            Person person = DAOFactory.getDAOPerson().findByLogin(login);
            PasswordAuthentication pa = new PasswordAuthentication();
            if (person != null 
                    && pa.authenticate(pwd.toCharArray(), person.getPwd())
                    && person.getStatus() ==0) {
                bean = person;
            } else {
                errors.put("login", new RuntimeException("Aucun utilisateur valide avec ces paramètres de connexion"));
            }
        }
        return bean;
    }
}
