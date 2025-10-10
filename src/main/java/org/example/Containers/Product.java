package org.example.Containers;

public class Product {
    private int id;
    private int price;
    private String name,category;
    private static int currentID = 0;


    public int getId() {return id;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getCategory() {return category;}
    public void setCategory(String category){this.category=category;}



    public Product(int price, String name, String category) {
        this.price = price;
        this.name = name;
        this.category = category;

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
