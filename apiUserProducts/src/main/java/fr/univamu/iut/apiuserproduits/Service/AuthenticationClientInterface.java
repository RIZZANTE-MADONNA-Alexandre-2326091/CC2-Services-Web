package fr.univamu.iut.apiuserproduits.Service;

/**
 * Interface pour le client d'authentification.
 */
public interface AuthenticationClientInterface {
    boolean checkPermission(String userId, String permission);
}