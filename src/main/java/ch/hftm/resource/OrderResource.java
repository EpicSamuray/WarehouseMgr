package ch.hftm.resource;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.jboss.logging.Logger;

import ch.hftm.model.Order;
import ch.hftm.services.OrderService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@GraphQLApi
public class OrderResource {
    
    @Inject
    OrderService orderService;
    
    private static final Logger LOG = Logger.getLogger(OrderResource.class);

    @Mutation
    @Description("Create a new order")
    public Order createOrder(@Valid Order order) {
        ObjectId id = new ObjectId();
        LOG.info("Creating new order: " + id);
        order.id = id;
        LOG.info("Creating new order: " + order.id);
        try {
            Order newOrder = orderService.createOrder(order);
            return newOrder;
        } catch (Exception e) {
            LOG.error("Error creating order: " + id + " | " + e.getMessage());
            return null;
        }

    }
}
