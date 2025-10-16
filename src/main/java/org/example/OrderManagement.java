package org.example;

import org.example.Containers.Order;
import org.example.Repositories.OrderRepository;
import org.example.Repositories.ProductRepository;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderManagement {

    private final static Logger log = LoggerFactory.getLogger(OrderManagement.class);


    private final TerminalIO io;
    private final ProductRepository productRepository = new ProductRepository();
    private final OrderRepository orderRepository = new OrderRepository();
    private final OrderFactory orderFactory = new OrderFactory();

    private void populateOrderRepository(){

        log.info("Populating repositories");

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

        customerOrders.put("Dora",new ArrayList<>(List.of(
                Arrays.asList(1, 2, 3, 5, 5, 6, 6, 1)

        )));

        customerOrders.forEach((key, value) -> value.forEach(list ->
                orders.add(orderFactory.createOrderFromIDList(key, list, productRepository))));

        orders.forEach(orderRepository::add);

        log.info("repositories populated");
    }

    //Instantiate the class with a TerminalIO to serve as our input/ouput menu
    public OrderManagement(TerminalIO io){
        this.io = io;
        log.info("Program Started: using{}", io.toString());
        populateOrderRepository();
    }

    public void placeOrder(){
        log.info("Starting orderCreation process");
        try {
            orderRepository.add(
                    orderFactory.createNewOrderProcess(productRepository)
            );
        } catch (ElementIsEmptyException _) {

        }


    }

    public void listCategory(){
        Set<String>categories =productRepository.getAllCategories();
        try{
            TerminalIO categoryIO = new TerminalIO(categories.stream().toList());

            productRepository.filteredListByCategory(
                            categoryIO.validMenuOption("Which Category?\n" +
                                    categories,"error"))
                    .forEach(System.out::println);

        } catch (Exception e) {
            log.error(e.getMessage(), productRepository);
        }




    }

    public void customerValue(){


        try{
            Set<String>customers = orderRepository.getOrdersByCustomer().keySet();
            TerminalIO customerIO = new TerminalIO(customers.stream().toList());
            System.out.println(orderRepository.valueOfCustomer(
                    customerIO.validMenuOption("which customer" + customers,"Error")));
        } catch (Exception e) {
            log.error(e.getMessage(),orderRepository);
        }



    }
    public void top3(){

        orderRepository.top3Products().forEach(System.out::println);

    }


    public void launch(){

        while(true){
            String selectedOption = io.validMenuOption("""
                            1,Place Order
                            2,find Products in Category
                            3,Total Value of Customer
                            4,Top3 Products
                            5,quit""",
                    "Invalid Option");

            switch (selectedOption){

                case "1"->placeOrder();
                case "2"->listCategory();
                case "3"->customerValue();
                case "4"->top3();
                case "5"->{
                    log.info("Shutting down program");
                    return;
                }

            }

        }


    }





}
