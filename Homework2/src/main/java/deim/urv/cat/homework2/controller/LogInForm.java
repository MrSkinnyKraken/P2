package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

@Named("LogInForm")
@RequestScoped
public class LogInForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
   
    @NotBlank
    @MvcBinding
    @FormParam("password")
    private String password;

    @NotBlank
    @FormParam("email")
    @Email(message = "Email should be valid")
    @MvcBinding
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }

    @Override
    public String toString() {
        return "UserForm{" +", password=" + password + ", email=" + email + '}';
    }
    
   
}
