package fr.univamu.iut.panier;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface d'accès aux données des paniers
 */
public interface PanierRepositoryInterface {

    /**
     *  Méthode fermant le dépôt dans lequel sont stockées les informations sur les paniers
     */
    public void close();

    /**
     * Méthode retournant le panier dont la référence est passée en paramètre
     * @param id identifiant du panier recherché
     * @return un objet panier représentant le panier recherché
     */
    public Panier getPanier( String id );

    /**
     * Méthode retournant la liste des paniers
     * @return une liste d'objets paniers
     */
    public ArrayList<Panier> getAllPaniers() ;

    /**
     * Méthode permettant de mettre à jour un panier enregistré
     * @param id Id du panier
     * @param quantite Nouvelle quantité disponible du panier
     * @param produits Nouveaux id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     * @param prix Nouveau prix du panier
     * @return true si le panier existe et la mise à jour a été faite, false sinon
     */
    public boolean updatePanier(String id, int quantite, Map<String, Map<Integer, String>> produits, int prix);
}