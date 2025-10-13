package org.example.Containers;

public class Product {
    protected static int currentID;
    protected int id;
    protected int price;
    protected String name;
    protected String category;

    public Product(int price, String name, String category) {

        this.id = currentID;
        this.price = price;
        this.name = name;
        this.category = category;
        currentID++;
    }


    public int getId() {
        return id;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
