package fr.univamu.iut.panier;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class PanierService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les paniers
     */
    protected PanierRepositoryInterface panierRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param panierRepo objet implémentant l'interface d'accès aux données
     */
    public  PanierService( PanierRepositoryInterface panierRepo) {
        this.panierRepo = panierRepo;
    }

    /**
     * Méthode retournant les informations sur les paniers au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPaniersJSON(){

        ArrayList<Panier> allPaniers = panierRepo.getAllPaniers();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allPaniers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un panier recherché
     * @param id l'id du panier recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getPanierJSON( String id ){
        String result = null;
        Panier myPanier = panierRepo.getPanier(id);

        // si le livre a été trouvé
        if( myPanier != null ) {

            // création du json et conversion du panier
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPanier);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jour les informations d'un panier
     * @param id id du panier à mettre à jour
     * @param panier les nouvelles informations à utiliser
     * @return true si le panier a pu être mis à jour
     */
    public boolean updatePanier(String id, Panier panier) {
        return panierRepo.updatePanier(id, panier.quantite, panier.produits, panier.prix);
    }
}
