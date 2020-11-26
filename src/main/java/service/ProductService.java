package service;

import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.daisymdev.inventory-management-api.entity.Product;
//import com.daisymdev.inventory-management-api.repository.ProductRepository;

@Service
public class ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repo;

    public Iterable<Product> getProducts() {
        return repo.findAll();
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Product product, Long id) throws Exception {
        try {
            Product oldProduct = repo.findById(id);
            oldProduct.setDescription(product.getDescription());
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            return repo.save(oldProduct);
        } catch (Exception e) {
            Logger.error("Exception occurred while trying to update product: " + id, e);
            throw new Exception("Unable to update product.");
        }
    }
}
