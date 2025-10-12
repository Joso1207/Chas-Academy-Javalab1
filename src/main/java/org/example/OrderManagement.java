package org.example;

import org.example.Containers.Order;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

public class OrderManagement {

    private TerminalIO io;

    public OrderManagement(TerminalIO io){
        this.io = io;
    }


    public void launch(){


        List<Order> orders;
        Map<String,List<Order>> orderHistory;
        //ProductList.PopulateFromFile?;

        while(true){






            /*
            1, Place Order -> Send User to Collect products for their order
            3, Order a product
            4, Find products in category /Sort by price
            5, Customer Value (Total value of all orders)
            6, Most Popular Products. TOP3

             */



            /* OPTIONALS
             4 -> List {
                  SelectList
                }
            5, -> List Filter/Sorted {
                SelectList->SelectOperation
                }
             */


        }


    }





}
