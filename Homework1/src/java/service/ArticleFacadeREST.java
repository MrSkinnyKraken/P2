/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.RetrieveUserFilter;
import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.entities.Article;
import model.entities.Topic;
import model.entities.User;
import utils.BigArticle;
import utils.LittleArticle;

/**
 *
 * @author arnau & rafa
 *
 */
@Stateless
@Path("article")
public class ArticleFacadeREST extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    @Context
    private ResourceInfo resourceInfo;
    @Context
    ContainerRequestContext requestCtx;

    public ArticleFacadeREST() {
        super(Article.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * GET /rest/api/v1/article?topic={topic1}&author={author} Filters articles
     * by topic and author.
     *
     * @param topicNames The topic or topics of the article.
     * @param author The author of the article.
     * @return A JSON list of filtered articles.
     * @throws java.io.IOException
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getArticles(
            @QueryParam("topic") List<String> topicNames, // List of topic names as strings
            @QueryParam("author") String author) throws IOException {
        //User authenticatedUser = RetrieveUserFilter.filter(em, resourceInfo, requestCtx);

        // Check if there are more than 2 topics
        if (topicNames != null && topicNames.size() > 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A maximum of 2 topics are allowed")
                    .build();
        }

        StringBuilder queryBuilder = new StringBuilder("SELECT a FROM Article a");

        //JOIN with Topic table to filter by actual Topic entity
        if (topicNames != null && !topicNames.isEmpty()) {
            queryBuilder.append(" JOIN a.topics t");
        }

        queryBuilder.append(" WHERE 1=1");

        // If there's a topic param, filter by only the corresponding topic articles 
        if (topicNames != null && !topicNames.isEmpty()) {
            queryBuilder.append(" AND t.name IN :topics");
        }

        // If there's an author param, filter by only the corresponding author articles 
        if (author != null && !author.isEmpty()) {
            queryBuilder.append(" AND a.author.name = :author");
        }

        // Si el usuario no está autenticado, excluye los artículos privados
        //if (authenticatedUser == null) {
        //    queryBuilder.append(" AND a.isPrivate = false");
        //}
        // Order by views count in descending order
        queryBuilder.append(" ORDER BY a.views DESC");

        TypedQuery<Article> query = em.createQuery(queryBuilder.toString(), Article.class);

        // Check if the parameters exists (author & topic are OPTIONAL)
        if (topicNames != null && !topicNames.isEmpty()) {
            query.setParameter("topics", topicNames);
        }
        if (author != null && !author.isEmpty()) {
            query.setParameter("author", author);
        }

        List<Article> articles = query.getResultList();

        List<LittleArticle> littleArticles;
        littleArticles = articles.stream()
                .map(article -> new LittleArticle(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getPublishedDate(),
                article.getViews(),
                article.getSummary(),
                article.getImg(),
                article.isIsPrivate()
        ))
                .toList();

        return Response.ok(littleArticles).build();
    }

    /**
     * GET /rest/api/v1/article/${id} Retrieves an article information by it's
     * id.Ensures private access to private articles.
     *
     * @param id
     * @return The article or an error if conditions aren't ensured.
     * @throws java.io.IOException
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response getArticle(@PathParam("id") Long id) throws IOException {
        Article article = super.find(id);

        // If the article isn't found, return 404
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article NOT FOUND")
                    .build();
        }

        // Increment view count directly without checking user
        article.setViews(article.getViews() + 1);
        super.edit(article); // Persist the change

        // Return the article
        BigArticle finalArticle = new BigArticle(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getPublishedDate(),
                article.getViews(),
                article.getFullText(),
                article.getImg(),
                article.getTopics(),
                article.isIsPrivate()
        );
        return Response.ok(finalArticle).build();
    }

    /**
     * DELETE /rest/api/v1/article/${id} Method that removes an article from de
     * DB only if the user is authenticated and is the owner of the object
     *
     * @param requestCtx
     * @param id of the article to remove
     * @return No content response or an error if conditions aren't ensured.
     * @throws java.io.IOException
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response deleteArticle(@Context ContainerRequestContext requestCtx, @PathParam("id") Long id) throws IOException {
        // Use filter to get the authenticated user
        User authenticatedUser = RetrieveUserFilter.filter(em, resourceInfo, requestCtx);
        if (authenticatedUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authentication required")
                    .build();
        }

        // Retrieve the article
        Article article = super.find(id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article not found")
                    .build();
        }

        // Check if the authenticated user is the author
        if (!article.getAuthor().equals(authenticatedUser)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You are not authorized to delete this article")
                    .build();
        }

        // Remove the article
        super.remove(article);
        return Response.noContent().build();
    }

    /**
     * POST/rest/api/v1/article !! Uses the authentication at the request header
     * !!
     *
     * @param newArticle is the JSON of the new article
     * @return returns the 201 code if created with the id. If the user doesn't
     * exist returns error, also error if the topic isn't valid.
     * @throws java.io.IOException
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response createArticle(Article newArticle) throws IOException {
        // Filter method to retrieve the authenticated user
        User authenticatedUser = RetrieveUserFilter.filter(em, resourceInfo, requestCtx);
        if (authenticatedUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authentication required")
                    .build();
        }

        // Set the author and publication date
        newArticle.setAuthor(authenticatedUser);
        newArticle.setPublishedDate(LocalDateTime.now());

        // Validate topics (similar logic to what you already have)
        List<Topic> managedTopics = new ArrayList<>();
        for (Topic topic : newArticle.getTopics()) {
            TypedQuery<Topic> topicQuery = em.createQuery(
                    "SELECT t FROM Topic t WHERE t.name = :name", Topic.class);
            topicQuery.setParameter("name", topic.getName());
            Topic managedTopic = topicQuery.getResultStream().findFirst().orElse(null);

            if (managedTopic == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid topic: " + topic.getName())
                        .build();
            }
            managedTopics.add(managedTopic);
        }

        // Replace the article's topics with the managed ones
        newArticle.setTopics(managedTopics);

        // Persist the article
        super.create(newArticle);

        authenticatedUser.setLastArticleLink(String.valueOf(newArticle.getId())); //set link of last article in user
        em.merge(authenticatedUser); // Update the user in the database

        return Response.status(Response.Status.CREATED)
                .entity(newArticle.getId())
                .build();
    }

    /**
     * GET /rest/api/v1/article/topics Retrieves a list of all topics.
     *
     * @return A JSON list of all available topics.
     */
    @GET
    @Path("topics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllTopics() {
        List<Topic> topics = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();

        // Map topics to a simplified response
        List<String> topicNames = topics.stream()
                .map(Topic::getName)
                .toList();
        return Response.ok(topicNames).build();
    }

    /**
     * GET /rest/api/v1/article/authors Retrieves a list of all authors who have
     * published articles.
     *
     * @return A JSON list of authors' names.
     */
    @GET
    @Path("authors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAuthors() {
        List<String> authors = em.createQuery(
                "SELECT DISTINCT u FROM User u",
                String.class
        ).getResultList();

        return Response.ok(authors).build();
    }
}
