package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.*;
import deim.urv.cat.homework2.service.*;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Path("articles")
public class ArticleController {
    @Inject
    private ArticleServiceImpl articleService;
    @Inject
    private Models models;
    @Inject
    private Logger log;

    @GET
    public String getAllArticles() {
        try {
            List<ArticleDTO> articles = articleService.getAllArticles(); //TODO: articles is null so it never shows anything
            //log.log(Level.INFO, "Retrieved articles"+articles.size());
            models.put("articles", articles);
            return "listArticles.jsp"; // JSP to display the list of articles
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Error fetching articles: {0}", e.getMessage());
            models.put("error", "Failed to fetch articles");
            return "Error404.jsp";
        }
    }

    @GET
    @Path("/{id}")
    public String getArticleById(@PathParam("id") Long id) {
        try {
            log.log(Level.INFO, "Fetching article with ID: {0}", id);
            ArticleDTO article = articleService.getArticleById(id);
            if (article != null) {
                models.put("article", article);
                return "article-detail.jsp"; // Forward to JSP
            } else {
                log.log(Level.WARNING, "Article not found with ID: {0}", id);
                models.put("error", "Article not found");
                return "error.jsp"; // Forward to error page
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching article: {0}", e.getMessage());
            models.put("error", "Internal server error");
            return "error.jsp";
        }
    }

    @GET
    @Path("/filter")
    public String getArticlesByFilter(@QueryParam("topic") List<String> topicNames,
                                      @QueryParam("author") String author) {
        try {
            log.log(Level.INFO, "Fetching articles with filter: topic={0} author={1}", new Object[]{topicNames, author});
            List<ArticleDTO> articles = articleService.getArticlesByTopic(topicNames);
            if (author != null && !author.isEmpty()) {
                articles = articleService.getArticlesByAuthor(Long.valueOf(author));
            }
            models.put("articles", articles);
            return "listArticles.jsp"; // JSP to display filtered articles
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Error fetching articles with filter: {0}", e.getMessage());
            models.put("error", "Failed to fetch articles with filter");
            return "error.jsp";
        }
    }


}