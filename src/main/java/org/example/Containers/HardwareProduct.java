package org.example.Containers;

//Example of product inheritance,  Containing extra information.
public class HardwareProduct extends Product {

    boolean inStore;
    int timeToShip;


    public HardwareProduct(int price, String name, String category) {
        super(price, name, category);
        id = ++currentID;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
