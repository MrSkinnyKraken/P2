package deim.urv.cat.homework2.model;

import java.io.Serializable;

/**
 *
 * @author arnau & rafa
 * 
 */

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;


    private String email;

    private String name;

    private String password;

    private LinkDTO links = new LinkDTO();

    // Constructors
    public UserDTO() {
    }

    public UserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
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

    public LinkDTO getLinks() {
        return links;
    }

       // Method to set the last article link
    public void setLastArticleLink(String articleId) {
        if (links == null){
            links = new LinkDTO();
        }
        if (articleId != null && !articleId.isEmpty()) {
            this.links.setArticle("/article/" + articleId);
        } else {
            this.links.setArticle(null); // No article available
        }
    }
}

