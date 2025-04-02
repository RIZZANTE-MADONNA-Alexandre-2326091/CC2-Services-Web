package fr.univamu.iut.api_commandes.commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Ressource associée aux commandes (point d'accès de l'API REST)
 */
@Path("/commandes")
@ApplicationScoped
public class CommandeRessource {

    /**
     * Service utilisé pour accéder aux données des commandes
     */
    private CommandeService service;

    /**
     * Constructeur par défaut
     */
    public CommandeRessource() {}

    /**
     * Constructeur permettant d'initialiser le service avec un repository
     * @param commandeRepo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public CommandeRessource(CommandeRepositoryInterface commandeRepo) {
        this.service = new CommandeService(commandeRepo);
    }

    /**
     * Constructeur permettant d'initialiser le service directement
     */
    public CommandeRessource(CommandeService service) {
        this.service = service;
    }

    /**
     * Endpoint permettant de récupérer toutes les commandes enregistrées
     * @return la liste des commandes au format JSON
     */
    @GET
    @Produces("application/json")
    public List<Commande> getAllCommandes() throws SQLException {
        return service.getAllCommandes();
    }

    /**
     * Endpoint permettant d'afficher une commande via son ID
     * @param id l'identifiant unique de la commande
     * @return la commande en JSON si trouvée, sinon erreur 404
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Commande getCommande(@PathParam("id") int id) {
        Commande commande = service.getCommande(id);
        if (commande == null)
            throw new NotFoundException();
        return commande;
    }

    /**
     * Endpoint permettant de mettre à jour une commande
     * @param id identifiant de la commande à modifier
     * @param commande nouvelle version de la commande
     * @return une réponse "updated" si la mise à jour a réussi, sinon erreur 404
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commande commande) {
        if (!service.updateCommande(id, commande))
            throw new NotFoundException();
        return Response.ok("updated").build();
    }
}