package springdata.products.entities;

import org.springframework.data.annotation.Id;

/**
 * Product class
 * @author  Dominik Kesim
 * @date 28.04.19
 */
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;
    private int quantity;

    public Product() { }

    public Product(String id, String name, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        return String.format("Product[id = %s, name = '%s', price = '%f'" +
                        "quantity = '%d']",
                id, name, price, quantity);
    }
}
