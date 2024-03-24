package ch.hftm.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import ch.hftm.model.location.Location;
import ch.hftm.model.product.Product;
import ch.hftm.model.product.ProductCreateDTO;
import ch.hftm.model.product.ProductUpdatedDTO;
import ch.hftm.model.product.util.StockMovement;
import ch.hftm.repository.LocationRepository;
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

    @Inject
    LocationRepository locationRepo;

    private static final Logger LOG = Logger.getLogger(ProductService.class);

    public Product createProduct(ProductCreateDTO product) {
        LOG.info("Creating new item: " + product.getName());
        if (product.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        Product item = createDTOtoProduct(product);

        LOG.info("Location exists: " + product.getNewestLocationId());
        if (!locationRepo.isPersisted(new ObjectId(product.getNewestLocationId()))) {
            throw new IllegalArgumentException("Location does not exist");
        }

        item.setNewestLocationId(new ObjectId(product.getNewestLocationId()));
        
        if (productRepo.isPersisted(item.getId())) {
            throw new IllegalArgumentException("Item already exists");
        } 
        productRepo.persist(item);
        if (productRepo.isPersisted(item.getId())){
            LOG.info("Sending item for validation: " + item.getId());
            return productRepo.findById(item.getId());
        } 
        return null;
    }

    public Product transferProduct(ObjectId id, ObjectId locationId) {
        LOG.info("Transferring item: " + id + " to location: " + locationId);
        if (id == null) {
            LOG.error("Error transferring item because id is required");
            throw new IllegalArgumentException("id is required");
        }

        if (!productRepo.isPersisted(id)) {
            LOG.error("Error transferring item because item does not exist");
            throw new IllegalArgumentException("Item does not exist");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(); 

        Product item = productRepo.findById(id);
        Location oldLocation = locationRepo.findById(item.getNewestLocationId());
        Location newLocation = locationRepo.findById(locationId);
        int quantity = item.getTotalQuantity();
        oldLocation.setCapacity(oldLocation.getCurrentCapacity() - quantity);
        newLocation.setCapacity(newLocation.getCurrentCapacity() + quantity);


        item.setNewestLocationId(locationId);
        item.getStockMovements().add(new StockMovement(new ObjectId(), StockMovement.MovementType.TRANSFER, quantity, formatter.format(date), locationId));
        productRepo.update(item);
        locationRepo.update(oldLocation);
        locationRepo.update(newLocation);
        return productRepo.findById(id);
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

    public Product updateProduct(ObjectId id, ProductUpdatedDTO product) {
        LOG.info("Updating item: " + id);
        if (id == null) {
            LOG.error("Error updating item because id is required");
            throw new IllegalArgumentException("id is required");
        }

        if (!productRepo.isPersisted(id)) {
            LOG.error("Error updating item because item does not exist");
            throw new IllegalArgumentException("Item does not exist");
        }        

        Product item = productRepo.findById(id);
        if (product.getName() != null) item.setName(product.getName());
        if (product.getDescription() != null) item.setDescription(product.getDescription());
        if (product.getPrice() != 0) item.setPrice(product.getPrice());
        if (product.getCategory() != null) item.setCategory(product.getCategory());

        try {
            productRepo.update(item);
            return productRepo.findById(id);
        } catch (Exception e) {
            LOG.error("Error updating item: " + id + " | " + e.getMessage());
            return null;
        }

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
        return productRepo.deleteById(item.getId());
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

    public Product createDTOtoProduct(ProductCreateDTO product) {
        Product item = new Product();
        StockMovement stockMovement = new StockMovement();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        item.setId(new ObjectId());
        stockMovement.setId(new ObjectId());

        stockMovement.setDate(formatter.format(date));
        stockMovement.setType(StockMovement.MovementType.IN);
        stockMovement.setQuantity(product.getStockMovements().getQuantity());
        stockMovement.setLocationId(new ObjectId(product.getNewestLocationId()));

        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setCategory(product.getCategory());
        item.setTotalQuantity(product.getStockMovements().getQuantity());
        item.setStockMovements(List.of(stockMovement));
        return item;
    }
    //TODO: Refactor this method to use the ItemReq record
    public void sendItemForValidation(ObjectId itemid) {
        LOG.info("Sending item for validation: " + itemid);
        Product item = productRepo.findById(itemid);
        LOG.info("Payload Item: " + item.getId());
        if (item != null) {
            LOG.info("Sended item for validation: " + item.getId());
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
            LOG.info("Persisting item: " + item.getId());
            productRepo.update(item);
            LOG.info("Persisted item: " + item.getId());
        }
        LOG.info("Payload Item: ");
    }

    
}
