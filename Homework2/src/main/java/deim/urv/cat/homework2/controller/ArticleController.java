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
import jakarta.servlet.http.HttpSession;


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
    @Inject
    private HttpSession session;

    @GET
    @Path("/{id}")
    public String getArticleById(@PathParam("id") Long id) {
        UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            models.put("error", "You need to log in to access this article.");
            return "login-form.jsp";
        }

        BigArticleDTO article = articleService.getArticleById(id, loggedInUser);

        if (article == null) {
            models.put("error", "Article not found or you are not authorized.");
            return "error.jsp";
        }

        models.put("article", article);
        return "article-detail.jsp";
    }

    @GET
    public String listArticles(
            @QueryParam("topic") List<String> topic,
            @QueryParam("author") String author) {
        // Fetch all unique topics and authors for dropdowns
        List<String> topics = articleService.getAllTopics();
        List<UserDTO> authors = articleService.getAllAuthors();
        List<ArticleDTO> articles;
        if (topic != null && !topic.isEmpty() && author != null) {
            articles = articleService.getArticleByAuthorAndTopics(author, topic);
        } else if (topic != null && !topic.isEmpty()) {
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
