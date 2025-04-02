package fr.univamu.iut.api_commandes.commande;

import fr.univamu.iut.api_commandes.commande.Commande;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commande")
@ApplicationScoped
public class CommandeRessource {

    @Inject
    private CommandeService service;

    @GET
    @Produces("application/json")
    public String getAllCommande() {
        return service.getAllCommandeJSON();
    }

    @GET
    @Path("{id_commande}")
    @Produces("application/json")
    public String getCommande(@PathParam("id_commande") int id_commande) {
        String result = service.getCommandeJSON(id_commande);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateCommande(Commande commande) {
        if (!service.updateCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteCommande(Commande commande) {
        if (!service.deleteCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addCommande(Commande commande) {
        if (!service.addCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("add").build();
    }
}