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
            return response.readEntity(new GenericType<List<ArticleDTO>>() {});
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
            return response.readEntity(new GenericType<List<ArticleDTO>>() {});
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
            return response.readEntity(new GenericType<List<ArticleDTO>>() {});
        }
        return null;
    }
    
    @Override
    public ArticleDTO getArticleByAuthorAndTopics(Long authorId, List<String> topicNames) {
        // Cambié la ruta a "/rest/api/v1/article?topic={topic1}&author={author}"
        Response response = webTarget.queryParam("author", authorId) // Usando queryParam para autor
                .queryParam("topic", topicNames) // Usando queryParam para temas
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(ArticleDTO.class);
        }
        return null;
    }

    @Override
    public void close() {
        client.close();
    }

}
