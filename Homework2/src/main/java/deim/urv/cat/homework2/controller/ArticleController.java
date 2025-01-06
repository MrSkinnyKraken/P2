package deim.urv.cat.homework2.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author arnau & rafa
 */

import deim.urv.cat.homework2.model.*;
import deim.urv.cat.homework2.service.ArticleService;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Path("articles")
public class ArticleController {    
    @Inject
    private ArticleService articleService;
    @Inject
    private Models models;
    @Inject
    private Logger log;
    
    
    @GET
    public String getAllArticles() {
        try {
            log.info("Fetching all articles");
            List<ArticleDTO> articles = articleService.getAllArticles();
            models.put("articles", articles);
            return "listArticles.jsp"; // JSP to display the list of articles
        } catch (Exception e) {
            log.severe("Error fetching all articles: " + e.getMessage());
            models.put("error", "Failed to fetch articles");
            return "error.jsp";
        }
    }
    
    @GET
    @Path("/{id}")
    public String getArticleById(@PathParam("id") Long id) {
        try {
            log.info("Fetching article with ID: " + id);
            ArticleDTO article = articleService.getArticleById(id);
            if (article != null) {
                models.put("article", article);
                return "article-detail.jsp"; // Forward to JSP
            } else {
                log.warning("Article not found with ID: " + id);
                models.put("error", "Article not found");
                return "error.jsp"; // Forward to error page
            }
        } catch (Exception e) {
            log.severe("Error fetching article: " + e.getMessage());
            models.put("error", "Internal server error");
            return "error.jsp";
        }
    }
    
    @GET
    @Path("/topic/{topic}")
    public String getArticlesByTopic(@PathParam("topic") String topic) {
        try {
            log.info("Fetching articles by topic: " + topic);
            List<ArticleDTO> articles = articleService.getArticlesByTopic(topic);
            models.put("articles", articles);
            return "listArticles.jsp"; // JSP to display articles by topic
        } catch (Exception e) {
            log.severe("Error fetching articles by topic: " + e.getMessage());
            models.put("error", "Failed to fetch articles by topic");
            return "error.jsp";
        }
    }
    
    @GET
    @Path("/author/{authorId}")
    public String getArticlesByAuthor(@PathParam("authorId") Long authorId) {
        try {
            log.info("Fetching articles by author ID: " + authorId);
            List<ArticleDTO> articles = articleService.getArticlesByAuthor(authorId);
            models.put("articles", articles);
            return "listArticles.jsp"; // JSP to display articles by author
        } catch (Exception e) {
            log.severe("Error fetching articles by author: " + e.getMessage());
            models.put("error", "Failed to fetch articles by author");
            return "error.jsp";
        }
    }
    
    @GET
    @Path("/author/{authorId}/{topic}")
    public String getArticleByAuthorAndTopics(){
            return "0";
    }
    
    
    
}

