/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package work;

import interfaces.Identifiable;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Caroline Casteras
 */
public abstract class FormChecker <T extends Identifiable> {
    
    protected Map<String, RuntimeException> errors;
    protected T bean;
    protected HttpServletRequest request;

    public FormChecker(HttpServletRequest request, T bean) {
        errors = new HashMap<>();
        this.request = request;
        this.bean = bean;
    }

    public abstract T checkForm();

    public Map<String, RuntimeException> getErrors() {
        return errors;
    }
}
