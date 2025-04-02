package fr.univamu.iut.apiuserproduits;

import fr.univamu.iut.apiuserproduits.Repository.ProductRepositoryInterface;
import fr.univamu.iut.apiuserproduits.Repository.ProductRepositoryMariadb;
import fr.univamu.iut.apiuserproduits.Repository.UserRepositoryInterface;
import fr.univamu.iut.apiuserproduits.Repository.UserRepositoryMariadb;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe principale de l'application de gestion des utilisateurs et des produits.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    /**
     * Crée une instance de UserRepositoryInterface.
     *
     * @return une instance de UserRepositoryInterface
     */
    @Produces
    @ApplicationScoped
    public UserRepositoryInterface createUserRepository() {
        UserRepositoryMariadb db = null;

        try {
            db = new UserRepositoryMariadb("jdbc:mariadb://mysql-sebbak.alwaysdata.net/sebbak_user_products",
                    "sebbak_user", "SebbakUser");
            if (db == null) {
                throw new RuntimeException("Échec de création de l'instance UserRepositoryMariadb");
            }
            db.getAllUsers();
        } catch (Exception e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Échec d'initialisation de la connexion à la base de données", e);
        }
        return db;
    }

    /**
     * Ferme la connexion au repository utilisateur.
     *
     * @param userRepo l'instance de UserRepositoryInterface à fermer
     */
    public void closeUserRepository(@Disposes UserRepositoryInterface userRepo) {
        if (userRepo != null) {
            userRepo.close();
        }
    }

    /**
     * Crée une instance de ProductRepositoryInterface.
     *
     * @return une instance de ProductRepositoryInterface
     */
    @Produces
    @ApplicationScoped
    public ProductRepositoryInterface createProductRepository() {
        ProductRepositoryMariadb db = null;

        try {
            db = new ProductRepositoryMariadb("jdbc:mariadb://mysql-sebbak.alwaysdata.net/sebbak_user_products",
                    "sebbak_user", "SebbakUser");
            if (db == null) {
                throw new RuntimeException("Échec de création de l'instance ProductRepositoryMariadb");
            }
            db.getAllProducts();
        } catch (Exception e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Échec d'initialisation de la connexion à la base de données", e);
        }
        return db;
    }

    /**
     * Ferme la connexion au repository produit.
     *
     * @param productRepo l'instance de ProductRepositoryInterface à fermer
     */
    public void closeProductRepository(@Disposes ProductRepositoryInterface productRepo) {
        if (productRepo != null) {
            productRepo.close();
        }
    }
}