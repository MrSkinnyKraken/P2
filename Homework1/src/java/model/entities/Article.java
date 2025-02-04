/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author arnau & rafa
 * 
 */

@Entity
@Table(name = "articles")
public class Article implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="Article_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Article_Gen")
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 50)
    private String summary;

    @Column(nullable = false, length = 500)
    private String fullText;

    @Column(nullable = false)
    private int views;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(nullable = false)
    private LocalDateTime publishedDate;

    @ManyToMany
    @JoinTable(
        name = "article_topics", 
        joinColumns = @JoinColumn(name = "article_id"), 
        inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> topics;

    @Column(nullable = false)
    private boolean isPrivate;
    
    @Column (nullable = false)
    private String img; //link to img

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
        public String getImg() {
        return img;
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
    
        public void setImg(String img) {
        this.img = img;
    }
    
}