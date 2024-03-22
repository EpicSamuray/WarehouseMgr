package ch.hftm.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import ch.hftm.model.Order;
import ch.hftm.model.Product;
import ch.hftm.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {

    public record ItemReq(String id, String name) {}
    public record ItemRes(String id, boolean isValid) {}

    @Inject
    ProductRepository productRepo;

    private static final Logger LOG = Logger.getLogger(ProductService.class);

    public Product createProduct(Product product) {
        LOG.info("Creating new item: " + product.getName() + " | ID: " + product.id);
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (productRepo.isPersisted(product.id)) {
            throw new IllegalArgumentException("Item already exists");
        } 
        productRepo.persist(product);
        if (productRepo.isPersisted(product.id)){
            LOG.info("Sending item for validation: " + product.id);
            return productRepo.findById(product.id);
        } 
        return null;
    }

    public List<Product> getAllProducts() {
        LOG.info(LOG.getName());
        return productRepo.findAll().list();
    }

    public Product find(ObjectId id) {
        return productRepo.findById(id);
    }

    public Product getProductById(ObjectId id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(ObjectId id, Product product) {
        LOG.info("Updating item: " + id);
        if (id == null) {
            LOG.error("Error updating item because id is required");
            throw new IllegalArgumentException("id is required");
        }
        if (productRepo.isPersisted(id)) {
            LOG.info("Persisting item: " + id);
            product.id = id;
            productRepo.update(product);
            LOG.info("Updated item: " + id);
            LOG.info("Sending item for validation: " + id);
            Product item = productRepo.findById(id);
            LOG.info("Payload Item: " + item.id);
            return item;
        }
        return null;
    }

    public Long countAll() {
        return productRepo.countAll();
    }

    public List<Product> getAllProducts(int page, int pageSize) {
        return productRepo.findAllProducts(page, pageSize);
    }

    public boolean isPersistend(ObjectId id) {
        if (id == null) {
            LOG.error("Error checking if item is persisted because id is required");
            throw new IllegalArgumentException("id is required");
        }
        return productRepo.isPersisted(id);
    }


    public boolean delete(Product item) {
        if (item == null) {
            LOG.error("Error deleting item because item is required");
            throw new IllegalArgumentException("item is required");
        }
        return productRepo.deleteById(item.id);
    }

    public boolean delete(boolean all) {
        try {
            productRepo.deleteAll();
            return true;
        } catch (Exception e) {
            LOG.error("Error deleting all items: " + e.getMessage());
            return false;
        }
    }

    //TODO: Refactor this method to use the ItemReq record
    public void sendItemForValidation(ObjectId itemid) {
        LOG.info("Sending item for validation: " + itemid);
        Product item = productRepo.findById(itemid);
        LOG.info("Payload Item: " + item.id);
        if (item != null) {
            LOG.info("Sended item for validation: " + item.id);
        }
    }
    
    //FIXME: This method is not used
    @Transactional
    public void updatedItemValidationStatus(ItemRes message) {
        LOG.info("Received validation result: " + message);
        ObjectId objectId = new ObjectId(message.id());
        Product item = productRepo.findById(objectId);
        LOG.info("Payload Item: " + objectId);
        LOG.info("Payload Item: " + message.isValid());
        if (item != null) {
            LOG.info("Persisting item: " + item.id);
            productRepo.update(item);
            LOG.info("Persisted item: " + item.id);
        }
        LOG.info("Payload Item: ");
    }

    
}
