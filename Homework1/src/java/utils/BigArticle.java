/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import model.entities.Topic;
import model.entities.User;

/**
 * class for return big version of Article (without summary)
 * @author Arnau & Rafa
 */
public class BigArticle implements Serializable{
    private Long id;
    private String title;
    private User author;
    private LocalDateTime publishedDate;
    private int views;
    private String fullText;
    private String image;
    private List<Topic> topics;
    private boolean isPrivate;
    
    public BigArticle(){
    }
    
    public BigArticle(Long id, String title, User author, LocalDateTime publishedDate, int views, String fullText, String img, List<Topic> topics, boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.views = views;
        this.fullText = fullText;
        this.image = img;
        this.topics = topics;
        this.isPrivate = isPrivate;
    }

    public boolean isIsPrivate() {
        return isPrivate;
    }
//getter
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

    public String getFullText() {
        return fullText;
    }

    public String getImage() {
        return image;
    }

    public List<Topic> getTopics() {
        return topics;
    }
    
//setter

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

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    

    
}