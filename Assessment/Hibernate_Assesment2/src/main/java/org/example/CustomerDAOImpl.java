package org.example;

import jakarta.persistence.*;

import java.util.List;

public class CustomerDAOImpl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hiber5");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    // Insert customer with order
    public void insertCustomer(Customer c){

        et.begin();
        em.persist(c);
        et.commit();

        System.out.println("Customer inserted successfully");
    }

    // Update customer
    public void updateCustomer(int id,String name){

        Customer c = em.find(Customer.class,id);

        if(c!=null){
            et.begin();
            c.setCustomerName(name);
            em.merge(c);
            et.commit();
            System.out.println("Customer updated");
        }
    }

    // Delete customer
    public void deleteCustomer(int id){

        Customer c = em.find(Customer.class,id);

        if(c!=null){
            et.begin();
            em.remove(c);
            et.commit();
            System.out.println("Customer deleted");
        }
    }

    // Fetch customer by id
    public Customer fetchCustomerById(int id){

        Customer c = em.find(Customer.class,id);
        return c;
    }

    // Fetch all customers
    public List<Customer> fetchAllCustomers(){

        Query q = em.createQuery("select c from Customer c");
        List<Customer> list = q.getResultList();
        return list;
    }
}