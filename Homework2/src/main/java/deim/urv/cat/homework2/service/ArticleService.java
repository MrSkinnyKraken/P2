/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.ArticleDTO;
import deim.urv.cat.homework2.model.BigArticleDTO;
import deim.urv.cat.homework2.model.UserDTO;
import jakarta.mvc.Models;
import java.util.List;

/**
 *
 * @author arnau & rafa
 */
public interface ArticleService {
    public List<ArticleDTO> getAllArticles();
    public BigArticleDTO getArticleById(Long id, UserDTO user);
    public List<ArticleDTO> getArticlesByTopic(List<String> topicNames);
    public List<ArticleDTO> getArticlesByAuthor(String author);
    public List<ArticleDTO> getArticleByAuthorAndTopics(String author, List<String> topicNames);
    public List<String> getAllTopics();
    public List<UserDTO> getAllAuthors();
    public void close();
}
