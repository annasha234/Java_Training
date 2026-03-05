package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//        System.out.println("Starting JPA...");
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("taskPU");
//        System.out.println("EntityManagerFactory created successfully!");
//        emf.close();
//        System.out.println("Closed successfully!");

        TaskDAO taskDAO=new TaskDAO();
        Task task1=new Task();
        task1.setTask("Learn Hibernate");
        task1.setDescription("Complete hibernate tutorial");
        taskDAO.create(task1);

        Task task2=new Task();
        task2.setTask("Build REST API");
        task2.setDescription("Create REST API with spring boot");
        taskDAO.create(task2);

        Task task3=new Task();
        task3.setTask("Database Optimization");
        task3.setDescription("Optimize database queries");
        taskDAO.create(task3);

        System.out.println("\n--- Read: Getting a single task ID----");
        TaskDTO readTask=taskDAO.read(1L);
        if(readTask != null){
            System.out.println("Retrieve Task: "+readTask);
        }

        System.out.println("\n--- LIST: Getting all tasks---");
        List<TaskDTO> allTasks=taskDAO.getAllTask();
        System.out.println("All tasks in database");
        for(TaskDTO dto:allTasks){
            System.out.println(dto);
        }

        System.out.println("\n-----UPDATE: updating the tasks in the database");
        TaskDTO updateTask=taskDAO.update(1L,"Learn Advanced Hibernate","Complete Advanced mapping");
        System.out.println("updated task: "+updateTask);

        System.out.println("\n-----DETELE: deleting the tasks from the database");
        boolean deleted=taskDAO.delete(2L);
        System.out.println("Deleted: "+deleted);

        taskDAO.close();

    }
}
