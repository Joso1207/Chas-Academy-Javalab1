package org.example;

import org.example.Containers.Order;
import org.example.Repositories.OrderRepository;
import org.example.Repositories.ProductRepository;

import java.sql.Array;
import java.util.*;

public class OrderManagement {

    private TerminalIO io;
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private OrderFactory orderFactory = new OrderFactory();

    private void populateOrderRepository(){

        List<Order> orders = new ArrayList<>();

        Map<String,List<List<Integer>>> customerOrders = new HashMap<>();
        customerOrders.put("Nora",new ArrayList<>(Arrays.asList(
                Arrays.asList(1,2,3,5,5,6,6,1),
                Arrays.asList(1,6,3,2,5,6,6,6),
                Arrays.asList(8,2,3,2,5,2,1,1)
        )));

        customerOrders.put("Vera",new ArrayList<>(Arrays.asList(
                Arrays.asList(5,2,3,5,5,3,4,1),
                Arrays.asList(1,0,3,2,5,6,6,6)
        )));

        customerOrders.put("Dora",new ArrayList<>(Arrays.asList(
                Arrays.asList(1,2,3,5,5,6,6,1)

        )));

        customerOrders.forEach((key, value) -> value.forEach(list ->
                orders.add(orderFactory.createOrderFromIDList(key, list, productRepository))));

        orders.forEach(orderRepository::add);
    }


    public OrderManagement(TerminalIO io){
        this.io = io;

        populateOrderRepository();
    }

    public void placeOrder(){

        orderRepository.add(
                orderFactory.createNewOrderProcess(productRepository)
        );
    }

    public void listCategory(){

        Set<String>categories =productRepository.getAllCategories();
        TerminalIO categoryIO = new TerminalIO(categories.stream().toList());

        productRepository.filteredListByCategory(
                categoryIO.validMenuOption("Which Category?\n" +
                        categories,"error"))
                .forEach(System.out::println);
    }

    public void customerValue(){

        Set<String>customers = orderRepository.getOrdersByCustomer().keySet();
        TerminalIO customerIO = new TerminalIO(customers.stream().toList());


        System.out.println(orderRepository.valueOfCustomer(
                customerIO.validMenuOption("which customer" + customers,"Error")));


    }
    public void top3(){

        orderRepository.top3Products().forEach(System.out::println);

    }


    public void launch(){

        while(true){
            String selectedOption = io.validMenuOption("1,Place Order\n" +
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
