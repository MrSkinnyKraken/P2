/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


public class ArticleDTO implements Serializable{
    private Long id;

    private String title;

   
    private String summary;

  
    private String fullText;

    private int views;


    private UserDTO author;

    private LocalDateTime publishedDate;


    private List<TopicDTO> topics;

    private boolean isPrivate;

    private String image; //link to image

    // Constructors
    public ArticleDTO() {
    }

    public ArticleDTO(String title, String summary, String fullText, UserDTO author, List<TopicDTO> topics, boolean isPrivate) {
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

    public UserDTO getAuthor() {
        return author;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public boolean isIsPrivate() {
        return isPrivate;
    }
    
    public String getImage() {
        return image;
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

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
        public void setImage(String img) {
        this.image = img;
    }
    
}