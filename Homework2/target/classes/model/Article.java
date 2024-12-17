/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author arnau & rafa
 * 
 */

public class Article implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String summary;

    private String fullText;

    private int views;
    
    private User author;

    private LocalDateTime publishedDate;

    private List<Topic> topics;

    private boolean isPrivate;

    // Constructors
    public Article() {
    }

    public Article(String title, String summary, String fullText, User author, List<Topic> topics, boolean isPrivate) {
        this.title = title;
        this.summary = summary;
        this.fullText = fullText;
        this.author = author;
        this.topics = topics;
        this.isPrivate = isPrivate;
        this.publishedDate = LocalDateTime.now();
        this.views = 0; // Default views
    }
    //Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getFullText() {
        return fullText;
    }

    public int getViews() {
        return views;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public boolean isIsPrivate() {
        return isPrivate;
    }
    
    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    
}
