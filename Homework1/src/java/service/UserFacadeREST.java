/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Credentials;
import authn.RetrieveUserFilter;
import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entities.User;

/**
 *
 * @author arnau & rafa
 * 
 */

@Stateless
@Path("customer")
public class UserFacadeREST extends AbstractFacade<User>{

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    @Context
    private ResourceInfo resourceInfo;
    @Context
    ContainerRequestContext requestCtx;
    
    public UserFacadeREST() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    /**
    *   GET /rest/api/v1/customer
    * @return A JSON response containing a list of users with their basic information 
    *        (id, name, email) and HATEOAS links to their last published article, if available.
    */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsuaris(){
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        
        // Map users to a response format without confidential information
        List<Map<String, Object>> userResponseList = users.stream().map(user -> {
            // Create a user response map
            Map<String, Object> userResponse = new HashMap<>();
            userResponse.put("id", user.getId());
            userResponse.put("name", user.getName());
            userResponse.put("email", user.getEmail());
            userResponse.put("lastArticle", user.getLinks());
            if(user.getLinks()==null){
                userResponse.put("lastArticle", "user without articles");
            }
             
            return userResponse;
        }).toList();
        return Response.ok(userResponseList).build();
    }
    


    /**
     *
     *  GET /rest/api/v1/customer/${id}
     *  @param id
     *  @return A JSON response containing the user's basic information 
     *         (id, name, email) and the link to their last article if available. 
     *         Returns 404 if the user is not found.
     */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id) {
        User user = em.find(User.class, id);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }
        // Map user to a response format without confidential information
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", user.getId());
        userResponse.put("name", user.getName());
        userResponse.put("email", user.getEmail());
        userResponse.put("lastArticle", user.getLinks());

        return Response.ok(userResponse).build();
    }


    /**
     *    PUT /rest/api/v1/customer/${id}
     * 
     * @param id
     * @param updateFields
     * @return A JSON response indicating the status of the update:
     *         - 204 (No Content) if the update is successful.
     *         - 404 (Not Found) if the user does not exist.
     *         - 401 (Unauthorized) if authentication is missing or invalid.
     *         - 403 (Forbidden) if the authenticated user is not allowed to modify this user.
     * @throws IOException
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Secured
    public Response updateUser(@PathParam("id") Long id, Map<String, String> updateFields) throws IOException {
        User user = super.find(id);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }

        User authenticatedUser = RetrieveUserFilter.filter(em, resourceInfo, requestCtx);
        Long authenticatedUserId = authenticatedUser.getId();
        if (authenticatedUserId == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authentication required")
                    .build();
        }
        
       //Validated user = user to modify
        if (!authenticatedUserId.equals(user.getId())) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You are not authorized to update this user")
                    .build();
        }

        // update the fields we have
        if (updateFields.containsKey("name")) {
            user.setName(updateFields.get("name"));
        }
        if (updateFields.containsKey("email")) {
            user.setEmail(updateFields.get("email"));
        }

        em.merge(user); // Persistimos los cambios

        return Response.status(Response.Status.NO_CONTENT).build();  // No content, pero el recurso se actualizó.
    }
    
    /**
     * POST /rest/api/v1/customer
     * 
     * @param user El usuario recibido en formato JSON.
     * @return Respuesta con:
     *         - 201 (Created) si se crea el usuario exitosamente.
     *         - 400 (Bad Request) si los datos del usuario no son válidos.
     *         - 409 (Conflict) si el email ya existe.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        // Validar que el objeto recibido no sea nulo y contenga los campos requeridos
        if (user == null || user.getName() == null || user.getEmail() == null || user.getPassword()==null) {
            if(user.getName()== null){
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name are required")
                    .build();
            }
            else if(user.getEmail()== null){
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("email are required")
                    .build();
            }
            else if(user.getPassword()== null){
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("password are required")
                    .build();
            }
        }
        Credentials c = new Credentials();
        c.setUser(user);
        c.setPassword(user.getPassword());
        c.setUsername(user.getName());

        try {
            em.persist(user);
            em.persist(c);
            return Response.status(Response.Status.CREATED)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while creating the user")
                    .build();
        }

    }

    
    /**
    * GET /rest/api/v1/customer/${email}
    * 
    * @param email Email del usuario a buscar.
    * @return Respuesta con:
    *         - 200 (OK) si se encuentra el usuario, incluyendo su información básica.
    *         - 404 (Not Found) si no existe ningún usuario con ese email.
    */
   @GET
   @Path("email/{email}")
       @Produces(MediaType.APPLICATION_JSON)
   public Response getUserByEmail(@PathParam("email") String email) {
       List<User> users = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                            .setParameter("email", email)
                            .getResultList();

       // Si no se encuentra el usuario, devolver un error 404
       if (users.isEmpty()) {
           return Response.status(Response.Status.NOT_FOUND)
                          .entity("User with email " + email + " not found")
                          .build();
       }

       // Tomar el primer resultado (email es único, debería haber solo uno)
       User user = users.get(0);

       // Mapear la respuesta del usuario sin datos confidenciales
       Map<String, Object> userResponse = new HashMap<>();
       userResponse.put("id", user.getId());
       userResponse.put("name", user.getName());
       userResponse.put("email", user.getEmail());
       userResponse.put("lastArticle", user.getLinks() != null ? user.getLinks() : "User without articles");
       userResponse.put("password", user.getPassword());

       return Response.ok(userResponse).build();
   }
   
         /**
     * POST /rest/api/v1/auth/login
     * 
     * @param credentials Las credenciales (correo y contraseña) del usuario.
     * @return Respuesta con:
     *         - 200 (OK) si el correo y la contraseña coinciden con un usuario.
     *         - 401 (Unauthorized) si el correo o la contraseña no son correctos.
     */
    @POST
    @Path("auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        
        // Validación de las credenciales
        if (credentials == null || credentials.getUsername() == null || credentials.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username and password are required.")
                    .build();
        }

        // Buscar usuario por correo
        User user = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                      .setParameter("name", credentials.getUsername())
                      .getSingleResult();

        // Si no se encuentra el usuario, devolver un error 401
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid email.")
                    .build();
        }

        // Verificar si la contraseña coincide
        if (!user.getPassword().equals(credentials.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid password.")
                    .build();
        }

        // Si las credenciales son correctas, devolver un código 200 (OK)
        return Response.status(Response.Status.OK)
                       .entity("Authentication successful.")
                       .build();
    }
}

    

