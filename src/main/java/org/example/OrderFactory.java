package org.example;

import org.example.Containers.Order;
import org.example.Containers.Product;
import org.example.Repositories.ProductRepository;

import java.util.*;

public class OrderFactory {

    TerminalIO reader = new TerminalIO(Arrays.asList("y","n"));

    public Order createNewOrderProcess(ProductRepository repository){
        Map<Product,Integer> productsInOrder = new HashMap<>();
        String customerName = reader.readTextInput("Whats your name?","Error reading input");
        boolean repeat = true;

        while(repeat){

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
            throw new ElementIsEmptyException("Empty elements should be discarded");
        }

        return new Order(customerName,productsInOrder);

    }

    public Order createOrderFromIDList(String customerName,List<Integer> IDlist,ProductRepository repository){
        Map<Product,Integer> listofProducts = new HashMap<>();

        IDlist.stream().forEach(id-> listofProducts.merge(repository.productByID(id),1,Integer::sum));
        return new Order(customerName,listofProducts);


    }



}
