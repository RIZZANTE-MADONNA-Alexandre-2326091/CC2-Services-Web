package fr.univamu.iut.apiuserproduits.Api;

import fr.univamu.iut.apiuserproduits.Entity.Product;
import fr.univamu.iut.apiuserproduits.Repository.ProductRepositoryInterface;
import fr.univamu.iut.apiuserproduits.Service.ProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * Classe ressource pour gérer les requêtes HTTP liées aux produits.
 */
@Path("/products")
@ApplicationScoped
public class ProductResource {

    private ProductService service;

    public ProductResource() {
    }

    @Inject
    public ProductResource(ProductRepositoryInterface repo) {
        this.service = new ProductService(repo);
    }

    public ProductResource(ProductService service) {
        this.service = service;
    }

    /**
     * Récupère tous les produits.
     *
     * @return une réponse contenant la liste de tous les produits
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> products = service.getAllProducts();
        return Response.ok(products).build();
    }

    /**
     * Récupère un produit par son identifiant.
     *
     * @param id l'identifiant du produit
     * @return une réponse contenant le produit
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") String id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit non trouvé").build();
        }
        return Response.ok(product).build();
    }

    /**
     * Ajoute un nouveau produit.
     *
     * @param product le produit à ajouter
     * @return une réponse indiquant le résultat de l'opération
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        if (service.addProduct(product)) {
            return Response.status(Response.Status.CREATED).entity("Produit ajouté").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout du produit").build();
    }

    /**
     * Met à jour un produit existant.
     *
     * @param id l'identifiant du produit à mettre à jour
     * @param product le produit mis à jour
     * @return une réponse indiquant le résultat de l'opération
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") String id, Product product) {
        if (!service.updateProduct(id, product)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit non trouvé").build();
        }
        return Response.ok("Produit mis à jour").build();
    }

    /**
     * Supprime un produit par son identifiant.
     *
     * @param id l'identifiant du produit à supprimer
     * @return une réponse indiquant le résultat de l'opération
     */
    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        if (!service.removeProduct(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Produit non trouvé").build();
        }
        return Response.ok("Produit supprimé").build();
    }
}