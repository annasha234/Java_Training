package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hiber5");
        EntityManager em=emf.createEntityManager();
        EntityTransaction et= em.getTransaction();
        et.begin();
        Customer c=new Customer(10,"A","ann12@gmail.com","F",45697785, LocalDate.of(2026,1,20));
        Order o=new Order(101,"540","Burger",5,599.0,LocalDate.of(2026,1,25));

        c.setOrder(o);
        o.setCustomer(c);

        em.persist(c);
        et.commit();

        em.close();
        emf.close();
    }
}