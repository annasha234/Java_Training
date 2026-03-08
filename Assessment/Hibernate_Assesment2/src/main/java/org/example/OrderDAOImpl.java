package org.example;

import jakarta.persistence.*;

public class OrderDAOImpl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hiber5");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    // Update order
    public void updateOrder(int id,double price){

        Order o = em.find(Order.class,id);

        if(o!=null){
            et.begin();
            o.setPrice(price);
            em.merge(o);
            et.commit();
            System.out.println("Order updated");
        }
    }

    // Fetch order by id
    public Order fetchOrderById(int id){

        Order o = em.find(Order.class,id);
        return o;
    }
}