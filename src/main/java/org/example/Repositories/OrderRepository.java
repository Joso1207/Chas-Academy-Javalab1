package org.example.Repositories;

import org.example.Containers.Order;
import org.example.Containers.Product;



import java.util.*;
import java.util.stream.Collectors;

public class OrderRepository {
    List<Order> orders;
    Map<String,List<Order>> ordersByCustomer;


    public Map<String, List<Order>> getOrdersByCustomer() {
        return ordersByCustomer;
    }



    public OrderRepository(){
        orders = new ArrayList<>();
        ordersByCustomer = new HashMap<>();
    }

    public void add(Order newOrder){
        orders.add(newOrder);

        if(ordersByCustomer.containsKey(newOrder.getCustomerName())){
            ordersByCustomer.get(newOrder.getCustomerName()).add(newOrder);
        } else {
            ordersByCustomer.put(newOrder.getCustomerName(),new ArrayList<>(List.of(newOrder)));
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

        return timesPurchased.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
    }








}
