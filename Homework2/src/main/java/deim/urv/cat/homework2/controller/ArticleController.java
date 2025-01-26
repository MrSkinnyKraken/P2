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
    private UserServiceImpl userService;
    @Inject
    private Models models;
    @Inject
    private Logger log;

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
    public String listArticles(
            @QueryParam("topic") List<String> topic,
            @QueryParam("author") String author) {
        // Fetch all unique topics and authors for dropdowns
        List<String> topics = articleService.getAllTopics();
        List<UserDTO> authors = articleService.getAllAuthors();
        List<ArticleDTO> articles;
        if (topic !=null && !topic.isEmpty() && author!=null){
            articles = articleService.getArticleByAuthorAndTopics(author, topic);
        }else if (topic !=null && !topic.isEmpty()) {
            articles = articleService.getArticlesByTopic(topic);
        } else if (author != null) {
            articles = articleService.getArticlesByAuthor(author);
        } else {
            articles = articleService.getAllArticles();
        }

        models.put("topics", topics);
        models.put("authors", authors);
        models.put("articles", articles);
        return "listArticles.jsp";
    }

}
