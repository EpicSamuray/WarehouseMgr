package ch.hftm.resource;

import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import ch.hftm.model.Product;
import ch.hftm.services.ProductService;

import java.util.List;

@GraphQLApi
public class ProductResource {

    @Inject
    ProductService itemService;

    private static final Logger LOG = Logger.getLogger(ProductResource.class);

    @Query
    public List<Product> getAllProducts() {
        return itemService.listAll();
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
    public Product createProduct(Product item) {
        LOG.info("Creating new item: " + item.id);
        itemService.persist(item);
        if (itemService.isPersistend(item.id)){
            itemService.sendItemForValidation(item.id);
            return item;
        }
        return null;
    }

    @Mutation
    public Product putItem(String id, Product item) {
        ObjectId objectId = new ObjectId(id);
        Product entity = itemService.find(objectId);
        if (entity == null) {
            return null;
        }

        entity.setName(item.getName());
        entity.setPrice(item.getPrice());
        entity.setQuantity(item.getQuantity());

        return entity;
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
