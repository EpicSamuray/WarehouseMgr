package ch.hftm.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import ch.hftm.model.product.Product;
import ch.hftm.model.product.ProductCreateDTO;
import ch.hftm.model.product.ProductUpdatedDTO;
import ch.hftm.services.ProductService;

import java.util.List;

@GraphQLApi
public class ProductResource {

    @Inject
    ProductService itemService;

    private static final Logger LOG = Logger.getLogger(ProductResource.class);

    @Mutation
    @Description("Create a new product")
    public Product createProduct(ProductCreateDTO item) {
        LOG.info("Creating new item: " + item.getName());
        Product product = itemService.createProduct(item);
        if (product != null) {
            LOG.info("Sending item for validation: " + product.getId());
            return product;
        }
        return null;
    }

    @Mutation
    @Description("Update a product")
    public Product updateProduct(String id, ProductUpdatedDTO item) {
        LOG.info("Updating item: " + id);
        if (itemService.find(new ObjectId(id)) == null) {
            return null;
        }
        try {
            return itemService.updateProduct(new ObjectId(id),item);
        } catch (Exception e) {
            LOG.error("Error updating item: " + id + " | " + e.getMessage());
            return null;
        }
    }

    @Mutation
    @Description("Transfer a product")
    public Product transferProduct(String id, String locationId, int quantity) {
        LOG.info("Transferring item: " + id + " to location: " + locationId);
        if (id == null) {
            LOG.error("Error transferring item because id is required");
            throw new IllegalArgumentException("id is required");
        }
        if (locationId == null) {
            LOG.error("Error transferring item because locationId is required");
            throw new IllegalArgumentException("locationId is required");
        }
        return itemService.transferProduct(new ObjectId(id), new ObjectId(locationId));
    }

    @Mutation
    @Description("Delete a product with Boolean as Return Type")
    public Boolean deleteProduct(String id) {
        ObjectId objectId = new ObjectId(id);
        Product item = itemService.find(objectId);
        boolean deleted = itemService.delete(item);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    @Query
    @Description("Get all products")
    public List<Product> allProducts() {
        LOG.info("getAllProducts");
        return itemService.getAllProducts();
    }

    @Query
    @Description("Get a product by id")
    public Product productById(String id) {
        ObjectId objectId = new ObjectId(id);
        Product product = itemService.find(objectId);
        if (product != null) {
            return product;
        } else {
            return null;
        }
    }

    @Mutation
    @Description("Delete all products")
    public boolean deleteAll() {
        return itemService.delete(true);
        
    }

}
