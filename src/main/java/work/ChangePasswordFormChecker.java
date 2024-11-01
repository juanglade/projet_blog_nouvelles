/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package work;

import beans.Person;
import jakarta.servlet.http.HttpServletRequest;
import tools.PasswordAuthentication;

/**
 *
 * @author Caroline Casteras
 */
public class ChangePasswordFormChecker extends FormChecker<Person>{

    public ChangePasswordFormChecker(HttpServletRequest request) {
        super(request, (Person) request.getSession().getAttribute("person"));
    }
    
    @Override
    public Person checkForm() {
        //récupère les data
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        String confirm = request.getParameter("confirm");
        
        //vérification relative à la conformité du remplissage des champs
        if (oldPwd.length() < 3) {
            errors.put("oldPwd", new RuntimeException("Mot de passe trop court"));
        }
        if (newPwd.length() < 3) {
            errors.put("newPwd", new RuntimeException("Mot de passe trop court"));
        }
        if (confirm.length() < 3) {
            errors.put("confirm", new RuntimeException("Mot de passe trop court"));
        }
        if (!newPwd.equals(confirm)) {
            errors.put("confirm", new RuntimeException("Mots de passe non concordants"));
        }
        if (errors.isEmpty()) {
            //vérification de l'ancien pwd
            if (!oldPwd.equals(bean.getPwd())) {
                //si il concorde pas
                errors.put("oldPwd", new RuntimeException("Mot de passe incorrect"));
            } else {
                //hachage du mot de passe et stockage en DB
                bean.setPwd(newPwd);
            }
        }
        return bean;
    }
}
