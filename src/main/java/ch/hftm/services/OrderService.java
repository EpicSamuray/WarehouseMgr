package ch.hftm.services;

import java.util.List;

import org.bson.types.ObjectId;

import ch.hftm.model.Order;
import ch.hftm.model.Product;
import ch.hftm.model.ProductOrder;
import ch.hftm.repository.OrderRepository;
import ch.hftm.repository.ProductOrderRepository;
import ch.hftm.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;
    
    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductOrderRepository productOrderRepository;

    public Order createOrder(Order order) {

        if (order.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Products are required");
        }

        for (ProductOrder productOrder : order.getProducts()) {
            productOrderRepository.persist(productOrder);
        }

        for (ProductOrder productOrder : order.getProducts()) {
            Product product = productRepository.findById(productOrder.getProduct().id);
            product.setQuantity(product.getQuantity() - productOrder.getQuantity());
            productRepository.update(product);
        }
        
        orderRepository.persist(order);
        
        if (orderRepository.isPersisted(order.id)){
            return orderRepository.findById(order.id);
        }

        return null;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll().list();
    }

    public Order findById(ObjectId id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Order order) {
        
        if (order.id == null) {
            throw new IllegalArgumentException("id is required");
        }

        for (ProductOrder productOrder : order.getProducts()) {
            productOrderRepository.update(productOrder);
        }

        for (ProductOrder productOrder : order.getProducts()) {
            Product product = productRepository.findById(productOrder.getProduct().id);
            product.setQuantity(product.getQuantity() - productOrder.getQuantity());
            productRepository.update(product);
        }

        orderRepository.update(order);
        return orderRepository.findById(order.id);
    }

    public void deleteOrder(ObjectId id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            orderRepository.delete(order);
        }
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
    
}
