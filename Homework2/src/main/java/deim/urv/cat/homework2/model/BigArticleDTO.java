/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author arnau
 */
public class BigArticleDTO implements Serializable{
    private Long id;
    private String title;
    private UserDTO author;
    private LocalDateTime publishedDate;
    private int views;
    private String fullText;
    private String image;
    private List<TopicDTO> topics;
    private boolean isPrivate;
    
    public BigArticleDTO(){
    }
    
    public BigArticleDTO(Long id, String title, UserDTO author, LocalDateTime publishedDate, int views, String fullText, String img, List<TopicDTO> topics, boolean isPrivate) {
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

    public UserDTO getAuthor() {
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

    public List<TopicDTO> getTopics() {
        return topics;
    }
    
//setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }
    

    
}
