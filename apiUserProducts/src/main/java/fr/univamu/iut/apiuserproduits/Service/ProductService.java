package fr.univamu.iut.apiuserproduits.Service;

import fr.univamu.iut.apiuserproduits.Entity.Product;
import fr.univamu.iut.apiuserproduits.Repository.ProductRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Classe de service pour gérer la logique métier liée aux produits.
 */
@ApplicationScoped
public class ProductService {

    private ProductRepositoryInterface productRepo;

    public ProductService() {
    }

    @Inject
    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    public Product getProductById(String id) {
        return productRepo.getProductById(id);
    }

    public boolean addProduct(Product product) {
        return productRepo.addProduct(product);
    }

    public boolean removeProduct(String id) {
        return productRepo.removeProduct(id);
    }

    public boolean updateProduct(String id, Product product) {
        return productRepo.updateProduct(id, product.getName(), product.getPrice(), product.getQuantity(), product.getUnit());
    }
}