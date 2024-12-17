package deim.urv.cat.homework2.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author arnau & rafa
 * 
 */

public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String email;

    private String name;

    private String password;
    
    private String apiKey;
    
    private List<Article> articles;
    
    private List<Credentials> credentials;

    public List<Credentials> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credentials> credentials) {
        this.credentials = credentials;
    }

    // Constructors
    public User() {
    }

    public User(String email, String name, String password, String apiKey) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.apiKey = apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}