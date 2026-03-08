package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CustomerDAOImpl cdao = new CustomerDAOImpl();
        OrderDAOImpl odao = new OrderDAOImpl();

        // Insert customer with order
        Customer c = new Customer(10,"A","ann12@gmail.com","F",45697785,
                LocalDate.of(2026,1,20));

        Order o = new Order(101,"540","Burger",5,599.0,
                LocalDate.of(2026,1,25));

        c.setOrder(o);
        o.setCustomer(c);

        cdao.insertCustomer(c);

        // Fetch customer by id
        Customer c1 = cdao.fetchCustomerById(10);
        System.out.println("Customer Name: "+c1.getCustomerName());

        // Fetch all customers
        List<Customer> list = cdao.fetchAllCustomers();

        for(Customer cus : list){
            System.out.println(cus.getCustomerName());
        }

        // Update customer
        cdao.updateCustomer(10,"Annasha");

        // Update order
        odao.updateOrder(101,700);

        // Fetch order
        Order ord = odao.fetchOrderById(101);
        System.out.println("Order Product: "+ord.getProductName());

        // Delete customer
        cdao.deleteCustomer(10);
    }
}