package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

//TODO: add type same Products
@MongoEntity(collection = "productOrder")
public class ProductOrder extends PanacheMongoEntity {
    
    @BsonProperty("product")
    private Product product;

    @BsonProperty("order")
    private Order order;

    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("priceAtOrder")
    private double priceAtOrder;

    public ProductOrder() {
    }

    public ProductOrder(Product product, Order order, int quantity, double priceAtOrder) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtOrder() {
        return priceAtOrder;
    }

    public void setPriceAtOrder(double priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }

    
}
