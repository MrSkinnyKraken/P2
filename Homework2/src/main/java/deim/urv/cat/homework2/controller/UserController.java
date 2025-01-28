/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.LinkDTO;
import deim.urv.cat.homework2.model.UserDTO;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arnau & rafa
 */
@Controller
@Path("users")
public class UserController {
    @Inject
    private UserServiceImpl userService;

    @Inject
    private Models models;

    @Inject
    private Logger log;

    @GET
    @Path("/{id}")
    public String getUserById(@PathParam("id") Long id) {
        try {
            log.log(Level.INFO, "Fetching user with ID: {0}", id);
            // Use the service method to get the user data
            UserDTO user = userService.getUserById(id);
            LinkDTO link = user.getLinks();
            if (user != null) {
                // Add the user object to the model
                models.put("user", user);
                return "user-info.jsp"; // Forward to JSP
            } else {
                log.log(Level.WARNING, "User not found with ID: {0}", id);
                models.put("error", "User not found");
                return "error.jsp"; // Forward to error page
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching user: {0}", e.getMessage());
            models.put("error", "Internal server error");
            return "error.jsp";
        }
    }
}
