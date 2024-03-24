package ch.hftm.resource;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import ch.hftm.model.order.Order;
import ch.hftm.model.order.OrderCreateDTO;
import ch.hftm.model.productOrder.ProductOrder;
import ch.hftm.services.OrderService;
import jakarta.inject.Inject;

@GraphQLApi
public class OrderResource {
    
    @Inject
    OrderService orderService;
    
    private static final Logger LOG = Logger.getLogger(OrderResource.class);

    @Mutation
    @Description("Create a new order")
    public Order createOrder(OrderCreateDTO order) {
        LOG.info("Creating new order: " + order.getClass());
        try {
            Order newOrder = orderService.createOrder(order);
            return newOrder;
        } catch (Exception e) {
            LOG.error("Error creating order: " + order.getClass() + " | " + e.getMessage());
            return null;
        }
    }

    @Mutation
    @Description("Delete all Order")
    public boolean deleteAllOrders() {
        LOG.info("Deleting all Order");
        return orderService.deleteAllOrders();
    }

    @Mutation
    @Description("Delete Order by ID")
    public Order deleteOrderById(String id) {
        LOG.info("Deleting Order by ID: " + id);
        return orderService.deleteOrder(new ObjectId(id));
    }

    @Query
    @Description("Get all Products Order")
    public List<ProductOrder> allProductsOrderFromOrder() {
        LOG.info("Getting all Products Order");
        return orderService.getAllProductsOrder();
    }

    @Query
    @Description("Get all Orders")
    public List<Order> allOrders() {
        LOG.info("Getting all Orders");
        return orderService.getAllOrders();
    }

    @Query
    @Description("Get Order by ID")
    public Order orderById(String id) {
        LOG.info("Getting Order by ID: " + id);
        return orderService.findById(new ObjectId(id));
    }
}
