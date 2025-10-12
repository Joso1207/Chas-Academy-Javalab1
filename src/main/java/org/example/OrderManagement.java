package org.example;

import org.example.Containers.Order;

import java.util.List;
import java.util.Map;

public class OrderManagement {

    private TerminalIO io;

    public OrderManagement(TerminalIO io){
        this.io = io;
    }

    public void placeOrder(){

    }

    public void listCategory(){

    }

    public void customerValue(){

    }
    public void top3(){

    }


    public void launch(){

        while(true){
            String selectedOption = io.getOption("1,Place Order\n" +
                    "2,find Products in Category\n" +
                    "3,Total Value of Customer\n" +
                    "4,Top3 Products\n" +
                    "5,quit",
                    "Invalid Option");

            switch (selectedOption){

                case "1"->placeOrder();
                case "2"->listCategory();
                case "3"->customerValue();
                case "4"->top3();
                case "5"->{
                    return;
                }

            }


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
