package ch.hftm.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import ch.hftm.model.Product;
import ch.hftm.services.ProductService;
import io.vertx.core.cli.annotations.Description;

import java.util.List;

@GraphQLApi
public class ProductResource {

    @Inject
    ProductService itemService;

    private static final Logger LOG = Logger.getLogger(ProductResource.class);

    @Mutation
    @Description("Create a new product")
    public Product createProduct(@Valid Product item) {
        ObjectId id = new ObjectId();
        item.id = id;
        LOG.info("Creating new item: " + item.id);
        Product product = itemService.createProduct(item);
        if (product != null) {
            LOG.info("Sending item for validation: " + item.id);
            return product;
        }
        return null;
    }

    @Mutation
    @Description("Update a product")
    public Product putItem(String id, Product item) {
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

    //TODO: Alle Unter diesem Todo muss noch angepasst werden

    @Query
    public List<Product> getAllProducts() {
        LOG.info("getAllProducts");
        return itemService.getAllProducts();
    }

    @Query
    public Product getItem(String id) {
        ObjectId objectId = new ObjectId(id);
        Product product = itemService.find(objectId);
        if (product != null) {
            return product;
        } else {
            return null;
        }
    }

    @Mutation
    public Product deleteItem(String id) {
        ObjectId objectId = new ObjectId(id);
        Product item = itemService.find(objectId);
        boolean deleted = itemService.delete(item);
        if (deleted) {
            return item;
        } else {
            return null;
        }
    }

    @Mutation
    public boolean deleteAll() {
        return itemService.delete(true);
        
    }

}
