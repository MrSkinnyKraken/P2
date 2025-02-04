/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDateTime;
import model.entities.User;

/**
 * class for return litle version of Article (without fullText & topics)
 * @author Arnau & Rafa
 */
public class LittleArticle {
    private Long id;
    private String title;
    private User author;
    private LocalDateTime publishedDate;
    private int views;
    private String summary;
    private String image;
    private boolean isPrivate;

    public LittleArticle(Long id, String title, User author, LocalDateTime publishedDate, int views, String summary, String img, boolean priv) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.views = views;
        this.summary = summary;
        this.image = img;
        this.isPrivate = priv;
    }
//getters
    public boolean isPriv(){
        return isPrivate;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public int getViews() {
        return views;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }
//setters
    
    public void setPriv(boolean isPrivate){
        this.isPrivate = isPrivate;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}