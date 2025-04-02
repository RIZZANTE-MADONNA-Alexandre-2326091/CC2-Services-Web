package fr.univamu.iut.apicommandes;

import java.util.ArrayList;

/**
 * Interface pour le dépôt d'utilisateurs.
 */
public interface UserRepositoryInterface {

    /**
     * Ferme la connexion au dépôt.
     */
    public void close();

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant
     */
    public User getUserById(String id);

    /**
     * Récupère tous les utilisateurs.
     *
     * @return une liste de tous les utilisateurs
     */
    public ArrayList<User> getAllUsers();

    /**
     * Récupère un utilisateur par son nom.
     *
     * @param name le nom de l'utilisateur
     * @return l'utilisateur correspondant
     */
    public User getUserByName(String name);

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user l'utilisateur à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean addUser(User user);

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return true si la suppression a réussi, false sinon
     */
    public boolean removeUser(String id);

    /**
     * Met à jour un utilisateur.
     *
     * @param id l'identifiant de l'utilisateur
     * @param name le nouveau nom
     * @param email le nouvel email
     * @param password le nouveau mot de passe
     * @param isAdmin le nouveau statut admin
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updateUser(String id, String name, String email, String password, boolean isAdmin);

    /**
     * Vérifie les identifiants d'un utilisateur.
     *
     * @param username le nom d'utilisateur
     * @param password le mot de passe
     * @return true si les identifiants sont valides, false sinon
     */
    public boolean checkCredentials(String username, String password);
}