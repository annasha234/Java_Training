package org;

import jakarta.persistence.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskPU");
        EntityManager em = Persistence.createEntityManager();

        em.getTransaction().begin();


//        User u = new User();
//        u.setName("Annu");
//        u.setId(10);
//        u.setDescription("User 1");
//        em.persist(u);

        AutoEntity a = new AutoEntity();
        a.setName("Shreya");
        a.setId(20);
        a.setDescription("User 2");
        em.persist(a);

        SequenceEntity s = new SequenceEntity();
        s.setName("Asha");
        s.setId(30);
        s.setDescription("User 3");
        em.persist(s);

        em.getTransaction().commit();

        System.out.println("User ID: " + u.getId());
        System.out.println("Auto ID: " + a.getId());
        System.out.println("Sequence ID: " + s.getId());

        em.close();
        emf.close();
    }
}