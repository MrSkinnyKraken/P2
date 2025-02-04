/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import authn.Credentials;
import com.sun.xml.messaging.saaj.util.Base64;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
import model.entities.User;

/**
 *
 * @author Arnau & Rafa
 */
public class RetrieveUserFilter {
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    
    public static User filter(EntityManager em, ResourceInfo resourceInfo, ContainerRequestContext requestCtx)
            throws IOException {
         List<String> authorizationHeaders = null;
         String authHeader = null;
        Method method = resourceInfo.getResourceMethod();

        // Verify the method requires authentification
        if (method != null /*&& method.isAnnotationPresent(Secured.class)*/) {
            authorizationHeaders = requestCtx.getHeaders().get(HttpHeaders.AUTHORIZATION);

            // If the header ain't provided, returns 401
            if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
                requestCtx.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity("Missing Authorization header").build()
                );
                return null;
            }

            authHeader = authorizationHeaders.get(0);

            // Verifies the BASIC prefix
            if (!authHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
                requestCtx.abortWith(
                        Response.status(Response.Status.BAD_REQUEST).entity("Invalid Authorization header format").build()
                );
                return null;
            }

            try {
                // Decode to get username and psw 
                String encodedCredentials = authHeader.replace(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedCredentials = Base64.base64Decode(encodedCredentials);
                StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                // Seeks credentials in DB
                TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
                query.setParameter("username", username);
                Credentials credentials = query.getSingleResult();

                // Verifies the password
                if (!credentials.getPassword().equals(password)) {
                    requestCtx.abortWith(
                            Response.status(Response.Status.FORBIDDEN).entity("Invalid username or password").build()
                    );
                    return null;
                }

                // If the code arrives here, Returns the auth. user 
                return credentials.getUser();

            } catch (NoResultException e) {
                requestCtx.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build()
                );
                return null;
            } catch (Exception e) {
                requestCtx.abortWith(
                        Response.status(Response.Status.BAD_REQUEST).entity("Invalid Authorization header").build()
                );
                return null;
            }
        }

        return null; // (Should never get here) If the method ain't @secured, does nothing
    }
}
