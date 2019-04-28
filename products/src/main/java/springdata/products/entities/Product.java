package springdata.products.entities;

import org.springframework.data.annotation.Id;

/**
 * Product class
 * @author  Dominik Kesim
 * @date 28.04.19
 */
public class Product {

    @Id
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product() { }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}