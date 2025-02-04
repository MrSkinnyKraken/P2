/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;


import jakarta.json.bind.annotation.JsonbProperty;
import utils.Link;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 *
 * @author arnau & rafa
 * 
 */

@Entity
@XmlRootElement
@Table(name = "users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="User_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Gen")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String password;
    
    @Column (nullable = true) /*store the last article*/
    private Link links = new Link();

    // Constructors
    public User() {
    }

    public User(String email, String name, String password) {
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
    @JsonbTransient //Exclude properties from the JSON output
    public String getPassword() {
        return password;
    }
@JsonbProperty("password") // Permite que se mapee desde el JSON de entrada (deserialización)
    public void setPassword(String password) {
        this.password = password;
    }

    public Link getLinks() {
        return links;
    }

       // Method to set the last article link
    public void setLastArticleLink(String articleId) {
        if (links == null){
            links = new Link();
        }
        if (articleId != null && !articleId.isEmpty()) {
            this.links.setArticle("/article/" + articleId);
        } else {
            this.links.setArticle(null); // No article available
        }
    }
}

