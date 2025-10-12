package org.example.Repositories;

import org.example.Containers.Order;
import org.example.Containers.Product;


import java.util.*;
import java.util.stream.Collectors;

public class OrderRepository {
    List<Order> orders;
    Map<String,List<Order>> ordersByCustomer;

    public OrderRepository(){
        orders = new ArrayList<>();
        ordersByCustomer = new HashMap<>();
    }

    public void add(String customerName,Order newOrder){
        orders.add(newOrder);

        if(ordersByCustomer.containsKey(customerName)){
            ordersByCustomer.get(customerName).add(newOrder);
        } else {
            ordersByCustomer.put(customerName,new ArrayList<>(Arrays.asList(newOrder)));
        }

    }

    public void remove(int orderID){
        Order orderToRemove;

        try{
            orderToRemove = orders.stream().filter(o->o.getId()==orderID).toList().getFirst();
        } catch (NoSuchElementException e){
            System.err.println("ID matches no order");
            return;
        }

        orders.remove(orderToRemove);
        ordersByCustomer.get(orderToRemove.getCustomerName()).remove(orderToRemove);


    }

    public int valueOfCustomer(String name){

        try{
            if(ordersByCustomer.containsKey(name)){
               return ordersByCustomer.get(name).stream().mapToInt(Order::totalCost).sum();
            } else{
                throw new  NoSuchElementException();
            }

        } catch (NoSuchElementException e){
            System.err.println("No such Customer");
            return 0;
        }


    }

    public List<Product> top3Products(){
        Map<Product,Integer> timesPurchased = new HashMap<>();

        orders.forEach(o->
            o.getProductsInOrder().forEach((key,value)->
                    timesPurchased.merge(key,value, Integer::sum)));

        return timesPurchased.keySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(3).collect(Collectors.toList());
    }





}
