package ch.hftm.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

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
    ProductRepository invItemRepo;

    private static final Logger LOG = Logger.getLogger(ProductService.class);

    public List<Product> listAll() {
        LOG.info(LOG.getName());
        return invItemRepo.listAll();
    }

    public void persist(Product item) {
        LOG.info("Persisting item: " + item.id);
        invItemRepo.persist(item);
    }

    public boolean isPersistend(ObjectId id) {
        return invItemRepo.isPersisted(id);
    }

    public Product find(ObjectId id) {
        return invItemRepo.findById(id);
    }

    public boolean delete(Product item) {
        return invItemRepo.deleteById(item.id);
    }

    public boolean delete(boolean all) {
        try {
            invItemRepo.deleteAll();
            return true;
        } catch (Exception e) {
            LOG.error("Error deleting all items: " + e.getMessage());
            return false;
        }
    }

    //TODO: Refactor this method to use the ItemReq record
    public void sendItemForValidation(ObjectId itemid) {
        LOG.info("Sending item for validation: " + itemid);
        Product item = invItemRepo.findById(itemid);
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
        Product item = invItemRepo.findById(objectId);
        LOG.info("Payload Item: " + objectId);
        LOG.info("Payload Item: " + message.isValid());
        if (item != null) {
            LOG.info("Persisting item: " + item.id);
            invItemRepo.update(item);
            LOG.info("Persisted item: " + item.id);
        }
        LOG.info("Payload Item: ");
    }

    
}
