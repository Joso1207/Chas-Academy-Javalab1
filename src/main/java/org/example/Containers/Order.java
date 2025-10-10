package org.example.Containers;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    int id;
    String customerName;

    //Hashmap to store Product Identifier + amount of this product has been ordered,
    //We use hashmap due to it the methods Get(),Contains(),Next() all having O(1) complexity.
    //This makes it suitable for looking through large lists of products or for orders containing a specific product.
    Map<Product,Integer> productsInOrder = new LinkedHashMap<>();


    public Map<Product, Integer> getProductsInOrder() {return productsInOrder;}
    public void setProductsInOrder(Map<Product, Integer> productsInOrder) {this.productsInOrder = productsInOrder;}
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public int getId() {return id;}


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", productsInOrder=" + productsInOrder +
                '}';
    }




}
