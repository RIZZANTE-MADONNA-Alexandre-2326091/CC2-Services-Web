package fr.univamu.iut.apiuserproduits.Repository;

import fr.univamu.iut.apiuserproduits.Entity.Product;

import java.util.List;

/**
 * Interface pour le dépôt de produits.
 */
public interface ProductRepositoryInterface {

    void close();

    Product getProductById(String id);

    List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean removeProduct(String id);

    boolean updateProduct(String id, String name, double price, int quantity, String unit);
}