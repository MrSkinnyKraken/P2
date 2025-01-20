/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.ArticleDTO;
import deim.urv.cat.homework2.model.UserDTO;
import java.util.List;

/**
 *
 * @author arnau &rafa
 */
public interface ArticleService {
    public List<ArticleDTO> getAllArticles();
    public ArticleDTO getArticleById(Long id);
    public List<ArticleDTO> getArticlesByTopic(List<String> topicNames);
    public List<ArticleDTO> getArticlesByAuthor(Long authorId);
    public List<ArticleDTO> getArticleByAuthorAndTopics(Long authorId, List<String> topicNames);
    public List<String> getAllTopics();
    public List<UserDTO> getAllAuthors();
    public void close();
}
