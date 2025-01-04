package deim.urv.cat.homework2.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author arnau & rafa
 */

//import deim.urv.cat.homework2.model.*;
import deim.urv.cat.homework2.service.ArticleService;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Path("articles")
public class ArticleController {    
    // CDI
    @Inject Logger log;
    @Inject Models models;
    @Inject ArticleService articleService;

    /**
     * Show the list of articles, optionally filtered by topic or author.
     * @param topic
     * @param author
     */
    @GET
    public String showArticles(@QueryParam("topic") List<Topic> topic, 
                               @QueryParam("author") User author) {
        log.log(Level.INFO, "Loading articles with filters - Topic: {0}, Author: {1}", new Object[]{topic, author});

        List<Article> articles;
        if ((topic != null && !topic.isEmpty()) || (author != null)) {
            // Apply filters
            articles = articleService.getFilteredArticles(topic, author);
        } else {
            // No filters
            articles = articleService.getAllArticles();
        }

        models.put("articles", articles);
        //models.put("topics", articleService.getAvailableTopics());
        //models.put("authors", articleService.getAvailableAuthors());
        return "listArticles.jsp"; // Main page for articles
    }

    /**
     * Show the details of a specific a
     * @param id
     */
    @GET
    @Path("detail")
    public String showArticleDetail(@QueryParam("id") int id) {
        log.log(Level.INFO, "Loading details for article with ID: {0}", id);

        Article article = articleService.getArticleById(id);
        if (article == null) {
            log.log(Level.WARNING, "Article with ID {0} not found.", id);
            models.put("message", "Article not found!");
            return "error.jsp"; // Redirect to an error page
        }

        models.put("article", article);
        return "article-detail.jsp"; // Details page
    }
}

