package org.example.Containers;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    int id;
    static int currentID;
    String customerName;

    //Hashmap to store Product Identifier + amount of this product has been ordered,
    //We use hashmap due to it the methods Get(),Contains(),Next() all having O(1) complexity.
    //This makes it suitable for looking through large lists of products or for orders containing a specific product.
    Map<Product,Integer> productsInOrder = new LinkedHashMap<>();


    public Order(){
        id = currentID;
        currentID++;
    }

    public Order(String customerName,Map<Product,Integer>products){
        id = currentID;
        currentID++;

        setCustomerName(customerName);
        setProductsInOrder(products);
    }

    public void addToOrder(Product product){
        productsInOrder.merge(product,1,Integer::sum);
    }




    public Map<Product, Integer> getProductsInOrder() {return productsInOrder;}
    public void setProductsInOrder(Map<Product, Integer> productsInOrder) {this.productsInOrder = productsInOrder;}
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public int getId() {return id;}

    public int totalCost(){

        return productsInOrder.entrySet().stream()
                .mapToInt((e)->
                        e.getKey().getPrice()*e.getValue()).sum();
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", productsInOrder=" + productsInOrder +
                '}';
    }




}
