package ch.hftm.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import ch.hftm.model.order.Order;
import ch.hftm.model.order.OrderCreateDTO;
import ch.hftm.model.product.Product;
import ch.hftm.model.product.util.StockMovement;
import ch.hftm.model.productOrder.ProductOrder;
import ch.hftm.model.productOrder.ProductOrderCreateDTO;
import ch.hftm.repository.OrderRepository;
import ch.hftm.repository.ProductOrderRepository;
import ch.hftm.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

enum Status {
    PENDING, APPROVED, REJECTED
}

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;
    
    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductOrderRepository productOrderRepository;

    private static final Logger LOG = Logger.getLogger(OrderService.class);


    public Order createOrder(OrderCreateDTO order) {

        if (order.getProductOrders().isEmpty()) {
            LOG.error("Error creating order because products are required");
            throw new IllegalArgumentException("Products are required");
        }

        LOG.info("Creating new order: " + order.getProductOrders() + " | Order Date: " + order.getOrderDate());

        Order newOrder = createDTOtoOrder(order);

        for (ProductOrderCreateDTO productOrder : order.getProductOrders()) {
            ProductOrder newProductOrder = createDTOtoProductOrder(productOrder, newOrder);
            newOrder.getProducts().add(newProductOrder);
            productOrderRepository.persist(newProductOrder);
        }

        for (ProductOrderCreateDTO productOrder : order.getProductOrders()) {
            ProductOrder newProductOrder = createDTOtoProductOrder(productOrder, newOrder);
            Product product = productRepository.findById(newProductOrder.getProductId());
            product.setTotalQuantity(product.getTotalQuantity() - productOrder.getQuantity());
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date = new Date(); 
            product.getStockMovements().add(new StockMovement(new ObjectId(), StockMovement.MovementType.OUT, productOrder.getQuantity(), formatter.format(date), product.getNewestLocationId()));
            productRepository.update(product);
        }
        
        orderRepository.persist(newOrder);
        
        if (orderRepository.isPersisted(newOrder.getId())){
            return orderRepository.findById(newOrder.getId());
        }

        return null;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll().list();
    }

    public Order findById(ObjectId id) {
        return orderRepository.findById(id);
    }

    public Order deleteOrder(ObjectId id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            orderRepository.delete(order);
            return order;
        }
        return null;
    }

    public boolean deleteAllOrders() {
        orderRepository.deleteAll();
        if (orderRepository.countAll() == 0) {
            LOG.info("All Orders deleted");
            return true;
        }

        return false;
    }

    public Order createDTOtoOrder(OrderCreateDTO order) {
        double totalPrice = 0;
        List<ProductOrder> productOrders = new ArrayList<>();
        for (ProductOrderCreateDTO productOrder : order.getProductOrders()){
            Product product = productRepository.findById(new ObjectId(productOrder.getProductId()));
            totalPrice += product.getPrice() * productOrder.getQuantity();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

            Order newOrder = new Order();
            newOrder.setId(new ObjectId());
            if (order.getOrderDate() == null) {
                newOrder.setOrderDate(formatter.format(date));
            } else {
                newOrder.setOrderDate(order.getOrderDate());
            }
            newOrder.setOrderDate(order.getOrderDate());
            newOrder.setStatus(Order.Status.PENDING);
            newOrder.setTotalPrice(totalPrice);
            newOrder.setProducts(productOrders);
        return newOrder;
    }

    public ProductOrder createDTOtoProductOrder(ProductOrderCreateDTO productOrder, Order order) {
        Product product = productRepository.findById(new ObjectId(productOrder.getProductId()));
        ProductOrder newProductOrder = new ProductOrder();
        newProductOrder.setId(new ObjectId());
        newProductOrder.setProductId(new ObjectId(productOrder.getProductId()));
        newProductOrder.setQuantity(productOrder.getQuantity());
        newProductOrder.setOrderId(order.getId());
        newProductOrder.setPriceAtOrder(product.getPrice() * productOrder.getQuantity());
        return newProductOrder;
    }

    public List<ProductOrder> getAllProductsOrder() {
        List<ProductOrder> productOrders = productOrderRepository.findAll().list();
        return productOrders;
    }
    
}
