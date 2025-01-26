/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;


import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.SignUpAttempts;
import deim.urv.cat.homework2.model.UserDTO;
import deim.urv.cat.homework2.service.UserServiceImpl;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.security.CsrfProtected;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rpino
 */

@Controller
@Path("LogIn")
public class LogInController {

    // CDI
    @Inject BindingResult bindingResult;
    @Inject Logger log;
    @Inject UserServiceImpl service;
    @Inject Models models;
    @Inject AlertMessage flashMessage;
    @Inject SignUpAttempts attempts;

    @GET
    public String showForm() {
        return "login-form.jsp"; // Vista del formulario de inicio de sesión
    }    

    @POST
    @UriRef("log-in")
    @CsrfProtected
    public String LogIn(@Valid @BeanParam LogInForm LogInForm) {
        models.put("user", LogInForm);
        
        if (bindingResult.isFailed()) {
            AlertMessage alert = AlertMessage.danger("Validation failed!");
            bindingResult.getAllErrors()
                    .stream()
                    .forEach((ParamError t) -> {
                        alert.addError(t.getParamName(), "", t.getMessage());
                    });
            log.log(Level.WARNING, "Data binding for LogInFormController failed.");
            models.put("errors", alert);
            return "login-form.jsp";
        }
        
        if(attempts.hasExceededMaxAttempts()) {
            models.put("message", "You have exceeded the maximum number of login attempts.");
            return "login-form.jsp";
        }
        
        // Verificar si el usuario existe
        UserDTO user = service.findUserByEmail(LogInForm.getEmail());
        if (user == null) {
            log.log(Level.WARNING, "No user found with this e-mail address {0}.", LogInForm.getEmail());
            models.put("message", "No user found with this e-mail address.");
            attempts.increment();
            return "login-form.jsp";
        }
        
        // Verificar si la contraseña es correcta
        if (!service.checkPassword(user.getName(), LogInForm.getPassword())) {
            log.log(Level.WARNING, "Incorrect password for user {0}.", LogInForm.getEmail());
            models.put("message", "Incorrect password.");
            attempts.increment();
            return "login-form.jsp";
        }
        
        attempts.reset();
        log.log(Level.INFO, "User {0} successfully signed in.", LogInForm.getEmail());
        return "listArticles.jsp"; // Página de éxito al iniciar sesión
    }
}
