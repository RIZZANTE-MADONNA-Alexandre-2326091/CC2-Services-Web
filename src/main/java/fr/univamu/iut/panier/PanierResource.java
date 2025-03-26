package fr.univamu.iut.panier;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux paniers
 * (point d'accès de l'API REST)
 */
@Path("/paniers")
public class PanierResource {

    /**
     * Service utilisé pour accéder aux données des paniers et récupérer/modifier leurs informations
     */
    private PanierService service;

    /**
     * Constructeur par défaut
     */
    public PanierResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param PanierRepo objet implémentant l'interface d'accès aux données
     */
    public PanierResource( PanierRepositoryInterface PanierRepo ){
        this.service = new PanierService( PanierRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux paniers
     */
    public PanierResource( PanierService service ){
        this.service = service;
    }

    /**
     * Endpoint permettant de publier de tous les paniers enregistrés
     * @return la liste des paniers (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return service.getAllPaniersJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un panier dont la référence est passée paramètre dans le chemin
     * @param reference référence du panier recherché
     * @return les informations du panier recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getPanier( @PathParam("reference") String reference){

        String result = service.getPanierJSON(reference);

        // si le panier n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jour le statut d'un panier uniquement
     * (la requête patch doit fournir le nouveau statut sur panier, les autres informations sont ignorées)
     * @param reference la référence du panier dont il faut changer le statut
     * @param panier le panier transmis en HTTP au format JSON et convertit en objet Panier
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("reference") String reference, Panier panier ){

        // si le panier n'a pas été trouvé
        if( ! service.updatePanier(reference, panier) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}