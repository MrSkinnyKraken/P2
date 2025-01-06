/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.ArticleDTO;
import java.util.List;

/**
 *
 * @author arnau &rafa
 */
public interface ArticleService {
    public List<ArticleDTO> getAllArticles();
    public ArticleDTO getArticleById(Long id);
    public ArticleDTO createArticle(ArticleDTO article);
    public ArticleDTO updateArticle(Long id, ArticleDTO article);
    public boolean deleteArticle(Long id);
    public List<ArticleDTO> getArticlesByTopic(String topic);
    public List<ArticleDTO> getArticlesByAuthor(Long authorId);
    public ArticleDTO getArticleByAuthorAndTopics(Long authorId, List<String> topics);
    public void close();
}
