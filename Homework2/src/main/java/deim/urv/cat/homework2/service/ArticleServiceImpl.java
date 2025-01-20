/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import deim.urv.cat.homework2.model.*;
import jakarta.ws.rs.core.Response;
import java.util.Collections;

/**
 *
 * @author arnau & rafa
 */
public class ArticleServiceImpl implements ArticleService {

    private static final String BASE_URI = "http://localhost:8080/P1/rest/api/v1";
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;

    public ArticleServiceImpl() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("article");
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ArticleDTO>>() {
            });
        }
        return null;
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Response response = webTarget.path("{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(ArticleDTO.class);
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getArticlesByTopic(List<String> topicNames) {
        // "/rest/api/v1/article?topic={topic1}&author={author}"
        Response response = webTarget.queryParam("topic", topicNames)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ArticleDTO>>() {
            });
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getArticlesByAuthor(Long authorId) {
        //"/rest/api/v1/article?author={author}"
        Response response = webTarget.queryParam("author", authorId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ArticleDTO>>() {
            });
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getArticleByAuthorAndTopics(Long authorId, List<String> topicNames) {
        // Add query parameters for author and topics
        WebTarget target = webTarget;
        if (authorId != null) {
            target = target.queryParam("author", authorId);
        }
        if (topicNames != null && !topicNames.isEmpty()) {
            target = target.queryParam("topic", topicNames);
        }

        // Make the GET request
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Handle the response
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ArticleDTO>>() {
            });
        } else {
            // Log the error or handle it appropriately
            System.err.println("Error: " + response.getStatus() + " - " + response.getStatusInfo());
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getAllTopics() {
        // Make a GET request to "/rest/api/v1/article/topics"
        Response response = webTarget.path("topics")
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            // Parse and return the list of topics
            return response.readEntity(new GenericType<List<String>>() {
            });
        } else {
            // Log the error or handle appropriately
            System.err.println("Error fetching topics: " + response.getStatus() + " - " + response.getStatusInfo());
            return Collections.emptyList();
        }
    }

    @Override
    public List<UserDTO> getAllAuthors() {
        // Make a GET request to "/rest/api/v1/article/authors"
        WebTarget userWebTarget = client.target(BASE_URI).path("authors");
        Response response = userWebTarget
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            // Parse and return the list of authors as UserDTO
            return response.readEntity(new GenericType<List<UserDTO>>() {
            });
        } else {
            // Log the error or handle appropriately
            System.err.println("Error fetching authors: " + response.getStatus() + " - " + response.getStatusInfo());
            return Collections.emptyList();
        }
    }

    @Override
    public void close() {
        client.close();
    }

}
