package fr.univamu.iut.apiuserproduits;

/**
 * Interface pour le client d'authentification.
 */
public interface AuthenticationClientInterface {
    boolean checkPermission(String userId, String permission);
}