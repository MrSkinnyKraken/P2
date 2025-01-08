package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

@Named("userForm")
@RequestScoped
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
    // JSR 303 validation
    @NotBlank
    @FormParam("name")
    @MvcBinding
    @Size(min=2, max=30, message = "name must be between 2 and 30 characters")
    private String name;
    
    
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
    
    
    public String getName() {
        return fixNull(this.name);
    }

    public void setName(String name) {
        this.name = name;
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
        return "UserForm{" + "firstName=" + name + ", password=" + password + ", email=" + email + '}';
    }
    
   
}
