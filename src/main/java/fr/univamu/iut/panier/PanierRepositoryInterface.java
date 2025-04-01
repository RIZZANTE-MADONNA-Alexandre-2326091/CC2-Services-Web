package fr.univamu.iut.panier;

import java.sql.Date;
import java.util.ArrayList;

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
     * @param dateMaj Date de la dernière mise à jour du panier
     * @return true si le panier existe et la mise à jour a été faite, false sinon
     */
    public boolean updatePanier(String id, int quantite, String produits, int prix, Date dateMaj);

    /**
     * Méthode permettant de créer un panier
     * @param id Id du panier
     * @param quantite Nouvelle quantité disponible du panier
     * @param produits Nouveaux id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     * @param prix Nouveau prix du panier
     * @param dateMaj Date de la dernière mise à jour du panier
     * @return true si le panier a été créé, false sinon
     */
    public boolean createPanier(String id, int quantite, String produits, int prix, Date dateMaj);

    /**
     * Méthode permettant de créer un panier
     * @param id Id du panier
     * @return true si le panier existe et la suppression a été faite, false sinon
     */
    public boolean deletePanier(String id);
}