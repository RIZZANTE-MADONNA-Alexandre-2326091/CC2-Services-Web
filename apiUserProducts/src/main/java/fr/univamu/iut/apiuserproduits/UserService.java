package fr.univamu.iut.apiuserproduits;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Classe de service pour gérer la logique métier liée aux utilisateurs.
 */
@ApplicationScoped
public class UserService {

    protected UserRepositoryInterface userRepo;
    protected AuthenticationClientInterface authClient;

    public UserService() {
    }

    @Inject
    public UserService(UserRepositoryInterface userRepo, AuthenticationClientInterface authClient) {
        this.userRepo = userRepo;
        this.authClient = authClient;

        if (userRepo instanceof UserRepositoryMariadb) {
            ((UserRepositoryMariadb) userRepo).setAuthClient(authClient);
        }
    }

    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Récupère tous les utilisateurs.
     *
     * @return une liste de tous les utilisateurs
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = userRepo.getAllUsers();
        users.forEach(user -> {
            user.setPwd("");
            user.setMail("");
        });
        return users;
    }

    /**
     * Récupère tous les utilisateurs au format JSON.
     *
     * @return la liste des utilisateurs au format JSON
     */
    public String getAllUsersJson() {
        ArrayList<User> users = getAllUsers();
        String result = null;
        try(Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(users);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur
     */
    public User getUserById(String id) {
        return userRepo.getUserById(id);
    }

    /**
     * Récupère un utilisateur par son identifiant au format JSON.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur au format JSON
     */
    public String getUserJson(String id) {
        User user = getUserById(id);
        if (user != null) {
            user.setPwd("");
            try(Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(user);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param user l'utilisateur mis à jour
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean updateUser(String id, User user) {
        return userRepo.updateUser(id, user.getName(), user.getMail(), user.getPwd(), user.gestionnaire());
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean removeUser(String id) {
        return userRepo.removeUser(id);
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user l'utilisateur à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean addUser(User user) {
        return userRepo.addUser(user);
    }

    /**
     * Récupère un utilisateur par son nom.
     *
     * @param name le nom de l'utilisateur
     * @return l'utilisateur
     */
    public User getUserByName(String name) {
        return userRepo.getUserByName(name);
    }

    /**
     * Vérifie les identifiants d'un utilisateur.
     *
     * @param username le nom d'utilisateur
     * @param password le mot de passe
     * @return true si les identifiants sont valides, false sinon
     */
    public boolean checkCredentials(String username, String password) {
        return userRepo.checkCredentials(username, password);
    }

    /**
     * Vérifie les permissions d'un utilisateur.
     *
     * @param userId l'identifiant de l'utilisateur
     * @param permission la permission à vérifier
     * @return true si l'utilisateur a la permission, false sinon
     */
    public boolean checkPermission(String userId, String permission) {
        if (authClient != null) {
            return authClient.checkPermission(userId, permission);
        }
        return false;
    }
}