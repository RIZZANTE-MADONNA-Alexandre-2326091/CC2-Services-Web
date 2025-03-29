package fr.univamu.iut.panier;

import java.util.Date;
import java.util.Map;

public class Panier {
    /**
     * Id du panier
     */
    String id;

    /**
     * Quantité disponible du panier
     */
    int quantite;

    /**
     * Id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     */
    Map<String, Map<Integer, String>> produits;

    /**
     * Prix du panier
     */
    int prix;

    /**
     * Date de la dernière mise à jour du panier
     */
    Date dateMaj;

    /**
     * Constructeur de panier
     * @param id Id du panier
     * @param quantite Quantité disponible du panier
     * @param produits Id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     * @param prix Prix du panier
     * @param dateMaj Date de la dernière mise à jour du panier
     */
    public Panier(String id, int quantite, Map<String, Map<Integer, String>> produits, int prix, Date dateMaj) {
        this.id = id;
        this.quantite = quantite;
        this.produits = produits;
        this.prix = prix;
        this.dateMaj = dateMaj;
    }

    /**
     * Méthode permettant d'accéder à l'id du panier
     * @return une chaîne de caractères avec l'id du panier
     */
    public String getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'id du panier
     * @param id une chaîne de caractères avec l'id à utiliser
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Méthode permettant d'accéder à la quantité disponible du panier
     * @return une chaîne de caractères avec la quantité disponible du panier
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Méthode permettant de modifier la quantité disponible du panier
     * @param quantite Méthode permettant de modifier la quantité disponible du panier
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Méthode permettant d'accéder aux produits
     * @return une chaîne de caractères avec produits
     */
    public Map<String, Map<Integer, String>> getProduits() {
        return produits;
    }

    /**
     * Méthode permettant de modifier les produits
     * @param produits une chaîne de caractères avec les produits à utiliser
     */
    public void setProduits(Map<String, Map<Integer, String>> produits) {
        this.produits = produits;
    }

    /**
     * Méthode permettant d'accéder au prix
     * @return une chaîne de caractères avec le prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier le prix
     * @param prix une chaîne de caractères avec le prix à utiliser
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Méthode permettant d'accéder à la date de la dernière mise à jour du panier
     * @return une chaîne de caractères avec date de la dernière mise à jour du panier
     */
    public Date getDateMaj() {
        return dateMaj;
    }

    /**
     * Méthode permettant de modifier date de la dernière mise à jour du panier
     * @param dateMaj une chaîne de caractères avec date de la dernière mise à jour à utiliser
     */
    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }
}
