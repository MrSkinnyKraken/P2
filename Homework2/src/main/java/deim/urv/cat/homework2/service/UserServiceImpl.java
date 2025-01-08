package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.UserDTO;
import deim.urv.cat.homework2.controller.UserForm;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.ClientBuilder;    

public class UserServiceImpl implements UserService {
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/P1/rest/api/v1/";
    
    public UserServiceImpl() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("customer");
    }

    @Override
    public UserDTO findUserByEmail(String email){
            Response response = webTarget.path("email").path(email)
            .request(MediaType.APPLICATION_JSON)
            .get();
        if (response.getStatus() == 200) {
            return response.readEntity(UserDTO.class);
        }
        return null;
    }

@Override
public boolean addUser(UserForm user) {
    try {
        // Realizar la solicitud POST
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), Response.class);

        // Verificar el código de estado
        if (response.getStatus() == 201) {
            return true; // Usuario creado exitosamente
        } else {
            // Manejar errores según el código de estado
            String errorMessage = response.readEntity(String.class);
            System.err.println("Error al agregar usuario. Código de estado: " 
                                + response.getStatus() + ", Mensaje: " + errorMessage);
            return false;
        }
    } catch (jakarta.ws.rs.ProcessingException e) {
        // Error de procesamiento (por ejemplo, deserialización fallida)
        System.err.println("Error de procesamiento durante la solicitud POST: " + e.getMessage());
        e.printStackTrace();
        return false;
    } catch (jakarta.ws.rs.WebApplicationException e) {
        // Error general de la API web (por ejemplo, problemas de red)
        System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        // Cualquier otro error inesperado
        System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}


}
