/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author arnau
 */
public class BigArticleDTO {

    private Long id;
    private String title;
    private UserDTO author;
    private LocalDateTime publishedDate;
    private int views;
    private String fullText;
    private String image;
    private List<TopicDTO> topics;
    private boolean isPrivate;

    public BigArticleDTO(Long id, String title, UserDTO author, LocalDateTime publishedDate, int views, String fullText, String image, List<TopicDTO> topics, boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.views = views;
        this.fullText = fullText;
        this.image = image;
        this.topics = topics;
        this.isPrivate = isPrivate;
    }

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

    public boolean isIsPrivate() {
        return isPrivate;
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

}
