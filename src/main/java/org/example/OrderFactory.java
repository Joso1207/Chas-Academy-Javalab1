package org.example;

import org.example.Containers.Order;
import org.example.Containers.Product;
import org.example.Repositories.ProductRepository;
import org.slf4j.LoggerFactory;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Simple order creation factory, its sole purpose is to create new and assemble filled orders.
public class OrderFactory {

    private static final Logger log = LoggerFactory.getLogger(OrderFactory.class);
    TerminalIO reader = new TerminalIO(Arrays.asList("y","n"));

    public Order createNewOrderProcess(ProductRepository repository) throws ElementIsEmptyException{
        Map<Product,Integer> productsInOrder = new HashMap<>();
        String customerName = reader.readTextInput("Whats your name?","Error reading input");
        boolean repeat = true;

        //Example of how to use our own exception, In this case ElementIsEmpty which is thrown when the program
        //expects resources and data from another container or class but receives none.
        if(repository.getProductList().isEmpty()){
            log.error("No products to order");
            throw new ElementIsEmptyException("Repository is empty, no orders can be made");
        }

        while(repeat){

            repository.getProductList().forEach(System.out::println);
            int productID = reader.readNumberInput("Type the ID of the product to add, Blank entry finalizes"," Error Reading Input ");
            Product productToAdd = repository.productByID(productID);

            if (productToAdd!=null) {
                productsInOrder.merge(productToAdd, 1, Integer::sum);
            }

            if(reader.validMenuOption("Would you like to add another product? y/n","Please answer y or n").equals("n")){
                repeat = false;
            }

        }

        if(productsInOrder.isEmpty()){
            throw new ElementIsEmptyException("Discarding Empty order");
        } else {
            System.out.println("New order has been created with these products:");
            productsInOrder.entrySet().stream().forEach(set->{
                System.out.println( set.getKey().getName()+" Amount:"+set.getValue() +" for "+set.getValue()*set.getKey().getPrice());
            });
            System.out.println("Total cost: "+productsInOrder.entrySet().stream().mapToInt(p->p.getValue()*p.getKey().getPrice()).sum());
        }

        return new Order(customerName,productsInOrder);

    }

    public Order createOrderFromIDList(String customerName,List<Integer> IDlist,ProductRepository repository){
        Map<Product,Integer> listofProducts = new HashMap<>();

        IDlist.stream().forEach(id-> listofProducts.merge(repository.productByID(id),1,Integer::sum));
        return new Order(customerName,listofProducts);


    }



}
