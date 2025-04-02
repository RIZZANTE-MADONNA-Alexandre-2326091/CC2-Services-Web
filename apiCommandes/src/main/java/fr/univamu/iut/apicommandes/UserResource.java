package fr.univamu.iut.apicommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Classe ressource pour gérer les requêtes HTTP liées aux utilisateurs.
 */
@Path("/users")
@ApplicationScoped
public class UserResource {

    private UserService service;

    public UserResource() {
    }

    @Inject
    public UserResource(UserRepositoryInterface repo) {
        this.service = new UserService(repo);
    }

    public UserResource(UserService service) {
        this.service = service;
    }

    /**
     * Récupère tous les utilisateurs.
     *
     * @return une réponse contenant la liste de tous les utilisateurs
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = service.getAllUsers();
        return Response.ok(users).build();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return une réponse contenant l'utilisateur
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String id) {
        User user = service.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        return Response.ok(user).build();
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user l'utilisateur à ajouter
     * @return une réponse indiquant le résultat de l'opération
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if (service.addUser(user)) {
            return Response.status(Response.Status.CREATED).entity("Utilisateur ajouté").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout de l'utilisateur").build();
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param user l'utilisateur mis à jour
     * @return une réponse indiquant le résultat de l'opération
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, User user) {
        if (!service.updateUser(id, user)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        return Response.ok("Utilisateur mis à jour").build();
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     * @return une réponse indiquant le résultat de l'opération
     */
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") String id) {
        if (!service.removeUser(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        return Response.ok("Utilisateur supprimé").build();
    }

    /**
     * Vérifie les identifiants d'un utilisateur.
     *
     * @param credentials objet contenant username et password
     * @return une réponse indiquant si les identifiants sont valides
     */
    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(Credentials credentials) {
        if (service.checkCredentials(credentials.getUsername(), credentials.getPassword())) {
            return Response.ok("Authentification réussie").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Identifiants invalides").build();
    }

    /**
     * Récupère un utilisateur par son nom.
     *
     * @param name le nom de l'utilisateur
     * @return une réponse contenant l'utilisateur
     */
    @GET
    @Path("/by-name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@PathParam("name") String name) {
        User user = service.getUserByName(name);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        return Response.ok(user).build();
    }
}


class Credentials {
    private String username;
    private String password;
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
    
    public String getOldPassword() { return oldPassword; }
    public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}