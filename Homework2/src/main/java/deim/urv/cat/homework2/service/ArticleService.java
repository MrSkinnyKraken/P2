/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

/**
 *
 * @author rpino & arnau
 */

import deim.urv.cat.homework2.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {

    private List<Article> articles;
    public List<Article> getAllArticles() {
        return articles;
    }

    public List<Article> getFilteredArticles(List<Topic> topic, User author) {
        return articles.stream()
            .filter(a -> (topic == null || topic.isEmpty() || a.getTopics().equals(topic)) &&
                         (author == null || a.getAuthor().equals(author)))
            .collect(Collectors.toList());
    }

    public Article getArticleById(int id) {
        return articles.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElse(null);
    }


}
